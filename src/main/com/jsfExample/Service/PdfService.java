package main.com.jsfExample.Service;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import main.com.jsfExample.DTOs.MyClient;
import main.com.jsfExample.DTOs.Tariff;

import javax.ejb.Stateless;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Stateless
public class PdfService implements Serializable{

    private static final long serialVersionUID = 8L;

    public void makePdf( String tariffId,
                         List<MyClient> clientList )
            throws DocumentException, IOException {

        // выюираем куда положить и как назвать
        String fileName = "C:\\report" + tariffId + ".pdf";
        // готовим документ
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter.getInstance( document, new FileOutputStream( fileName ));
        document.open();
        // записываем и закрываем
        for ( MyClient myClient : clientList ){

            document.add( new Paragraph( myClient.toString() ));
        }
        document.close();
    }
}
