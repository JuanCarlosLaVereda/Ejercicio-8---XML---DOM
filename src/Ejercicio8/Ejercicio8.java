package Ejercicio8;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ejercicio8 {
    public static void main(String[] args) {
        try {
            File archivo = new File("src/Ejercicio8/resources/xml/GOTini.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(archivo);
            doc.getDocumentElement().normalize();

            System.out.println("--" + doc.getDocumentElement().getNodeName() +":");
            NodeList characters = doc.getDocumentElement().getChildNodes();

            show(characters);
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            NodeList nodoRaiz = doc.getElementsByTagName("character");
/*            Element nombre = doc.createElement("playedBy");
            Text valor = doc.createTextNode("Alfie Allen");
            nodoRaiz.item(0).appendChild(nombre);
            nombre.appendChild(valor);*/

            String[] array = {"Alfie Allen", "Isaac Hempstead-Wright", "Art Parkinson", "Richard Madden", "Sophie Turner"};
            List<String> listaNombres = new ArrayList<>();
            listaNombres.addAll(Arrays.asList(array));
            for (int i = 0; i < nodoRaiz.getLength(); i++) {
                Element etiqueta = doc.createElement("playedBy");
                Text actor = doc.createTextNode(listaNombres.get(i));
                nodoRaiz.item(i).appendChild(etiqueta);
                etiqueta.appendChild(actor);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/Ejercicio8/resources/xml/GOTini.xml"));

            transformer.transform(source, result);

            show(characters);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void show(NodeList characters) {
        for (int i = 0; i < characters.getLength(); i++) {
            Node character = characters.item(i);
            if (character.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println("-----" + character.getNodeName() +":");
                for (int j = 0; j < character.getChildNodes().getLength(); j++) {
                    Node childNode = character.getChildNodes().item(j);
                    if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                        int childsLenght = childNode.getChildNodes().getLength();
                        if (childsLenght == 0) {
                            System.out.println("--------" + childNode.getNodeName() +":");
                        } else if (childsLenght == 1) {
                            System.out.println("--------" + childNode.getNodeName() +":" + childNode.getTextContent());
                        } else {

                            NodeList childNodes2 = childNode.getChildNodes();
                            for (int k = 0; k < childNodes2.getLength(); k++) {
                                Node child2 = childNodes2.item(k);
                                if (child2.getNodeType() == Node.ELEMENT_NODE) {
                                    System.out.println("-----------" + child2.getNodeName() +":" + child2.getTextContent());
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}
