package naivebayes.classification;

import static naivebayes.classification.Classifications.PHISHING;
import naivebayes.vocabulary.Vocabulary;
import naivebayes.vocabulary.VocabularyGenerator;

public class PhishingClassification extends Classification {

	public PhishingClassification(final ClassificationContainer classificationContainer) throws Exception {
		super(PHISHING.name(), 0.08,loadVocabulary(), classificationContainer);
	}

	private static Vocabulary loadVocabulary() throws Exception {
		final VocabularyGenerator vocabularyGenerator = new VocabularyGenerator();
		return vocabularyGenerator.extractVocabularyFromFile("/home/fpierin/workspace/naive-bayes/src/main/config/vocabulary/phishing.dat");
	}

}
