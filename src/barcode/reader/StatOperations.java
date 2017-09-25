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
    public void count() throws SQLException {
        PreparedStatement pre;
        //Event event= new Event();
        ArrayList<Event> events = new ArrayList<Event>();
        String query = "SELECT COUNT(eventId) from Barcode.Event";
        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed

        ResultSet count = pre.executeQuery(query);//get the result from database as an object
        pre.close();
    }

//This method to get all the events 
    public ArrayList<Event> selectAllEvents() throws SQLException {
        Operations operation = new Operations();
        ArrayList<Event> item = operation.selectAllEvents();
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
        ResultSet rs = pre.executeQuery();//get the result from database as an object

        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type = rs.getString("type");
            User student = new User(id, name, college, department, type);
            students.add(student);
        }//end of loop
        pre.close();
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
        ResultSet rs = pre.executeQuery();//get the result from database as an object

        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("userName");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type = rs.getString("userType");
            User student = new User(id, name, college, department, type);
            students.add(student);
        }//end of loop
        pre.close();
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
        ResultSet rs = pre.executeQuery();//get the result from database as an object

        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type = rs.getString("type");
            User staff = new User(id, name, college, department, type);
            staffs.add(staff);
        }//end of loop
        pre.close();
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
        ResultSet rs = pre.executeQuery();//get the result from database as an object

        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("userName");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type = rs.getString("userType");
            User staff = new User(id, name, college, department, type);
            staffs.add(staff);
        }//end of loop
        pre.close();
        return staffs;
    }

//This method to get all Guest in all Events
    public ArrayList<User> selectAllUsers() throws SQLException {

        PreparedStatement pre;
        ArrayList<User> users = new ArrayList<User>();
        String query = "SELECT * from Barcode.User";
        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed

        ResultSet rs = pre.executeQuery();//get the result from database as an object
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type = rs.getString("type");
            User user = new User(id, name, college, department, type);
            users.add(user);
        }
        pre.close();
        return users;

    }
//This method to get all Students in all events 

    public ArrayList<User> selectAllStudents() throws SQLException {

        PreparedStatement pre;
        ArrayList<User> students = new ArrayList<User>();
        String query = "SELECT * from Barcode.User where type='student'";

        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed        
        ResultSet rs = pre.executeQuery(query);//get the result from database as an object

        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type = rs.getString("type");
            User student = new User(id, name, college, department, type);
            students.add(student);
        }//end of loop
        pre.close();
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
        ResultSet rs = pre.executeQuery();//get the result from database as an object

        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("userName");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type = rs.getString("userType");
            User student = new User(id, name, college, department, type);
            students.add(student);
        }//end of loop
        pre.close();
        return students;
    }

//This method to get all guests in all events 
    public ArrayList<User> selectAllGuests() throws SQLException {

        PreparedStatement pre;
        ArrayList<User> guests = new ArrayList<User>();
        String query = "SELECT * from Barcode.User where type='guest'";

        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed        
        ResultSet rs = pre.executeQuery();//get the result from database as an object

        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type = rs.getString("type");
            User guest = new User(id, name, college, department, type);
            guests.add(guest);
        }//end of loop
        pre.close();
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
        ResultSet rs = pre.executeQuery();//get the result from database as an object

        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("userName");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type = rs.getString("userType");
            User guest = new User(id, name, college, department, type);
            guests.add(guest);
        }//end of loop
        pre.close();
        return guests;
    }
