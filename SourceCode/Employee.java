package EPapp;
import java.sql.*;
import java.io.*;

class Employee {

static int display() throws IOException {
          System.out.println("\t Employee Database ");
          System.out.println("\t ~~~~~~~~~~~~~~~~~ ");
          System.out.println("\t 1. Add Employee. ");
          System.out.println("\t 2. Delete Employee. ");
          System.out.println("\t 3. Edit Employee. ");
          System.out.println("\t 4. Display Employees. ");
          System.out.println("\t 5. Exit. ");
          System.out.print(" Choose an option : ");
          int ch = Integer.parseInt(getInput());
          return ch;
        }

static String getInput()  {
          String str;
          try {
                   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                   str = br.readLine();
              }catch(Exception e) {
                   return null;}
                   return str;
          }

   public static void main(String args[]) throws IOException {
          System.out.println(System.getProperty("java.class.path"));
          DBConnect db = new DBConnect();
          while(true) {
          int ch = display();
          switch(ch) {
                   case 1:

                             System.out.print("\n Enter Employee Id : ");
                             String id = getInput();
                             System.out.print(" Employee Name : ");
                             String name = getInput();
                             System.out.print(" ADDRESS : ");
                             String addr = getInput();
                             System.out.print(" DOB (YYYY-MM-DD) : ");
                             String dob = getInput();
                             System.out.print(" Phone No. : ");
                             String cell = getInput();
                             System.out.print(" Salary : ");
                             String tmp = getInput();
                             Double salary = !tmp.equals("")? Double.parseDouble(tmp): 0.0;
                             db.insert(id, name, addr, dob, cell, salary);
                             break;
                   case 2:
                     System.out.print("\nEnter Employee Id to delete : ");
                             id = getInput();
                             db.deleteX(id);
                             break;
                   case 3:

                             System.out.print("\n Enter Employee Id : ");
                             id = getInput();
                             System.out.print(" Employee Name : ");
                             name = getInput();
                             System.out.print(" Address : ");
                             addr = getInput();
                             System.out.print(" DOB (YYYY-MM-DD) : ");
                             dob = getInput();
                             System.out.print(" Phone No. : ");
                             cell = getInput();
                             System.out.print(" Salary : ");
                             tmp = getInput();
                             salary = !tmp.equals("")? Double.parseDouble(tmp): 0.0;
                             db.updateX(id, name, addr, dob, cell, salary);
                             break;
                   case 4:

                             db.display();
                             break;
                   case 5:

                             db.close();
                             System.exit(0);
                   } 
          }
        }
     }