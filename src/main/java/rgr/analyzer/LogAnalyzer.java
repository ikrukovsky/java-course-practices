package rgr.analyzer;

import java.util.List;
import rgr.model.LogLine;

/** Общий интерфейс анализа записей журнала. */
public interface LogAnalyzer {
  AnalysisResult analyze(List<LogLine> lines);
}
