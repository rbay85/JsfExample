package main.com.jsfExample.Controller;

import com.itextpdf.text.DocumentException;
import main.com.jsfExample.DTOs.MyClient;
import main.com.jsfExample.Service.PdfService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


//@Named( "Controller" )
//@SessionScoped
@WebServlet( "/printClientList" )
public class TariffController extends HttpServlet implements Serializable{

    private static final long serialVersionUID = 6L;

    //@EJB
    @Inject
    private RestClientService restClientService;

    //@Inject
    @EJB
    private PdfService pdfService;

    @Override
    public void doGet( HttpServletRequest req,
                       HttpServletResponse resp )
            throws ServletException,
                   IOException {

        String tariffId = req.getParameter( "tariffId" );

        List<MyClient> clientList = getClientListFromMap( restClientService.getClientMapList( tariffId ));

        try {
            pdfService.makePdf( tariffId, clientList );
        } catch ( DocumentException e) {
            req.setAttribute( "error", "Sorry, DocumentException arose :(" );
        }

        req.getRequestDispatcher( "/index.xhtml" ).forward( req, resp );
    }

    private List<MyClient> getClientListFromMap ( List<HashMap> clientMapList ){
        List<MyClient> clientList = new ArrayList<MyClient>();
        for ( HashMap hashMap : clientMapList ){
            MyClient myClient = new MyClient();
            myClient.setFirstName( String.valueOf( hashMap.get( "firstName" )));
            myClient.setLastName( String.valueOf( hashMap.get( "lastName" )));
            myClient.setBirthDay( String.valueOf( hashMap.get( "birthDay" )));
            clientList.add( myClient );
        }
        return clientList;
    }
}
