/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Service;

import Controller.CoworkingReservationsController;
import Entity.CoworkingReservationsEntity;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet responsible for handling coworking reservations-related requests.
 * Supports retrieving, creating, and deleting reservations.
 * 
 * @author brandonescudero
 */
@WebServlet(name = "CoworkingReservationsService", urlPatterns = {"/CoworkingReservationsService"})
public class CoworkingReservationsService extends HttpServlet {
    CoworkingReservationsController CoworkingReservationsController = new CoworkingReservationsController();

    /**
     * Processes requests for both HTTP GET and POST methods.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles HTTP GET requests by returning a list of coworking reservations in JSON format.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CoworkingReservationsEntity> list;
        Gson gson;
        String json;
        
        list = CoworkingReservationsController.find();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        gson = new Gson();
        json = gson.toJson(list);
        
        response.getWriter().write(json);
    }

    /**
     * Handles HTTP POST requests for creating or deleting reservations based on the 'action' parameter.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action;
        boolean success;
        PrintWriter out;
        
        action = request.getParameter("action");
        out = response.getWriter();
        
        if (action == null) 
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"status\":\"error\", \"message\":\"No action provided\"}");
            out.flush();
            return;
        }
        
        switch (action) 
        {
            case "create":
                success = create(request);
                break;
            case "delete":
                success = delete(request);
                break;
            default:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"status\":\"error\", \"message\":\"Invalid action\"}");
                out.flush();
                return;
        }
        
        if (success) 
        {
            out.print("{\"status\":\"success\"}");
        } else 
        {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"status\":\"error\"}");
        }
        
        out.flush();
    }
    
    /**
     * Creates a new coworking reservation using request parameters.
     * 
     * @param request servlet request containing reservation details
     * @return true if the reservation is successfully created, false otherwise
     */
    private boolean create(HttpServletRequest request)
    {
        CoworkingReservationsEntity CoworkingReservationsEntity;
        String userName, workspace, reservationDuration;
        Date reservationDate;
        SimpleDateFormat dateFormat;
        
        userName = request.getParameter("userName");
        dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adjust format as needed
        
        try {
            reservationDate = dateFormat.parse(request.getParameter("reservationDate"));
        } catch (ParseException e) {
            e.printStackTrace(); // Handle parsing exception
            return false;
        }
        
        workspace = request.getParameter("workspace");
        reservationDuration = request.getParameter("reservationDuration");
        
        CoworkingReservationsEntity = new CoworkingReservationsEntity();
        CoworkingReservationsEntity.setUserName(userName);
        CoworkingReservationsEntity.setReservationDate(reservationDate);
        CoworkingReservationsEntity.setWorkspace(workspace);
        CoworkingReservationsEntity.setReservationDuration(reservationDuration);
        
        return CoworkingReservationsController.create(CoworkingReservationsEntity);
    }
    
    /**
     * Deletes an existing coworking reservation using the provided ID.
     * 
     * @param request servlet request containing the reservation ID
     * @return true if the reservation is successfully deleted, false otherwise
     */
    private boolean delete(HttpServletRequest request) 
    {
        Long id;
        
        id = Long.valueOf(request.getParameter("id"));
        
        return CoworkingReservationsController.detroy(id);
    }

    /**
     * Returns a short description of the servlet.
     * 
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}