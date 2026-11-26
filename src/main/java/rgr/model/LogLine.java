package rgr.model;

import java.time.LocalDateTime;

/** Одна запись журнала приложения. */
public class LogLine {
  private final LocalDateTime timestamp;
  private final LogLevel level;
  private final String logger;
  private final String message;

  public LogLine(LocalDateTime timestamp, LogLevel level, String logger, String message) {
    if (timestamp == null || level == null || logger == null || message == null) {
      throw new IllegalArgumentException("Поля записи лога не должны быть null");
    }
    this.timestamp = timestamp;
    this.level = level;
    this.logger = logger;
    this.message = message;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public LogLevel getLevel() {
    return level;
  }

  public String getLogger() {
    return logger;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return timestamp + ";" + level + ";" + logger + ";" + message;
  }
}
