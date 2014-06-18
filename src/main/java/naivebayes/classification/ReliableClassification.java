package naivebayes.classification;

import static naivebayes.classification.Classifications.RELIABLE;
import naivebayes.vocabulary.Vocabulary;
import naivebayes.vocabulary.VocabularyGenerator;

public class ReliableClassification extends Classification {

	public ReliableClassification(final ClassificationContainer classificationContainer) throws Exception {
		super(RELIABLE.name(), 0.92, loadVocabulary(), classificationContainer);
	}

	private static Vocabulary loadVocabulary() throws Exception {
		final VocabularyGenerator vocabularyGenerator = new VocabularyGenerator();
		return vocabularyGenerator.extractVocabularyFromFile("/home/fpierin/workspace/naive-bayes/src/main/config/vocabulary/reliable.dat");
	}

}
