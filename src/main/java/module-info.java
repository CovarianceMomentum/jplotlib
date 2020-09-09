module ru.covariance.jplotlib {
  requires javafx.fxml;
  requires javafx.base;
  requires javafx.controls;

  opens ru.covariance.jplotlib to javafx.fxml;

  exports ru.covariance.jplotlib;
  exports ru.covariance.jplotlib.engine;
}