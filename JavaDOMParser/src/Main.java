import java.io.File;
	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.DocumentBuilder;
	import org.w3c.dom.Document;
	import org.w3c.dom.NodeList;
	import org.w3c.dom.Node;
	import org.w3c.dom.Element;

public class Main {
	
	   public static void main(String[] args) {

	      try {
	         
	    	 // fajl koji parsiramo
	    	 File inputFile = new File("input.txt");
	         
	    	 // pribavljanje parsera xml-a u dom objekat
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         
	         // parsiranje xml u dom iz InputStreams, Files, URLs, ili SAX InputSources
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         
	         doc.getDocumentElement().normalize();
	         
	         // Default: Root element: class
	         System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
	         
	         System.out.println("----------------------------");
	     
	         // Vraća elemente sa određenim imenom taga
	         NodeList nList = doc.getElementsByTagName("student");
	         
	         // Prođi kroz prethodno vraćene liste nList
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	        	
	        	// praćenje trenutnog čvora
	            Node nNode = nList.item(temp);
	            
	            // Default: Current Element: student
	            // vrati ime trenutnog čvora
	            System.out.println("\nCurrent Element: " + nNode.getNodeName());
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               
	               // čvor parisiran u element?
	               Element eElement = (Element) nNode;
	               
	               // rollno je ime taga u txt fajlu
	               System.out.println("Student roll no: " 
	                  + eElement.getAttribute("rollno"));
	               
	               // svi elementi su prvi u svojima stablima jer
	               // sve njih vraća sa indexom 0 *.item(0).
	               System.out.println("First Name : " 
	                  + eElement
	                  .getElementsByTagName("firstname")
	                  .item(0)
	                  .getTextContent());
	               System.out.println("Last Name : " 
	                  + eElement
	                  .getElementsByTagName("lastname")
	                  .item(0)
	                  .getTextContent());
	               System.out.println("Nick Name : " 
	                  + eElement
	                  .getElementsByTagName("nickname")
	                  .item(0)
	                  .getTextContent());
	               System.out.println("Marks : " 
	                  + eElement
	                  .getElementsByTagName("marks")
	                  .item(0)
	                  .getTextContent());
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	}