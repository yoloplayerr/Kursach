package workActivity;

import db.DbManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import sample.BaseClass;
import urlParser.Data;

import java.awt.*;


public class GraphController {


    public static String userName;

    public static void setUserName(String uName) {
        GraphController.userName = uName;
    }

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
    void initialize() {
        Platform.runLater(() -> {
            closeBtn();
            openBtn();
            lowBtn();
            highBtn();
        });
        clear();
    }
    private void drawGraph(String valueType) {
        series = new XYChart.Series<>();
        Node fill = series.getNode().lookup(".chart-series-area-fill"); // only for AreaChart
        Node line = series.getNode().lookup(".chart-series-area-line");

        Color color = Color.RED; // or any other color

        String rgb = String.format("%d, %d, %d",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));

        fill.setStyle("-fx-fill: rgba(" + rgb + ", 0.15);");
        line.setStyle("-fx-stroke: rgba(" + rgb + ", 1.0);");

        for (Data d : DbManager.getData(userName)) {
            series.getData().add(new XYChart.Data<>(d.value(valueType), Integer.parseInt(d.getDate().substring(5, 7))));
        }
        series.setName(valueType);
        chart.getData().add(series);
    }

    private void closeBtn() {
        CloseBtn.setOnAction(event -> {
            drawGraph(CloseBtn.getText());
        });
    }

    private void openBtn() {
        OpenBtn.setOnAction(event -> {
            drawGraph(OpenBtn.getText());
        });
    }

    private void lowBtn() {
        LowBtn.setOnAction(event -> {
            drawGraph(LowBtn.getText());
        });
    }

    private void highBtn() {
        HighBtn.setOnAction(event -> {
            drawGraph(HighBtn.getText());
        });
    }

    private void clear() {
        clearBtn.setOnAction(event -> {
            chart.getData().clear();
        });
    }


}
