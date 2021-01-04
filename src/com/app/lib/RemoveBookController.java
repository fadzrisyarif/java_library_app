package com.app.lib;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RemoveBookController implements Initializable {

    @FXML
    private Button cancelRemoveBtn;
    @FXML
    private TextField isbnInputx;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void cancelRemoveBtnOnActione(ActionEvent event){
        Stage stage = (Stage) cancelRemoveBtn.getScene().getWindow();
        stage.close();
    }

    public void removeOnActionBtn(ActionEvent event){
        if(isbnInputx.getText().isBlank()==false){
            DatabaseConnection connection = new DatabaseConnection();
            Connection connection1 = connection.getDatabaseLink();

            String verifyChangeBook = "DELETE FROM book WHERE isbn = '"+ isbnInputx.getText() +"';";
            try{
                Statement statement = connection1.createStatement();
                statement.executeLargeUpdate(verifyChangeBook);
                cancelRemoveBtnOnActione(new ActionEvent());
            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
    }
}
