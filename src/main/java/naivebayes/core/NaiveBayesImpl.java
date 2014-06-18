package naivebayes.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import naivebayes.classification.Classification;
import naivebayes.classification.ClassificationContainer;
import naivebayes.probability.ProbabilityCalculator;
import naivebayes.vocabulary.Vocabulary;

public class NaiveBayesImpl implements NaiveBayes {

	private final ClassificationContainer classificationList;
	private final ProbabilityCalculator probabilityCalculator;

	public NaiveBayesImpl(final ClassificationContainer classificationList, final ProbabilityCalculator probabilityCalculator) {
		this.classificationList = classificationList;
		this.probabilityCalculator = probabilityCalculator;
	}

	@Override
	public Map<String, Double> classify(final List<String> bagOfWords) {
		double withProbabilitySum = 0.0;
		final List<Classification> classifications = classificationList.getClassifications();
		final Map<String, Double> result = new HashMap<>();
		for (int i = 0; i < classifications.size(); i++) {
			final Classification classification = classificationList.get(i);
			final double probabilityLog = probabilityCalculator.calculate(from(bagOfWords), classification);
			withProbabilitySum += probabilityLog;
			result.put(classification.getName(), probabilityLog);
		}
		return normalization(result, withProbabilitySum);
	}

	/**
	 * @param result
	 * @param withProbabilitySum
	 * @return
	 */
	private Map<String, Double> normalization(final Map<String, Double> result, final double withProbabilitySum) {
		if (withProbabilitySum != 0) {
			final Set<Entry<String, Double>> entrySet = result.entrySet();
			final Iterator<Entry<String, Double>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				final Entry<String, Double> next = iterator.next();
				final double realProbability = 1 - next.getValue() / withProbabilitySum;
				result.put(next.getKey(), realProbability);
				next.getValue();
			}
		}
		return result;
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
