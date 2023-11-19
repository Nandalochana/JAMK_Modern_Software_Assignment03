package com.javatpoint.dataHandlers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pojos.Reservation;
import pojos.User;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.javatpoint.dataHandlers.Config.*;

public class ReservationManagementController {
    public Reservation createOrUpdateSlot(Reservation bookingInfo) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            InputStream is = Files.newInputStream(Paths.get(FILENAME_BOOKING));
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(is);

            // update to barber schema

            NodeList listOfStaff = doc.getElementsByTagName("booking");
            if (listOfStaff.getLength() > 0) {
                for (int i = 0; i < listOfStaff.getLength(); i++) {
                    Node systemUser = listOfStaff.item(i);
                    if (systemUser.getNodeType() == Node.ELEMENT_NODE) {
                        String id = systemUser.getAttributes().getNamedItem("id").getTextContent();
                        if ((bookingInfo.getId() + "").equals(id.trim())) {

                            NodeList childNodes = systemUser.getChildNodes();

                            for (int j = 0; j < childNodes.getLength(); j++) {
                                Node item = childNodes.item(j);
                                if (item.getNodeType() == Node.ELEMENT_NODE) {

                                    if ("customerId".equalsIgnoreCase(item.getNodeName())) {
                                        item.setTextContent(bookingInfo.getCustomerId()+"");
                                    }

                                    if ("barberId".equalsIgnoreCase(item.getNodeName())) {
                                        item.setTextContent(bookingInfo.getBarberId()+" ");
                                    }

                                    if ("time".equalsIgnoreCase(item.getNodeName())) {
                                        item.setTextContent(bookingInfo.getTime().getTime()+"");
                                    }

                                    if ("date".equalsIgnoreCase(item.getNodeName())) {
                                        item.setTextContent(bookingInfo.getDate().getTime()+"");
                                    }

                                    if ("status".equalsIgnoreCase(item.getNodeName())) {
                                        item.setTextContent(bookingInfo.getStatus()+"");
                                    }
                                }

                            }
                            writeXml(doc);
                            return bookingInfo;
                        }

                    }

                }
            } else {
                Element rootElement = doc.createElement("barber-reservation");
                doc.appendChild(rootElement);
                Element staff = doc.createElement("booking");
                rootElement.appendChild(staff);
                staff.setAttribute("id", bookingInfo.getId() + "");

                Element userName = doc.createElement("customerId");
                userName.setTextContent(bookingInfo.getCustomerId()+"");
                staff.appendChild(userName);

                Element firstName = doc.createElement("barberId");
                firstName.setTextContent(bookingInfo.getBarberId()+"");
                staff.appendChild(firstName);

                Element lstName = doc.createElement("time");
                lstName.setTextContent(bookingInfo.getTime().getTime()+"");
                staff.appendChild(lstName);

                Element email = doc.createElement("date");
                email.setTextContent(bookingInfo.getDate().getTime()+"");
                staff.appendChild(email);

                Element password = doc.createElement("status");
                password.setTextContent(bookingInfo.getStatus()+"");
                staff.appendChild(password);

            }

            // output to console
            // writeXml(doc, System.out);

            writeXml(doc);
            return bookingInfo;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeXml(Document doc) throws FileNotFoundException, TransformerException {

        FileOutputStream output =
                new FileOutputStream(FILENAME_BOOKING);

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

    public Integer deleteBookingInfo(Reservation bookingInfo) {
        int status = 0;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            InputStream is = Files.newInputStream(Paths.get(FILENAME_USER));
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(is);

            NodeList listOfStaff = doc.getElementsByTagName("booking");
            if (listOfStaff.getLength() > 0) {
                for (int i = 0; i < listOfStaff.getLength(); i++) {
                    Node systemUser = listOfStaff.item(i);
                    if (systemUser.getNodeType() == Node.ELEMENT_NODE) {
                        String id = systemUser.getAttributes().getNamedItem("id").getTextContent();
                        if ((bookingInfo.getId() + "").equals(id.trim())) {
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
            e.fillInStackTrace();
        }
        return status;
    }

    public List<Reservation> showBookingInfo() {
        List<Reservation> myList = new ArrayList<>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILENAME_BOOKING));
            NodeList list = doc.getElementsByTagName("booking");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    // get staff's attribute
                    String id = element.getAttribute("id");

                    // get text
                    String customerId = element.getElementsByTagName("customerId").item(0).getTextContent();
                    String barberId = element.getElementsByTagName("barberId").item(0).getTextContent();
                    String time = element.getElementsByTagName("time").item(0).getTextContent();
                    String date = element.getElementsByTagName("date").item(0).getTextContent();
                    String status = element.getElementsByTagName("status").item(0).getTextContent();

                    Reservation reservation = new Reservation();
                    reservation.setId(Integer.parseInt(id));
                    reservation.setCustomerId(Integer.parseInt(customerId));
                    reservation.setBarberId(Integer.parseInt(barberId));
                    reservation.setDate(new Date(Long.parseLong(date)));
                    reservation.setTime(new Date(Long.parseLong(time)));
                    reservation.setStatus(Integer.parseInt(status));
                    myList.add(reservation);
                }
            }
        }
        catch (Exception exception){
            exception.fillInStackTrace();
        }

        return myList;
    }
}
