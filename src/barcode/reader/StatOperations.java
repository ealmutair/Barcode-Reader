/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcode.reader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author EssamAlmutair
 */
public class StatOperations {

    DatabaseOperation dbOperation = new DatabaseOperation();
    
    
    //This method to count 
    public void count() throws SQLException{
        PreparedStatement pre;
        //Event event= new Event();
        ArrayList<Event> events = new ArrayList<Event>();
        String query = "SELECT COUNT(eventId) from Barcode.Event";
        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed

        ResultSet count  = pre.executeQuery(query);//get the result from database as an object
        
    }
    
//This method to get all the events 
    public ArrayList<Event> selectAllEvents() throws SQLException {
        Operations operation = new Operations();
       ArrayList<Event>item= operation.selectAllEvents();
        return item;
    }
    
    /*Users Query in from All Events (Staff,Proffessor,Guest,Students)*/
    
//This method to get all the professors who attend all the events
   public ArrayList<User> selectAllProfessor() throws SQLException {
        
        PreparedStatement pre;
        ArrayList<User> students = new ArrayList<User>();
        String query = "SELECT * from Barcode.User where type=?";
        
        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed 
        pre.setString(1, "doctor");
        ResultSet rs  = pre.executeQuery();//get the result from database as an object
         
        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type=rs.getString("type");
            User student = new User(id, name, college, department,type);
            students.add(student);
        }//end of loop
       return students;     
    } 
   
   //This method to get all the professors who attend specific event
   public ArrayList<User> selectAllProfessor2(String event) throws SQLException {
        
        PreparedStatement pre;
        ArrayList<User> students = new ArrayList<User>();
        //this is from view not from table
        String query = "SELECT id,userName,college,department,userType from Barcode.view1 where eventName=? and userType='doctor'";
        
        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed 
        pre.setString(1, event);
        ResultSet rs  = pre.executeQuery();//get the result from database as an object
         
        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("userName");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type=rs.getString("userType");
            User student = new User(id, name, college, department,type);
            students.add(student);
        }//end of loop
       return students;     
    } 
   
   
//This method to get all the staff in all events
    public ArrayList<User> selectAllStaff() throws SQLException {
    
        PreparedStatement pre;
        ArrayList<User> staffs = new ArrayList<User>();
        String query = "SELECT * from Barcode.User where type=?";
        
        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed 
        pre.setString(1, "staff");
        ResultSet rs  = pre.executeQuery();//get the result from database as an object
         
        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type=rs.getString("type");
            User staff = new User(id, name, college, department,type);
            staffs.add(staff);
        }//end of loop
       return staffs;     
    }
    
//This method to get all the staff in specific event using the view
    public ArrayList<User> selectAllStaff2(String event) throws SQLException {
    
        PreparedStatement pre;
        ArrayList<User> staffs = new ArrayList<User>();
        //this is from view not from table
        String query = "SELECT id,userName,college,department,userType from Barcode.view1 where userType='staff'and eventName=?";
        
        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed     
        pre.setString(1, event);
        ResultSet rs  = pre.executeQuery();//get the result from database as an object
         
        while (rs.next()) {//read data from database and safe them in array of objects
             int id = rs.getInt("id");
            String name = rs.getString("userName");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type=rs.getString("userType");
            User staff = new User(id, name, college, department,type);
            staffs.add(staff);
        }//end of loop
       return staffs;     
    }
    
    
    
//This method to get all Guest in all Events
    public ArrayList<User> selectAllUsers() throws SQLException {
       
        PreparedStatement pre;
        ArrayList<User> users = new ArrayList<User>();
        String query = "SELECT * from Barcode.User";
        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed

        ResultSet rs  = pre.executeQuery();//get the result from database as an object
         while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type=rs.getString("type");
            User user = new User(id, name, college, department,type);
            users.add(user);
        }
       return users;
        
    }
//This method to get all Students in all events 
    public ArrayList<User> selectAllStudents() throws SQLException {
  
        PreparedStatement pre;
        ArrayList<User> students = new ArrayList<User>();
        String query = "SELECT * from Barcode.User where type='student'";
        
        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed        
        ResultSet rs  = pre.executeQuery(query);//get the result from database as an object
         
        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type=rs.getString("type");
            User student = new User(id, name, college, department,type);
            students.add(student);
        }//end of loop
       return students;     
    }

    //This method to get all Students in specific event 
    public ArrayList<User> selectAllStudents2(String event) throws SQLException {
  
        PreparedStatement pre;
        ArrayList<User> students = new ArrayList<User>();
        System.out.println(event);
        String query = "SELECT id,userName,college,department,userType from Barcode.view1 where eventName=? and userType='student'";
        
        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed  
        pre.setString(1, event);
        ResultSet rs  = pre.executeQuery();//get the result from database as an object
         
        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("userName");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type=rs.getString("userType");
            User student = new User(id, name, college, department,type);
            students.add(student);
        }//end of loop
       return students;     
    }

    
    
//This method to get all guests in all events 
    public ArrayList<User> selectAllGuests() throws SQLException {
  
        PreparedStatement pre;
        ArrayList<User> guests = new ArrayList<User>();
        String query = "SELECT * from Barcode.User where type='guest'";
        
        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed        
        ResultSet rs  = pre.executeQuery();//get the result from database as an object
         
        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type=rs.getString("type");
            User guest = new User(id, name, college, department,type);
            guests.add(guest);
        }//end of loop
       return guests;     
    }
 
       
//This method to get all guests in specific event 
    public ArrayList<User> selectAllGuests2(String event) throws SQLException {
  
        PreparedStatement pre;
        ArrayList<User> guests = new ArrayList<User>();
        String query = "SELECT id,userName,college,department,userType from Barcode.view1 where eventName=? and userType='guest'";
        
        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed
        pre.setString(1, event);
        ResultSet rs  = pre.executeQuery();//get the result from database as an object
         
        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("userName");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type=rs.getString("userType");
            User guest = new User(id, name, college, department,type);
            guests.add(guest);
        }//end of loop
       return guests;     
    }
 
    
}

