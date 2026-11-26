package rgr.analyzer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import rgr.model.LogLevel;
import rgr.model.LogLine;

/** Выбирает ошибки, попавшие во временной интервал. */
public class ErrorPeriodSelector {
  private final LocalDateTime from;
  private final LocalDateTime to;

  public ErrorPeriodSelector(LocalDateTime from, LocalDateTime to) {
    if (from == null || to == null || from.isAfter(to)) {
      throw new IllegalArgumentException("Некорректные границы периода");
    }
    this.from = from;
    this.to = to;
  }

  public List<LogLine> select(List<LogLine> lines) {
    List<LogLine> result = new ArrayList<LogLine>();

    for (LogLine line : lines) {
      boolean isError = line.getLevel() == LogLevel.ERROR;
      boolean isInPeriod = !line.getTimestamp().isBefore(from) && !line.getTimestamp().isAfter(to);

      if (isError && isInPeriod) {
        result.add(line);
      }
    }

    return result;
  }
}
