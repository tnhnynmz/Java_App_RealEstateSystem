package cme2210;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
//ID lerin basina O,P,B vs. gelecek****
public class Main {
	static HashMap<String, Property> properties = new HashMap<>();
	static HashMap<String, Owner> owners = new HashMap<>();
	static HashMap<String, Buyer> buyers = new HashMap<>();
	static HashMap<String, Admin> admins = new HashMap<>();
	static HashMap<String, User> users = new HashMap<>();
	static HashMap<String, Sale> sales = new HashMap<>();
	static String currentUserID = "0";
	public static void main(String[] args) throws IOException {

		//CREATE USERS FROM TEXT
		try {
		      File myObj = new File("users.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data[] = myReader.nextLine().split("\t");
		        if (data[0].charAt(0)=='O') {
		        	Owner o1 = new Owner(data[0],data[1],data[2],data[3],data[4],data[5],data[6]);
			        owners.put(o1.getID(), o1);
		        }
		        else if (data[0].charAt(0)=='B') {
		        	Buyer b = new Buyer(data[0],data[1],data[2],data[3],data[4],data[5],data[6]);
			        buyers.put(b.getID(), b);
		        }
		        else if (data[0].charAt(0)=='A') {
		        	Admin a = new Admin(data[0],data[1],data[2],data[3],data[4],data[5],data[6]);
		        	admins.put(a.getID(), a);
		        }
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("Check User File!!");
		      e.printStackTrace();
		    }
		
		//CREATE PROPERTY OBJECTS FROM TEXT FILE
		try {
		      File myObj = new File("properties.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data[] = myReader.nextLine().split("\t");
		        Property p = new Property(data[0],data[1],data[2],Integer.parseInt(data[3]),Integer.parseInt(data[4]),Integer.parseInt(data[5])
		        		,Integer.parseInt(data[6]),Integer.parseInt(data[7]),Boolean.parseBoolean(data[8]),Boolean.parseBoolean(data[9])
		        		,Boolean.parseBoolean(data[10]),Boolean.parseBoolean(data[11]),data[12],data[13]);
		        properties.put(p.getID(), p);
		        owners.get(p.getOwnerID()).addOwnerProperty(p);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("Check Property File!!");
		      e.printStackTrace();
		    }
		
		try {
		      File myObj = new File("sales.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data[] = myReader.nextLine().split("\t");
		        Sale s = new Sale(data[0],properties.get(data[1]),buyers.get(data[2]),data[3]);
		        sales.put(s.getID(), s);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("Check Sale File!!");
		      e.printStackTrace();
		    }
		/*
		//CREATE OWNER OBJECTS FROM TEXT FILE
		try {
		      File myObj = new File("owners.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data[] = myReader.nextLine().split("\t");
		        Owner o1 = new Owner(data[0],data[1],data[2],data[3],data[4],data[5],data[6]);
		        owners.put(o1.getID(), o1);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		//CREATE BUYER OBJECTS FROM TEXT FILE
			try {
			      File myObj = new File("buyers.txt");
			      Scanner myReader = new Scanner(myObj);
			      while (myReader.hasNextLine()) {
			        String data[] = myReader.nextLine().split("\t");
			        Buyer b = new Buyer(data[1],data[2],data[0],data[3],data[4],data[5],data[6]);
			        buyers.put(data[0], b);
				    }
				    myReader.close();
				    } catch (FileNotFoundException e) {
				      System.out.println("Check Property File!!");
				      e.printStackTrace();
				    }*/
		LoginFrame loginPanel = new LoginFrame();
		loginPanel.main(args);
		
	}
	public static void writeUsers() throws IOException {
		File f = new File("users.txt");
		FileWriter fw = new FileWriter(f, false); // true to append
		                                                     // false to overwrite.
		for (Entry<String,Admin> a: admins.entrySet()) {
			Admin temp = a.getValue();
			fw.write(temp.getID()+"\t"+temp.getUsername()+"\t"+temp.getPassword()+"\t"+temp.getName()+"\t"+temp.getPhone()+"\t"+temp.getEmail()+"\t"+temp.getEmail()+"\n");
		}
		for (Entry<String,Owner> o: owners.entrySet()) {
			Owner temp = o.getValue();
			fw.write(temp.getID()+"\t"+temp.getUsername()+"\t"+temp.getPassword()+"\t"+temp.getName()+"\t"+temp.getPhone()+"\t"+temp.getEmail()+"\t"+temp.getEmail()+"\n");
		}
		for (Entry<String,Buyer> b: buyers.entrySet()) {
			Buyer temp = b.getValue();
			fw.write(temp.getID()+"\t"+temp.getUsername()+"\t"+temp.getPassword()+"\t"+temp.getName()+"\t"+temp.getPhone()+"\t"+temp.getEmail()+"\t"+temp.getEmail()+"\n");
		}
		fw.close();
	}
	public static void writeProperties() throws IOException {
		File f = new File("properties.txt");
		FileWriter fw = new FileWriter(f, false); // true to append
		                                                     // false to overwrite.
		for (Entry<String,Property> p: properties.entrySet()) {
			Property temp = p.getValue();
			fw.write(temp.getID()+"\t"+temp.getOwnerID()+"\t"+temp.getPropType()+"\t"+temp.getSqrMeter()+"\t"+temp.getPrice()+"\t"
			+temp.getBedrooms()+"\t"+temp.getBathrooms()+"\t"+temp.getAge()+"\t"+String.valueOf(temp.isBalcony())+"\t"+String.valueOf(temp.isGarage())
			+"\t"+String.valueOf(temp.isGarden())+"\t"+String.valueOf(temp.isPool())+"\t"+temp.getAddress()+"\t"+temp.getDescription() +"\n");
		}
		fw.close();
	}
	public static void writeSales() throws IOException {
		File f = new File("sales.txt");
		FileWriter fw = new FileWriter(f, false); // true to append
		                                                     // false to overwrite.
		for (Entry<String,Sale> s: sales.entrySet()) {
			Sale temp = s.getValue();
			fw.write(temp.getID()+"\t"+temp.getPropToSale().getID()+"\t"+temp.getSaleToBuyer().getID()+"\t"+temp.getFinalPrice()+"\n");
		}
		fw.close();
	}
}
