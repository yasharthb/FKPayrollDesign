package EPMS;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat; 

public class DBConnect{

  Connection cn;
  PreparedStatement ps,ps1;
  ResultSet rs,rs1;       

  public DBConnect() {
    try{
           Class.forName("com.mysql.jdbc.Driver");
           cn= DriverManager.getConnection ("jdbc:mysql://localhost:3306/EMPS?useSSL=false", "yasharth", "yasharth@sql");
           System.out.println("\n Database Connected..\n");
        }catch(Exception e) {
           System.out.println("\n Database not Connected..\n" + e);
        }
  }

  public Employee getEmployeeByID(int emp_id){
         Employee emp =null;
    try {

                     ps = cn.prepareStatement("select * from employee where emp_id=?");
                     ps.setInt(1,emp_id);
                     rs = ps.executeQuery();
                     rs.next();
                     emp = new Employee(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),
                                        rs.getString(5),rs.getString(6),rs.getString(7),Integer.parseInt(rs.getString(8)),
                                        rs.getString(9),Double.parseDouble(rs.getString(10)),Double.parseDouble(rs.getString(11)));
            }catch(Exception e) {
                     System.out.print("\n Unable to find Employee..\n" + e);
            }
      return emp;
  }

public FixedEmployee getFixedEmployeeByID(int emp_id){
         FixedEmployee emp =null;
    try {

                     ps = cn.prepareStatement("select * from employee where emp_id=?");
                     ps.setInt(1,emp_id);
                     rs = ps.executeQuery();
                     rs.next();
                     
                     ps = cn.prepareStatement("select * from fixed_employee where emp_id=?");
                     ps.setInt(1,emp_id);
                     rs1 = ps.executeQuery();
                     rs1.next();
                     

                     emp = new FixedEmployee(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),
                                        rs.getString(5),rs.getString(6),rs.getString(7),Integer.parseInt(rs.getString(8)),
                                        rs.getString(9),Double.parseDouble(rs.getString(10)),Double.parseDouble(rs.getString(11)),Double.parseDouble(rs1.getString(2)),
                                        Double.parseDouble(rs1.getString(3)));
            }catch(Exception e) {
                     System.out.print("\n Unable to find Fixed Employee..\n" + e);
            }
      return emp;
  }


  public DailyEmployee getDailyEmployeeByID(int emp_id){
         DailyEmployee emp =null;
    try {

                     ps = cn.prepareStatement("select * from employee where emp_id=?");
                     ps.setInt(1,emp_id);
                     rs = ps.executeQuery();
                     rs.next();
                     
                     ps = cn.prepareStatement("select * from daily_employee where emp_id=?");
                     ps.setInt(1,emp_id);
                     rs1 = ps.executeQuery();
                     rs1.next();
                     

                     emp = new DailyEmployee(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),
                                        rs.getString(5),rs.getString(6),rs.getString(7),Integer.parseInt(rs.getString(8)),
                                        rs.getString(9),Double.parseDouble(rs.getString(10)),Double.parseDouble(rs.getString(11)),Double.parseDouble(rs1.getString(2)));
            }catch(Exception e) {
                     System.out.print("\n Unable to find Daily Employee..\n" + e);
            }
      return emp;
  }

  public void insert(DailyEmployee emp){//String id, String name, String addr, String dob, String cell, double salary) {
            try {
                     ps = cn.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?,?,?,?)");
                     ps.setInt(1, emp.emp_id);
                     ps.setString(2, emp.emp_name);
                     ps.setString(3, emp.dob);
                     ps.setString(4, emp.email);
                     ps.setString(5, emp.type);
                     ps.setString(6, emp.pay_till);
                     ps.setString(7, emp.payment_mode);
                     ps.setInt(8, emp.union_member);
                     ps.setString(9, emp.paid_date);
                     ps.setDouble(10, emp.amount_payable);
                     ps.setDouble(11,emp.deduction);
                     ps.executeUpdate();

                     ps = cn.prepareStatement("select * from employee where email=?");
                     ps.setString(1,emp.email);
                     rs = ps.executeQuery();
                     rs.next();
                     emp.emp_id = Integer.parseInt(rs.getString(1));

                     ps = cn.prepareStatement("insert into daily_employee values(?,?)");
                     ps.setInt(1, emp.emp_id);
                     ps.setDouble(2, emp.hrly_rate);
                     ps.executeUpdate();

                     if(emp.union_member==1){
                       ps = cn.prepareStatement("insert into union_employee values(?,?,?)");
                       ps.setInt(1, emp.emp_id);
                       ps.setDouble(2, 0.0);
                       ps.setDouble(3,0.0);
                       ps.executeUpdate();
                     }                      

                     System.out.println("\n New Daily Employee Added...\n");
            }catch(Exception e) {
                     System.out.print("\n Unable to add Employee..\n" + e);
            }
      }

  public void insert(FixedEmployee emp){//String id, String name, String addr, String dob, String cell, double salary) {
            try {
                     ps = cn.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?,?,?,?)");
                     ps.setInt(1, emp.emp_id);
                     ps.setString(2, emp.emp_name);
                     ps.setString(3, emp.dob);
                     ps.setString(4, emp.email);
                     ps.setString(5, emp.type);
                     ps.setString(6, emp.pay_till);
                     ps.setString(7, emp.payment_mode);
                     ps.setInt(8, emp.union_member);
                     ps.setString(9, emp.paid_date);
                     ps.setDouble(10, emp.amount_payable);
                     ps.setDouble(11,emp.deduction);
                     ps.executeUpdate();

                     ps = cn.prepareStatement("select * from employee where email=?");
                     ps.setString(1,emp.email);
                     rs = ps.executeQuery();
                     rs.next();
                     emp.emp_id = Integer.parseInt(rs.getString(1));

                     ps = cn.prepareStatement("insert into fixed_employee values(?,?,?)");
                     ps.setInt(1, emp.emp_id);
                     ps.setDouble(2, emp.salary);
                     ps.setDouble(3, emp.commission_rate);
                     ps.executeUpdate();

                     if(emp.union_member==1){
                       ps = cn.prepareStatement("insert into union_employee values(?,?,?)");
                       ps.setInt(1, emp.emp_id);
                       ps.setDouble(2, 0.0);
                       ps.setDouble(3,0.0);
                       ps.executeUpdate();
                     }

                     System.out.println("\n New Fixed Employee added...\n");
            }catch(Exception e) {
                     System.out.print("\n Unable to add Employee..\n" + e);
            }
      }

  public void updateX(DailyEmployee emp1){//String id, String name, String addr, String dob, String cell, double salary) {
            try {    
                     Employee emp2 = getEmployeeByID(emp1.emp_id);
                     ps = cn.prepareStatement("select * from daily_employee where emp_id=?");
                     ps.setInt(1, emp1.emp_id);
                     rs = ps.executeQuery();
                     rs.next();
                     
                     ps = cn.prepareStatement("update employee set emp_name=?, dob=?, email=?, pay_till=?, payment_mode=?, union_member=? WHERE emp_id=?");
                     if(!emp1.emp_name.equals(""))
                      ps.setString(1, emp1.emp_name);
                     else
                      ps.setString(1, emp2.emp_name);

                     if(!emp1.dob.equals(""))
                      ps.setString(2, emp1.dob);
                     else
                      ps.setString(2, emp2.dob);

                     if(!emp1.email.equals(""))
                      ps.setString(3, emp1.email);
                    else
                      ps.setString(3, emp2.email);
                    if(!emp1.pay_till.equals(""))
                      ps.setString(4, emp1.pay_till);
                    else
                      ps.setString(4, emp2.pay_till);
                    if(!emp1.payment_mode.equals(""))
                      ps.setString(5, emp1.payment_mode);
                    else
                      ps.setString(5, emp2.payment_mode);
                    if(!(emp1.union_member==-1))
                      ps.setInt(6, emp1.union_member);
                    else
                      ps.setInt(6, emp2.union_member); 
                    ps.setInt(7, emp1.emp_id);
                    ps.executeUpdate();


                     ps = cn.prepareStatement("update daily_employee set hrly_rate=? WHERE emp_id=?");
                     if(!(emp1.hrly_rate==0.0))
                      ps.setDouble(1, emp1.hrly_rate);
                    else
                      ps.setDouble(1, Double.parseDouble(rs.getString(2)));
                     ps.setInt(2, emp1.emp_id);
                     ps.executeUpdate();

                     System.out.println("\nRecorded Update...\n");
            }catch(Exception e) {
                     System.out.print("\n Unable to Update..\n" + e);
            }
    }

    public void updateX(FixedEmployee emp1){//String id, String name, String addr, String dob, String cell, double salary) {
            try {    
                     Employee emp2 = getEmployeeByID(emp1.emp_id);
                     ps = cn.prepareStatement("select * from fixed_employee where emp_id=?");
                     ps.setInt(1, emp1.emp_id);
                     rs = ps.executeQuery();
                     rs.next();
                     
                     ps = cn.prepareStatement("update employee set emp_name=?, dob=?, email=?, pay_till=?, payment_mode=?, union_member=? WHERE emp_id=?");
                     if(!emp1.emp_name.equals(""))
                      ps.setString(1, emp1.emp_name);
                     else
                      ps.setString(1, emp2.emp_name);

                     if(!emp1.dob.equals(""))
                      ps.setString(2, emp1.dob);
                     else
                      ps.setString(2, emp2.dob);

                     if(!emp1.email.equals(""))
                      ps.setString(3, emp1.email);
                    else
                      ps.setString(3, emp2.email);
                    if(!emp1.pay_till.equals(""))
                      ps.setString(4, emp1.pay_till);
                    else
                      ps.setString(4, emp2.pay_till);
                    if(!emp1.payment_mode.equals(""))
                      ps.setString(5, emp1.payment_mode);
                    else
                      ps.setString(5, emp2.payment_mode);
                    if(!(emp1.union_member==-1))
                      ps.setInt(6, emp1.union_member);
                    else
                      ps.setInt(6, emp2.union_member); 
                    ps.setInt(7, emp1.emp_id);
                    ps.executeUpdate();


                     ps = cn.prepareStatement("update fixed_employee set salary=?, commision_rate=? WHERE emp_id=?");
                     if(!(emp1.salary==0.0))
                      ps.setDouble(1, emp1.salary);
                    else
                      ps.setDouble(1, Double.parseDouble(rs.getString(2)));
                    if(!(emp1.commission_rate==0.0))
                      ps.setDouble(2, emp1.commission_rate);
                    else
                      ps.setDouble(2, Double.parseDouble(rs.getString(3)));
                     ps.setInt(3, emp1.emp_id);
                     ps.executeUpdate();

                     System.out.println("\nRecorded Update...\n");
            }catch(Exception e) {
                     System.out.print("\n Unable to Update..\n" + e);
            }
    }


  public void display() {
            try {
                     ps = cn.prepareStatement("select * from employee");
                     rs = ps.executeQuery();
                               System.out.println("  ID    |  NAME      |  DOB    |  EMAIL    | TYPE    |  "+
                                  "EMPLOYED TILL   | PAYEMENT MODE  | UNION MEMBER   | LAST PAID   |  AMOUNT PAYABLE  | DEDUCTIONS");
                               System.out.println("--------------------------------------------------------------------------");
                     while(rs.next()) {
                               System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)
                                        +"\t"+rs.getString(5)+"\t"+rs.getString(6)+"\t"+rs.getString(7)+"\t"+rs.getString(8)+"\t"+
                                        rs.getString(9)+"\t"+rs.getString(10)+"\t"+rs.getString(11));
                     }        //       end of while
            }catch(Exception e) {
                  System.out.println("\n Unable to Display.. \n"+e);
            }

        }

