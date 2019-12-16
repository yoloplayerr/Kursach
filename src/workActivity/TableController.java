package workActivity;

import db.DbManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.BaseClass;
import sample.User;
import urlParser.Data;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TableController extends BaseClass {
    private ObservableList<Data> usersData = FXCollections.observableArrayList();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Data> tableUsers;

    @FXML
    private TableColumn<Data, String> tickerId;

    @FXML
    private TableColumn<Data, String> perId;

    @FXML
    private TableColumn<Data, String> dateId;

    @FXML
    private TableColumn<Data, Float> openId;

    @FXML
    private TableColumn<Data, Float> highId;

    @FXML
    private TableColumn<Data, Float> lowId;

    @FXML
    private TableColumn<Data, Float> closeId;

    @FXML
    private Button BackBtn;

    @FXML
    void initialize() {
        Platform.runLater(() -> {
            drawTable();
            back();
        });

    }
    private void back(){
        BackBtn.setOnAction(event -> {
            BackBtn.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/workActivity/workActivity.fxml"));
            Parent root = null;
            try {
                root = (Parent)fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            WorkController controller = fxmlLoader.<WorkController>getController();
            controller.setUserName(userName);
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
        });
    }

    private void drawTable() {
        tickerId.setCellValueFactory(new PropertyValueFactory<Data, String>("name"));
        perId.setCellValueFactory(new PropertyValueFactory<Data, String>("period"));
        dateId.setCellValueFactory(new PropertyValueFactory<Data, String>("date"));
        openId.setCellValueFactory(new PropertyValueFactory<Data, Float>("openValue"));
        highId.setCellValueFactory(new PropertyValueFactory<Data, Float>("highValue"));
        closeId.setCellValueFactory(new PropertyValueFactory<Data, Float>("closeValue"));
        lowId.setCellValueFactory(new PropertyValueFactory<Data, Float>("lowValue"));

        usersData.addAll(DbManager.getData(userName));
        tableUsers.setItems(usersData);
    }
}
