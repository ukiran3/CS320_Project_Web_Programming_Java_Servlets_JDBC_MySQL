package kickstarter.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs320Starter.model.Pledges;
import cs320Starter.model.ProjList;
@WebServlet("/MyProfile")
public class MyProfile extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public MyProfile() 
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
		String user = String.valueOf(request.getSession().getAttribute("user"));
		request.setAttribute("pledges", getPledges(user));
		request.getRequestDispatcher( "/WEB-INF/MyProfile.jsp" ).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
	ArrayList<Pledges> getPledges(String user) throws ServletException
	{
		ArrayList<Pledges> pleds = new ArrayList<Pledges>();
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu38";
            String username = "cs320stu38";
            String password = "#.l.y#cE";

            Connection c = DriverManager.getConnection( url, username, password );
            String sql = "select u.first_name, u.last_name, p.title, pl.amount, pl.rew_description from project_list p, project_pledges pl, users u where u.username = ? and pl.user_name = ? and p.id = pl.project_id";
            
            PreparedStatement pstmt = c.prepareStatement( sql );
            
            pstmt.setString( 1, user );
            pstmt.setString( 2, user );
            ResultSet rs = pstmt.executeQuery();
            
            String sql2 = "select id, title from project_list where name = ?";
            PreparedStatement pstmt2 = c.prepareStatement( sql2 );
            pstmt2.setString(1, user);
            
            ResultSet rs2 = pstmt2.executeQuery();
            ArrayList<ProjList> own = new ArrayList<ProjList>();
            
            while(rs2.next())
            {
            	int id = rs2.getInt(1);
            	String title = rs2.getString(2);
            	ProjList proj = new ProjList(id, title);
            	own.add(proj);
            }
            
            while(rs.next())
            {
            	String fname = rs.getString(1);
            	String lname = rs.getString(2);
            	String ptitle = rs.getString(3);
            	int amount = rs.getInt(4);
            	String rewDes = rs.getString(5);
            	
            	Pledges pled = new Pledges(fname, lname, ptitle, amount, rewDes);
            	pled.setOwnProjs(own);
            	pleds.add(pled);
            }

            c.close();
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }
        return pleds;
   	}
}
