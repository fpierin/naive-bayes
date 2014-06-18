package naivebayes.core;

import java.util.List;
import java.util.Map;

public interface NaiveBayes {

	Map<String, Double> classify(List<String> bagOfWords);

}
