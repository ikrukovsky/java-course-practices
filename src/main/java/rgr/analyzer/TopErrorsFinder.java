package rgr.analyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rgr.model.LogLevel;
import rgr.model.LogLine;

/** Находит наиболее частые сообщения среди записей уровня ERROR. */
public class TopErrorsFinder {
  public List<String> find(List<LogLine> lines, int limit) {
    Map<String, Integer> frequencies = new HashMap<String, Integer>();

    for (LogLine line : lines) {
      if (line.getLevel() == LogLevel.ERROR) {
        String message = line.getMessage();
        Integer currentCount = frequencies.get(message);
        if (currentCount == null) {
          frequencies.put(message, 1);
        } else {
          frequencies.put(message, currentCount + 1);
        }
      }
    }

    List<Map.Entry<String, Integer>> entries =
        new ArrayList<Map.Entry<String, Integer>>(frequencies.entrySet());
    Collections.sort(
        entries,
        new Comparator<Map.Entry<String, Integer>>() {
          @Override
          public int compare(Map.Entry<String, Integer> first, Map.Entry<String, Integer> second) {
            int byFrequency = second.getValue().compareTo(first.getValue());
            if (byFrequency != 0) {
              return byFrequency;
            }
            return first.getKey().compareTo(second.getKey());
          }
        });

    List<String> result = new ArrayList<String>();
    int resultSize = Math.min(Math.max(limit, 0), entries.size());
    for (int index = 0; index < resultSize; index++) {
      Map.Entry<String, Integer> entry = entries.get(index);
      result.add(entry.getKey() + " (" + entry.getValue() + ")");
    }
    return result;
  }
}
