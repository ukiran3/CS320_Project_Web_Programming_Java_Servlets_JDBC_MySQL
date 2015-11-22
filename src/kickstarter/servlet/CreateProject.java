package kickstarter.servlet;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateProject")
public class CreateProject extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	public CreateProject()
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
		request.getRequestDispatcher( "/WEB-INF/CreateProject.jsp" ).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String name = String.valueOf(request.getSession().getAttribute( "user" ));
		if( name == null )
		{
			name = request.getParameter( "name" );
		}
		else
		{
			name = String.valueOf(request.getSession().getAttribute( "user" ));
		}
		String title = request.getParameter( "title" );
		String desc = request.getParameter( "description" );
		int amount = Integer.valueOf(request.getParameter( "amount" ));
		String sdate = request.getParameter( "start" );
		int days = Integer.valueOf(request.getParameter( "days" ));
		int id = 0;
		
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Integer.valueOf(sdate.substring(6, sdate.length())), Integer.valueOf(sdate.substring(0, 2)) - 1, Integer.valueOf(sdate.substring(3, 5)));
		java.sql.Date cal = new java.sql.Date(cal1.getTime().getTime());
		
		try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu38";
            String username = "cs320stu38";
            String password = "#.l.y#cE";

            Connection c = DriverManager.getConnection( url, username, password );
            String sql = "insert into project_list values(?,?,?,?,?,?,?)";
            String sql2 = "select max(id) m from project_list";
            PreparedStatement pstmt = c.prepareStatement( sql );
            PreparedStatement pstmt2 = c.prepareStatement( sql2 );
            ResultSet rs = pstmt2.executeQuery();
            if(rs.next())
            {
            	id = rs.getInt("m") + 1;
            }
            pstmt.setInt( 1, id );
            pstmt.setString( 2, name );
            pstmt.setString( 3, title );
            pstmt.setString( 4, desc );
            pstmt.setFloat( 5, amount );
            pstmt.setDate( 6, cal);
            pstmt.setInt( 7, days );
            pstmt.executeUpdate();

            c.close();
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }
		
		response.sendRedirect("AddRewards");
	}

}