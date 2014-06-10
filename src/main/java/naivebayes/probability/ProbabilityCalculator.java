package naivebayes.probability;

import naivebayes.classification.Classification;
import naivebayes.vocabulary.Vocabulary;

public interface ProbabilityCalculator {

	double calculate(Vocabulary vocabulary, Classification classification);

}
