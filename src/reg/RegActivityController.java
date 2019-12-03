package reg;

import db.DbManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.BaseClass;
import sample.User;

import java.io.IOException;

public class RegActivityController extends  BaseClass{

    @FXML
    private PasswordField passText;

    @FXML
    private TextField loginText;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    @FXML
    private TextField Country;

    @FXML
    private Button logInButton;

    @FXML
    private Button backBtn;

 @FXML
 void initialize(){
     DbManager dbManager=new DbManager();

        logInButton.setOnAction(event -> {
           boolean flag=dbManager.addUser(new User(loginText.getText(),FirstName.getText(),LastName.getText(),Country.getText(),passText.getText()));
                if(flag){
                    drawAlert("User created successfully");
                    logInButton.getScene().getWindow().hide();
                    FXMLLoader loader=new FXMLLoader();
                    loader.setLocation(getClass().getResource("/Authentication/AuthenticationActivity.fxml"));

                    try {
                        loader.load();
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                    Parent root=loader.getRoot();
                    Stage stage=new Stage();
                    stage.setScene(new Scene((root)));
                    stage.show();
                }else {
                    drawAlert("User already exists");
                }



        });


 }

}
