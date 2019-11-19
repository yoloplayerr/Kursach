package sample;

import db.DbManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthenticationController extends BaseClass {
    boolean flag=false;

    @FXML
    private PasswordField passText;

    @FXML
    private TextField loginText;

    @FXML
    private Button logIn;

    @FXML
    private Button signIn;



    @FXML
    void initialize(){


            signIn.setOnAction(event -> {
                signIn.getScene().getWindow().hide();

                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/regActivity.fxml"));

                try {
                    loader.load();
                }
                    catch(IOException e){
                    e.printStackTrace();
                }
                Parent root=loader.getRoot();
                Stage stage=new Stage();
                stage.setScene(new Scene((root)));
                stage.showAndWait();

            });

        logIn.setOnAction(event -> {
            DbManager dbManager=new DbManager();
            for(User usr:dbManager.getUsers()){
                if(usr.getUserName().equals(loginText.getText()) && usr.getPassword().equals(passText.getText())){
                    flag=true;
                }
            }
            if (flag==false){
                drawAlert("error");
            }else {

                logIn.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/workActivity.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene((root)));
                stage.showAndWait();
            }
        });




    }
}
