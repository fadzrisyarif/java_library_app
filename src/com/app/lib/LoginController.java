package com.app.lib;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private TextField unameTF;
    @FXML
    private PasswordField passPF;

    public void loginButtonOnAction(ActionEvent event){
        if(unameTF.getText().isBlank() == false && passPF.getText().isBlank() == false) {
            validateLogin();
        }
        else{
            loginMessageLabel.setText("Please enter email and password!");
        }
    }

    private void validateLogin() {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connection1 = connection.getDatabaseLink();

        String verifyLogin = "SELECT * From users Where email = '" + unameTF.getText() + "' And password = '"+ passPF.getText() +
                "';";
        try {
            Statement statement = connection1.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            int i = 1;
            while(queryResult.next()){
                if(queryResult.getString(3).equals(unameTF.getText().toString())&&queryResult.getString(4).equals(passPF.getText().toString())){
                    userLoggedIn();
                    cancelButtonOnAction(new ActionEvent());
                }else{
                    loginMessageLabel.setText("Invalid login, please try again!");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("images/library_img_1.png");
        Image brandingImage =  new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);
    }

    public void RegistrationForm(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage RegisterStage = new Stage();
            RegisterStage.initStyle(StageStyle.UNDECORATED);
            RegisterStage.setTitle("Registration");
            RegisterStage.setScene(new Scene(root, 600, 440));
            RegisterStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void userLoggedIn(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("user.fxml"));
            Stage loggedInStage = new Stage();
            loggedInStage.initStyle(StageStyle.UNDECORATED);
            loggedInStage.setTitle("Home");
            loggedInStage.setScene(new Scene(root, 800, 600));
            loggedInStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}
