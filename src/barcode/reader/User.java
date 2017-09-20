/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcode.reader;

/**
 *
 * @author EssamAlmutair
 * 
 */

//represent User table in Database
class User {
 
    private int id;
    private String name;
    private String college;
    private String department;
    private String type;

    User(int id, String name, String college, String department, String type) {
        this.id=id;
        this.name=name;
        this.college=college;
        this.department=department;
        this.type=type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

 
    
}
