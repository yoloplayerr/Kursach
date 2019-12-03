package workActivity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TableController {
    private ObservableList<User> usersData = FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> tableUsers;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> loginColumn;

    @FXML
    private TableColumn<?, ?> passwordColumn;

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private Button BackBtn;

    @FXML
    void initialize() {
        BackBtn.setOnAction(event -> {
            BackBtn.getScene().getWindow().hide();

            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/workActivity/workActivity.fxml"));

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
        });


       /* // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));

        // заполняем таблицу данными
        tableUsers.setItems(usersData);*/
    }
}
