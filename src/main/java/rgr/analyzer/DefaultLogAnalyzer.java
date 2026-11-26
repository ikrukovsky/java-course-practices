package rgr.analyzer;

import java.util.List;
import java.util.Map;
import rgr.model.LogLevel;
import rgr.model.LogLine;

/** Выполняет все предусмотренные вариантом 11 виды анализа. */
public class DefaultLogAnalyzer implements LogAnalyzer {
  private final LevelCounter levelCounter;
  private final TopErrorsFinder topErrorsFinder;
  private final ErrorPeriodSelector periodSelector;

  public DefaultLogAnalyzer(ErrorPeriodSelector periodSelector) {
    this.levelCounter = new LevelCounter();
    this.topErrorsFinder = new TopErrorsFinder();
    this.periodSelector = periodSelector;
  }

  @Override
  public AnalysisResult analyze(List<LogLine> lines) {
    Map<LogLevel, Integer> levelStatistics = levelCounter.count(lines);
    List<String> topErrors = topErrorsFinder.find(lines, 5);
    List<LogLine> periodErrors = periodSelector.select(lines);

    return new AnalysisResult(levelStatistics, topErrors, periodErrors);
  }
}
