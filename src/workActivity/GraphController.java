package workActivity;

import db.DbManager;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.BaseClass;
import urlParser.Data;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;


public class GraphController extends BaseClass {

    @FXML
    private LineChart<Number, Number> chart;

    XYChart.Series<Number, Number> series;

    @FXML
    private Button CloseBtn;

    @FXML
    private Button OpenBtn;

    @FXML
    private Button HighBtn;

    @FXML
    private Button LowBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private Button backBtn;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private NumberAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private ComboBox<String> methodSelecter;

    @FXML
    private DatePicker datePickerFrom;

    @FXML
    private DatePicker datePickerUntil;

    ArrayList<Data> dataList;

    public float data[];

    @FXML
    private Button mnkBtn;

    private String btnFlag;


    @FXML
    void initialize() {
        Platform.runLater(() -> {
            init();
            closeBtn();
            openBtn();
            lowBtn();
            highBtn();
            back();
            mnkBtn();
        });
        comboBoxListener();
        clear();
    }

    private void init() {
        dataList = DbManager.getData(userName);
    }

    private void drawGraph(String valueType) {
        ChangeListener<Color> listener = (obs, oldColor, newColor) ->
                updateStyles(chart, colorPicker.getValue());
        colorPicker.valueProperty().addListener(listener);
        series = new XYChart.Series<>();
        int i = 0;
        if (datePickerFrom.getValue() != null && datePickerUntil.getValue() != null) {
            for (Data d : dataList) {
                if (Date.valueOf(datePickerFrom.getValue()).getTime() < Date.valueOf(d.getDate()).getTime() && Date.valueOf(datePickerUntil.getValue()).getTime() > Date.valueOf(d.getDate()).getTime()) {
                    series.getData().add(new XYChart.Data<>(i, d.value(valueType)));
                    i++;
                }
            }
        } else {
            for (Data d : dataList) {
                series.getData().add(new XYChart.Data<>(i, d.value(valueType)));
                i++;
            }
        }
        x.setLabel("Days");
        y.setLabel("Values");
        chart.setCreateSymbols(false);
        series.setName(valueType);
        chart.getData().add(series);
    }

    private void drawSqr(String valueType) {
        series = new XYChart.Series<>();
        data =MNK.sqrtStat(dataList, valueType);
        for (int i = 0; i < dataList.size(); i++) {
            series.getData().add(new XYChart.Data<>(i, i * i * data[0] + i * data[1] + data[2]));
        }
        DbManager.insertResults(userName,"sqr","a="+data[0]+",b="+data[1]+",c="+data[2]);
        series.setName("MNK Sqr");
        chart.getData().add(series);
    }

    private void drawExp(String valueType) {
        series = new XYChart.Series<>();
        data= MNK.expStat(dataList, valueType);
        for (int i = 0; i < dataList.size(); i++) {
            series.getData().add(new XYChart.Data<>(i, Math.exp(data[0]) * Math.exp(data[1] * i)));
        }
        DbManager.insertResults(userName,"exp","a="+data[0]+",b="+data[1]);
        series.setName("MNK Exp");
        chart.getData().add(series);
    }

    private void drawLine(String valueType) {
        series = new XYChart.Series<>();
       data = MNK.mnk(dataList, valueType);
        series.getData().add(new XYChart.Data<>(0, (-1) * data[0] + data[1]));
        series.getData().add(new XYChart.Data<>(dataList.size(), (dataList.size()) * data[0] + data[1]));
        DbManager.insertResults(userName,"line","a="+data[0]+",b="+data[1]);
        series.setName("MNK Line");
        chart.getData().add(series);
    }
    private void drawHyp(String valueType) {
        series = new XYChart.Series<>();
        data = MNK.hyperStat(dataList, valueType);
        for (int i=1;i<dataList.size();i++) {
            series.getData().add(new XYChart.Data<>(i, data[0] + data[1] / i));
        }
        DbManager.insertResults(userName,"hyp","a="+data[0]+",b="+data[1]);
        series.setName("MNK ");
        chart.getData().add(series);
    }

    private void comboBoxListener() {
        ObservableList<String> methods = FXCollections.observableArrayList();
        methods.add("sqr");
        methods.add("exp");
        methods.add("line");
        methods.add("hyp");
        methodSelecter.setItems(methods);
    }

    private void mnkBtn() {
        mnkBtn.setOnAction(event -> {
            switch (methodSelecter.getSelectionModel().getSelectedItem().toString()) {
                case ("sqr"):
                    drawSqr(btnFlag);
                    break;
                case ("exp"):
                    drawExp(btnFlag);
                    break;
                case ("line"):
                    drawLine(btnFlag);
                    break;
                case ("hyp"):
                    drawHyp(btnFlag);
                    break;
            }
        });
    }

    private void closeBtn() {
        CloseBtn.setOnAction(event -> {
            chart.getData().clear();
            btnFlag = CloseBtn.getText();
            drawGraph(CloseBtn.getText());
        });
    }

    private void openBtn() {
        OpenBtn.setOnAction(event -> {
            chart.getData().clear();
            btnFlag = OpenBtn.getText();
            drawGraph(OpenBtn.getText());
        });
    }

    private void lowBtn() {
        LowBtn.setOnAction(event -> {
            chart.getData().clear();
            btnFlag = LowBtn.getText();
            drawGraph(LowBtn.getText());
        });
    }

    private void highBtn() {
        HighBtn.setOnAction(event -> {
            chart.getData().clear();
            btnFlag = HighBtn.getText();
            drawGraph(HighBtn.getText());
        });
    }

    private void clear() {
        clearBtn.setOnAction(event -> {
            chart.getData().clear();
        });
    }

    private void updateStyles(Node node, Color color1) {
        node.setStyle(String.format("CHART_COLOR_1: %s ;", format(color1)));
    }

    private String format(Color c) {
        int r = (int) (255 * c.getRed());
        int g = (int) (255 * c.getGreen());
        int b = (int) (255 * c.getBlue());

        return String.format("#%02x%02x%02x", r, g, b);
    }

    private void back() {
        backBtn.setOnAction(event -> {
            backBtn.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/workActivity/workActivity.fxml"));
            Parent root = null;
            try {
                root = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            WorkController controller = fxmlLoader.<WorkController>getController();
            controller.setUserName(userName);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        });
    }


}
