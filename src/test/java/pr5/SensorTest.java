package pr5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SensorTest {
  @Test
  void temperatureUsesOffsetForCalibration() {
    Sensor sensor = new Temp("T-01", "Lab", 20.0, 1.5);
    assertEquals(21.5, sensor.calibratedValue(), 0.000001);
  }

  @Test
  void pressureUsesCalibrationFactor() {
    Sensor sensor = new Pressure("P-01", "Lab", 100.0, 1.02);
    assertEquals(102.0, sensor.calibratedValue(), 0.000001);
  }

  @Test
  void lightUsesPercentageCorrection() {
    Sensor sensor = new Light("L-01", "Lab", 200.0, 2.5);
    assertEquals(205.0, sensor.calibratedValue(), 0.000001);
  }

  @Test
  void calculationStrategiesProduceTheSameSum() {
    Sensor[] sensors = {
      new Temp("T-01", "Lab", 20.0, 1.0),
      new Pressure("P-01", "Lab", 100.0, 1.1),
      new Light("L-01", "Lab", 200.0, 5.0)
    };
    SensorService service = new SensorService(sensors, new Readable[0], new Calibratable[0]);
    Calculation anonymous =
        new Calculation() {
          @Override
          public double calculate(Sensor sensor) {
            return sensor.calibratedValue();
          }
        };
    Calculation lambda = sensor -> sensor.calibratedValue();

    assertEquals(
        service.sumCalculations(sensors, anonymous),
        service.sumCalculations(sensors, lambda),
        0.000001);
  }

  @Test
  void selectionWorksForNonEmptyAndEmptyResults() {
    Sensor sensor = new Temp("T-01", "Lab", 20.0, 1.0);
    Selection nonEmpty = item -> item.calibratedValue() > 10.0;
    Selection empty = item -> item.calibratedValue() > 100.0;

    assertTrue(nonEmpty.test(sensor));
    assertFalse(empty.test(sensor));
  }

  @Test
  void readableDefaultMethodAddsLabel() {
    Readable readable = new Temp("T-01", "Lab", 20.0, 1.0);
    assertTrue(readable.readWithLabel().startsWith("Считанное показание: "));
  }
}
