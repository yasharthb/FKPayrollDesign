# FKPayrollDesign
Maintains code for project on Employee Payroll System Design

## Prerequisites
 Java JDK 11.0.7
 MySQL

## Setup MySQL Server on the local server
``  sudo apt update
    sudo apt install mysql-server
    sudo mysql_secure_installation
    sudo mysql
 //In the SQL shell run
    CREATE USER 'yasharth'@'localhost' IDENTIFIED BY 'yasharth@sql';
    GRANT ALL PRIVILEGES ON *.* TO 'sammy'@'localhost' WITH GRANT OPTION;
``

## Get started
``javac Employee.java DBConnect.java -d ClassFiles
java -cp ClassFiles/mysql-connector-java-8.0.11.jar:ClassFiles/ EPapp.Employee``