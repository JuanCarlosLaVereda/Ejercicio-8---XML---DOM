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

            addJon();
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
    private static void addJon() {
        try {
            // Cargar el archivo XML
            File xmlFile = new File("src/Ejercicio8/resources/xml/GOTini.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Normalizar el documento XML
            doc.getDocumentElement().normalize();

            // Obtener la raíz del documento ("GOT")
            Element root = doc.getDocumentElement();

            // Crear el nuevo nodo de personaje ("character") para Jon Snow
            Element newCharacter = doc.createElement("character");

            // Crear y agregar los elementos de Jon Snow
            addElementWithText(doc, newCharacter, "id", "583");
            addElementWithText(doc, newCharacter, "name", "Jon Snow");
            addElementWithText(doc, newCharacter, "gender", "Male");
            addElementWithText(doc, newCharacter, "culture", "Northmen");
            addElementWithText(doc, newCharacter, "born", "In 283 AC, at Winterfell");
            addElementWithText(doc, newCharacter, "died", "In 305 AC, at Winterfell");  // Esta es la información que pusimos ficticia para cuando Jon muere en la serie
            addElementWithText(doc, newCharacter, "alive", "FALSE");

            // Crear y agregar el elemento "titles" con sus títulos
            Element titles = doc.createElement("titles");
            addElementWithText(doc, titles, "title", "Lord Commander of the Night's Watch");
            addElementWithText(doc, titles, "title", "King in the North");
            newCharacter.appendChild(titles);

            // Crear y agregar el elemento "aliases" con sus alias
            Element aliases = doc.createElement("aliases");
            addElementWithText(doc, aliases, "alias", "Lord Snow");
            addElementWithText(doc, aliases, "alias", "Ned Stark's Bastard");
            addElementWithText(doc, aliases, "alias", "The Snow of Winterfell");
            addElementWithText(doc, aliases, "alias", "The Crow-Come-Over");
            addElementWithText(doc, aliases, "alias", "The 998th Lord Commander of the Night's Watch");
            addElementWithText(doc, aliases, "alias", "The Bastard of Winterfell");
            addElementWithText(doc, aliases, "alias", "The Black Bastard of the Wall");
            addElementWithText(doc, aliases, "alias", "Lord Crow");
            newCharacter.appendChild(aliases);

            // Agregar padre y madre
            addElementWithText(doc, newCharacter, "father", "Eddard Stark");
            addElementWithText(doc, newCharacter, "mother", "Lyanna Stark"); // Corregido con la madre correcta de Jon Snow

            // Agregar el nodo de los libros donde aparece Jon Snow
            Element books = doc.createElement("books");
            addElementWithText(doc, books, "book", "A Game of Thrones (1996)");
            addElementWithText(doc, books, "book", "A Clash of Kings (1998)");
            addElementWithText(doc, books, "book", "A Storm of Swords (2000)");
            addElementWithText(doc, books, "book", "A Feast for Crows (2005)");
            addElementWithText(doc, books, "book", "A Dance with Dragons (2011)");
            newCharacter.appendChild(books);

            // Agregar las temporadas de la serie donde aparece Jon Snow
            Element tvSeries = doc.createElement("tvSeries");
            addElementWithText(doc, tvSeries, "season", "Season 1");
            addElementWithText(doc, tvSeries, "season", "Season 2");
            addElementWithText(doc, tvSeries, "season", "Season 3");
            addElementWithText(doc, tvSeries, "season", "Season 4");
            addElementWithText(doc, tvSeries, "season", "Season 5");
            addElementWithText(doc, tvSeries, "season", "Season 6");
            addElementWithText(doc, tvSeries, "season", "Season 7");
            addElementWithText(doc, tvSeries, "season", "Season 8");
            newCharacter.appendChild(tvSeries);

            // Agregar el actor que interpreta a Jon Snow
            addElementWithText(doc, newCharacter, "playedBy", "Kit Harington");

            // Agregar el nuevo personaje a la raíz
            root.appendChild(newCharacter);

            // Guardar el documento actualizado
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/Ejercicio8/resources/xml/GOTini.xml"));
            transformer.transform(source, result);

            System.out.println("Jon Snow ha sido añadido al archivo XML correctamente.");

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void addElementWithText(Document doc, Element parent, String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(textContent));
        parent.appendChild(element);
    }
}
