package rgr.analyzer;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import rgr.model.LogLevel;
import rgr.model.LogLine;

/** Подсчитывает количество записей каждого уровня. */
public class LevelCounter {
  public Map<LogLevel, Integer> count(List<LogLine> lines) {
    Map<LogLevel, Integer> counts = new EnumMap<LogLevel, Integer>(LogLevel.class);

    for (LogLevel level : LogLevel.values()) {
      counts.put(level, 0);
    }

    for (LogLine line : lines) {
      int currentCount = counts.get(line.getLevel());
      counts.put(line.getLevel(), currentCount + 1);
    }

    return counts;
  }
}
