package com.app.lib;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    private double x_pos = 0,y_pos = 0;

    @FXML
    private AnchorPane loggedInScreen, addBookScreen;
    @FXML
    private Label bookList, isbnList;
    @FXML
    private ImageView clsBtnImg;
    @FXML
    private Button closeBtnHome;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookDatabase();
        File XFile = new File("images/close-x-image.png");
        javafx.scene.image.Image XImage =  new javafx.scene.image.Image(XFile.toURI().toString());
        clsBtnImg.setImage(XImage);
        makeDraggableObject(loggedInScreen);
    }

    public void bookDatabase(){
        DatabaseConnection connection = new DatabaseConnection();
        Connection connection1 = connection.getDatabaseLink();

        String verifyBook = "SELECT * From book;";
        try {
            Statement statement = connection1.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyBook);
            String bookResult = "";
            String isbnResult = "";
            while(queryResult.next()){
                bookResult+=queryResult.getString(3)+"\n";
                isbnResult+=queryResult.getString(2)+"\n";
            }
            bookList.setText(bookResult);
            isbnList.setText(isbnResult);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void closeBtnUserOnAction(ActionEvent actionEvent){
        Stage stage = (Stage) closeBtnHome.getScene().getWindow();
        stage.close();
    }

    public void makeDraggableObject(Region obj){
        obj.setOnMousePressed((event) -> {
            x_pos = event.getSceneX();
            y_pos = event.getSceneY();
        });

        obj.setOnMouseDragged((event)->{
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x_pos);
            stage.setY(event.getScreenY() - y_pos);
        });
    }

    public void insertScreenOnActionBtn(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("addBook.fxml"));
            Stage insertStage = new Stage();
            insertStage.initStyle(StageStyle.UNDECORATED);
            insertStage.setTitle("Home");
            insertStage.setScene(new Scene(root, 500, 350));
            insertStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void editScreenOnActionBtn(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("editBook.fxml"));
            Stage editStage = new Stage();
            editStage.initStyle(StageStyle.UNDECORATED);
            editStage.setTitle("Home");
            editStage.setScene(new Scene(root, 500, 350));
            editStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void removeScreenOnActionBtn(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("removeBook.fxml"));
            Stage loggedInStage = new Stage();
            loggedInStage.initStyle(StageStyle.UNDECORATED);
            loggedInStage.setTitle("Home");
            loggedInStage.setScene(new Scene(root, 350, 174));
            loggedInStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}
