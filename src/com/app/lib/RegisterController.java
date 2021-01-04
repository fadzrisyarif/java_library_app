package com.app.lib;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private ImageView shieldImageView;
    @FXML
    private Button closeButton;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button registerActionButton;
    @FXML
    private Label registrationMessageLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File shieldFile = new File("images/shield_logo.png");
        Image shieldImage =  new Image(shieldFile.toURI().toString());
        shieldImageView.setImage(shieldImage);
    }
    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage)  closeButton.getScene().getWindow();
        stage.close();
    }
    public void registerButtonOnAction(ActionEvent event){
        if(firstName.getText().isBlank()==false&&lastName.getText().isBlank()==false&&emailField.getText().isBlank()==false&&passwordField.getText().isBlank()==false){
            DatabaseConnection connection = new DatabaseConnection();
            Connection connection1 = connection.getDatabaseLink();

            String verifyRegistration = "INSERT INTO users(fname, lname, email, password) VALUES('"+ firstName.getText()+ "','"+ lastName.getText() + "','"+ emailField.getText() +
                    "','"+ passwordField.getText() +"');";
            try{
                Statement statement = connection1.createStatement();
                statement.executeUpdate(verifyRegistration);

            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
                registrationMessageLabel.setText("Email has been registered!");
            }
        }
    }
}
