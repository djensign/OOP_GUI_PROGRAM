//Connects to DB
//https://blog.ngopal.com.np/2011/10/19/dyanmic-tableview-data-from-database/

package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {

  protected static Connection conNect;
  private static String DBURL = "jdbc:derby:lib\\Planner";

  public static Connection connect() throws SQLException{
    try{
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
    }
    catch(ClassNotFoundException CNFExep){
      System.err.println("Oops! " + CNFExep.getMessage());
    }
    catch(InstantiationException IExep){
      System.err.println("Oops! " + IExep.getMessage());
    }
    catch(IllegalAccessException IAExep){
      System.err.println("Oops! " + IAExep.getMessage());
    }

    conNect = DriverManager.getConnection(DBURL);
    return conNect;

  }

  public static Connection getConnection() throws SQLException, ClassNotFoundException{
    if(conNect != null && !conNect.isClosed())
      return conNect;
    connect();
        return conNect;
  }

}
