package naivebayes.core;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;

import naivebayes.classification.Classification;
import naivebayes.classification.ClassificationContainer;
import naivebayes.classification.ClassificationContainerImpl;
import naivebayes.preprocessing.VocabularyGenerator;
import naivebayes.preprocessing.VocabularyGeneratorImpl;
import naivebayes.probability.ProbabilityCalculator;
import naivebayes.probability.ProbabilityCalculatorImpl;
import naivebayes.vocabulary.Vocabulary;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Felipe Pierin <fpierin@uolinc.com>
 *
 */
public class NaiveBayesImplTest {

	private final static VocabularyGenerator vocabularyGenerator = new VocabularyGeneratorImpl();
	private final static ClassificationContainer classificationContainer = new ClassificationContainerImpl();
	private final static ProbabilityCalculator probabilityCalculator = new ProbabilityCalculatorImpl();
	private static NaiveBayes naiveBayes;

	@Before
	public void setAmbience() throws Exception {
		final Vocabulary phishingVocabulary = vocabularyGenerator.extractVocabularyFromFile("/home/felipe/workspace/naive-bayes/src/vocabulary/phishing.dat");
		final Classification phishingClassification = new Classification("PHISHING", phishingVocabulary);
		classificationContainer.add(phishingClassification);

		final Vocabulary reliableVocabulary = vocabularyGenerator.extractVocabularyFromFile("/home/felipe/workspace/naive-bayes/src/vocabulary/reliable.dat");
		final Classification reliableClassification = new Classification("RELIABLE", reliableVocabulary);
		classificationContainer.add(reliableClassification);

		naiveBayes = new NaiveBayesImpl(classificationContainer, probabilityCalculator);
	}

	@Test
	public void checkNaiveAnalysis() throws Exception {
		final File folder = new File("/home/felipe/workspace/roboExact/samples/all/testes");
		final File[] fileArray = folder.listFiles();
		for (final File sample : fileArray) {
			final boolean phishingSample = new File("/home/felipe/workspace/roboExact/samples/all/phishing/" + sample.getName()).exists();
			final String realClassification = phishingSample ? "PHISHING" : "RELIABLE";

			final List<String> bagOfWords = vocabularyGenerator.extractWordsFromFile(sample.getAbsolutePath());
			final String bayesianClassification = naiveBayes.classify(bagOfWords);

			System.out.println(MessageFormat.format("{0} | {1} | {2}", realClassification, bayesianClassification, sample.getName()));
		}
	}

}
