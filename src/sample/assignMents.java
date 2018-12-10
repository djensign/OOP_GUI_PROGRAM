//Methods and Constructors for assignment features.
//Similar to Vehicle.java in Activity 2-1.
package sample;

import javafx.collections.ObservableList;

import java.util.Date;

public class assignMents {

  public String className;

  public String assignName;

  public Date dueDate;

  //public boolean impLevel; Importance Yes/No to be implemented

  public assignMents(){
    className = "";
    assignName = "";
    dueDate = new Date();
  }

  public assignMents(String className, String assignName, Date dueDate){
    this.className = className;
    this.assignName = assignName;
    this.dueDate = dueDate;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getAssignName() {
    return assignName;
  }

  public void setAssignName(String assignName) {
    this.assignName = assignName;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  @Override
  public String toString() {
    return "Class Name : " + className + '\n' +
        "Assignment Name : " + assignName + '\n' +
        "Date Due : " + dueDate;
  }
}
