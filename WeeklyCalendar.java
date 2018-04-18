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
    public static ArrayList < String > readFromFile(File file) {
        ArrayList < String > fileList = new ArrayList < String > (); //New ArrayList <String>
        try (BufferedReader br = new BufferedReader(new FileReader(file))) { //Try-Catch
            String line;
            while ((line = br.readLine()) != null) {
                fileList.add(line);
            }
        } catch (FileNotFoundException e) { // Catch File Not Found
            e.printStackTrace();
        } catch (IOException e) { // Catch IOExeption
            e.printStackTrace();
        }
        Collections.sort(fileList); //Sort the ArrayList
        return fileList;
    }


    //Method to write to txt file
    //Param <String> = String to be written to txt file
    public static void writeToFile(String s) throws IOException {
        File f = new File("Calendar.txt"); //Create file if not allready created
        FileWriter fw = new FileWriter(f, true); //New FileWriter
        fw.write(s + System.getProperty("line.separator")); //Use FileWriter to wite the param<String> to txt file
        fw.close(); //Close out txt file
    }

    public static void overWriteToFile() throws IOException {
        File f = new File("Calendar.txt"); //Create file if not allready created
        FileWriter fw = new FileWriter(f, false); //New FileWriter
        fw.write(""); //Use FileWriter to wite the param<String> to txt file
        fw.close(); //Close out txt file
    }



    // Method to first check appointment trying to be added if already added return false if not write app. to file and return true
    //Param 1 <String[]> = This is the current StringArray of entire txt file (Uses readFromFile Method to populate)
    //Param 2 <String> = The day to check/write
    //Param 3 <String> = The time to check/write
    public static boolean addAppointment(ArrayList < String > appointmentList, String specifiedDay, String specifiedTime) {

        String queuedAddApointment = specifiedDay + "@" + specifiedTime;

        for (int i = 0; i < appointmentList.size(); i++) {
            String queuedApointmentFromTxt = appointmentList.get(i);
            if (queuedAddApointment.equalsIgnoreCase(queuedApointmentFromTxt)) {
                return false;
            }
        }
        try {
            writeToFile(queuedAddApointment);
            return true;
        } catch (IOException e) { //Handles IoException
            System.out.println("ERROR: Could not Write to File");
            return false;
        }

    }

    // Method to first check appointment trying to be deleted if not found return false if not delete app. from file and return true
    public static boolean removeAppoingtment(ArrayList < String > appointmentList, String specifiedDay, String specifiedTime) {
        String queuedRemoveApointment = specifiedDay + "@" + specifiedTime;
        boolean removeAppoingtment = false;

        for (int i = 0; i < appointmentList.size(); i++) {
            String queuedApointmentFromTxt = appointmentList.get(i);
            if (queuedApointmentFromTxt.equalsIgnoreCase(queuedRemoveApointment)) {
                appointmentList.remove(i);
                removeAppoingtment = true;
            }
        }
        try {
            overWriteToFile();
        } catch (IOException e1) {
            System.out.println("ERROR: OverWritingFile");
            e1.printStackTrace();
        }
        for (int x = 0; x < appointmentList.size(); x++) {
            String tempListWrtie = appointmentList.get(x);
            try {
                writeToFile(tempListWrtie);
            } catch (IOException e) {
                System.out.println("ERROR: ReWriteingFile");
                e.printStackTrace();
            }
        }

        return removeAppoingtment;
    }

    //Method to check for a specific appointment
    //Param 1 <String[]> = Array of all appointments from text file
    //Param 2 <String> = Specified day
    //Param 3 <String> = Specified time
    public static boolean hasAppointmentAt(ArrayList < String > appointmentList, String specifiedDay, String specifiedTime) {
        String HasApointmentAt = specifiedDay + "@" + specifiedTime;
        for (int i = 0; i < appointmentList.size(); i++) {
            String queuedHasApointmentAt = appointmentList.get(i);
            if (HasApointmentAt.equalsIgnoreCase(queuedHasApointmentAt)) {
                return true;
            }
        }
        return false;
    }

    //Method to check for all appointments on a specific day
    //Param 1 <String[]> = Array of all appointments from text file
    //Param 2 <String> = Specified day
    //Return <String[]> = Returns string array of all times from the specified day
    public static ArrayList < String > appointmentsOnDay(ArrayList < String > appointmentList, String specifiedDay) {
        ArrayList < String > listTimes = new ArrayList < String > ();
        for (int i = 0; i < appointmentList.size(); i++) {
            String str = appointmentList.get(i);
            String day = str.substring(0, str.indexOf('@'));
            String time = str.substring(str.indexOf('@') + 1);
            if (day.equalsIgnoreCase(specifiedDay)) {
                listTimes.add(time);
            }
        }
        return listTimes;
    }

    public static void appointmentList() {
        File f = new File("Calendar.txt");
        ArrayList < String > monday = appointmentsOnDay(readFromFile(f), "monday");
        ArrayList < String > tuesday = appointmentsOnDay(readFromFile(f), "tuesday");
        ArrayList < String > wednesday = appointmentsOnDay(readFromFile(f), "wednesday");
        ArrayList < String > thursday = appointmentsOnDay(readFromFile(f), "thursday");
        ArrayList < String > friday = appointmentsOnDay(readFromFile(f), "friday");
        ArrayList < String > saturday = appointmentsOnDay(readFromFile(f), "saturday");
        ArrayList < String > sunday = appointmentsOnDay(readFromFile(f), "sunday");

        System.out.print("Monday: ");
        for (int i = 0; i < monday.size(); i++)
            System.out.print(monday.get(i) + " ");
        System.out.println();
        System.out.print("Tuesday: ");
        for (int i = 0; i < tuesday.size(); i++)
            System.out.print(tuesday.get(i) + " ");
        System.out.println();
        System.out.print("Wednesday: ");
        for (int i = 0; i < wednesday.size(); i++)
            System.out.print(wednesday.get(i) + " ");
        System.out.println();
        System.out.print("Thursday: ");
        for (int i = 0; i < thursday.size(); i++)
            System.out.print(monday.get(i) + " ");
        System.out.println();
        System.out.print("Friday: ");
        for (int i = 0; i < friday.size(); i++)
            System.out.print(friday.get(i) + " ");
        System.out.println();
        System.out.print("Saturday: ");
        for (int i = 0; i < saturday.size(); i++)
            System.out.print(saturday.get(i) + " ");
        System.out.println();
        System.out.print("Sunday: ");
        for (int i = 0; i < sunday.size(); i++)
            System.out.print(sunday.get(i) + " ");
    }

    //Main Method
    //Thro9ws IOException
    public static void main(String[] args) throws IOException {
        File f = new File("Calendar.txt");
        if (!f.exists()) {
            PrintWriter writer = new PrintWriter("Calendar.txt");
        }
        ArrayList < String > readFile = readFromFile(f);
        boolean runProgram = true;
        String[] daysCheck = {
            "monday",
            "tuesday",
            "wednesday",
            "thursday",
            "friday",
            "saturday",
            "sunday"
        };
        String[] timeCheck = {
            "1AM",
            "2AM",
            "3AM",
            "4AM",
            "5AM",
            "6AM",
            "7AM",
            "8AM",
            "9AM",
            "10AM",
            "11AM",
            "12AM",
            "1PM",
            "2PM",
            "3PM",
            "4PM",
            "5PM",
            "6PM",
            "7PM",
            "8PM",
            "9PM",
            "10PM",
            "11PM",
            "12PM"
        };

        System.out.println("                       Welcom to the Calender App");

        while (runProgram == true) {
            System.out.println();
            System.out.println();
            System.out.println("_______________________________________________________________________________________________________________________________________________________________");
            System.out.println("Would you like to “add”, “remove”, “printDay”, “printAll”, “checkTime”, or “quit”");
            System.out.println("           Please enter your choice as seen above");
            Scanner scan = new Scanner(System.in);
            String myLine = scan.nextLine();
            System.out.println("_______________________________________________________________________________________________________________________________________________________________");
            ///////////////////////////////////////////////////////////////////ADD///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if (myLine.equalsIgnoreCase("add")) {
                String dayInput;
                String timeInput;
                boolean validDay = true;
                boolean validTime = true;
                while (validDay == true) {
                    System.out.println("What day would you like to set an appointment for?");
                    System.out.println("_______________________________________________________________________________________________________________________________________________________________");
                    Scanner addScan = new Scanner(System.in);
                    String addScanInput = scan.nextLine();
                    if (addScanInput.equalsIgnoreCase(daysCheck[0]) || addScanInput.equalsIgnoreCase(daysCheck[1]) || addScanInput.equalsIgnoreCase(daysCheck[2]) ||
                        addScanInput.equalsIgnoreCase(daysCheck[3]) || addScanInput.equalsIgnoreCase(daysCheck[4]) || addScanInput.equalsIgnoreCase(daysCheck[5]) || addScanInput.equalsIgnoreCase(daysCheck[6])) {
                        dayInput = addScanInput;
                        validDay = false;
                        while (validTime == true) {
                            System.out.println("What time would you like to set an appointment for? Please use the format (Time)AM or (Time)AM examples. '3AM' or '11PM' ");
                            System.out.println("_______________________________________________________________________________________________________________________________________________________________");
                            Scanner addScanTime = new Scanner(System.in);
                            String addScanInputTime = scan.nextLine();
                            if (addScanInputTime.equalsIgnoreCase(timeCheck[1]) || addScanInputTime.equalsIgnoreCase(timeCheck[2]) || addScanInputTime.equalsIgnoreCase(timeCheck[3]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[4]) || addScanInputTime.equalsIgnoreCase(timeCheck[5]) || addScanInputTime.equalsIgnoreCase(timeCheck[6]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[7]) || addScanInputTime.equalsIgnoreCase(timeCheck[8]) || addScanInputTime.equalsIgnoreCase(timeCheck[9]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[10]) || addScanInputTime.equalsIgnoreCase(timeCheck[11]) || addScanInputTime.equalsIgnoreCase(timeCheck[12]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[13]) || addScanInputTime.equalsIgnoreCase(timeCheck[14]) || addScanInputTime.equalsIgnoreCase(timeCheck[15]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[16]) || addScanInputTime.equalsIgnoreCase(timeCheck[17]) || addScanInputTime.equalsIgnoreCase(timeCheck[18]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[19]) || addScanInputTime.equalsIgnoreCase(timeCheck[20]) || addScanInputTime.equalsIgnoreCase(timeCheck[21]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[22]) || addScanInputTime.equalsIgnoreCase(timeCheck[23]) || addScanInputTime.equalsIgnoreCase(timeCheck[0])) {
                                validTime = false;
                                timeInput = addScanInputTime;
                                if (addAppointment(readFromFile(f), dayInput, timeInput) == true) {
                                    System.out.println("_______________________________________________________________________________________________________________________________________________________________");
                                    System.out.println("Appointment added to calender sucsessfully");
                                } else {
                                    System.out.println("_______________________________________________________________________________________________________________________________________________________________");
                                    System.out.println("Appointment was not added to calender (Mabye you have an apointment allready at that time)");
                                }
                            } else {
                                System.out.println("Invalid Time");
                                validTime = true;
                            }
                        }
                    } else {
                        System.out.println("Invalid Day");
                        validDay = true;
                    }
                }
            }
            ///////////////////////////////////////////////////////////////////Remove/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (myLine.equalsIgnoreCase("remove")) {
                String dayInput;
                String timeInput;
                boolean validDay = true;
                boolean validTime = true;

                while (validDay == true) {
                    System.out.println("What is the day of the appointment you wish to delete?");
                    System.out.println("_______________________________________________________________________________________________________________________________________________________________");
                    Scanner addScan = new Scanner(System.in);
                    String addScanInput = scan.nextLine();
                    if (addScanInput.equalsIgnoreCase(daysCheck[0]) || addScanInput.equalsIgnoreCase(daysCheck[1]) || addScanInput.equalsIgnoreCase(daysCheck[2]) ||
                        addScanInput.equalsIgnoreCase(daysCheck[3]) || addScanInput.equalsIgnoreCase(daysCheck[4]) || addScanInput.equalsIgnoreCase(daysCheck[5]) || addScanInput.equalsIgnoreCase(daysCheck[6])) {
                        dayInput = addScanInput;
                        validDay = false;
                        while (validTime == true) {
                            System.out.println("What is the time of the appointment you wish to deleter? Please use the format (Time)AM or (Time)AM examples. '3AM' or '11PM' ");
                            System.out.println("_______________________________________________________________________________________________________________________________________________________________");
                            Scanner addScanTime = new Scanner(System.in);
                            String addScanInputTime = scan.nextLine();
                            if (addScanInputTime.equalsIgnoreCase(timeCheck[1]) || addScanInputTime.equalsIgnoreCase(timeCheck[2]) || addScanInputTime.equalsIgnoreCase(timeCheck[3]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[4]) || addScanInputTime.equalsIgnoreCase(timeCheck[5]) || addScanInputTime.equalsIgnoreCase(timeCheck[6]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[7]) || addScanInputTime.equalsIgnoreCase(timeCheck[8]) || addScanInputTime.equalsIgnoreCase(timeCheck[9]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[10]) || addScanInputTime.equalsIgnoreCase(timeCheck[11]) || addScanInputTime.equalsIgnoreCase(timeCheck[12]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[13]) || addScanInputTime.equalsIgnoreCase(timeCheck[14]) || addScanInputTime.equalsIgnoreCase(timeCheck[15]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[16]) || addScanInputTime.equalsIgnoreCase(timeCheck[17]) || addScanInputTime.equalsIgnoreCase(timeCheck[18]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[19]) || addScanInputTime.equalsIgnoreCase(timeCheck[20]) || addScanInputTime.equalsIgnoreCase(timeCheck[21]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[22]) || addScanInputTime.equalsIgnoreCase(timeCheck[23]) || addScanInputTime.equalsIgnoreCase(timeCheck[0])) {
                                validTime = false;
                                timeInput = addScanInputTime;
                                if (removeAppoingtment(readFromFile(f), dayInput, timeInput) == true) {
                                    System.out.println("_______________________________________________________________________________________________________________________________________________________________");
                                    System.out.println("Appointment deleted from calender sucsessfully");
                                } else {
                                    System.out.println("_______________________________________________________________________________________________________________________________________________________________");
                                    System.out.println("Appointment was not able to be deleted from calender (Mabye you do not have an appointment at that time)");
                                }
                            } else {
                                System.out.println("Invalid Time");
                                validTime = true;
                            }
                        }
                    } else {
                        System.out.println("Invalid Day");
                        validDay = true;
                    }
                }
            }
            ///////////////////////////////////////////////////////////////////Print Day//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (myLine.equalsIgnoreCase("printday")) {
                String dayInput;
                boolean validDay = true;

                while (validDay == true) {
                    System.out.println("What day of the week would you like to check appointments for?");
                    System.out.println("_______________________________________________________________________________________________________________________________________________________________");
                    Scanner addScan = new Scanner(System.in);
                    String addScanInput = scan.nextLine();
                    if (addScanInput.equalsIgnoreCase(daysCheck[0]) || addScanInput.equalsIgnoreCase(daysCheck[1]) || addScanInput.equalsIgnoreCase(daysCheck[2]) ||
                        addScanInput.equalsIgnoreCase(daysCheck[3]) || addScanInput.equalsIgnoreCase(daysCheck[4]) || addScanInput.equalsIgnoreCase(daysCheck[5]) || addScanInput.equalsIgnoreCase(daysCheck[6])) {
                        dayInput = addScanInput;
                        ArrayList < String > appDayCheck = appointmentsOnDay(readFromFile(f), dayInput);
                        System.out.println("_______________________________________________________________________________________________________________________________________________________________");
                        System.out.print(dayInput + ": ");
                        for (int i = 0; i < appDayCheck.size(); i++) {
                            System.out.print(appDayCheck.get(i) + " ");
                        }
                        validDay = false;

                    } else {
                        System.out.println("Invalid Day");
                        validDay = true;
                    }
                }
            }
            ///////////////////////////////////////////////////////////////////Print All//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (myLine.equalsIgnoreCase("printall")) {
                appointmentList();
            }
            ///////////////////////////////////////////////////////////////////Check Time///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (myLine.equalsIgnoreCase("checktime")) {
                String dayInput;
                String timeInput;
                boolean validDay = true;
                boolean validTime = true;

                while (validDay == true) {
                    System.out.println("What is the day you wish to check?");
                    System.out.println("_______________________________________________________________________________________________________________________________________________________________");
                    Scanner addScan = new Scanner(System.in);
                    String addScanInput = scan.nextLine();
                    if (addScanInput.equalsIgnoreCase(daysCheck[0]) || addScanInput.equalsIgnoreCase(daysCheck[1]) || addScanInput.equalsIgnoreCase(daysCheck[2]) ||
                        addScanInput.equalsIgnoreCase(daysCheck[3]) || addScanInput.equalsIgnoreCase(daysCheck[4]) || addScanInput.equalsIgnoreCase(daysCheck[5]) || addScanInput.equalsIgnoreCase(daysCheck[6])) {
                        dayInput = addScanInput;
                        validDay = false;
                        while (validTime == true) {
                            System.out.println("What is the time you wish to check on " + dayInput + "? Please use the format (Time)AM or (Time)AM examples. '3AM' or '11PM' ");
                            System.out.println("_______________________________________________________________________________________________________________________________________________________________");
                            Scanner addScanTime = new Scanner(System.in);
                            String addScanInputTime = scan.nextLine();
                            if (addScanInputTime.equalsIgnoreCase(timeCheck[1]) || addScanInputTime.equalsIgnoreCase(timeCheck[2]) || addScanInputTime.equalsIgnoreCase(timeCheck[3]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[4]) || addScanInputTime.equalsIgnoreCase(timeCheck[5]) || addScanInputTime.equalsIgnoreCase(timeCheck[6]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[7]) || addScanInputTime.equalsIgnoreCase(timeCheck[8]) || addScanInputTime.equalsIgnoreCase(timeCheck[9]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[10]) || addScanInputTime.equalsIgnoreCase(timeCheck[11]) || addScanInputTime.equalsIgnoreCase(timeCheck[12]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[13]) || addScanInputTime.equalsIgnoreCase(timeCheck[14]) || addScanInputTime.equalsIgnoreCase(timeCheck[15]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[16]) || addScanInputTime.equalsIgnoreCase(timeCheck[17]) || addScanInputTime.equalsIgnoreCase(timeCheck[18]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[19]) || addScanInputTime.equalsIgnoreCase(timeCheck[20]) || addScanInputTime.equalsIgnoreCase(timeCheck[21]) ||
                                addScanInputTime.equalsIgnoreCase(timeCheck[22]) || addScanInputTime.equalsIgnoreCase(timeCheck[23]) || addScanInputTime.equalsIgnoreCase(timeCheck[0])) {
                                validTime = false;
                                timeInput = addScanInputTime;
                                if (hasAppointmentAt(readFromFile(f), dayInput, timeInput) == true) {
                                    System.out.println("_______________________________________________________________________________________________________________________________________________________________");
                                    System.out.println("You have an appointment at " + timeInput + " on " + dayInput);
                                } else {
                                    System.out.println("_______________________________________________________________________________________________________________________________________________________________");
                                    System.out.println("You do not have an appointment at " + timeInput + " on " + dayInput);
                                }
                            } else {
                                System.out.println("Invalid Time");
                                validTime = true;
                            }
                        }
                    } else {
                        System.out.println("Invalid Day");
                        validDay = true;
                    }
                }
            }
            ///////////////////////////////////////////////////////////////////Quit///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (myLine.equalsIgnoreCase("quit")) {
                System.out.println("Quitting application");
                runProgram = false;
            }
            ///////////////////////////////////////////////////////////////////Invalid Selection//////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else {
                System.out.println("That is an invalid option");
            }


        }

    }
}