package admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Servlet1 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String ruta = request.getRealPath("/");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        try
        {
        SAXBuilder builder = new SAXBuilder();
        File archivoXML = new File(ruta + "\\archivoXML.xml");
        Document documento=builder.build(archivoXML);
        Element raiz=documento.getRootElement();
        List lista=raiz.getChildren("usuario");
            out.println(validateUP(lista, user, pass));
        }
        catch(JDOMException e)
        {
        e.printStackTrace();
        }
        }
    
    static int validateUP(List lista,String user,String pass){
        for(int i=0;i<lista.size();i++)
            {
                Element elemento=(Element)lista.get(i);
                String cadena=elemento.getAttributeValue("username");
                if(cadena.equals((Object)user)){
                    cadena = elemento.getAttributeValue("password");
                    if(cadena.equals((Object)pass)){
                        return 1;
                    }
                }
            }
            return 0;
    
    }
}