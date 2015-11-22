package kickstarter.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cs320Starter.model.ProjList;

@WebServlet(urlPatterns = "/ProjectList", loadOnStartup = 1)
public class ProjectList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ProjectList()
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
		request.setAttribute( "projects", getFromDB() );

		request.getRequestDispatcher( "/WEB-INF/ProjectList.jsp" ).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
			doGet(request, response);
	}
	
	List<ProjList> getFromDB() throws ServletException
	{
		List<ProjList> projs = new ArrayList<ProjList>();

        try
        {
            String url = "jdbc:mysql://localhost/cs320stu38";
            String username = "cs320stu38";
            String password = "#.l.y#cE";

            Connection c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select *, datediff(DATE_ADD(start_date, INTERVAL funding_duration DAY), current_date) dtg from project_list where datediff(DATE_ADD(start_date, INTERVAL funding_duration DAY), current_date) > 0 order by datediff(DATE_ADD(start_date, INTERVAL funding_duration DAY), current_date) asc" );
            
            while( rs.next() )
            {
                Integer id = rs.getInt( 1 );
                String name = rs.getString( "name" );
                String title = rs.getString( 3 );
                String desc = rs.getString( 4 );
                float fun_tar = rs.getFloat( 5 );
                Date date = rs.getDate( 6 );
                int fun_dur = rs.getInt( 7 );
                
                projs.add( new ProjList( id, name, title, desc, fun_tar, date, fun_dur ) );
                ProjList proj = projs.get(projs.size() - 1);
                proj.setDaysToGo(rs.getInt("dtg"));
                
                Statement stmt2 = c.createStatement();
                ResultSet rs2 = stmt2.executeQuery("select * from project_pledges where project_id="+id);
                int fundCollected = 0;
                while(rs2.next())
                {
                	fundCollected = fundCollected + Integer.valueOf(rs2.getInt("amount"));
                }
                
        		proj.setFundCollected(fundCollected);
        		
        		float fundingTarget = proj.getFunTarget();
        		
        		float percent = (fundCollected / fundingTarget) * 100;
        		
        		proj.setPercentCollected(percent);
            }

            c.close();
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }

		return	projs;
	}
}