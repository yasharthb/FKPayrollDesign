package EPMS;
import java.sql.*;
import java.io.*;

class Driver {

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
          Employee emp;
          int emp_id=0,union_member=0;
          String emp_name="",type="",email="",dob="",payment_mode="",pay_till="",paid_date="2020-05-09",tmp="";
          double amount_payable=0.0,salary=0.0,hrly_rate=0.0,commission_rate=0.0;
          while(true) {
          int ch = display();
          switch(ch) {
                   case 1:
                             System.out.print("\n Enter Employee Type [daily/fixed]:");
                             type = getInput();
                             System.out.print(" Employee Name : ");
                             emp_name = getInput();
                             System.out.print(" Email(Unique): ");
                             email = getInput();
                             System.out.print(" DOB (YYYY-MM-DD) : ");
                             dob = getInput();
                             System.out.print(" Payment Mode [mail/pickup/deposit] : ");
                             payment_mode = getInput();
                             System.out.print(" Pay till Date (YYYY-MM-DD) : ");
                             pay_till = getInput();
                             System.out.print(" Union Member?[1/0] :");
                             tmp = getInput();
                             union_member = !tmp.equals("")?Integer.parseInt(tmp): 0;
                             
                             switch(type){
                              case "daily" :
                                            System.out.print(" Hourly Wage Rate[\u20B9] : ");
                                            tmp = getInput();
                                            hrly_rate = !tmp.equals("")? Double.parseDouble(tmp): 0.0;
                                            db.insert(new DailyEmployee(0,emp_name,dob,email,type,pay_till,payment_mode,
                                                      union_member,paid_date,0.0,hrly_rate));
                                            break;
                              case "fixed" :
                                            System.out.print(" Salary[\u20B9] : ");
                                            tmp = getInput();
                                            salary = !tmp.equals("")? Double.parseDouble(tmp): 0.0;
                                            System.out.print(" Commission Rate[%] : ");
                                            tmp = getInput();
                                            commission_rate = !tmp.equals("")? Double.parseDouble(tmp): 0.0;
                                            db.insert(new FixedEmployee(0,emp_name,dob,email,type,pay_till,payment_mode,
                                                      union_member,paid_date,0.0,salary,commission_rate));
                                            break;
                              default :
                                        System.out.print("Bad Type of Choice. Retry...");
                             }
                             
                             break;
                   case 2:
                              System.out.print("\nEnter Employee Id to delete : ");
                              emp_id = Integer.parseInt(getInput());
                              emp = db.getEmployeeByID(emp_id);
                              db.deleteX(emp);

                             break;
                   case 3:
                             System.out.print("\n Enter Employee ID:");
                             emp_id = Integer.parseInt(getInput());
                             System.out.print(" Employee Name : ");
                             emp_name = getInput();
                             System.out.print(" Email(Unique): ");
                             email = getInput();
                             System.out.print(" DOB (YYYY-MM-DD) : ");
                             dob = getInput();
                             System.out.print(" Payment Mode [mail/pickup/deposit] : ");
                             payment_mode = getInput();
                             System.out.print(" Pay till Date (YYYY-MM-DD) : ");
                             pay_till = getInput();
                             System.out.print(" Union Member?[1/0] :");
                             tmp = getInput();
                             union_member = !tmp.equals("")?Integer.parseInt(tmp): -1;
                             emp = db.getEmployeeByID(emp_id);
                             switch(emp.type){
                              case "daily" :
                                            System.out.print(" Hourly Wage Rate[\u20B9] : ");
                                            tmp = getInput();
                                            hrly_rate = !tmp.equals("")? Double.parseDouble(tmp): 0.0;
                                            db.updateX(new DailyEmployee(emp.emp_id,emp_name,dob,email,type,pay_till,payment_mode,
                                                      union_member,paid_date,emp.amount_payable,hrly_rate));
                                            break;
                              case "fixed" :
                                            System.out.print(" Salary[\u20B9] : ");
                                            tmp = getInput();
                                            salary = !tmp.equals("")? Double.parseDouble(tmp): 0.0;
                                            System.out.print(" Commission Rate[%] : ");
                                            tmp = getInput();
                                            commission_rate = !tmp.equals("")? Double.parseDouble(tmp): 0.0;
                                            db.updateX(new FixedEmployee(emp.emp_id,emp_name,dob,email,type,pay_till,payment_mode,
                                                      union_member,paid_date,emp.amount_payable,salary,commission_rate));
                                            break;
                              default :
                                        System.out.print("Bad Type of Choice. Retry...");
                             }
                             
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