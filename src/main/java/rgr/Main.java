package rgr;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import rgr.analyzer.AnalysisResult;
import rgr.analyzer.DefaultLogAnalyzer;
import rgr.analyzer.ErrorPeriodSelector;
import rgr.model.LogLevel;
import rgr.model.LogLine;
import rgr.reader.LogFileReader;
import rgr.writer.ResultWriter;

/** Точка входа приложения для анализа логов. */
public class Main {
  private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

  private Main() {}

  public static void main(String[] args) {
    Path inputPath = args.length > 0 ? Path.of(args[0]) : Path.of("app.log");
    Path outputPath = args.length > 1 ? Path.of(args[1]) : Path.of("results", "analysis.txt");

    LocalDateTime from = LocalDateTime.of(2026, 1, 1, 0, 0);
    LocalDateTime to = LocalDateTime.of(2026, 12, 31, 23, 59, 59);

    LOGGER.info("Запуск анализа логов приложения");

    try {
      LogFileReader reader = new LogFileReader();
      List<LogLine> lines = reader.readAll(inputPath);

      DefaultLogAnalyzer analyzer = new DefaultLogAnalyzer(new ErrorPeriodSelector(from, to));
      AnalysisResult result = analyzer.analyze(lines);

      String report = createReport(result);
      new ResultWriter().write(outputPath, report);

      System.out.println(report);
      LOGGER.info("Анализ завершён. Отчёт сохранён: " + outputPath.toAbsolutePath());
    } catch (IOException | IllegalArgumentException exception) {
      LOGGER.log(Level.SEVERE, "Не удалось выполнить анализ", exception);
    }
  }

  private static String createReport(AnalysisResult result) {
    StringBuilder report = new StringBuilder();
    report.append("АНАЛИЗ ЛОГОВ ПРИЛОЖЕНИЯ").append(System.lineSeparator());
    report.append("Количество записей по уровням:").append(System.lineSeparator());

    Map<LogLevel, Integer> statistics = result.getLevelStatistics();
    for (LogLevel level : LogLevel.values()) {
      report
          .append("  ")
          .append(level)
          .append(": ")
          .append(statistics.get(level))
          .append(System.lineSeparator());
    }

    report
        .append(System.lineSeparator())
        .append("Топ-5 сообщений об ошибках:")
        .append(System.lineSeparator());
    List<String> topErrors = result.getTopErrors();
    if (topErrors.isEmpty()) {
      report.append("  Ошибок нет").append(System.lineSeparator());
    } else {
      for (String error : topErrors) {
        report.append("  ").append(error).append(System.lineSeparator());
      }
    }

    report
        .append(System.lineSeparator())
        .append("Ошибки за выбранный период:")
        .append(System.lineSeparator());
    List<LogLine> periodErrors = result.getPeriodErrors();
    if (periodErrors.isEmpty()) {
      report.append("  Записей нет").append(System.lineSeparator());
    } else {
      for (LogLine line : periodErrors) {
        report.append("  ").append(line).append(System.lineSeparator());
      }
    }

    return report.toString();
  }
}
