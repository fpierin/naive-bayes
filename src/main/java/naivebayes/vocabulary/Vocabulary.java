package naivebayes.vocabulary;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Vocabulary {

	private Map<String, Integer> frequencyMap = new HashMap<String, Integer>();
	private int total = 0;

	public Vocabulary() {
	}

	public void add(final String value) {
		final int newValue = getFrequencyMap().containsKey(value) ? getFrequencyMap().get(value) + 1 : 1;
		getFrequencyMap().put(value, newValue);
		total++;
	}

	public void add(final List<String> words) {
		if (words != null && !words.isEmpty()) {
			for (final String string : words) {
				add(string);
			}
		}
	}

	public void remove(final String value) {
		if (getFrequencyMap().containsKey(value)) {
			final int keyValue = getFrequencyMap().get(value);
			if (keyValue > 1) {
				getFrequencyMap().put(value, keyValue - 1);
			} else {
				getFrequencyMap().remove(value);
			}
		}
		total--;
	}

	public int frequencyOf(final String value) {
		if (getFrequencyMap().containsKey(value)) {
			return getFrequencyMap().get(value);
		}
		return 0;
	}

	public boolean isEmpty() {
		return frequencyMap.isEmpty();
	}

	private void calculateTotalOf(final Map<String, Integer> frequencyMap) {
		final Collection<Integer> values = frequencyMap.values();
		total = 0;
		for (final int value : values) {
			total += value;
		}
	}

	public boolean contains(final String value) {
		return frequencyMap.containsKey(value);
	}

	public Map<String, Integer> getFrequencyMap() {
		return frequencyMap;
	}

	public void setFrequencyMap(final Map<String, Integer> frequencyMap) {
		this.frequencyMap = frequencyMap;
		calculateTotalOf(frequencyMap);
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(final int total) {
		this.total = total;
	}

	public static Vocabulary fromFile(final String string) throws Exception {
		final Vocabulary vocabulary = new Vocabulary();
		final List<String> words = VocabularyGenerator.extractWordsFromFile(string);
		vocabulary.add(words);
		return vocabulary;
	}

	public static Vocabulary fromString(final String string) {
		final Vocabulary vocabulary = new Vocabulary();
		final List<String> words = VocabularyGenerator.wordsFromString(string);
		vocabulary.add(words);
		return vocabulary;
	}

}
