//Controller for Main/FXML
//Huge help from Carlos Perez and
//https://blog.ngopal.com.np/2011/10/19/dyanmic-tableview-data-from-database/

package sample;

import static sample.DBConn.conNect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class Controller implements Initializable {

  private final String[] colNames = {"Class", "Asgmt Name", "Due Date"};
  private ObservableList<ObservableList> data;
  @FXML private TableView<ObservableList> tableView;
  @FXML private TextField C_NAME;
  @FXML private TextField A_NAME;
  @FXML private DatePicker DATE_DUE;
  @FXML public Button addbtn;
  @FXML public Button clearbtn;  //TO BE ADDED BEFORE FINAL DUE*\
  @FXML public Button removebtn; //TO BE ADDED BEFORE FINAL DUE*/
  @FXML private Label errLbl;


  //CREDIT: HELP FROM CARLOS PEREZ (STUDENT)
  public void addData() {
    //Check fields are filled
    if (C_NAME.getText().isEmpty() || A_NAME.getText().isEmpty() || DATE_DUE.getValue() == null) {
      errLbl.setText("Incorrect Data Entered");

    } else {

      try {
        //blank error
        errLbl.setText("");

        //db connection
        Connection conNect = DBConn.connect();

        //supposed to insert data into the columns
        String sql = "INSERT INTO ASSIGNMENTS(CLASS, ASSIGN, DATE)" +
            "VALUES (?,?,?)";

        PreparedStatement stateMent = conNect.prepareStatement(sql);
        stateMent.setString(1, C_NAME.getText());
        stateMent.setString(2, A_NAME.getText());
        stateMent.setString(3, DATE_DUE.getValue().toString());
        stateMent.executeUpdate();

        //Inputs data into table
        buildData();
        conNect.close();

      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }
  }

  public void removeData() {

    String rowSelect = (tableView.getSelectionModel().getSelectedItem().toString());

    rowSelect =rowSelect.substring(1,rowSelect.indexOf(','));
    System.out.println("Remove: "+rowSelect);

    try {
      errLbl.setText("");
      Connection conNect = DBConn.connect();
      String sql = "DELETE FROM ASSIGNMENTS WHERE CLASS = ?";
    PreparedStatement stateMent2 = conNect.prepareStatement(sql);

    stateMent2.setString(1, rowSelect);

    stateMent2.executeUpdate();

    buildData();

    conNect.close();

    }
    catch (SQLException e){
      System.out.println(e.getMessage());
    }
  }

  public void buildData() {

    //Column set up, credit at top
    data = FXCollections.observableArrayList();

    try {
      Connection conNect = DBConn.connect();
      String SQL = "SELECT * from ASSIGNMENTS";
      ResultSet rs = conNect.createStatement().executeQuery(SQL);

      for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
        final int j = i;
        TableColumn col = new TableColumn(colNames[i]);
        col.setCellValueFactory(
            (Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

        tableView.getColumns().addAll(col);
      }

      while (rs.next()) {
        ObservableList<String> row = FXCollections.observableArrayList();

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
          row.add(rs.getString(i));
        }

        data.add(row);

      }

      conNect.close();

      //puts data in table
      tableView.setItems(data);

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Data Building Error");

    }
  }

  public void initialize(URL url, ResourceBundle resources) {
    buildData();

    //Table row selected listener
    //Thanks to Carlos Perez
    tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      if (newSelection != null) {
        System.out.println("Table select:" +newSelection);
      }
    });
  }
}
