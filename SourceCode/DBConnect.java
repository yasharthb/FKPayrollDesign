package EPapp;
import java.sql.*;

public class DBConnect{

  Connection cn;
  PreparedStatement ps;
  ResultSet rs;                                
  public DBConnect() {
    try{
           Class.forName("com.mysql.jdbc.Driver");
           cn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/test?useSSL=false", "yasharth", "yasharth@sql");
           System.out.println("\n Database Connected..\n");
        }catch(Exception e) {
           System.out.println("\n Database not Connected..\n" + e);
        }
  }

  public void insert(String id, String name, String addr, String dob, String cell, double salary) {
            try {
                     ps = cn.prepareStatement("insert into employee values(?,?,?,?,?,?)");
                     ps.setString(1, id);
                     ps.setString(2, name);
                     ps.setString(3, addr);
                     ps.setString(4, dob);
                     ps.setString(5, cell);
                     ps.setDouble(6, salary);
                     ps.executeUpdate();

                     System.out.println("\n 1 Record inserted...\n");
            }catch(Exception e) {
                     System.out.print("\n Unable to insert..\n" + e);
            }
      }
  public void updateX(String id, String name, String addr, String dob, String cell, double salary) {
            try {
                     ps = cn.prepareStatement("select * from employee where emp_id=?");
                     ps.setString(1, id);
                     rs = ps.executeQuery();
                     rs.next();
                     
                     ps = cn.prepareStatement("update employee set emp_name=?, addr=?, dob=?, cell=?, salary=? WHERE emp_id=?");
                     if(!name.equals(""))
                      ps.setString(1, name);
                     else
                      ps.setString(1, rs.getString(2));

                     if(!addr.equals(""))
                      ps.setString(2, addr);
                     else
                      ps.setString(2, rs.getString(3));

                     if(!dob.equals(""))
                      ps.setString(3, dob);
                     else
                      ps.setString(3, rs.getString(4));

                     if(!cell.equals(""))
                      ps.setString(4, cell);
                    else
                      ps.setString(4, rs.getString(5));

                     if(!(salary==0.0))
                      ps.setDouble(5, salary);
                    else
                      ps.setDouble(5, Double.parseDouble(rs.getString(6)));

                     ps.setString(6, id);
                     ps.executeUpdate();

                     System.out.println("\nRecorded Update...\n");
            }catch(Exception e) {
                     System.out.print("\n Unable to Update..\n" + e);
            }
            }        // end of insert
  public void display() {
            try {
                     ps = cn.prepareStatement("select * from employee");
                     rs = ps.executeQuery();
                               System.out.println("  ID    |    NAME        |    ADDRESS   |   DOB     | PHONE     |   SALARY");
                               System.out.println("--------------------------------------------------------------------------");
                     while(rs.next()) {
                               System.out.println( rs.getString(1) + "\t " + rs.getString(2) + "\t " + rs.getString(3) + "\t " + rs.getString(4) + "\t " + rs.getString(5) + "\t " + rs.getString(6));
                     }        //       end of while
            }catch(Exception e) {
                  System.out.println("\n Unable to Display.. \n"+e);
            }

        }                
  public void deleteX(String id) {
            try {
                     ps = cn.prepareStatement("delete from employee where emp_id=?");
                     ps.setString(1, id);
                     ps.executeUpdate();

                     System.out.println("Record Deleted..");
            }catch(Exception e){ 
                     System.out.println("\n Unable to Delete..\n"+e);
            }
        } 
  public void close()  {   
            try {
                     rs.close();
                     ps.close();
                     cn.close();
            }catch(Exception e) {
              System.out.println("\n Unable to Close..Force Exit\n"+e);
            }
      } 
}