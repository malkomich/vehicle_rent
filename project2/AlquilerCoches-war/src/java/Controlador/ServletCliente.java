package Controlador;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import DespliegueReserva.IReservas;
import DespliegueUsuario.IUsuarios;
import DespliegueVehiculo.IVehiculos;
import Dominio.Licencia;
import Dominio.Usuario;
import Dominio.Vehiculo;
import MyExceptions.MiTiendaException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "Controlador_Cliente", urlPatterns = {"/reserva", "/confirmar"})
public class ServletCliente extends HttpServlet {
        
    @EJB
    private IUsuarios gestorUsuarios;
    @EJB
    private IReservas gestorReservas;
    @EJB
    private IVehiculos gestorVehiculos;
    
    private String url;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        String userPath = request.getServletPath();
        try {
            HttpSession sesion = request.getSession();
            String accion = request.getParameter("accion");
            if (accion == null) { // No se viene de formulario
                this.url = (String) sesion.getAttribute("siguienteURL");
            } else { // Caso navegacion normal
                switch (accion) {
                    case "Reservar": 
                        int v_index = Integer.valueOf(request.getParameter("v_index"));
                        Vehiculo vehiculoPedido = ((List<Vehiculo>) sesion.getAttribute("catalogo")).get(v_index);
                        //Comprobamos si el usuario posee el carnet requerido
                        Collection<Licencia> carnets = (Collection<Licencia>) sesion.getAttribute("user_carnets");
                        Collection<Dominio.Tipocarnet> tipoCarnetVehiculo = vehiculoPedido.getIdmodelo().getTipocarnetCollection();
                        boolean valido = false;
                        for(Licencia l: carnets){
                           for(Dominio.Tipocarnet t: tipoCarnetVehiculo){
                               if(l.getTipocarnet().getTipo().equals(t.getTipo()))
                                   valido = true;
                           }
                        }
                        if (!valido) {
                            throw new MiTiendaException("No posee el carnet requerido para conducir este vehículo");
                        }
                        //Añadir el el vehiculo pedido y su categoría a variables de sesion
                        sesion.setAttribute("v_pedido", vehiculoPedido);

                        this.url = "/WEB-INF/view/Invoice.jsp";
                        sesion.setAttribute("siguienteURL", this.url);
                        break;
                    case "Catalogo":
                        this.url = "/catalogo";
                        sesion.setAttribute("siguienteURL", this.url);
                        break;
                    case "Confirmar":
                        getServletContext().log("CREAR RESERVA");
                        
                        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat sdfOutput = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaIni = sdfInput.parse(request.getParameter("fechaIni"));
                        Date fechaFin = sdfInput.parse(request.getParameter("fechaFin"));
                        String inicioString;
                        String finString;
                        if(fechaIni != null)
                            inicioString = "Desde el día " + sdfOutput.format(fechaIni);
                        else
                            inicioString = "Sin día de inicio ";
                        if(fechaFin != null)
                            finString = "y hasta el día " + sdfOutput.format(fechaFin);
                        else
                            finString = "y sin fecha de fin.";
                        request.setAttribute("fechaIni", inicioString);
                        request.setAttribute("fechaFin", finString);
                        Usuario usuario = (Usuario)sesion.getAttribute("usuario");
                        Vehiculo vehiculo = (Vehiculo)sesion.getAttribute("v_pedido");
                        
                        gestorReservas.crearReserva(fechaIni, fechaFin, usuario.getNif(), vehiculo.getMatricula());

                        this.url = "/WEB-INF/view/EndPurchase.jsp";
                        sesion.setAttribute("siguienteURL", this.url);
                        break;
                }
            }
        } catch (MiTiendaException ex) {
            request.setAttribute("tipo", ex);
            this.url = "/WEB-INF/view/error.jsp?error=MiException";
        } catch (ParseException ex) {
            request.setAttribute("tipo", ex);
            this.url = "/WEB-INF/view/error.jsp?error=MiException";
        } finally {
            RequestDispatcher respuesta = getServletContext().getRequestDispatcher(this.url);
            respuesta.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