public void displayPayRoll() {
            try {
                     ps = cn.prepareStatement("select * from payroll");
                     rs = ps.executeQuery();
                               System.out.println("  TRANSACTION ID  |  ID  |  PAYMENT DATE  |  AMOUNT PAYABLE   |  DEDUCTIONS  | AMOUNT PAID | PAYEMENT MODE ");
                               System.out.println("--------------------------------------------------------------------------");
                     while(rs.next()) {
                               System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)
                                        +"\t"+rs.getString(5)+"\t"+rs.getString(6)+"\t"+rs.getString(7));
                     }        //       end of while
            }catch(Exception e) {
                  System.out.println("\n Unable to Display PayRoll.. \n"+e);
            }

        }



  public void deleteX(Employee emp) {
            try {    
                     if((emp.type).equals("daily")){
                       ps = cn.prepareStatement("delete from daily_employee where emp_id=?");
                       ps1= cn.prepareStatement("delete from daily_log where emp_id=?");
                     }
                     else{
                       ps = cn.prepareStatement("delete from fixed_employee where emp_id=?");
                       ps1= cn.prepareStatement("delete from sales_log where emp_id=?");
                     }
                     
                     ps.setInt(1, emp.emp_id);
                     ps1.setInt(1, emp.emp_id);
                     ps.executeUpdate();
                     ps1.executeUpdate();
                     
                     if(emp.union_member==1)
                     {
                        ps = cn.prepareStatement("delete from union_employee where emp_id=?");
                        ps.setInt(1,emp.emp_id);
                        ps.executeUpdate();

                     }
                     ps = cn.prepareStatement("delete from payroll where emp_id=?");
                     ps.setInt(1, emp.emp_id);
                     ps.executeUpdate();

                     ps = cn.prepareStatement("delete from employee where emp_id=?");
                     ps.setInt(1, emp.emp_id);
                     ps.executeUpdate();

                     System.out.println("\nAll Records for "+emp.emp_name+" Deleted..\n");
            }catch(Exception e){ 
                     System.out.println("\n Unable to Delete..\n"+e);
            }
        } 

  public void postTimeCard(DailyEmployee emp,String dow,int hrs_clocked){
    try {
                     if(hrs_clocked<=0){
                      System.out.println("\nYou need to work to get Paid.Retry...\n");
                      return;
                      }

                     ps = cn.prepareStatement("insert into daily_log values(?,?,?,?)");
                     ps.setInt(1,0);
                     ps.setInt(2, emp.emp_id);
                     ps.setString(3, dow);
                     ps.setInt(4, hrs_clocked);
                     ps.executeUpdate();
                     
                     double wage = emp.calcPayment(dow,hrs_clocked);
                     ps = cn.prepareStatement("update employee set amount_payable=? WHERE emp_id=?");
                     ps.setDouble(1,emp.amount_payable);
                     ps.setInt(2,emp.emp_id);
                     ps.executeUpdate();

                     System.out.println("\n Sales Receipt dated "+dow+" added succesfully for "+emp.emp_name+"...Today's wage[\u20B9] :"+wage+"\n");
                    }catch(Exception e) {
                     System.out.print("\n Unable to add Time Card\n" + e);
            }


  }


  public void postSalesReceipt(FixedEmployee emp, String dow, double amount_sale){
            try {
                    if(amount_sale<=0.0){
                      System.out.println("\nYou need to work to get Paid.Retry...\n");
                      return;
                      }
                    
                     ps = cn.prepareStatement("insert into sales_log values(?,?,?,?)");
                     ps.setInt(1,0);
                     ps.setInt(2, emp.emp_id);
                     ps.setString(3, dow);
                     ps.setDouble(4, amount_sale);
                     ps.executeUpdate();
                     
                     double commission = emp.calcPayment(dow,amount_sale);

                     ps = cn.prepareStatement("update employee set amount_payable=? WHERE emp_id=?");
                     ps.setDouble(1,emp.amount_payable);
                     ps.setInt(2,emp.emp_id);
                     ps.executeUpdate();

                     System.out.println("\n Sales Receipt dated "+dow+" added succesfully for "+emp.emp_name+"...Commission Amount[\u20B9] :"+commission+"\n");
            }catch(Exception e) {
                     System.out.print("\n Unable to add Sales Receipt\n" + e);
            }

  }

  public void updateUnion(Employee emp, double weekly_dues,double membership_fees)
  {  
            try {   ps = cn.prepareStatement("select * from union_employee where emp_id=?");
                     ps.setInt(1, emp.emp_id);
                     rs = ps.executeQuery();
                     rs.next();

                     ps = cn.prepareStatement("update union_employee set union_rate=?, other_fees=? where emp_id=?");
                     if(!(weekly_dues==-1.0))
                      ps.setDouble(1, weekly_dues);
                    else
                      ps.setDouble(1, Double.parseDouble(rs.getString(2)));
                    if(!(membership_fees==-1.0))
                      ps.setDouble(2, membership_fees);
                    else
                      ps.setDouble(2, Double.parseDouble(rs.getString(3)));
                     ps.setInt(3, emp.emp_id);
                     ps.executeUpdate();

                     System.out.println("\nUnion Charges for "+emp.emp_name+" Updated...\n");
            }catch(Exception e) {
                     System.out.print("\n Unable to Update..\n" + e);
            }   
  }

  public void unionDeductions(){
         try {
                     ps = cn.prepareStatement("select * from employee where union_member=1");
                     rs = ps.executeQuery();
                    while(rs.next()) {
                        if(rs.getString(9).equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){continue;}
                         ps = cn.prepareStatement("select * from union_employee where emp_id=?");
                         ps.setInt(1,Integer.parseInt(rs.getString(1)));
                         rs1 = ps.executeQuery();
                         rs1.next();
                         double weekly_dues = Double.parseDouble(rs1.getString(2));
                         double membership_fees = Double.parseDouble(rs1.getString(3));
                         double deduction = weekly_dues+membership_fees;
                         
                         ps = cn.prepareStatement("update employee set deduction=? where emp_id=?");
                         ps.setDouble(1,deduction+Double.parseDouble(rs.getString(11)));
                         ps.setInt(2,Integer.parseInt(rs.getString(1)));
                         ps.executeUpdate();

                         System.out.println("Union deductions for"+rs.getString(2)+" worth \u20B9"+deduction+" levied Successfully");
                     }        //       end of while
            }catch(Exception e) {
                  System.out.println("\n Unable to Display.. \n"+e);
            }

  }

  public void payDailyEmployees(){
       try {         

                     ps = cn.prepareStatement("select * from employee where type=?");
                     ps.setString(1,"daily");
                     rs = ps.executeQuery();
                    while(rs.next()) {
                         if(rs.getString(9).equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){continue;}
                         ps = cn.prepareStatement("insert into payroll values(?,?,?,?,?,?,?)");
                         ps.setInt(1, 0);
                         ps.setInt(2, Integer.parseInt(rs.getString(1)));
                         ps.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                         ps.setDouble(4, Double.parseDouble(rs.getString(10)));
                         ps.setDouble(5, Double.parseDouble(rs.getString(11)));
                         ps.setDouble(6, Double.parseDouble(rs.getString(10))-Double.parseDouble(rs.getString(11)));
                         ps.setString(7, rs.getString(7));
                         ps.executeUpdate();

                         ps = cn.prepareStatement("update employee set paid_date=?, amount_payable=?, deduction=? where emp_id=?");
                         ps.setString(1,new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                         ps.setDouble(2,0.0);
                         ps.setDouble(3,0.0);
                         ps.setInt(4,Integer.parseInt(rs.getString(1)));
                         ps.executeUpdate();
                     }
                      System.out.println("\n Daily Employees Paid Successfully.\n");

            }catch(Exception e) {
                  System.out.println("\n Unable to Display.. \n"+e);
            }

  }

  public void payFixedEmployees(){
       try {         

                     ps = cn.prepareStatement("select * from employee where type=?");
                     ps.setString(1,"fixed");
                     rs = ps.executeQuery();
                    while(rs.next()) {
                         if(rs.getString(9).equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date())))
                          {continue;}
                         ps = cn.prepareStatement("insert into payroll values(?,?,?,?,?,?,?)");
                         ps.setInt(1, 0);
                         ps.setInt(2, Integer.parseInt(rs.getString(1)));
                         ps.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                         ps.setDouble(4, Double.parseDouble(rs.getString(10)));
                         ps.setDouble(5, Double.parseDouble(rs.getString(11)));
                         ps.setDouble(6, Double.parseDouble(rs.getString(10))-Double.parseDouble(rs.getString(11)));
                         ps.setString(7, rs.getString(7));
                         ps.executeUpdate();

                         ps1 = cn.prepareStatement("update employee set paid_date=?, amount_payable=?, deduction=? where emp_id=?");
                         ps1.setString(1,new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                         ps1.setDouble(2,(getFixedEmployeeByID(Integer.parseInt(rs.getString(1)))).salary);
                         ps1.setDouble(3,0.0);
                         ps1.setInt(4,Integer.parseInt(rs.getString(1)));
                         ps1.executeUpdate();
                     }
                      System.out.println("\n Fixed Employees Paid Successfully.\n");

            }catch(Exception e) {
                  System.out.println("\n Unable to Display.. \n"+e);
            }

  }

  public void close()  {   
            try {
                     if(rs!=null)
                      rs.close();
                     if(ps!=null)
                      ps.close();
                     if(cn!=null)
                      cn.close();
            }catch(Exception e) {
              System.out.println("\n Unable to Close..Force Exit\n"+e);
            }
      } 
}
