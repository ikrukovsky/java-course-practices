package pr5;

/** Роль датчика, который может предоставить текстовое представление показания. */
public interface Readable {
  String read();

  default String readWithLabel() {
    return "Считанное показание: " + read();
  }
}
