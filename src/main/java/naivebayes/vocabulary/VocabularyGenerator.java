package naivebayes.vocabulary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VocabularyGenerator {
	
	private static final String BLANK_WORD = "";
	private static final String HTML_TAGS = "\\<.*?>";
	private static final Pattern QUOTE_PATTERN = Pattern.compile("\"([^\"]*)\"");
	
	private abstract static class AbstractReader {

		private BufferedReader bufferedReader;

		public AbstractReader() {
		}
		
		public void read(final String filepath) throws Exception {
			read(new File(filepath));			
		}

		public void read(final File sample) throws Exception {
			final FileInputStream inputStream = new FileInputStream(sample);
			final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			final String filename = sample.getName();
			
			String line = filename.substring(8, filename.length());
			final List<String> filenameWords = wordsFromString(line);
			for (final String word : filenameWords) {
				with(word);
			}
			
			while ((line = bufferedReader.readLine()) != null) {
				final List<String> wordList = wordsFromString(line);
				for (final String word : wordList) {
					with(word);
				}
			}
		}

		public abstract void with(String word) throws Exception;
		
	}

	public static void extract(final String sourceFolder, final String targetFile) throws Exception {
		final File target = new File(targetFile);
		if (target.exists()) {
			target.createNewFile();
		}
		final File folder = new File(sourceFolder);
		final File[] fileArray = folder.listFiles();
		for (final File sample : fileArray) {
			final String filename = sample.getName();
			final List<String> wordsFromString = wordsFromString(filename.substring(8, filename.length()));
			if (wordsFromString != null && !wordsFromString.isEmpty()) {
				for (final String s : wordsFromString) {
					appendTokenToFile(s, target);
				}
			}
			new AbstractReader() {
				@Override
				public void with(final String word) throws IOException {
					appendTokenToFile(word, target);
				}
			}.read(sample);
		}
	}

	private static void appendTokenToFile(final String data, final File target) throws IOException {
		final FileWriter fileWritter = new FileWriter(target.getAbsolutePath(), true);
		final BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		bufferWritter.write(data + "\n");
		bufferWritter.close();
	}

	public Vocabulary extractVocabularyFromFile(final String filepath) throws Exception {
		final Vocabulary vocabulary = new Vocabulary();
		new AbstractReader() {
			@Override
			public void with(final String word) {
				vocabulary.add(word);
			}
		}.read(filepath);
		return vocabulary;
	}

	public static List<String> extractWordsFromFile(final String filepath) throws Exception {
		final List<String> words = new ArrayList<String>();
		new AbstractReader() {
			@Override
			public void with(final String word) {
				words.add(word);
			}
		}.read(filepath);
		return words;
	}
	
	public static List<String> wordsFromString(final String string) {
		final List<String> wordList = new ArrayList<String>();
		if (string != null && string.length() > 0) {
			wordList.addAll(valuesInsideQuotes(string));
			final String noHTML = string.replaceAll(HTML_TAGS, BLANK_WORD);
			final StringTokenizer stringTokenizer = new StringTokenizer(noHTML);
			while (stringTokenizer.hasMoreTokens()) {
				final String token = stringTokenizer.nextToken();
				final String finalToken = token.trim().toUpperCase();
				if (finalToken.length() > 1) {
					wordList.add(finalToken);
				}
			}
		}
		return wordList;
	}

	private static Collection<? extends String> valuesInsideQuotes(final String string) {
		final List<String> wordsInsideQuotes = new ArrayList<>();
		final Matcher m = QUOTE_PATTERN.matcher(string);
		while (m.find()) {
			final String trimStr = m.group(1).trim().toUpperCase();
			if (trimStr != null && trimStr.length() > 0) {
				wordsInsideQuotes.add(trimStr);
			}
		}
		return wordsInsideQuotes;
	}
	
}
