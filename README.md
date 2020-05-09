# FKPayrollDesign
Maintains code for project on Employee Payroll System Design

## Prerequisites
 -Java JDK 11.0.7
 -MySQL

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
    create database test;
    use test;
    create table employee(
       emp_id int not null,
       emp_name varchar(255) not null,
       addr varchar(255),
       dob date,
       cell bigint,
       salary float(24),
       unique(emp_id));
```

## Get started
```bash
javac Employee.java DBConnect.java -d ClassFiles
java -cp ClassFiles/mysql-connector-java-8.0.11.jar:ClassFiles/ EPapp.Employee
```