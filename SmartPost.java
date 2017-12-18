/*
for reading data from xml
*/

package harkkatyo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import harkkatyo.Posts;


public class SmartPost {

	private Document doc1;
	private ArrayList<String> cityList = new ArrayList<>();
	private ArrayList<Posts> posts = new ArrayList<Posts>();
	private HashMap<String, String> map;
	public HashMap<String, String> getMap() {
	    return map;
	}
	
	public SmartPost() {
		try {
			URL url1 = new URL("http://smartpost.ee/fi_apt.xml");
		
			BufferedReader br = new BufferedReader(
				new InputStreamReader(url1.openStream()));
		
			String content = "";
			String line;
		
			while((line =br.readLine()) !=null) {
				content +=line + "\n";	
		}
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		
		doc1 = dBuilder.parse(new InputSource(new StringReader(content)));
		doc1.getDocumentElement().normalize();

		parsePlaceData();
		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void parsePlaceData() {
		
		NodeList nodes = doc1.getElementsByTagName("place");
		
		for(int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) node;
	               String code = eElement.getElementsByTagName("code").item(0).getTextContent();
	               String city = eElement.getElementsByTagName("city").item(0).getTextContent();
	               String address = eElement.getElementsByTagName("address").item(0).getTextContent();
	               String availability = eElement.getElementsByTagName("availability").item(0).getTextContent();
	               String postoffice = eElement.getElementsByTagName("postoffice").item(0).getTextContent();
	               String lat = eElement.getElementsByTagName("lat").item(0).getTextContent();
	               String lng = eElement.getElementsByTagName("lng").item(0).getTextContent();

	               posts.add(new Posts(code, city, address, availability, postoffice, lat, lng));
	               cityList.add(city + "; " + postoffice);            
	            }
		}      
   }
	
	public ArrayList<String> getList(){
	    return cityList;
	}
		
	public String getAddress(String s) {
		String address = "";
		for (Posts p : posts) {
			String test = p.getCity() + "; " + p.getPostoffice();
			if(s.equals(test)) {
				address = p.getAddress() + ", " + p.getCode() + " " + p.getCity();
			}
		}
		return address;
	}
	
	public String getInfo(String s) {
		String info = "";
		for (Posts p : posts) {
			String test = p.getCity() + ", " + p.getPostoffice();
			if(s.equals(test)) {
				info = p.getAvailability();
			}
		}
		return info;
	}
	
	public ArrayList<Float> getArray(String itemname) {
		ArrayList<Float> flist = new ArrayList<>();
		Singleton sn = null;
		sn = Singleton.getInstance();
		
		ArrayList<String> t = new ArrayList<>();
		t = sn.getfromlist();

		String[] tokens = null;
		String[] tokens2 = null;
		for(int i = 0; i < t.size(); i++) {   
		    t.get(i);
		    tokens = t.get(i).split(": ");
		}
		
		for(int i = 0; i < tokens.length; i++) {    
		    tokens2 = tokens[1].split("; ");
		}

		String t1 = tokens2[1];
		String t2 = tokens2[3];
		
		
		for (Posts p : posts) {
			String test = p.getPostoffice();
			
			if(test.equals(t1)) {

				String lat11 = p.getLat();
				String lng11 = p.getLng();
				
				Float lat1 = Float.parseFloat(lat11);
				Float lng1 = Float.parseFloat(lng11);
				flist.add(lat1);
				flist.add(lng1);
			}
		}
		for (Posts p : posts) {
			String test = p.getPostoffice();
			if(test.equals(t2)) {
				String lat22 = p.getLat();
				String lng22 = p.getLng();
				Float lat2 = Float.parseFloat(lat22);
				Float lng2 = Float.parseFloat(lng22);
				flist.add(lat2);
				flist.add(lng2);
			}
		}
		return flist;
	}
}
