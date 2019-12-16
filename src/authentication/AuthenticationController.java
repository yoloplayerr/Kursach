package authentication;

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
import urlParser.FileDownload;
import workActivity.WorkController;

import java.io.IOException;

public class AuthenticationController extends BaseClass {


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
                loader.setLocation(getClass().getResource("/reg/regActivity.fxml"));
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
            if (dbManager.chekUser(loginText.getText(),passText.getText())==false){
                drawAlert("error");
            }else {
                logIn.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/workActivity/workActivity.fxml"));
                Parent root = null;
                try {
                    root = (Parent)fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                WorkController controller = fxmlLoader.<WorkController>getController();
                controller.setUserName(loginText.getText());
                Scene scene = new Scene(root);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.show();
            }
        });




    }
}
