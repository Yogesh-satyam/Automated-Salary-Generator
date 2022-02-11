/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Authservlet")
public class Authservlet extends HttpServlet {
    static int emp_id;
    static String pass;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment1&2","root","4556");
            PreparedStatement stmt= con.prepareStatement("select * from employee where emp_id=? and pass=?");
            emp_id=Integer.parseInt(request.getParameter("emp_id"));
            pass=request.getParameter("password");
            stmt.setInt(1,emp_id);
            stmt.setString(2,pass);
            ResultSet rs=stmt.executeQuery();
            if(!rs.next()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet auth</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Data not found, as per provided employee id and password.</h1>");
            
            }
            else {
                response.sendRedirect("asksalary.html");
                 }
            
            /* TODO output your page here. You may use following sample code. */
           con.close();
        }catch (Exception e)
        {
            out.println(e);
        }
         out.println("</body>");
            out.println("</html>");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
