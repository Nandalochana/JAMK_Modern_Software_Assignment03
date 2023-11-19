package com.javatpoint.dataHandlers;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import pojos.LogInfo;
import pojos.ViewInfo;
import pojos.User;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.javatpoint.dataHandlers.Config.FILENAME_USER;
import static com.javatpoint.dataHandlers.Config.FORMAT_USER_XSLT;


public class UserManagementController {


    public int deleteuser(User user) {
        int status = 0;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            InputStream is = Files.newInputStream(Paths.get(FILENAME_USER));
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(is);
            String rootTag = user.isBarber() ? "staff" : "customer";

            NodeList listOfStaff = doc.getElementsByTagName(rootTag);
            if (listOfStaff.getLength() > 0) {
                for (int i = 0; i < listOfStaff.getLength(); i++) {
                    Node systemUser = listOfStaff.item(i);
                    if (systemUser.getNodeType() == Node.ELEMENT_NODE) {
                        String id = systemUser.getAttributes().getNamedItem("id").getTextContent();
                        if ((user.getId() + "").equals(id.trim())) {
                            Node staff = listOfStaff.item(i);
                            NodeList childNodes = staff.getChildNodes();
                            for (int j = 0; j < childNodes.getLength(); j++) {
                                Node item = childNodes.item(j);
                                if (item.getNodeType() == Node.ELEMENT_NODE) {

                                    if ("id".equalsIgnoreCase(item.getNodeName())) {

                                        status = 1;
                                        staff.removeChild(item);

                                    }
                                }

                            }
                        }
                        writeXml(doc);
                    }

                }

            }
            writeXml(doc);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }


    public User createOrModifyuser(User user) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            InputStream is = Files.newInputStream(Paths.get(FILENAME_USER));
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(is);
            String rootTag = user.isBarber() ? "staff" : "customer";

            NodeList listOfStaff = doc.getElementsByTagName(rootTag);
            if (listOfStaff.getLength() > 0) {
                for (int i = 0; i < listOfStaff.getLength(); i++) {
                    Node systemUser = listOfStaff.item(i);
                    if (systemUser.getNodeType() == Node.ELEMENT_NODE) {
                        String id = systemUser.getAttributes().getNamedItem("id").getTextContent();
                        if ((user.getId() + "").equals(id.trim())) {

                            NodeList childNodes = systemUser.getChildNodes();

                            for (int j = 0; j < childNodes.getLength(); j++) {
                                Node item = childNodes.item(j);
                                if (item.getNodeType() == Node.ELEMENT_NODE) {

                                    if ("username".equalsIgnoreCase(item.getNodeName())) {
                                        item.setTextContent(user.getUsername());
                                    }

                                    if ("firstname".equalsIgnoreCase(item.getNodeName())) {
                                        item.setTextContent(user.getFirstName());
                                    }

                                    if ("lastname".equalsIgnoreCase(item.getNodeName())) {
                                        item.setTextContent(user.getLastName());
                                    }

                                    if ("email".equalsIgnoreCase(item.getNodeName())) {
                                        item.setTextContent(user.getEmail());
                                    }

                                    if ("password".equalsIgnoreCase(item.getNodeName())) {
                                        item.setTextContent(user.getPassword());
                                    }

                                    if ("userstatus".equalsIgnoreCase(item.getNodeName())) {
                                        item.setTextContent(user.getUserStatus() + "");
                                    }

                                    if ("role".equalsIgnoreCase(item.getNodeName())) {
                                        item.setTextContent(user.getRole());
                                    }
                                }

                            }
                            writeXml(doc);
                            return user;
                        }

                    }

                }
            } else {
                Element rootElement = doc.createElement("barber-reservation");
                doc.appendChild(rootElement);
                Element staff = doc.createElement(rootTag);
                rootElement.appendChild(staff);
                staff.setAttribute("id", user.getId() + "");

                Element userName = doc.createElement("username");
                userName.setTextContent(user.getUsername());
                staff.appendChild(userName);

                Element firstName = doc.createElement("firstname");
                firstName.setTextContent(user.getFirstName());
                staff.appendChild(firstName);

                Element lstName = doc.createElement("lastname");
                lstName.setTextContent(user.getLastName());
                staff.appendChild(lstName);

                Element email = doc.createElement("email");
                email.setTextContent(user.getEmail());
                staff.appendChild(email);

                Element password = doc.createElement("password");
                password.setTextContent(user.getPassword());
                staff.appendChild(password);

                Element userStatus = doc.createElement("userstatus");
                userStatus.setTextContent(user.getUserStatus() + "");
                staff.appendChild(userStatus);

                Element role = doc.createElement("role");
                role.setTextContent(user.getRole());
                staff.appendChild(role);
            }

