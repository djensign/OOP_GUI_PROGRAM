package sample;

import java.sql.*;

public class SQLdriver {


  public static void main(String args[]) {
    final String DB_URL = "jdbc:derby:C:\\Users\\Yeti\\Documents\\GitHub\\OOP_GUI_PROGRAM\\lib\\Planner";
    final String SEL_QUERY = "SELECT CNAME, ANAME, DATEDUE FROM ASSIGNMENTS";

    try {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
    } catch (ClassNotFoundException e) {
      System.out.println("Class not found.");
    }
    try {
      Connection conNect = DriverManager.getConnection(DB_URL);

      Statement stateMent = conNect.createStatement();
      ResultSet rs = stateMent
          .executeQuery("SELECT CNAME, ANAME, DATEDUE FROM ASSIGNMENTS");

      System.out.println("1");

      while (rs.next()) {
        String clasS = rs.getString("CNAME");
        String assigN = rs.getString("ANAME");
        Date datE = rs.getDate("DATEDUE");
        System.out.println(clasS + "    " + assigN + "     " + datE);
      }

    } catch (SQLException e) {
      System.out.println("SQL exception occurred " + e);
    }

  }
}
