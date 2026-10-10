
package com.smartcourse.studentmanagementsystem;

public class Student {
    private String studentID;
    private String firstName;
    private String lastName;
    private String course;
    
    public Student(String studentID, String firstName, String lastName, String course){
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
    }
    
    public String getStudentID(){
        return studentID;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getCourse(){
        return course;
    }
    
    public void setID(String id){
        this.studentID = id;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public void setCourse(String course){
        this.course = course;
    }
}
