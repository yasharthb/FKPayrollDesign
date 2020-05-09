package EPMS;
import java.sql.*;
import java.io.*;

public class Employee{
	int emp_id;
	String emp_name;
	String dob;
	String email;
	String type;
	String payment_mode;
	String pay_till;
	String paid_date;
	int union_member;
	double amount_payable;

    Employee(int emp_id, String emp_name, String dob, String email, String type,String pay_till,
     		String payment_mode,int union_member, String paid_date,double amount_payable){
    	this.emp_id=emp_id;
    	this.emp_name=emp_name;
    	this.dob=dob;
    	this.email=email;
    	this.type=type;
    	this.payment_mode=payment_mode;
    	this.paid_date=paid_date;
    	this.pay_till=pay_till;
    	this.union_member=union_member;
    	this.amount_payable=amount_payable;
    }


    // void addEmployee(){

    // }

    // void deleteEmployee(){
    // }

    // void editEmployee(){

    // }

    // void payEmployee(){

    // } 
}


class DailyEmployee extends Employee{
       double hrly_rate;
      
    DailyEmployee(int emp_id, String emp_name, String dob, String email, String type, String pay_till,
     		String payment_mode,int union_member, String paid_date,double amount_payable,double hrly_rate){
    	super(emp_id,emp_name,dob,email,type,pay_till,payment_mode,union_member,paid_date,amount_payable);
    	this.hrly_rate=hrly_rate;
    }

    // @Override
    // void addEmployee(){

    // }

    // @Override
    // void deleteEmployee(){

    // }

    // @Override
    // void editEmployee(){

    // }

    // @Override
    // void payEmployee(){

    // }

}



class FixedEmployee extends Employee{
	double salary;
	double commission_rate;

	FixedEmployee(int emp_id, String emp_name, String dob, String email, String type,String pay_till,
     		String payment_mode,int union_member, String paid_date,double amount_payable,double salary,double commission_rate){
    	super(emp_id,emp_name,dob,email,type,pay_till,payment_mode,union_member,paid_date,amount_payable);
    	this.salary=salary;
    	this.commission_rate=commission_rate;
    }
    
	// @Override
 //    void addEmployee(){

 //    }

 //    @Override
 //    void deleteEmployee(){

 //    }

 //    @Override
 //    void editEmployee(){

 //    }

 //    @Override
 //    void payEmployee(){

 //    }


}