            // output to console
            // writeXml(doc, System.out);

            writeXml(doc);
            return user;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeXml(Document doc) throws FileNotFoundException, TransformerException {

        FileOutputStream output =
                new FileOutputStream("barber-reservation.xml");

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        // add a xslt to remove the extra newlines
        Transformer transformer = transformerFactory.newTransformer(
                new StreamSource(new File(FORMAT_USER_XSLT)));

        // pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);


    }

    public List<User> showInfo(ViewInfo viewInfo) {
        List<User> myList = new ArrayList<>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILENAME_USER));

            doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");



            if (viewInfo.getShowType() == 1) {
                myList.addAll(loadUsers(doc, "staff"));
            } else if (viewInfo.getShowType() == 2) {
                myList.addAll(loadUsers(doc, "customer"));
            } else {
                myList.addAll(loadUsers(doc, "staff"));
                myList.addAll(loadUsers(doc, "customer"));
            }


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return myList;
    }

    private List<User> loadUsers(Document doc, String role) {
        NodeList list = doc.getElementsByTagName(role);
        List<User> myList = new ArrayList<>();
        for (int temp = 0; temp < list.getLength(); temp++) {

            Node node = list.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;

                // get staff's attribute
                String id = element.getAttribute("id");

                // get text
                String username = element.getElementsByTagName("username").item(0).getTextContent();
                String firstname = element.getElementsByTagName("firstname").item(0).getTextContent();
                String lastname = element.getElementsByTagName("lastname").item(0).getTextContent();
                String email = element.getElementsByTagName("email").item(0).getTextContent();
                String password = element.getElementsByTagName("password").item(0).getTextContent();
                String userstatus = element.getElementsByTagName("userstatus").item(0).getTextContent();
                String usertype = element.getElementsByTagName("usertype").item(0).getTextContent();
                String userrole = element.getElementsByTagName("role").item(0).getTextContent();

                User user = new User();
                user.setId(Integer.parseInt(id));
                user.setUsername(username);
                user.setFirstName(firstname);
                user.setLastName(lastname);
                user.setEmail(email);
                user.setPassword(password);
                user.setUserStatus(Integer.parseInt(userstatus));
                user.setUserType(Integer.parseInt(usertype));
                user.setRole(userrole);
                myList.add(user);
            }
        }
        return myList;
    }

    public User logIn(LogInfo logInfo) {
        List<User> users = showInfo(new ViewInfo());
        final User[] selectedUser = {null};
        users.forEach(o->{
            if(logInfo.getEmail().equalsIgnoreCase(o.getEmail())&& logInfo.getPassword().equalsIgnoreCase(o.getPassword())){
                selectedUser[0] = o;
            }
        });
        User user = selectedUser[0];
        tempCacheLevel.loginInfo.add(user);
        return user;
    }

    public User logOut(LogInfo logInfo) {
        final User[] selectedUser = {null};
        tempCacheLevel.loginInfo.forEach(o->{
            if(logInfo.getEmail().equalsIgnoreCase(o.getEmail())&& logInfo.getPassword().equalsIgnoreCase(o.getPassword())){
                selectedUser[0] = o;
            }
        });
        User user = selectedUser[0];
        tempCacheLevel.loginInfo.remove(user);
        return user;
    }

    public List<User> showLoggingUserInfo() {
        return tempCacheLevel.loginInfo;
    }
}
