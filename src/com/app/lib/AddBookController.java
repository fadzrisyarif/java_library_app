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

public class AddBookController implements Initializable {

    @FXML
    private Button addBookBtn, cancelBookBtn;
    @FXML
    private TextField isbnInput, judulInput, pengarangInput, penerbitInput, tahunTerbitInput, linkInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void cancelAddBtnOnAction(ActionEvent event){
        Stage stage = (Stage) cancelBookBtn.getScene().getWindow();
        stage.close();
    }

    public void addOnActionBtn(ActionEvent event){
        if(isbnInput.getText().isBlank()==false&&judulInput.getText().isBlank()==false&&pengarangInput.getText().isBlank()==false&&penerbitInput.getText().isBlank()==false&&tahunTerbitInput.getText().isBlank()==false&&linkInput.getText().isBlank()==false){
            DatabaseConnection connection = new DatabaseConnection();
            Connection connection1 = connection.getDatabaseLink();

            String verifyAddBook = "INSERT INTO book(isbn, judul, pengarang, penerbit, tahun_terbit, link) VALUES('"+ isbnInput.getText()+ "','"+ judulInput.getText() + "','"+ pengarangInput.getText() + "','"+ penerbitInput.getText() + "','"+ tahunTerbitInput.getText() +
                    "','"+ linkInput.getText() +"');";
            try{
                Statement statement = connection1.createStatement();
                statement.executeUpdate(verifyAddBook);
                cancelAddBtnOnAction(new ActionEvent());
            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
    }
}
