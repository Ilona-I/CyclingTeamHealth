package ua.nure.illiashenko.ilona.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ua.nure.illiashenko.ilona.dao.entities.Constraint;
import ua.nure.illiashenko.ilona.exceptions.CannotReadXMLFileException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static ua.nure.illiashenko.ilona.constants.UserConstants.ROLE;

public class ReadXMLFile {

    private static final Logger logger = LoggerFactory.getLogger(ReadXMLFile.class);

    public List<Constraint> readConstraints(File xmlFile) {
        List<Constraint> constraints = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            readDocument(constraints, document);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.error(e.getMessage());
            throw new CannotReadXMLFileException(e.getMessage());
        }
        return constraints;
    }

    private void readDocument(List<Constraint> constraints, Document document) {
        NodeList constraintNodeList = document.getElementsByTagName("constraint");
        for (int i = 0; i < constraintNodeList.getLength(); i++) {
            Node constraintNode = constraintNodeList.item(i);
            if (constraintNode.getNodeType() == Node.ELEMENT_NODE) {
                Element constraint = (Element) constraintNode;
                String urlPattern = constraint.getElementsByTagName("url-pattern").item(0).getTextContent();
                List<String> roles = new ArrayList<>();
                NodeList roleList = constraint.getElementsByTagName(ROLE);
                for (int j = 0; j < roleList.getLength(); j++) {
                    roles.add(roleList.item(j).getTextContent());
                }
                constraints.add(new Constraint(Pattern.compile(urlPattern), roles));
            }
        }
    }
}
