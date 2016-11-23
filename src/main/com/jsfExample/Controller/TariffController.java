package main.com.jsfExample.Controller;

import main.com.jsfExample.Service.RestClientService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;


//@Named( "Controller" )
//@SessionScoped
@WebServlet( "/printClientList" )
public class TariffController extends HttpServlet implements Serializable{

    private static final long serialVersionUID = 6L;

    //@EJB
    @Inject
    private RestClientService restClientService;

    @Override
    public void doGet( HttpServletRequest req,
                       HttpServletResponse resp )
            throws ServletException,
                   IOException {

        System.out.println( restClientService.getClientMapList( req.getParameter( "tariffId" ) ).toString() );

        req.getRequestDispatcher( "/index.xhtml" ).forward( req, resp );
    }
}
