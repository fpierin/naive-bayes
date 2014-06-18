package naivebayes.core;

import java.io.File;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import naivebayes.classification.ClassificationContainer;
import naivebayes.classification.ClassificationContainerImpl;
import naivebayes.classification.PhishingClassification;
import naivebayes.classification.ReliableClassification;
import naivebayes.probability.ProbabilityCalculator;
import naivebayes.probability.ProbabilityCalculatorImpl;
import naivebayes.vocabulary.Vocabulary;
import naivebayes.vocabulary.VocabularyGenerator;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class NaiveBayesImplTest {

	private final static ClassificationContainer classificationContainer = new ClassificationContainerImpl();
	private final static ProbabilityCalculator probabilityCalculator = new ProbabilityCalculatorImpl();
	private static NaiveBayes naiveBayes;

	@Before
	public void setAmbience() throws Exception {
		Vocabulary.fromFile("/home/fpierin/workspace/naive-bayes/src/main/config/vocabulary/reliable.dat");
		new PhishingClassification(classificationContainer);

		Vocabulary.fromFile("/home/fpierin/workspace/naive-bayes/src/main/config/vocabulary/phishing.dat");
		new ReliableClassification(classificationContainer);

		naiveBayes = new NaiveBayesImpl(classificationContainer, probabilityCalculator);
	}

	@Test
	public void checkNaiveAnalysis() throws Exception {
		final File folder = new File("/home/fpierin/workspace/naive-bayes/samples/all");
		final File[] fileArray = folder.listFiles();
		Double minValue = Double.POSITIVE_INFINITY;
		Double maxValue = Double.NEGATIVE_INFINITY;
		for (final File sample : fileArray) {
			final boolean phishingSample = new File("/home/fpierin/workspace/naive-bayes/samples/phishing/" + sample.getName()).exists();
			final String realClassification = phishingSample ? "PHISHING" : "RELIABLE";

			final List<String> bagOfWords = VocabularyGenerator.extractWordsFromFile(sample.getAbsolutePath());
			final Map<String, Double> bayesianClassification = naiveBayes.classify(bagOfWords);

			Double maxProbability = Double.NEGATIVE_INFINITY;
			String nomeDaClassificacao = null;
			final Set<Entry<String, Double>> entrySet = bayesianClassification.entrySet();
			final Iterator<Entry<String, Double>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				final Entry<String, Double> next = iterator.next();
				final Double nextValue = next.getValue();
				if (nextValue > maxProbability) {
					maxProbability = next.getValue();
					nomeDaClassificacao = next.getKey();
				}
			}
			if (maxProbability < minValue) {
				minValue = maxProbability;
			} 
			if (maxProbability > maxValue) {
				maxValue = maxProbability;
			}
			
			if ("PHISHING".equalsIgnoreCase(nomeDaClassificacao) && maxProbability < 0.522) {
				nomeDaClassificacao = "RELIABLE";
				maxProbability = 0.5;
			}
			System.out.println(MessageFormat.format("{0} | {1} ({2}) | {3}", realClassification, nomeDaClassificacao, maxProbability, sample.getName()));
		}
		System.out.println(MessageFormat.format("Valor minimo/maximo: {0}/{1}", minValue, maxValue));
	}

}
