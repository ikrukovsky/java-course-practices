package rgr.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import rgr.model.LogLine;
import rgr.parser.LogParser;

/** Читает файл и преобразует корректные строки в объекты LogLine. */
public class LogFileReader {
  private static final Logger LOGGER = Logger.getLogger(LogFileReader.class.getName());
  private final LogParser parser;

  public LogFileReader() {
    this.parser = new LogParser();
  }

  public List<LogLine> readAll(Path path) throws IOException {
    List<LogLine> result = new ArrayList<LogLine>();
    List<String> sourceLines = Files.readAllLines(path);

    for (int index = 0; index < sourceLines.size(); index++) {
      Optional<LogLine> parsedLine = parser.parse(sourceLines.get(index));
      if (parsedLine.isPresent()) {
        result.add(parsedLine.get());
      } else {
        LOGGER.warning("Строка " + (index + 1) + " пропущена");
      }
    }

    LOGGER.info("Прочитано корректных записей: " + result.size());
    return result;
  }
}
