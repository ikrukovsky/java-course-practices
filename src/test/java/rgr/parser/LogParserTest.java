package rgr.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import rgr.model.LogLevel;
import rgr.model.LogLine;

class LogParserTest {
  private final LogParser parser = new LogParser();

  @Test
  void parsesCorrectLine() {
    Optional<LogLine> result =
        parser.parse("2026-11-26T09:02:15;ERROR;database;Connection refused");

    assertTrue(result.isPresent());
    assertEquals(LocalDateTime.of(2026, 11, 26, 9, 2, 15), result.get().getTimestamp());
    assertEquals(LogLevel.ERROR, result.get().getLevel());
    assertEquals("database", result.get().getLogger());
    assertEquals("Connection refused", result.get().getMessage());
  }

  @Test
  void returnsEmptyForInvalidLine() {
    assertTrue(parser.parse("not;a;valid;timestamp;line").isEmpty());
  }

  @Test
  void parseLineThrowsForUnknownLevel() {
    assertThrows(
        IllegalArgumentException.class,
        () -> parser.parseLine("2026-11-26T09:02:15;TRACE;database;Message"));
  }
}
