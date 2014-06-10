package naivebayes.probability;

import static java.lang.Math.log;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import naivebayes.classification.Classification;
import naivebayes.vocabulary.Vocabulary;

public class ProbabilityCalculatorImpl implements ProbabilityCalculator {

	public double calculate(final Vocabulary vocabulary, final Classification classification) {
		double probabilityValue = 0.0;
		final double fit = 0.5;
		if (vocabulary != null && !vocabulary.isEmpty()) {
			final Set<Entry<String, Integer>> entrySet = vocabulary.getFrequencyMap().entrySet();
			final Iterator<Entry<String, Integer>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				final Entry<String, Integer> word = iterator.next();
				final double laplaceValue = laplace(classification.getVocabulary(), word.getKey(), vocabulary.getTotal(), fit);
				probabilityValue += log(laplaceValue); 
			}
		}
		return probabilityValue;
	}

	private double laplace(final Vocabulary vocabulary, final String term, final int flush, final double fit) {
		final double laplaceResult = 0;
		final double laplaceBase = vocabulary.getTotal() + flush;
		if (laplaceBase != 0 && vocabulary.contains(term)) {
			return (vocabulary.frequencyOf(term) + fit) / laplaceBase;
		}
		return laplaceResult;
	}

}
