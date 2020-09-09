package ru.covariance.jplotlib.engine;

import java.util.function.ToDoubleFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EulerLine {

  private final ToDoubleFunction<Point> derivative;
  private final double step;
  private final Point current;

  private EulerLine(ToDoubleFunction<Point> derivative, Point start, double step) {
    this.derivative = derivative;
    this.current = start;
    this.step = step;
  }

  private Point next() {
    Point result = new Point(current.getX(), current.getY());
    current.setY(current.getY() + derivative.applyAsDouble(current) * step);
    current.setX(current.getX() + step);
    return result;
  }

  private Stream<Point> stream() {
    return IntStream.iterate(0, i -> i).mapToObj(i -> next());
  }

  public static Stream<Point> stream(ToDoubleFunction<Point> derivative, Point start, double step) {
    return new EulerLine(derivative, start, step).stream();
  }
}
