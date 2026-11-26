package pr5;

import java.util.Locale;

/** Датчик освещённости: параметр калибровки задаёт процентную поправку. */
public class Light extends Sensor implements Readable, Calibratable {
  public Light(String id, String location, double rawValue, double percentCorrection) {
    super(id, location, rawValue, percentCorrection);
  }

  @Override
  public double calibratedValue() {
    return calibrate(getRawValue());
  }

  @Override
  public double calibrate(double rawValue) {
    return rawValue * (1.0 + getCalibrationParameter() / 100.0);
  }

  @Override
  public String read() {
    return String.format(Locale.ROOT, "%s: %.2f лк", getId(), calibratedValue());
  }
}
