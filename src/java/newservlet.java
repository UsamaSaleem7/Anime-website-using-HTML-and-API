/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usama
 */

public class newservlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     */
    
    private PreparedStatement p;
    
    public void init() throws ServletException
    {
        try {
            initJDBC();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(newservlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
    
        response.setContentType("text/html;charset=UTF-8");
        try{
        PrintWriter out = response.getWriter();
        
        String firstname = request.getParameter("Fname");
        String lastname = request.getParameter("Lname");
        String emailID = request.getParameter("emailadd");
        String anime = request.getParameter("Animename");
        String animereview = request.getParameter("animereview");
      
        addData(firstname, lastname, emailID, anime, animereview);
   
        
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Review</title>");            
            out.println("</head>");
            out.println("<body style='color:#FFFFFF;'>");
            out.println("<h1>Your review has been submitted</h1>");
           
            out.println("</body>");
            out.println("</html>");
            out.println("<body background='background.png'>");
            
        }
        
        catch (SQLException ex){
             System.out.println(ex.getMessage());
              out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Review</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>There is some error with the submission</h1>");
            out.println("</body>");
            out.println("</html>");
      
        }
        
    }
    public void initJDBC() throws ClassNotFoundException {
    try{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/reviewdatabase","usama","pakistan-143");
        
        p = connection.prepareStatement("insert into REVIEW " + "(firstname, secondname, emailaddress, animename, animerev) " + "values(?,?,?,?,?)");
        
               
    } catch(SQLException ex){
        System.out.println(ex.getMessage());
       
        
    }
    }
    
    private void addData(String firstname, String lastname, String emailID, String anime, String animereview)
            throws SQLException {
        
        p.setString(1, firstname);
     
        p.setString(2, lastname);
        
        p.setString(3, emailID);
       
        p.setString(4, anime);
        p.setString(5, animereview);
       
        p.executeUpdate();
      
 
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(newservlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(newservlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
