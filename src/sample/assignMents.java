//Methods and Constructors for assignment features.
//Similar to Vehicle.java in Activity 2-1.

//Still trying to connect input to database

package sample;

import java.util.Date;

public class assignMents {

  public String className;

  public String assignName;

  public Date dueDate;

  public assignMents() {

    className = "";

    assignName = "";

    dueDate = new Date();

  }

  public assignMents(String className, String assignName, Date dueDate) {

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

    return "assignMents{" +

        "className='" + className + '\'' +

        "assignName='" + assignName + '\'' +

        "dueDate='" + dueDate + '\'' +

        '}';
  }
}
