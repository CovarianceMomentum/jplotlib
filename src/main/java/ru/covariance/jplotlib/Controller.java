package ru.covariance.jplotlib;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class Controller {

  static class Settings {

    private final List<List<Data<Number, Number>>> lines = new ArrayList<>();
    private final List<String> lineNames = new ArrayList<>();

    public void addLine(String name, List<Data<Number, Number>> line) {
      lineNames.add(name);
      lines.add(line);
    }
  }

  public Controller(Settings settings) {
    this.settings = settings;
  }

  private final Settings settings;

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private LineChart<Number, Number> mainGraph;

  @FXML
  private NumberAxis xAxis;

  @FXML
  private NumberAxis yAxis;

  @FXML
  void initialize() {
    List<Series<Number, Number>> mainGraphData = mainGraph.getData();

    for (int i = 0; i != settings.lineNames.size(); ++i) {
      XYChart.Series<Number, Number> series = new XYChart.Series<>();
      series.setName(settings.lineNames.get(i));
      series.getData().addAll(settings.lines.get(i));
      mainGraphData.add(series);
    }
  }
}
