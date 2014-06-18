package naivebayes.vocabulary;


import java.io.File;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class VocabularyGeneratorImplTest {

	@Test
	public void gerador() throws Exception {
		String pathname;
		String saveTo;

		pathname = "/home/fpierin/workspace/naive-bayes/samples/phishing";
		saveTo = "/home/fpierin/workspace/naive-bayes/src/main/config/vocabulary/phishing.dat";
		new File(saveTo).delete();
		VocabularyGenerator.extract(pathname, saveTo);

		pathname = "/home/fpierin/workspace/naive-bayes/samples/reliable";
		saveTo = "/home/fpierin/workspace/naive-bayes/src/main/config/vocabulary/reliable.dat";
		new File(saveTo).delete();
		VocabularyGenerator.extract(pathname, saveTo);
	}

	@Test
	public void verificadorDeFrequencia() throws Exception {
		final Vocabulary fromFile = Vocabulary.fromFile("/home/felipe/workspace/massmail-trunk/uh-email-analyzer/src/main/config/bayes/phishing.dat");
		final Map<String, Integer> frequencyMap = fromFile.getFrequencyMap();
		final ValueComparator valueComparator = new ValueComparator(frequencyMap);
		final TreeMap<String, Integer> treeMap = new TreeMap<>(valueComparator);
		treeMap.putAll(frequencyMap);
		final Set<Entry<String, Integer>> entrySet = treeMap.entrySet();
		final Iterator<Entry<String, Integer>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			final Entry<String, Integer> next = iterator.next();
			System.out.println(next.getKey() + ": " + next.getValue());
		}
	}

	class ValueComparator implements Comparator<String> {

		Map<String, Integer> base;
		public ValueComparator(final Map<String, Integer> base) {
			this.base = base;
		}

		// Note: this comparator imposes orderings that are inconsistent with equals.
		@Override
		public int compare(final String a, final String b) {
			if (base.get(a) >= base.get(b)) {
				return -1;
			} else {
				return 1;
			}
		}
	}

}
