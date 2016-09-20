/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import DespliegueEntrega.IEntregas;
import DespliegueReserva.IReservas;
import DespliegueUsuario.IUsuarios;
import DespliegueVehiculo.IVehiculos;
import DominioReserva.Alquiler;
import Dominio.Cliente;
import DominioReserva.Reserva;
import Dominio.Vehiculo;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
 * @author malkomich
 */
@WebServlet(name = "Controlador_Empleado", urlPatterns = {"/admin", "/recogida", "/devolucion"})
public class ServletAdmin extends HttpServlet {

    @EJB
    private IUsuarios gestorUsuarios;
    @EJB
    private IReservas gestorReservas;
    @EJB
    private IVehiculos gestorVehiculos;
    @EJB
    private IEntregas gestorEntregas;
    
    private String url;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        String userPath = request.getServletPath();
        String accion = request.getParameter("accion");
        String dataOutput;
        float km;
        if(accion != null){
            switch(accion){
                case "Buscar Reservas":
                    getServletContext().log("BUSCAR RESERVAS");
                    String nif = request.getParameter("cliente");
                    Cliente cliente = gestorUsuarios.getClient(nif);
                    if (cliente == null) {
                        dataOutput = "[]";
                    } else{
                        dataOutput = "{\"reservas\":[";
                        List<Reserva> reservas = gestorReservas.mostrarReservasPendientes(nif);
                        sesion.setAttribute("reservasList", reservas);
                        SimpleDateFormat sdfOutput = new SimpleDateFormat("dd/MM/yyyy");
                        for(int i=0; i<reservas.size();i++){
                            dataOutput += "{";
                            
                            Vehiculo vehiculoReserva = gestorVehiculos.getVehiculo(reservas.get(i).getMatricula());
                            dataOutput += "\"idReserva\":" + reservas.get(i).getIdreserva()+ ",";
                            dataOutput += "\"fechaFin\":\"" + sdfOutput.format(reservas.get(i).getFechafinalquiler()) + "\",";
                            dataOutput += "\"matricula\":\"" + vehiculoReserva.getMatricula() + "\",";
                            dataOutput += "\"modelo\":\"" + vehiculoReserva.getIdmodelo().getNombre() + "\",";
                            dataOutput += "\"marca\":\"" + vehiculoReserva.getIdmodelo().getFabricante() + "\"";

                            if(i != reservas.size()-1){
                                dataOutput += "},";
                            } else{
                                dataOutput += "}";
                            }
                        }
                        dataOutput += "]}";
                    }
                    
                    response.setContentType("application/json");  
                    response.setCharacterEncoding("UTF-8"); 
                    response.getWriter().write(dataOutput);
                    break;
                case "Crear Alquiler":
                    getServletContext().log("CREAR ALQUILER");
                    List<Reserva> reservas = (List<Reserva>)sesion.getAttribute("reservasList");
                    int idReserva = Integer.valueOf(request.getParameter("idReserva"));
                    km = Float.valueOf(request.getParameter("kilometraje"));
                    gestorReservas.crearAlquiler(reservas.get(idReserva), km);
                    break;
                case "Mostrar":
                    getServletContext().log("MOSTRAR DATOS DEVOLUCION");
                    String matricula = request.getParameter("vehiculo");
                    Vehiculo vehiculo = gestorVehiculos.getVehiculo(matricula);
                    if (vehiculo == null) {
                        dataOutput = "[]";
                    } else{
                        dataOutput = "{\"alquiler\":[";
                        Alquiler alquilerDevolucion = gestorReservas.getAlquilerByVehicle(matricula);
                        sesion.setAttribute("alquilerDevolucion", alquilerDevolucion);
                        SimpleDateFormat sdfOutput = new SimpleDateFormat("dd/MM/yyyy");
                        if(alquilerDevolucion != null){
                            dataOutput += "{";
                            
                            Cliente clienteAlquiler = gestorUsuarios.getClient(alquilerDevolucion.getCliente());
                            dataOutput += "\"idAlquiler\":" + alquilerDevolucion.getIdalquiler() + ",";
                            dataOutput += "\"nombreCliente\":\"" + clienteAlquiler.getUsuario().getNombre() + "\",";
                            dataOutput += "\"nifCliente\":\"" + clienteAlquiler.getUsuario().getNif() + "\",";
                            dataOutput += "\"fechaInicio\":\"" + sdfOutput.format(alquilerDevolucion.getFechainicio()) + "\",";
                            dataOutput += "\"fechaFin\":\"" + sdfOutput.format(alquilerDevolucion.getFechafin()) + "\",";
                            dataOutput += "\"matricula\":\"" + alquilerDevolucion.getMatricula() + "\",";
                            dataOutput += "\"kilometrajeSalida\":" + alquilerDevolucion.getKilometrajesalida();
                            
                            dataOutput += "}";
                        }
                        dataOutput += "]}";
                    }
                    
                    response.setContentType("application/json");  
                    response.setCharacterEncoding("UTF-8"); 
                    response.getWriter().write(dataOutput);
                    break;
                case "Devolucion":
                    getServletContext().log("REGISTRAR DEVOLUCION");
                    Alquiler alquilerDevolucion = (Alquiler) sesion.getAttribute("alquilerDevolucion");
                    km = Float.valueOf(request.getParameter("kilometraje"));
                    gestorEntregas.registrarEntrega(alquilerDevolucion);
                    break;
            }
        }else{
            switch(userPath){
                case "/admin":

                    this.url = "/WEB-INF/view/admin.jsp";
                    sesion.setAttribute("siguienteURL", this.url);
                    break;
                case "/recogida":

                    this.url = "/WEB-INF/view/recogida.jsp";
                    sesion.setAttribute("siguienteURL", this.url);
                    break;
                case "/devolucion":

                    this.url = "/WEB-INF/view/devolucion.jsp";
                    sesion.setAttribute("siguienteURL", this.url);
                    break;
            }
            RequestDispatcher respuesta = getServletContext().getRequestDispatcher(this.url);
            respuesta.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
