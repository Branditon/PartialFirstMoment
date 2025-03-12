/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 * Servlet responsible for handling page navigation requests.
 * It retrieves the requested page from the parameters and forwards the request to the corresponding JSP file.
 * 
 * @author brandonescudero
 */
@WebServlet("/PageController")
public class PageController extends HttpServlet {
    
    /**
     * Handles GET requests by forwarding them to the appropriate JSP page.
     * 
     * @param request  the HttpServletRequest object containing the request details.
     * @param response the HttpServletResponse object used to send the response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve the requested page parameter from the HTTP request
        String page = request.getParameter("page");
        
        // Construct the path to the corresponding JSP file
        String nextPage = "views/" + page + ".jsp";
        
        // Forward the request to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }
}
