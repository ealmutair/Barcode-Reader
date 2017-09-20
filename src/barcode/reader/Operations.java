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
        String query = "DELETE FROME Barcode.Event where eventId =?";
        dbOperation.connect();//connect to database  
        pre = dbOperation.prepareStatement(query);

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
        String query ="select id,userName,college,department,userType from Barcode.view1 ";
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

    void selectAllProfessor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void selectAllStaff() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void selectAllGuest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
