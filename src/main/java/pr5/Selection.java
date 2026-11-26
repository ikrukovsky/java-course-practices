package pr5;

/** Условие отбора датчика. */
@FunctionalInterface
public interface Selection {
  boolean test(Sensor sensor);
}
