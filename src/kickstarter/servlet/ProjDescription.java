package kickstarter.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cs320Starter.model.ProjList;
import cs320Starter.model.RewardList;

@WebServlet(urlPatterns = "/ProjDescription", loadOnStartup = 2)
public class ProjDescription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ProjDescription()
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

    
    public ProjList getProj(int id, String user) throws ServletException
    {
    	ProjList proj = new ProjList();
    	ArrayList<RewardList> rewards = new ArrayList<RewardList>();
    	
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu38";
            String username = "cs320stu38";
            String password = "#.l.y#cE";

            Connection c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select *,datediff(DATE_ADD(start_date, INTERVAL funding_duration DAY), current_date) dtg from project_list where id = "+id );
            
            if( rs.next() )
            {
                Integer id1 = rs.getInt( 1 );
                String name = rs.getString( "name" );
                String title = rs.getString( 3 );
                String desc = rs.getString( 4 );
                float fun_tar = rs.getFloat( 5 );
                Date date = rs.getDate( 6 );
                int fun_dur = rs.getInt( 7 );
                proj = new ProjList( id1, name, title, desc, fun_tar, date, fun_dur ) ;
                proj.setDaysToGo(rs.getInt("dtg"));
            }
            
            String sql = "select * from project_rewards where project_id = ?";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs1 = pstmt.executeQuery();
            
            while(rs1.next())
            {
            	int amount = rs1.getInt(2);
            	String reward = rs1.getString(3);
            	
            	rewards.add(new RewardList(amount, reward));
            }
            
            proj.setRewards(rewards);
            
            String sql2 = "select * from project_pledges where project_id = ? and user_name = ?";
            PreparedStatement stmt2 = c.prepareStatement(sql2);
            stmt2.setInt(1, id);
            stmt2.setString(2, user);
            ResultSet rs2 = stmt2.executeQuery();

            if(rs2.next())
            {
            	proj.setSponsored(true);
            }
            
            c.close();
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }

		return	proj;
    }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int id = Integer.valueOf( request.getParameter( "id" ) );
		
		String user = String.valueOf(request.getSession().getAttribute("user"));
		
		ProjList proj = getProj(id, user);
        		 	
        getServletContext().setAttribute("proj", proj);
        
        request.getRequestDispatcher( "/WEB-INF/ProjDescription.jsp" ).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}