package rgr.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import rgr.model.LogLevel;
import rgr.model.LogLine;

/** Преобразует текстовую строку в объект LogLine. */
public class LogParser {
  private static final Logger LOGGER = Logger.getLogger(LogParser.class.getName());

  /** Разбирает строку. При ошибке возвращает Optional.empty(), не прерывая обработку файла. */
  public Optional<LogLine> parse(String sourceLine) {
    try {
      return Optional.of(parseLine(sourceLine));
    } catch (IllegalArgumentException exception) {
      LOGGER.log(Level.WARNING, "Некорректная строка лога: " + sourceLine, exception);
      return Optional.empty();
    }
  }

  /** Разбирает строку или выбрасывает IllegalArgumentException, если формат неверен. */
  public LogLine parseLine(String sourceLine) {
    if (sourceLine == null) {
      throw new IllegalArgumentException("Строка лога равна null");
    }

    String[] fields = sourceLine.split(";", 4);
    if (fields.length != 4) {
      throw new IllegalArgumentException("Ожидается четыре поля, разделённые ';'");
    }

    try {
      LocalDateTime timestamp = LocalDateTime.parse(fields[0].trim());
      LogLevel level = LogLevel.valueOf(fields[1].trim().toUpperCase());
      String loggerName = fields[2].trim();
      String message = fields[3].trim();

      if (loggerName.isEmpty() || message.isEmpty()) {
        throw new IllegalArgumentException("Имя logger и сообщение не должны быть пустыми");
      }

      return new LogLine(timestamp, level, loggerName, message);
    } catch (DateTimeParseException | IllegalArgumentException exception) {
      throw new IllegalArgumentException("Не удалось разобрать строку лога", exception);
    }
  }
}
