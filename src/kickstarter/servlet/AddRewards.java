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

@WebServlet("/AddRewards")

public class AddRewards extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
    public AddRewards()
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
		request.getRequestDispatcher( "/WEB-INF/AddRewards.jsp" ).forward(request, response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String amount = request.getParameter( "amount" );
        String question = request.getParameter( "question" );
        
        String selection = request.getParameter("finish");
        
        int id = 0;
        
        if(selection.equals("Add"))
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu38";
            String username = "cs320stu38";
            String password = "#.l.y#cE";

            Connection c = DriverManager.getConnection( url, username, password );
            String sql = "insert into project_rewards values(?,?,?)";
            String sql2 = "select max(id) m from project_list";
            
            PreparedStatement pstmt2 = c.prepareStatement( sql2 );
            ResultSet rs = pstmt2.executeQuery();
            if(rs.next())
            {
            	id = rs.getInt("m");
            }
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setInt( 1, id );
            pstmt.setString( 2, amount );
            pstmt.setString( 3, question );
            pstmt.executeUpdate();

            c.close();
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }
        
        if(selection.equals("Add"))
        {
        	response.sendRedirect( "AddRewards" );
        }
        else
        {
        	response.sendRedirect( "ProjectList" );
        }

	}

}