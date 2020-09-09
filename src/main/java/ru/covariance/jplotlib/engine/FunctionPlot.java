package ru.covariance.jplotlib.engine;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FunctionPlot {

  private final Function<Double, Double> function;
  private final Point current;
  private final double step;

  private FunctionPlot(Function<Double, Double> function, Point start, double step) {
    this.function = function;
    this.current = start;
    this.step = step;
  }

  private Point next() {
    Point result = new Point(current.getX(), current.getY());
    current.setX(current.getX() + step);
    current.setY(function.apply(current.getX()));
    return result;
  }

  private Stream<Point> stream() {
    return IntStream.iterate(0, i -> i)
        .mapToObj(i -> next());
  }

  public static Stream<Point> stream(Function<Double, Double> function, Point start, double step) {
    return new FunctionPlot(function, start, step).stream();
  }
}
