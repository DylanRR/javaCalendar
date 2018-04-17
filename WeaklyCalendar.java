package pa6;
import java.util.Scanner;
import java.io.*;

public class WeaklyCalendar {
	// Method to read text file and return a string array with all appointments
	public static String[] readFromFile(String file) {
		
		int ctr = 0;
		try {
			Scanner s1 = new Scanner(new File(file));
			while(s1.hasNextLine()) {
				ctr++;
			}
			String[] readFromFileReturn = new String[ctr];
			Scanner s2 = new Scanner(new File(file));
			for (int i = 0; i < ctr; i++) {
				readFromFileReturn[i] = s2.next();
			}
			return readFromFileReturn;
		}
			catch (FileNotFoundException e) {
				System.out.println("ERROR: FileNotFound");
			}
		return null;
				
		}
	
	public static void writeToFile(String s) throws IOException{
		File f = new File("myCalenda.txt");
		FileWriter fw = new FileWriter(f, true);
		fw.write(s);
		fw.close();
	}
	
	
	
	
	// Method to first check appointment trying to be added if already added return false if not write app. to file and return true
	public static boolean addAppointment (String[] appointmentList, String specifiedDay, String specifiedTime ) {
		
		int atPosition;
		atPosition = 0;
		if (!(appointmentList.equals(null))){
		int stringArraySize = appointmentList.length;
		for(int i = stringArraySize; i <= 0; i--) {
			String check =  appointmentList[i];
			char[] checkCharArray = check.toCharArray();
			for(int x=0;x < checkCharArray.length; x++) {
				if (checkCharArray[x] == '@') {
					atPosition = x;
				}
				else {
					System.out.println("Error: TextFileFormating:Missing'@'");
					return false;
				}
			}
			String appointmentListDayCheck = new String(checkCharArray, 0, atPosition-1).toLowerCase();
			String appointmentListTimeCheck = new String(checkCharArray, checkCharArray.length, atPosition+1).toLowerCase();
			
			if (!(appointmentListDayCheck.equals(specifiedDay.toLowerCase()) && appointmentListTimeCheck.equals(specifiedTime.toLowerCase()))) {
				try {
					writeToFile(specifiedDay + "@" + specifiedTime);
					return true;
				}
				catch(IOException e) {
					System.out.println("ERROR: Could not Write to File");
					return false;
				}
			}
		}
		}
		else {
			try {
				writeToFile(specifiedDay + "@" + specifiedTime);
				return true;
			}
			catch(IOException e) {
				System.out.println("ERROR: Could not Write to File");
				return false;
			}
		}
		return false;
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		File f = new File("myCalenda.txt");
		addAppointment(readFromFile("myCalenda.txt"), "Monday", "10AM");
		
		
	}
}
