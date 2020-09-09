package ru.covariance.jplotlib;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.stage.Stage;
import ru.covariance.jplotlib.Controller.Settings;
import ru.covariance.jplotlib.engine.Point;

public class Plotter extends Application {

  private static final Settings settings = new Controller.Settings();

  public static void main(String[] args) {
    launch(args);
  }

  // region line
  public static void addLine(String name, Stream<Point> pointStream) {
    settings.addLine(name, pointStream
        .unordered()
        .sequential()
        .map(i -> new XYChart.Data<Number, Number>(i.getX(), i.getY()))
        .collect(Collectors.toList()));
  }

  public static void addLine(String name, List<Point> pointList) {
    addLine(name, pointList.stream());
  }

  public static void addLine(String name, Point[] pointArray) {
    addLine(name, Arrays.stream(pointArray));
  }
  // endregion

  @Override
  public void start(Stage primaryStage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainPage.fxml"));
    fxmlLoader.setController(new Controller(settings));

    Parent root = fxmlLoader.load();

    primaryStage.setTitle("Plotter");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
}
