package workActivity;

import db.DbManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.BaseClass;
import urlParser.FileDownload;
import urlParser.Parser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WorkController extends BaseClass {

    final FileChooser fil_chooser = new FileChooser();
    @FXML
    private ResourceBundle resources;

    @FXML
    private Button graphBtn;

    @FXML
    private URL location;

    @FXML
    private Button DataBtn;

    @FXML
    private Button downloadBtn;

    @FXML
    private TextField urlField;

    @FXML
    private Button fileChooser;


    @FXML
    void initialize() {
        showGraph();
        showDataPage();
        selectFiles();
        downloadData();
    }

    private void showDataPage() {
        DataBtn.setOnAction(event -> {
            DataBtn.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/workActivity/table.fxml"));
            Parent root = null;
            try {
                root = (Parent)fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            TableController controller = fxmlLoader.<TableController>getController();
            controller.setUserName(userName);
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
        });

    }


    private void showGraph() {
        graphBtn.setOnAction(event -> {
            graphBtn.getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/workActivity/graph.fxml"));
            Parent root = null;
            try {
                root = (Parent)fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            GraphController controller = fxmlLoader.<GraphController>getController();
            controller.setUserName(userName);
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
        });
    }

    private void downloadData() {
        downloadBtn.setOnAction(event -> {
            FileDownload fileDownload = new FileDownload();
            if(fileDownload.fileDownload(urlField.getText(),userName)){
                DbManager.insertLogs(userName,"Пользователь скачал данные");
                drawAlert("Данные скачены");
            }else{
                drawAlert("Файл уже существует");
            }
        });
    }

    private void selectFiles() {
        fileChooser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = fil_chooser.showOpenDialog(fileChooser.getScene().getWindow());
                Parser parser=new Parser();
                if(parser.parser(userName,file)){
                    DbManager.insertLogs(userName,"Пользователь загрузил данные");
                    drawAlert("Данные успешно загружены");
                }
            }
        });
    }

}
