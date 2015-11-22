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

@WebServlet(urlPatterns = "/LogInPage", loadOnStartup = 2)
public class LogInPage extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    public void init( ServletConfig config ) throws ServletException
	{
		super.init( config );

        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
	}

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
    	request.getRequestDispatcher( "/WEB-INF/LogInPage.jsp" ).forward(request, response);
    }

    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
    	String uname = request.getParameter( "username" );
    	String pwd = request.getParameter( "password" );
    	try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu38";
            String username = "cs320stu38";
            String password = "#.l.y#cE";

            Connection c = DriverManager.getConnection( url, username, password );
            String sql = "select * from users where username = ? and password = ?";
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setString( 1, uname );
            pstmt.setString( 2, pwd );
            ResultSet rs = pstmt.executeQuery();

            if(rs.next())
            {
            	request.getSession().setAttribute( "user", uname);
            	c.close();
            	response.sendRedirect( "ProjectList" );
            }
            else
            {
            	c.close();
            	response.sendRedirect( "LogInPage" );
            }
            
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }

	}
     
}