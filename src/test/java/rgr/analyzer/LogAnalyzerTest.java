package rgr.analyzer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import rgr.model.LogLevel;
import rgr.model.LogLine;

class LogAnalyzerTest {
  private final List<LogLine> lines =
      Arrays.asList(
          new LogLine(LocalDateTime.of(2026, 11, 26, 9, 0), LogLevel.INFO, "app", "Started"),
          new LogLine(
              LocalDateTime.of(2026, 11, 26, 9, 1), LogLevel.ERROR, "db", "Connection refused"),
          new LogLine(
              LocalDateTime.of(2026, 11, 26, 9, 2), LogLevel.ERROR, "db", "Connection refused"),
          new LogLine(
              LocalDateTime.of(2026, 11, 26, 9, 3), LogLevel.WARN, "cache", "Cache warning"));

  @Test
  void countsRecordsByLevel() {
    LevelCounter counter = new LevelCounter();

    assertEquals(1, counter.count(lines).get(LogLevel.INFO));
    assertEquals(1, counter.count(lines).get(LogLevel.WARN));
    assertEquals(2, counter.count(lines).get(LogLevel.ERROR));
  }

  @Test
  void findsMostFrequentErrorMessages() {
    TopErrorsFinder finder = new TopErrorsFinder();

    assertEquals(Arrays.asList("Connection refused (2)"), finder.find(lines, 5));
  }

  @Test
  void selectsErrorsInsideInclusivePeriod() {
    ErrorPeriodSelector selector =
        new ErrorPeriodSelector(
            LocalDateTime.of(2026, 11, 26, 9, 1), LocalDateTime.of(2026, 11, 26, 9, 2));

    assertEquals(2, selector.select(lines).size());
  }
}
