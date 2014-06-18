package naivebayes.probability;

import static java.lang.Math.log;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import naivebayes.classification.Classification;
import naivebayes.vocabulary.Vocabulary;

public class ProbabilityCalculatorImpl implements ProbabilityCalculator {

	@Override
	public double calculate(final Vocabulary vocabulary, final Classification classification) {
		double probabilityValue = log(classification.getPriory());
		if (vocabulary != null && !vocabulary.isEmpty()) {
			final Set<Entry<String, Integer>> entrySet = vocabulary.getFrequencyMap().entrySet();
			final Iterator<Entry<String, Integer>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				final Entry<String, Integer> word = iterator.next();
				final Vocabulary classificationVocabulary = classification.getVocabulary();
				final String theWord = word.getKey().trim();
				if (theWord != null && theWord.length() > 0) {
					final int vocabularyTotal = vocabulary.getTotal();
					final double laplaceValue = laplace(classificationVocabulary, theWord, vocabularyTotal, 0.001);
					probabilityValue += vocabulary.frequencyOf(theWord) * log(laplaceValue);
				}
			}
		}
		return probabilityValue;
	}

	private double laplace(final Vocabulary vocabulary, final String term, final int flush, final double fit) {
		final double laplaceResult = 0.0;
		final double laplaceBase = vocabulary.getTotal() + flush;
		if (laplaceBase != 0) {
			return (vocabulary.frequencyOf(term) + fit) / laplaceBase;
		}
		return laplaceResult;
	}

}
