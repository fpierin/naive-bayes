/*
 * Copyright (c) 1996-2013 UOL Inc, Todos os direitos reservados
 *
 * Este arquivo e uma propriedade confidencial do Universo Online Inc. Nenhuma
 * parte do mesmo pode ser copiada, reproduzida, impressa ou transmitida por
 * qualquer meio sem autorizacao expressa e por escrito de um representante
 * legal do Universo Online Inc.
 *
 * All rights reserved
 *
 * This file is a confidential property of Universo Online Inc. No part of this
 * file may be reproduced or copied in any form or by any means without written
 * permission from an authorized person from Universo Online Inc.
 */
package naivebayes.preprocessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import naivebayes.vocabulary.Vocabulary;

/**
 * @author Felipe Pierin <fpierin@uolinc.com>
 *
 */
public class VocabularyGeneratorImpl implements VocabularyGenerator {

	/* (non-Javadoc)
	 * @see naivebayes.preprocessing.VocabularyGenerator#extract(java.lang.String, java.lang.String)
	 */
	public void extract(final String sourceFolder, final String targetFile) throws IOException {
		final File target = new File(targetFile);
		if (target.exists()) {
			target.createNewFile();
		}
		final File folder = new File(sourceFolder);
		final File[] fileArray = folder.listFiles();
		for (final File sample : fileArray) {
			String line = sample.getName();
			saveExpression(target, line);
			final FileInputStream inputStream = new FileInputStream(sample);
			final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			while ((line = bufferedReader.readLine()) != null) {
				saveExpression(target, line);
			}
		}
	}

	private void saveExpression(final File target, final String line) throws IOException {
		final StringTokenizer stringTokenizer = new StringTokenizer(line);
		while (stringTokenizer.hasMoreTokens()) {
			final String token = stringTokenizer.nextToken();
			final String finalToken = token.trim().toUpperCase();
			if (finalToken.length() > 1) {
				appendTokenToFile(finalToken, target);
			}
		}
	}

	/**
	 * @param finalToken
	 * @param target
	 * @throws IOException
	 */
	private void appendTokenToFile(final String data, final File target) throws IOException {
		final FileWriter fileWritter = new FileWriter(target.getAbsolutePath(), true);
		final BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		bufferWritter.write(data + "\n");
		bufferWritter.close();
	}

	/* (non-Javadoc)
	 * @see naivebayes.preprocessing.VocabularyGenerator#fromDirectory(java.lang.String)
	 */
	public Vocabulary extractVocabularyFromFile(final String string) throws IOException {
		final Vocabulary vocabulary = new Vocabulary();
		final File sample = new File(string);
		final FileInputStream inputStream = new FileInputStream(sample);
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line = sample.getName();
		while ((line = bufferedReader.readLine()) != null) {
			final StringTokenizer stringTokenizer = new StringTokenizer(line);
			while (stringTokenizer.hasMoreTokens()) {
				final String token = stringTokenizer.nextToken();
				final String finalToken = token.trim().toUpperCase();
				if (finalToken.length() > 1) {
					vocabulary.add(finalToken);
				}
			}
		}
		return vocabulary;
	}

	/* (non-Javadoc)
	 * @see naivebayes.preprocessing.VocabularyGenerator#fromFile(java.lang.String)
	 */
	public List<String> extractWordsFromFile(final String string) throws IOException {
		final List<String> words = new ArrayList<String>();
		final File sample = new File(string);
		final FileInputStream inputStream = new FileInputStream(sample);
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line = sample.getName();
		while ((line = bufferedReader.readLine()) != null) {
			final StringTokenizer stringTokenizer = new StringTokenizer(line);
			while (stringTokenizer.hasMoreTokens()) {
				final String token = stringTokenizer.nextToken();
				final String finalToken = token.trim().toUpperCase();
				if (finalToken.length() > 1) {
					words.add(finalToken);
				}
			}
		}
		return words;
	}

}
