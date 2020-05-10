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
    double deduction;

    Employee(int emp_id, String emp_name, String dob, String email, String type,String pay_till,
     		String payment_mode,int union_member, String paid_date,double amount_payable,double deduction){
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
        this.deduction=deduction;
    }


    // void addEmployee(){

    // }

    // void deleteEmployee(){
    // }

    // void editEmployee(){

    // }

     // void UpdatePayment(){

     // } 
}


class DailyEmployee extends Employee{
       double hrly_rate;
      
    DailyEmployee(int emp_id, String emp_name, String dob, String email, String type, String pay_till,
     		String payment_mode,int union_member, String paid_date,double amount_payable,double deduction,double hrly_rate){
    	super(emp_id,emp_name,dob,email,type,pay_till,payment_mode,union_member,paid_date,amount_payable,deduction);
    	this.hrly_rate=hrly_rate;
    }


    double calcPayment(String dow, int hrs_clocked){
        double wage = 0.0;
        if(hrs_clocked > 8)
            wage = 8.0*this.hrly_rate + 1.5*(hrs_clocked-8)*this.hrly_rate;
        else
            wage = this.hrly_rate*hrs_clocked; 
        this.amount_payable = this.amount_payable + wage;
        return wage;
     }

    // @Override
    // void deleteEmployee(){

    // }

    // @Override
    // void editEmployee(){

    // }

    // @Override
     // void UpdatePayment(){

     // }

}



class FixedEmployee extends Employee{
	double salary;
	double commission_rate;

	FixedEmployee(int emp_id, String emp_name, String dob, String email, String type,String pay_till,
     		String payment_mode,int union_member, String paid_date,double amount_payable,double deduction,double salary,double commission_rate){
    	super(emp_id,emp_name,dob,email,type,pay_till,payment_mode,union_member,paid_date,amount_payable,deduction);
    	this.salary=salary;
    	this.commission_rate=commission_rate;
    }

    double calcPayment(String dow, double amount_sale){
        double commission = 0.0;
        commission = this.commission_rate*amount_sale/100.0; 
        this.amount_payable = this.amount_payable + commission;
        return commission;
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
     // void UpdatePayment(){

     // }


}