//this method to get the id of a specific event
    public int getEventId(String selectedEvent) throws SQLException {
        PreparedStatement pre;

        String query = "select eventId from event where name =?";
        int id=0;
        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed
        pre.setString(1, selectedEvent);
        ResultSet rs = pre.executeQuery();//get the result from database as an object
        while (rs.next()){
             id =rs.getInt("eventId");
             System.out.print(id);
        }
        pre.close();
        return id;
    }
    //this method to get all guest who attend specific event 
    ArrayList<User> selectAllGuestsAtEvent(String selectedEvent) throws SQLException {
        
            PreparedStatement pre;
        ArrayList<User> guests = new ArrayList<User>();
        String query = "SELECT id,userName,college,department,userType from Barcode.view1 where eventName=? and userType=?";

        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed
        pre.setString(1, selectedEvent);
        pre.setString(2, "guest");
        ResultSet rs = pre.executeQuery();//get the result from database as an object

        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("userName");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type = rs.getString("userType");
            User guest = new User(id, name, college, department, type);
            guests.add(guest);
        }//end of loop
        pre.close();
        return guests; 
        
    }

    //this method to get all staff who attend specific event
    ArrayList<User> selectAllStaffAtEvent(String selectedEvent) throws SQLException {
      
            PreparedStatement pre;
        ArrayList<User> staffs = new ArrayList<User>();
        String query = "SELECT id,userName,college,department,userType from Barcode.view1 where eventName=? and userType=?";

        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed
        pre.setString(1, selectedEvent);
        pre.setString(2, "staff");
        ResultSet rs = pre.executeQuery();//get the result from database as an object

        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("userName");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type = rs.getString("userType");
            User staff = new User(id, name, college, department, type);
            staffs.add(staff);
        }//end of loop
        pre.close();
        return staffs; 
        
    }
    //this method to get all professor who attend specific event 
    ArrayList<User> selectAllProfessorAtEvent(String selectedEvent) throws SQLException {
     
            PreparedStatement pre;
        ArrayList<User> profesors = new ArrayList<User>();
        String query = "SELECT id,userName,college,department,userType from Barcode.view1 where eventName=? and userType=?";

        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed
        pre.setString(1, selectedEvent);
        pre.setString(2, "professor");
        ResultSet rs = pre.executeQuery();//get the result from database as an object

        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("userName");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type = rs.getString("userType");
            User professor = new User(id, name, college, department, type);
            profesors.add(professor);
        }//end of loop
        pre.close();
        return profesors;  
        
    }
        
    
    //This event to get all students who attend in specific event
    ArrayList<User> selectAllStudentsAtEvent(String selectedEvent) throws SQLException {
      PreparedStatement pre;
        ArrayList<User> students = new ArrayList<User>();
        String query = "SELECT id,userName,college,department,userType from Barcode.view1 where eventName=? and userType=?";

        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed
        pre.setString(1, selectedEvent);
        pre.setString(2, "student");
        ResultSet rs = pre.executeQuery();//get the result from database as an object

        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("userName");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type = rs.getString("userType");
            User student = new User(id, name, college, department, type);
            students.add(student);
        }//end of loop
        pre.close();
        return students;  
    }

    ArrayList<User> selectAllUsersAsId(int searchField) throws SQLException {
         PreparedStatement pre;
        ArrayList<User> students = new ArrayList<User>();
        String query = "SELECT * from Barcode.User where id=?";

        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed
        pre.setInt(1, searchField);
       
        ResultSet rs = pre.executeQuery();//get the result from database as an object

        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type = rs.getString("type");
            User student = new User(id, name, college, department, type);
            students.add(student);
        }//end of loop
        pre.close();
        return students;   
    }

    ArrayList<User> selectAllUsersAsName(String searchField) throws SQLException {
      
            PreparedStatement pre;
        ArrayList<User> students = new ArrayList<User>();
        String query = "SELECT * from Barcode.User where name=?";

        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed
        pre.setString(1, searchField);
       
        ResultSet rs = pre.executeQuery();//get the result from database as an object

        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            System.out.println("Id of the name is"+id);
            String name = rs.getString("name");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type = rs.getString("type");
            User student = new User(id, name, college, department, type);
            students.add(student);
        }//end of loop
        pre.close();
        return students;   
    }

    ArrayList<Event> selectAllEventsAsId(int searchField) throws SQLException {
 
            PreparedStatement pre;
        ArrayList<Event> events = new ArrayList<Event>();
        String query = "SELECT * from Barcode.Event where eventId=?";

        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed
        pre.setInt(1, searchField);
       
        ResultSet rs = pre.executeQuery();//get the result from database as an object

        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("eventId");
           
            String name = rs.getString("name");
            String status = rs.getString("status");
            String date = rs.getString("date");
            String type = rs.getString("type");
            String time=rs.getString("time");
            Event event= new Event(id,name,type,status,date,time);
            
            events.add(event);
        }//end of loop
        pre.close();
        return events;   
        
    }

    ArrayList<User> getPerson(int text) throws SQLException {
       
           PreparedStatement pre;
        ArrayList<User> users = new ArrayList<User>();
        String query = "SELECT * from Barcode.User where id=?";

        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed
        pre.setInt(1, text);
       
        ResultSet rs = pre.executeQuery();//get the result from database as an object

        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("id");
            System.out.println("Id of the name is"+id);
            String name = rs.getString("name");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type = rs.getString("type");
            User user = new User(id, name, college, department, type);
            users.add(user);
        }//end of loop
        pre.close();
        return users;   
        
    }

    ArrayList<Event> getEvent(int text) throws SQLException {

            PreparedStatement pre;
        ArrayList<Event> events = new ArrayList<Event>();
        String query = "SELECT * from Barcode.Event where eventId=?";

        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed
        pre.setInt(1, text);
       
        ResultSet rs = pre.executeQuery();//get the result from database as an object

        while (rs.next()) {//read data from database and safe them in array of objects
            int id = rs.getInt("eventId");
           
            String name = rs.getString("name");
            String status = rs.getString("status");
            String date = rs.getString("date");
            String type = rs.getString("type");
            System.out.println("event type is "+type);
            String time=rs.getString("time");
            Event event= new Event(id,name,type,status,date,time);
            
            events.add(event);
        }//end of loop
        pre.close();
        return events;
    }
    //this method to update the User's Values
    void updateUser(int parseInt, String text, String text0, String text1, String text2) throws SQLException {
         PreparedStatement pre;
        ArrayList<User> users = new ArrayList<User>();
        String query = "UPDATE Barcode.User SET name=?,college=?,department=?,type=? where id=?";

        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed
        pre.setInt(5, parseInt);
        pre.setString(1, text);
        pre.setString(2, text0);
        pre.setString(3, text1);
        pre.setString(4, text2); 
        ResultSet rs = pre.executeQuery();//get the result from database as an object

       
        pre.close();
        
    }
    // this method to delete a User
    void deleteUser(int parseInt, String text) throws SQLException {
    
           PreparedStatement pre;
        ArrayList<User> users = new ArrayList<User>();
        String query = "DELETE from Barcode.User where id=? and text=?";

        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed
        pre.setInt(1, parseInt);       
        pre.setString(2, text);
        ResultSet rs = pre.executeQuery();//get the result from database as an object       
        pre.close();
        
    }
    //this method to update the Event's Values
    void updateEvent(int parseInt, String text, String text0, String text1, String text2, String text3) throws SQLException {
    
           PreparedStatement pre;
        ArrayList<Event> events = new ArrayList<Event>();
        String query = "UPDATE Barcode.Event SET name =? ,type=?,status=?,date=?,time =? where eventId=?";

        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed
        pre.setInt(1, parseInt);
        pre.setString(2, text);
        pre.setString(3, text0);
        pre.setString(4, text1);
        pre.setString(5, text2);
        pre.setString(6, text3);        
         pre.executeQuery();//get the result from database as an object

       
        pre.close();
        
    }
        //this method to delete an Event
    void deleteEvent(int parseInt, String text) throws SQLException {
    
           PreparedStatement pre;
        ArrayList<Event> events = new ArrayList<Event>();
        String query = "DELETE from Barcode.Event where eventId=?";

        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed
        pre.setInt(1, parseInt);
        pre.setString(2, text);       
         pre.executeQuery();//get the result from database as an object       
        pre.close();
       
    }
    
}


