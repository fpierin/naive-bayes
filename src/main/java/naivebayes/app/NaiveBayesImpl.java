package naivebayes.app;

import java.util.LinkedHashMap;
import java.util.List;

import naivebayes.classification.Classification;
import naivebayes.classification.ClassificationContainer;
import naivebayes.probability.ProbabilityCalculator;
import naivebayes.vocabulary.Vocabulary;

public class NaiveBayesImpl implements NaiveBayes {

	private final ClassificationContainer classificationList;
	private final ProbabilityCalculator probabilityCalculator;

	public NaiveBayesImpl(final ClassificationContainer classificationList, final ProbabilityCalculator probabilityCalculator) {
		if (classificationList == null || classificationList.isEmpty()) {
			throw new IllegalArgumentException("Naive Bayes classification list is null");
		}
		this.classificationList = classificationList;
		this.probabilityCalculator = probabilityCalculator;
	}

	public String classify(final List<String> words) {
		final List<Classification> classifications = classificationList.getClassifications();
		String resultClassification = null;
		double resultProbability = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < classifications.size(); i++) {
			final Classification classification = classificationList.get(i);
			final double classificationProbability = probabilityCalculator.calculate(from(words), classification);
			if (classificationProbability > resultProbability) {
				resultProbability = classificationProbability;
				resultClassification = classification.getName();
			}
		}
		return resultClassification;
	}

	private Vocabulary from(final List<String> words) {
		final Vocabulary vocabulary = new Vocabulary();
		vocabulary.setFrequencyMap(new LinkedHashMap<String, Integer>());
		for (final String value : words) {
			vocabulary.add(value);
		}
		return vocabulary;
	}

}
