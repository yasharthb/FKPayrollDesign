package EPMS;
//import java.sql.*;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat; 

class Driver {

static int display() throws IOException {
          System.out.println("\t Employee PayRoll Management System ");
          System.out.println("\t ~~~~~~~~~~~~~~~~~ ");
          System.out.println("\t 1. Add Employee. ");
          System.out.println("\t 2. Delete Employee. ");
          System.out.println("\t 3. Edit Employee. ");
          System.out.println("\t 4. Display Employees. ");
          System.out.println("\t 5. Post Time Card. ");
          System.out.println("\t 6. Post Sales Receipt. ");
          System.out.println("\t 7. Update Union Charges.");
          System.out.println("\t 8. Pay Employees Today");
          System.out.println("\t 9. Display PayRoll");
          System.out.println("\t 10. Exit. ");
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
          int emp_id=0,union_member=0,hrs_clocked=0;
          String emp_name="",type="",email="",dob="",dow="",payment_mode="",pay_till="",paid_date="",tmp="";
          double amount_payable=0.0,salary=0.0,hrly_rate=0.0,commission_rate=0.0,amount_sale=0.0,weekly_dues=0.0,membership_fee=0.0;
          while(true) {
          dow = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
          paid_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(new Date().getTime() - 24*3600*1000));
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
                                                      union_member,paid_date,0.0,0.0,hrly_rate));
                                            break;
                              case "fixed" :
                                            System.out.print(" Salary[\u20B9] : ");
                                            tmp = getInput();
                                            salary = !tmp.equals("")? Double.parseDouble(tmp): 0.0;
                                            System.out.print(" Commission Rate[%] : ");
                                            tmp = getInput();
                                            commission_rate = !tmp.equals("")? Double.parseDouble(tmp): 0.0;
                                            db.insert(new FixedEmployee(0,emp_name,dob,email,type,pay_till,payment_mode,
                                                      union_member,paid_date,salary,0.0,salary,commission_rate));
                                            break;
                              default :
                                        System.out.print("\nBad Type of Choice. Retry...\n");
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
                             tmp =getInput();
                             if(tmp.equals("")){System.out.println("Enter Valid ID"); break;}
                             emp_id = Integer.parseInt(tmp);
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
                                                      union_member,paid_date,emp.amount_payable,emp.deduction,hrly_rate));
                                            break;
                              case "fixed" :
                                            System.out.print(" Salary[\u20B9] : ");
                                            tmp = getInput();
                                            salary = !tmp.equals("")? Double.parseDouble(tmp): 0.0;
                                            System.out.print(" Commission Rate[%] : ");
                                            tmp = getInput();
                                            commission_rate = !tmp.equals("")? Double.parseDouble(tmp): 0.0;
                                            db.updateX(new FixedEmployee(emp.emp_id,emp_name,dob,email,type,pay_till,payment_mode,
                                                      union_member,paid_date,emp.amount_payable,emp.deduction,salary,commission_rate));
                                            break;
                              default :
                                        System.out.print("\nBad Type of Choice. Retry...\n");
                             }
                             
                             break;
                   case 4:
                           
                             db.display();
                             break;
                   case 5: 
                           System.out.print("\n Enter Employee ID:");
                           tmp =getInput();
                           if(tmp.equals("")){System.out.println("Enter Valid ID"); break;}
                           emp_id = Integer.parseInt(tmp);
                           emp = db.getEmployeeByID(emp_id);
                           if(emp.type.equals("fixed"))
                           {
                            System.out.println("Employee is a Fixed Worker.Retry...");
                            break;
                           }
                           else{

                           System.out.print(" Date of Work (YYYY-MM-DD) : ");
                           tmp =getInput();
                           dow = !tmp.equals("")?tmp:dow;
                           System.out.print(" Hours Clocked [in Hrs] :");
                           tmp = getInput();
                           hrs_clocked = !tmp.equals("")?Integer.parseInt(tmp): -1;
                           db.postTimeCard(db.getDailyEmployeeByID(emp_id),dow,hrs_clocked);
                           }
                           break;
                   case 6:
                           System.out.print("\n Enter Employee ID:");
                           tmp =getInput();
                           if(tmp.equals("")){System.out.println("Enter Valid ID"); break;}
                           emp_id = Integer.parseInt(tmp);
                           emp = db.getEmployeeByID(emp_id);
                           if(emp.type.equals("daily"))
                           {
                            System.out.println("Employee is a Daily Worker.Retry...");
                            break;
                           }
                           else{

                           System.out.print(" Date of Sale (YYYY-MM-DD) : ");
                           dow = getInput();
                           System.out.print(" Amount of Sale [\u20B9] :");
                           tmp = getInput();
                           amount_sale = !tmp.equals("")?Double.parseDouble(tmp): -1.0;
                           db.postSalesReceipt(db.getFixedEmployeeByID(emp_id),dow,amount_sale);
                           }
                           break;
                   case 7:
                          System.out.print("\n Enter Employee ID:");
                          tmp =getInput();
                          if(tmp.equals("")){System.out.println("Enter Valid ID"); break;}
                          emp_id = Integer.parseInt(tmp);
                          emp =db.getEmployeeByID(emp_id);
                          if(emp.union_member==0){System.out.println("\nEmployee not a Union Member...");break;}
                          System.out.print("Weekly dues of Union Members[\u20B9] :");
                          tmp = getInput();
                          weekly_dues = !tmp.equals("")?Double.parseDouble(tmp): -1.0;
                          System.out.print("Membership Fee of Union Members[\u20B9] :");
                          tmp = getInput();
                          membership_fee = !tmp.equals("")?Double.parseDouble(tmp): -1.0;
                          db.updateUnion(emp,weekly_dues,membership_fee);
                          break; 
                   case 8:

                          db.payDailyEmployees();
                          db.payFixedEmployees();
                          break;
                   case 9:
                          db.displayPayRoll();
                          break; 
                   case 10:

                             db.close();
                             System.exit(0);
                   } 
          }
        }
     }