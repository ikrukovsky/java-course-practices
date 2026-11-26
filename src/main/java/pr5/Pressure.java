package pr5;

import java.util.Locale;

/** Датчик давления: параметр калибровки является множителем. */
public class Pressure extends Sensor implements Readable, Calibratable {
  public Pressure(String id, String location, double rawValue, double factor) {
    super(id, location, rawValue, factor);
  }

  @Override
  public double calibratedValue() {
    return calibrate(getRawValue());
  }

  @Override
  public double calibrate(double rawValue) {
    return rawValue * getCalibrationParameter();
  }

  @Override
  public String read() {
    return String.format(Locale.ROOT, "%s: %.2f кПа", getId(), calibratedValue());
  }
}
