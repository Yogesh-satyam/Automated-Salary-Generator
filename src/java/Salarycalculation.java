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

@WebServlet(urlPatterns = {"/Salarycalculation"})
public class Salarycalculation extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Salarycalculation</title>");            
            out.println("</head>");
            out.println("<body>");
            Class.forName("com.mysql.cj.jdbc.Driver");
             Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment1&2","root","4556");
             PreparedStatement stmt= con.prepareStatement("select * from employee where emp_id=? and pass=?");
             stmt.setInt(1,Authservlet.emp_id);
            stmt.setString(2,Authservlet.pass);
            ResultSet rs=stmt.executeQuery();
            rs.next();
             String category = rs.getString(3);
             double Days,Tsal,DA,bsal= rs.getDouble(4);
                Days=Integer.parseInt(request.getParameter("days"));
                DA = (Days / 30) * bsal;
                 switch (category)
                {
                    case "staff" : {
                        Tsal = bsal+((double) 20 / 100) * DA;
                        out.println("<h3> Total Salary for " + Days + " days  is " + Tsal+"</h3>");
                        break;
                    }
                    case "engineer" : {
                        Tsal = bsal+((double)15 / 100) * DA;
                        out.println("<h3> Total Salary for " + Days + " days is " + Tsal+"</h3>");
                        break;
                    }
                    case "manager" : {
                        Tsal = bsal+((double)10 / 100) * DA;
                        out.println("<h3> Total Salary for " + Days + " dyas is " + Tsal+"</h3>");
                        break;
                    }
                    default : out.println("<h3> Unknown Error Contact admin</h3>");
                }
            con.close();
        }catch(Exception e){
            out.println(e);
        }
        out.println("<br>\n" +
"            <br>\n" +
"            <a href=\"index.html\">Go back to Homepage</a>");
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
    }// </editor-fold>

}
