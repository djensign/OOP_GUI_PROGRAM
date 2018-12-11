//Tests if Database Connected
//Help from stackoverflow FAQ and Carlos Perez

package sample;

import java.sql.*;

public class DBConnTest {


  public static void main(String args[]) {
    final String DB_URL = "jdbc:derby:C:\\Users\\Yeti\\Documents\\GitHub\\OOP_GUI_PROGRAM\\lib\\Planner";

    try {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
    } catch (ClassNotFoundException e) {
      System.out.println("Class not found." + e);
    }
    try {
      Connection conNect = DriverManager.getConnection(DB_URL);

      Statement stateMent = conNect.createStatement();
      ResultSet rs = stateMent
          .executeQuery("SELECT CLASS, ASSIGN, DATE FROM ASSIGNMENTS");

      System.out.println("1");

      while (rs.next()) {
        String clasS = rs.getString("CLASS");
        String assigN = rs.getString("ASSIGNMENT");
        Date datE = rs.getDate("DATE");
        System.out.println(clasS + "    " + assigN + "     " + datE);
      }

    } catch (SQLException e) {
      System.out.println("SQL exception occurred " + e);
    }

  }
}