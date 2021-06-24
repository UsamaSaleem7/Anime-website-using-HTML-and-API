/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class getReviews extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet getReviews</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet getReviews at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    //this method is used to get data from the database and store the data on the servlet itself. 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             try {
                 //connection to datatbase is done here
            PrintWriter out = response. getWriter ();
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");  
            Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/reviewdatabase","usama","pakistan-143");
            Statement state = connection.createStatement();
            
            //data is taken from the database
            ResultSet resultSet = state.executeQuery("SELECT * from REVIEW");
            
            
            JSONObject newObj = new JSONObject();
            while(resultSet.next()){
                
                //the data that has been taken is put to different variables
                String id = resultSet.getString("reviewid");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("secondname");
                String email = resultSet.getString("emailaddress");
                String animeName = resultSet.getString("animename");
                String animeReview = resultSet.getString("animerev");
              
                //the data is changed to JSON structure
                JSONObject reviewObj = new JSONObject();
                reviewObj.put("id",id);
                reviewObj.put("firstName",firstName);
                reviewObj.put("lastName",lastName);
                reviewObj.put("email",email);
                reviewObj.put("animeName",animeName);
                reviewObj.put("animeReview",animeReview);
                newObj.put(id,reviewObj);
                
      }
             response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(newObj);
            out.flush();
        // don't add your web-app name to the path   
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
           ex.printStackTrace();
        }
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
