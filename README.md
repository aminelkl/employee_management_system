
# Employee management system application



https://user-images.githubusercontent.com/96929412/194971356-b5f8bf67-2d5a-400d-b59f-52ec9eff5c05.mp4

**Description :** 

This is a desktop application that let the user manage an employee database via a
graphical user interface. It can also be used as a template to store, edit and delete 
any specific data. 

**Login page :**

In order to connect in the dash board, you need to provide specifics user name and password
that are stored in a database. There is 2 specifics error messages, one if you try to connect
without giving any user name and password and the other one if you give false login.
 To have more privacy, password will be hidden (****).

**Dashboard :** 

After a succesful login, you arrive at the dash board. Table view is automatically initialize
with the employee database table (employee ID are generated automatically by the sql database).

The add button will get you to another page, you'll need to enter all attributes for an employee 
and click on finish to store them. In order to see the new employee in the table view , refresh 
it with the refresh button.

The delete button opens another page so you can delete an employee by using his ID, that is a 
primary key in the sql database. I decided to make it a bit harder to delete an employee because 
it is a sensitive action to do.

You can also edit any employee by double clicking on it , it will send you on an editing page. 

To make it easier to navigate from an employee to another one, I implemented a live search bar that will
go thru every piece of data in the table. Also, all data that you see in the tableview are 100% synchronize with the official database. 
At any moment you can log out or close the page by clicking on the log out button or the exit button.

**Front-end :** 

For the front-end, I went for a modern user interface and I added icon to improve user experience and readability. The design has been cleaned up for better usability and accessibility. Also, the choice of colors was made meticulously, instead of the basic colors usually used for management software, I chose to add life to my apps by using purple and blue. 


## Deployment

In order to implement this project yourself you'll need 2 table in your database, one for the user and
the other for the employee.

Here are the script for creating the table : 
```
create table employee
(
id int primary key not null auto_increment,
firstname varchar(20),
lastname varchar(20),
gender varchar(20),
yoe varchar(20)
);

create table user 
(
username varchar(20) ,
password varchar(20)
);
```
Than you need to import in your IDE all the files linked to this project and in the DataBase.java class, you have to use your own localhost, database name, username and password.

**Class Specification :**

- Main.java is the main class it show the first scene : SignIn.fxml
- DataBase.java is the connection to the database, it return a Connection instance.
- Connect.java is the controller of -> SignIn.fxml
- Board.java is the controller of -> DashBoard.fxml
- AddControl.java is the controller of -> Add.fxml
- Delete.java is the controller of -> Delete.fxml
- Edit.java is the controller of -> Edit.fxml
- EmployeeList.java is the template class for an employee used in the tableview
- All .png files are used as icon






## Tech Stack

**Client:** Java, FXML and Scene Builder

**Database:** mySQL



## Authors

- [@Amine Lakhal](https://github.com/aminelkl)

## Support

For support, email me at lakhal-a@hotmail.com.

