package kickstarter.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public SignUp() 
    {
        super();
    }
    
    public void init( ServletConfig config ) throws ServletException
	{
		super.init( config );
		
		//getServletContext().setAttribute("users", getUsers());
		
        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
				request.getRequestDispatcher( "/WEB-INF/SignUp.jsp" ).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String uname = request.getParameter("usname");
		String pwd = request.getParameter("pwd");
		String repwd = request.getParameter("repwd");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		
		String result = new String("ye");
		try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu38";
            String username = "cs320stu38";
            String password = "#.l.y#cE";

            Connection c = DriverManager.getConnection( url, username, password );
            
            
            
            PreparedStatement pstmt2 = c.prepareStatement( "select * from users where username=?" );
            pstmt2.setString(1, uname);
            ResultSet rs = pstmt2.executeQuery();
            
            if(uname.length() == 0 || pwd.length() == 0 || repwd.length() == 0 || fname.length() == 0 || lname.length() == 0)
            {
            	result = "emp";
            }
            else
            {
            	if(uname.length() < 4)
            	{
            		result = "2";
            	}
            	else
            	{
            		if(rs.next())
            		{
            			result = "3";
            		}
            		else
            		{
            			if(pwd.length() < 4)
            			{
            				result = "4";
            			}
            			else
            			{
            				if(!pwd.equals(repwd))
                			{
                				result = "5";
                			}
            				else
            				{
                			if(result.equals("ye"))
                			{
                				String sql = "insert into users values (? , ?, ?, ?)";
                	            PreparedStatement pstmt = c.prepareStatement( sql );
                	            pstmt.setString( 1, uname );
                	            pstmt.setString( 2, pwd );
                	            pstmt.setString( 3, fname );
                	            pstmt.setString( 4, lname );
                				pstmt.executeUpdate();
                				c.close();
                				response.sendRedirect("LogInPage");
                			}
                			else
                			{
                				c.close();
                			}
            				}
            			}
            		}
            	}
            }   
            
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }
		if(!result.equals("ye"))
		{
			request.setAttribute("result", result);
			request.getRequestDispatcher( "/WEB-INF/SignUp.jsp" ).forward(request, response);
		}
	}
	
}