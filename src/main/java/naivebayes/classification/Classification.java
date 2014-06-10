package naivebayes.classification;

import naivebayes.vocabulary.Vocabulary;

public class Classification {

	private final String name;
	private final Vocabulary vocabulary;

	public Classification(final String name, final Vocabulary vocabulary) {
		this.name = name;
		this.vocabulary = vocabulary;
	}

	public String getName() {
		return name;
	}

	public Vocabulary getVocabulary() {
		return vocabulary;
	}

}
