package pa6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.List;
import java.io.*;

public class WeeklyCalendar {
	
	// Method to read text file and return a string array with all appointments
	// Param <File> = File <f> designated in main method
	public static String[] readFromFile(File file) {
		List<String> fileList = new ArrayList<String>(); //New ArrayList <String>
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {  //Try-Catch
		    String line; //New temp String
		    while ((line = br.readLine()) != null) { 
		       fileList.add(line);
		    }
		} catch (FileNotFoundException e) { // Catch File Not Found
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		} catch (IOException e) { // Catch IOExeption
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(fileList); //Sort the ArrayList
		String[] fileStringArray = new String[fileList.size()]; //New StringArray
		for(int i=0;i<fileList.size();i++){	//Loop to take each element from ArrayList and put the element into the StringArray
			fileStringArray[i] = fileList.get(i); //Add element to StringArray
		}
			
		return fileStringArray; //Return the final StringArray
	}
	
	
	
	
	
	//Method to write to txt file
	//Param <String> = String to be written to txt file
	public static void writeToFile(String s) throws IOException{
		File f = new File("C:\\Users\\Mothership\\Desktop\\Calendar\\myCalenda.txt"); //Create file if not allready created
		FileWriter fw = new FileWriter(f, true);	//New FileWriter
		fw.write(s);	//Use FileWriter to wite the param<String> to txt file
		fw.close();	//Close out txt file
	}
	
	
	
	
	// Method to first check appointment trying to be added if already added return false if not write app. to file and return true
	//Param 1 <String[]> = This is the current StringArray of entire txt file (Uses readFromFile Method to populate)
	//Param 2 <String> = The day to check/write
	//Param 3 <String> = The time to check/write
	public static boolean addAppointment (String[] appointmentList, String specifiedDay, String specifiedTime ) {
		
		int atPosition;
		atPosition = 0;
		int stringArraySize = appointmentList.length; //Length of Param 1
		for(int i = stringArraySize; i <= 0; i--) {	//For loop that finds the '@' sign
			String check =  appointmentList[i];	//Temp String to populated by an element from StringArray
			char[] checkCharArray = check.toCharArray();	//Temp CharArray, takes Temp string and converts to CharArray
			for(int x=0;x < checkCharArray.length; x++) {	//For loop to find the '@' sign in char array
				if (checkCharArray[x] == '@') {
					atPosition = x;
				}
				else {	//Else clause to handle '@' sign not being found
					System.out.println("Error: TextFileFormating:Missing'@'");
					return false;
				}
			}
			String appointmentListDayCheck = new String(checkCharArray, 0, atPosition-1).toLowerCase();	//Uses the '@' sign position to create a Day String of all letters before the '@' sign
			String appointmentListTimeCheck = new String(checkCharArray, checkCharArray.length, atPosition+1).toLowerCase(); //Uses the '@' sign position to create a Time String of all letters after the '@' sign
			
			if (!(appointmentListDayCheck.equals(specifiedDay.toLowerCase()) && appointmentListTimeCheck.equals(specifiedTime.toLowerCase()))) { //If day/time do not match it writes day/time in proper format
				try {
					writeToFile(specifiedDay + "@" + specifiedTime);
					return true;
				}
				catch(IOException e) {	//Handles IoException
					System.out.println("ERROR: Could not Write to File");
					return false;
				}
			}
		}
		
		
		return false; //Returns false if above if statement defines the 2 params as equal
	}
	
	
	
	
	
	//Main Method
	//Throws IOException
	public static void main(String[] args) throws IOException {
		File f = new File("C:\\Users\\Mothership\\Desktop\\Calendar\\myCalenda.txt");
	//	writeToFile("Day@Time");
		System.out.println(readFromFile(f));
		
	}
}
