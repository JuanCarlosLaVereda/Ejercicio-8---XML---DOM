package Ejercicio1;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {
    public static void main(String[] args) {
        ejemploDOM();
    }
    public static void ejemploDOM() {
        try {
            //Indicaremos la ruta del fichero xml
            //src es el nombre del proyecto, main es la primera carpeta
            //resources la siguiente y dentro de xml encontraremos el fichero
            File arxXml = new File("src/Ejercicio1/resources/xml/Coches.xml");

            //Creamos los objetos para leer el fichero xml
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            //Le pasamos el fichero xml
            Document doc = db.parse(arxXml);
            doc.getDocumentElement().normalize();

            System.out.println("Elemento raiz: " + doc.getDocumentElement().getNodeName());

            NodeList nodeList = doc.getElementsByTagName("coche");
            //Creamos un bucle para leer los datos del fichero xml y los mostramos por consola

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("Marca: " + element.getElementsByTagName("marca").item(0).getTextContent());
                    System.out.println("Modelo: " + element.getElementsByTagName("modelo").item(0).getTextContent());
                    System.out.println("Color: " + element.getElementsByTagName("color").item(0).getTextContent());
                    System.out.println("Matriculacion: " + element.getElementsByTagName("matriculacion").item(0).getTextContent());

                }
            }

        } catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (SAXException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}