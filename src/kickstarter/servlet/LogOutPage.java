package kickstarter.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/LogOutPage", loadOnStartup = 2)
public class LogOutPage extends HttpServlet {

        private static final long serialVersionUID = 1L;

        protected void doGet( HttpServletRequest request,
            HttpServletResponse response ) throws ServletException, IOException
        {
            request.getSession().invalidate();
            response.sendRedirect( "ProjectList" );
        }

        protected void doPost( HttpServletRequest request,
            HttpServletResponse response ) throws ServletException, IOException
        {
            doGet( request, response );
        }

    }