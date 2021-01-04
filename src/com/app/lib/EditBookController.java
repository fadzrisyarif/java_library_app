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

public class EditBookController implements Initializable {

    @FXML
    private Button cancelBookBtne;
    @FXML
    private TextField isbnInpute, judulInpute, pengarangInpute, penerbitInpute, tahunTerbitInpute, linkInpute;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void editOnActionBtn(ActionEvent event){
        if(isbnInpute.getText().isBlank()==false&&judulInpute.getText().isBlank()==false&&pengarangInpute.getText().isBlank()==false&&penerbitInpute.getText().isBlank()==false&&tahunTerbitInpute.getText().isBlank()==false&&linkInpute.getText().isBlank()==false){
            DatabaseConnection connection = new DatabaseConnection();
            Connection connection1 = connection.getDatabaseLink();

            String verifyChangeBook = "UPDATE book SET judul = '"+ judulInpute.getText() +"', " + "pengarang = '"+ pengarangInpute.getText() +"'," + "penerbit = '"+ penerbitInpute.getText() +"'," + "tahun_terbit = '"+ tahunTerbitInpute.getText() +"'," + "link = '"+ linkInpute.getText() +"' " + "WHERE isbn = '"+ isbnInpute +"';";
            try{
                Statement statement = connection1.createStatement();
                statement.executeLargeUpdate(verifyChangeBook);
                cancelAddBtnOnActione(new ActionEvent());
            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
    }

    public void cancelAddBtnOnActione(ActionEvent event){
        Stage stage = (Stage) cancelBookBtne.getScene().getWindow();
        stage.close();
    }


}
