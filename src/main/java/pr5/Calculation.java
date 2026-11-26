package pr5;

/** Функция расчёта числового значения для датчика. */
@FunctionalInterface
public interface Calculation {
  double calculate(Sensor sensor);
}
