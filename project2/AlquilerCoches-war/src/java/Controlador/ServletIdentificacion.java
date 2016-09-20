package Controlador;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import DespliegueUsuario.IUsuarios;
import DespliegueVehiculo.IVehiculos;
import Dominio.Cliente;
import Dominio.Licencia;
import Dominio.Vehiculo;
import MyExceptions.MiTiendaException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "Controlador_Identificacion", urlPatterns = {"/login", "/catalogo", "/logout"})
public class ServletIdentificacion extends HttpServlet {
    
    @EJB
    private IUsuarios gestorUsuarios;
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

        String userPath = request.getServletPath();
        HttpSession sesion = request.getSession();
        try {
            if(userPath.equals("/login")){
                if (sesion.getAttribute("usuario") == null) { // comienzo sesion
                    String nif = request.getParameter("usuario");
                    if(nif != null){
                        getServletContext().log("LOGIN");
                        String passwd = request.getParameter("clave");
                        
                        // Control de acceso del usuario
                        Cliente cliente = gestorUsuarios.getClient(nif);
                        if (cliente == null) {
                            throw new MiTiendaException("Cliente No Registrado");
                        }
                        if (!cliente.getUsuario().getPassword().equals(passwd)) {
                            throw new MiTiendaException("Clave incorrecta");
                        }
                        //Guardamos en variable de sesion el conjunto de carnets del usuario
                        Collection<Licencia> user_carnets = cliente.getLicenciaCollection();
                        sesion.setAttribute("user_carnets", user_carnets);

                        Cookie cookie = new Cookie("MiTienda_user", nif);
                        response.addCookie(cookie);
                        //Crear variable de sesion del usuario y de la siguiente página JSP a mostrar.
                        sesion.setAttribute("usuario", cliente.getUsuario());

                        this.url = "/catalogo";
                        sesion.setAttribute("siguienteURL", url);
                    } else{
                        this.url = "/index.jsp";
                        sesion.setAttribute("siguienteURL", url);
                    }
                }else{
                    this.url = "/catalogo";
                    sesion.setAttribute("siguienteURL", url);
                }
            } else if(userPath.equals("/logout")){
                getServletContext().log("LOGOUT");
                sesion.invalidate();
            
                this.url= "/index.jsp";
                sesion.setAttribute("siguienteURL", url);
            } else if(userPath.equals("/catalogo")){
                if (sesion.getAttribute("usuario") == null) {
                    this.url = "/login";
                    sesion.setAttribute("siguienteURL", url);
                } else{
                    getServletContext().log("CATÁLOGO");
                    // Leemos catalogo y lo guardamos en sesion
                    // Como no va a cambiar durante la sesión y tiene que estar 
                    // disponible durante toda ella lo guardamos ahí. Así solo accedemos
                    // a la base de datos una vez por sesión
                    if(sesion.getAttribute("catalogo") == null){
                        List<Vehiculo> catalogo = gestorVehiculos.getCatalogo();
                        sesion.setAttribute("catalogo", catalogo);
                    }
                    
                    this.url = "/WEB-INF/view/Catalog.jsp";
                    sesion.setAttribute("siguienteURL", url);
                }
            }
        } catch (MiTiendaException ex) {
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
