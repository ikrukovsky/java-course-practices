package pr5;

import java.util.Locale;

/** Датчик температуры: параметр калибровки является поправкой в градусах. */
public class Temp extends Sensor implements Readable, Calibratable {
  public Temp(String id, String location, double rawValue, double offset) {
    super(id, location, rawValue, offset);
  }

  @Override
  public double calibratedValue() {
    return calibrate(getRawValue());
  }

  @Override
  public double calibrate(double rawValue) {
    return rawValue + getCalibrationParameter();
  }

  @Override
  public String read() {
    return String.format(Locale.ROOT, "%s: %.2f °C", getId(), calibratedValue());
  }
}
