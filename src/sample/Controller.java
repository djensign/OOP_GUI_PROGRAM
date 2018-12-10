package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Controller implements Initializable {

  private final String[] colNames = {"Class", "Asgnmt", "Due Date", "Status"};
  private ObservableList<ObservableList> data;
  @FXML private TableView<ObservableList> tableView;
  @FXML private JFXTextField CNAME;
  @FXML private JFXTextField ANAME;
  @FXML private JFXTextField DATEDUE;
  @FXML private JFXButton addbtn;
  @FXML private JFXButton clearbtn;
  @FXML private JFXButton removebtn;
  @FXML private Label lbl_Error;

  public void addNewIndex() {
    //Check fields are filled
    if (CNAME.getText().isEmpty() || ANAME.getText().isEmpty() || DATEDUE.getText().isEmpty()) {
      lbl_Error.setText("[Please fill out the form]");

    } else {

      try {
        lbl_Error.setText("");
        //Prepare Connection and SQL STATEMENT
        Connection conNect = DBConn.connect();
        String sql = "INSERT INTO ASSIGNMENTS(CNAME, ANAME, DATEDUE)" +
            "VALUES (?,?,?)";

        PreparedStatement statement = conNect.prepareStatement(sql);
        statement.setString(1, CNAME.getText());
        statement.setString(2, ANAME.getText());
        statement.setString(3, DATEDUE.getText());
        statement.executeUpdate();

        //Update table
        buildData();
        conNect.close();

      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }
  }

  public void buildData() {

    data = FXCollections.observableArrayList();

    try {
      Connection conNect = DBConn.connect();
      String SQL = "SELECT * from ASSIGNMENTS";
      ResultSet rs = conNect.createStatement().executeQuery(SQL);

      for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
        final int j = i;
        TableColumn col = new TableColumn(colNames[i]);
        col.setCellValueFactory(
            (Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(j).toString()));

            tableView.getColumns().addAll(col);
        System.out.println("Column [" + i + "] ");
      }

      while (rs.next()) {
        ObservableList<String> row = FXCollections.observableArrayList();

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
          row.add(rs.getString(i));
        }

        data.add(row);

      }

      conNect.close();

      tableView.setItems(data);

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error CONTROLLERCATCHSTSEMENT");

    }
  }


  @Override
  public void initialize(URL url, ResourceBundle resources) {
    buildData();

    //Table row selected listener
    tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      if (newSelection != null) {
        System.out.println("Table select:" +newSelection);
      }
    });
  }
}
