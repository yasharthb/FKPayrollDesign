# FKPayrollDesign
Maintains code for project on Employee Payroll System Design

## Prerequisites
 * Java JDK 11.0.7
 * MySQL

## Setup MySQL Server on the local server
```bash
  sudo apt update
  sudo apt install mysql-server
  sudo mysql_secure_installation
  sudo mysql
```
In the SQL shell
```sql
    CREATE USER 'yasharth'@'localhost' IDENTIFIED BY 'yasharth@sql';
    GRANT ALL PRIVILEGES ON *.* TO 'yasharth'@'localhost' WITH GRANT OPTION;
    CREATE DATABASE EMPS;
    USE EPMS;

    CREATE TABLE employee (
    emp_id INT AUTO_INCREMENT,
    emp_name VARCHAR(255) NOT NULL,
    dob DATE NOT NULL,
    email VARCHAR(100) NOT NULL,
    type VARCHAR(10) NOT NULL,
    pay_till DATE NOT NULL,
    payment_mode VARCHAR(10) NOT NULL,
    union_member BOOLEAN DEFAULT 0,
    paid_date DATE NOT NULL,
    amount_payable float DEFAULT 0.0,
    PRIMARY KEY (emp_id),
    UNIQUE(email)
);

    CREATE TABLE daily_employee (
    emp_id INT NOT NULL,
    hrly_rate FLOAT NOT NULL,
    PRIMARY KEY (emp_id),
    FOREIGN KEY (emp_id) REFERENCES employee(emp_id)
);

    CREATE TABLE fixed_employee (
    emp_id INT NOT NULL,
    salary FLOAT NOT NULL,
    commision_rate FLOAT NOT NULL,
    PRIMARY KEY (emp_id),
    FOREIGN KEY (emp_id) REFERENCES employee(emp_id)
);

   CREATE TABLE daily_log (
   	card_id INT AUTO_INCREMENT,
    emp_id INT NOT NULL,
    date_of_work DATE NOT NULL,
    hours INT NOT NULL,
    PRIMARY KEY (card_id),
    FOREIGN KEY (emp_id) REFERENCES employee(emp_id)
);


   CREATE TABLE sales_log (
   	sale_id INT AUTO_INCREMENT,
    emp_id INT NOT NULL,
    date_of_sale DATE NOT NULL,
    amount FLOAT NOT NULL,
    PRIMARY KEY (sale_id),
    FOREIGN KEY (emp_id) REFERENCES employee(emp_id)
);

   CREATE TABLE daily_variables (
   	union_rate FLOAT NOT NULL,
   	other_fees FLOAT NOT NULL
);
   CREATE TABLE payment_log (
   	payment_id INT AUTO_INCREMENT,
    emp_id INT NOT NULL,
    date_of_pay DATE NOT NULL,
    amount FLOAT NOT NULL,
    PRIMARY KEY (payment_id),
    FOREIGN KEY (emp_id) REFERENCES employee(emp_id)
);
```

## Get started
```bash
javac Employee.java DBConnect.java Driver.java -d ClassFiles
java -cp ClassFiles/mysql-connector-java-8.0.11.jar:ClassFiles/ EPMS.Driver
```