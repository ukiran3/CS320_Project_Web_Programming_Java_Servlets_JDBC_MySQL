package kickstarter.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/SponsorThisProject", loadOnStartup = 2)
public class SponsorThisProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SponsorThisProject()
    {
        super();
    }
    
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

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getRequestDispatcher( "/WEB-INF/SponsorForm.jsp" ).forward(request, response);
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int id = Integer.valueOf(request.getParameter("id"));
		String name = String.valueOf(request.getSession().getAttribute("user"));
		String reward = String.valueOf(request.getParameter("reward"));
        int amount = Integer.valueOf(request.getParameter("amount"));
		
		try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu38";
            String username = "cs320stu38";
            String password = "#.l.y#cE";

            Connection c = DriverManager.getConnection( url, username, password );
            String sql = "insert into project_pledges values(?,?,?,?)";
            PreparedStatement pstmt = c.prepareStatement( sql );
        
            pstmt.setInt( 1, id );
            pstmt.setString( 2, name );
            pstmt.setInt(3, amount);
            pstmt.setString( 4, reward);
            
            pstmt.executeUpdate();
            
            c.close();
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }
		
		response.sendRedirect("ProjectList");
		
	}

}