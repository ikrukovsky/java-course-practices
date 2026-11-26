package rgr.analyzer;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import rgr.model.LogLevel;
import rgr.model.LogLine;

/** Хранит результаты анализа файла журнала. */
public class AnalysisResult {
  private final Map<LogLevel, Integer> levelStatistics;
  private final List<String> topErrors;
  private final List<LogLine> periodErrors;

  public AnalysisResult(
      Map<LogLevel, Integer> levelStatistics, List<String> topErrors, List<LogLine> periodErrors) {
    this.levelStatistics = new EnumMap<LogLevel, Integer>(LogLevel.class);
    this.levelStatistics.putAll(levelStatistics);
    this.topErrors = new ArrayList<String>(topErrors);
    this.periodErrors = new ArrayList<LogLine>(periodErrors);
  }

  public Map<LogLevel, Integer> getLevelStatistics() {
    return new EnumMap<LogLevel, Integer>(levelStatistics);
  }

  public List<String> getTopErrors() {
    return new ArrayList<String>(topErrors);
  }

  public List<LogLine> getPeriodErrors() {
    return new ArrayList<LogLine>(periodErrors);
  }
}
