/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcode.reader;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.List;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author EssamAlmutair
 */

public class Operations {

    DatabaseOperation dbOperation = new DatabaseOperation();

    User user;
    static int i = 0;//temprory 

    public Operations() {

    }

    public void addUser(int id, String name, String college, String type) throws SQLException {
        PreparedStatement pre;
        String query = " INSERT INTO Barcode.User(id,name,college,type) VALUES(?,?,?,?)";
        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed 
        System.out.println(id + name + college + type);
        //assigne values to the queries
        pre.setInt(1, id);
        pre.setString(2, name);
        pre.setString(3, college);
        pre.setString(4, type);
        pre.executeUpdate();
        pre.close();
    }//end of method

    // Add event into Event Table in Database
    public void addEvent(int id, String title, String type,String date,String time) throws SQLException {
        PreparedStatement pre;
        //I have change the query a little bit*********************************************************************************************************        
        String query = " INSERT INTO Barcode.Event VALUES(?,?,?,?,?,?)";
        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed
        //event.setTime(type);//for testing
        pre.setInt(1, id);
        pre.setString(2, title);
        pre.setString(3, type);
        pre.setString(4, "new");
        pre.setString(5, date);
        pre.setString(6, time);
        pre.executeUpdate();

        pre.close();

    }//end of method

    //this method to check if the ensert id is exist or not 
    /*
    public boolean checkId(int id) throws SQLException{
         PreparedStatement pre;
        String temp ="SELECT eventId FROM Barcode.event";
        pre = dbOperation.prepareStatement(temp);

        ResultSet rs = pre.executeQuery();
        boolean val = true;   
        while(rs.next()){

                int idtemp = rs.getInt("eventId");

                if(idtemp != id){
                    val= true;
                }else {
                    val=false;
                }
            }
        return val;
    }
     */
    // for delete Event after clicking delete Event
    public void deleteEvent(int id) throws SQLException {
        PreparedStatement pre;
        String query = "Delete from Barcode.Event where eventId ='"+id+"'";//+id+"'";
        System.out.print("I am here one");
        dbOperation.connect();//connect to database  
        pre = dbOperation.prepareStatement(query);
        System.out.print("I am here two");
        pre.execute();
        pre.close();

    }//end of method

    //This mehtod to add the future Events in Management.java
    public ArrayList<Event> AddEventsList() throws SQLException {
        PreparedStatement pre;
        //Event event= new Event();
        ArrayList<Event> events = new ArrayList<Event>();
        String query = "SELECT * from Barcode.Event where status ='new'";
        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed
        //pre.setString(1, "done");

        ResultSet rs = pre.executeQuery(query);//get the result from database as an object

        while (rs.next()) {
            int id = rs.getInt("eventId");
            String name = rs.getString("name");
            String type = rs.getString("type");
            String status = rs.getString("status");
            String date=rs.getString("date");
            String time=rs.getString("time");
            Event event = new Event(id, name, type, status,date,time);
            events.add(event);

        }

        return events;

    }
    
    //This method is to mark a user present in specific Event
    /*not completed yet*/public void markPresent(){
        PreparedStatement pre;
        String query="INSERT INTO eventUser";
    }
// This method to get all date about an event and and show them in Management.java

    public ArrayList<User> showSelectedEventAttendence(String temp, int selectedId) {
        
        PreparedStatement pre;
        ArrayList<User> users = new ArrayList<User>();
       //insert into the table before you get date in the dialog
        //String query1 ="INSERT into eventUser (eventId,userId)values("+"'"+temp+"'"+","+"'"+selectedId+"'"+")";
        //String query = "SELECT * FROM Barcode.view1 id,userName,userType,college WHERE id="+selectedId;
        //String query ="select id,userName,college,department,userType from Barcode.view1 wher id="+ selectedId +"and eventname ="+"'"+ temp+"'"+" ;";
        String query="select * from  Barcode.view1 where id="+selectedId+" and eventName='"+temp+"'";
        try {

            dbOperation.connect();//connect to database
            pre = dbOperation.prepareStatement(query);// query that will be executed
            
            
            ResultSet rs;//get the result from database as an object
            
            rs = pre.executeQuery(query);
            
            while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("userName");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type=rs.getString("userType");
            User user = new User(id, name, college, department,type);
            users.add(user);
            }
            System.out.print("selected eventAttendence");
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    // to get all Events and show them in the MainPage.java 

    public ArrayList<Event> selectAllEvents() throws SQLException {

        PreparedStatement pre;
        //Event event= new Event();
        ArrayList<Event> events = new ArrayList<Event>();
        String query = "SELECT * from Barcode.Event";
        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed

        ResultSet rs = pre.executeQuery(query);//get the result from database as an object

        while (rs.next()) {
            int id = rs.getInt("eventId");
            String name = rs.getString("name");
            String type = rs.getString("type");
            String status = rs.getString("status");
            String date = rs.getString("date");
            String time = rs.getString("time");
            
            Event event = new Event(id, name, type, status,date,time);
            events.add(event);

        }

        return events;

    }

    // To mark event as acomplished event and update the future Eventlist in Management
    public ArrayList<Event> changeEventToDone(String name) throws SQLException {

        PreparedStatement pre;
        String query = "UPDATE Barcode.Event set status='done' where name =?";
        dbOperation.connect();//connect to database  
        pre = dbOperation.prepareStatement(query);
        pre.setString(1, name);

        pre.execute();
        // ArrayList<Event> events=AddEventsList();

        pre.close();
        //return events;
        return null;
    }

    /*need to check this return value is it correct to have int as return or 
    do we make it as array list becasue we might have more events have same name*/
    //this method to get the id of the selected event in the management.java
   public int getEventId(String temp) throws SQLException {
    
    PreparedStatement pre;
        String query = "SELECT eventId from Barcode.Event where name ='"+temp+"'";
        dbOperation.connect();//connect to database  
        pre = dbOperation.prepareStatement(query);
       int id = 0;

        ResultSet rs=pre.executeQuery();
        while(rs.next()){
            id=rs.getInt("eventId");
        }
        pre.close();
       return id; 
    }
   //this method to add new attendent to table eventUser before showing them in the GUI
   public void insertSelectedUserToEvent(int eventId,int userId) throws SQLException{
       
       PreparedStatement pre;
        String query = "INSERT INTO Barcode.eventUser VALUES(?,?)";
        dbOperation.connect();//connect to database  
        pre = dbOperation.prepareStatement(query);
        pre.setInt(1, eventId);
        pre.setInt(2, userId);

       pre.executeQuery();
       
        pre.close();
        System.out.print("insert");
   }
   //this method to show all people marked present in case someone close the program before mark event done
    ArrayList<User> showPresentUser(int eventId) throws SQLException {
      PreparedStatement pre;
        //Event event= new Event();
        ArrayList<User> users = new ArrayList<User>();
        String query="select * from  Barcode.view1 where  eventId='"+eventId+"'";
        dbOperation.connect();//connect to database       
        pre = dbOperation.prepareStatement(query);// query that will be executed
        
        ResultSet rs = pre.executeQuery(query);//get the result from database as an object
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("userName");
            String college = rs.getString("college");
            String department = rs.getString("department");
            String type = rs.getString("userType");
            
            
            User user = new User(id, name, type, college,department);
            users.add(user);

        }

        return users;  
    }

}
