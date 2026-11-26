package pr5;

/** Роль объекта, который умеет калибровать исходное значение. */
public interface Calibratable {
  double calibrate(double rawValue);
}
