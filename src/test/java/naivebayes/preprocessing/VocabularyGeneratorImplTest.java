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
 *
 */
package naivebayes.preprocessing;

import org.junit.Test;

/**
 * @author Felipe Pierin <fpierin@uolinc.com>
 *
 */
public class VocabularyGeneratorImplTest {

	@Test
	public void gerador() throws Exception {
		String pathname;
		String saveTo;

		final VocabularyGenerator vocabularyGenerator = new VocabularyGeneratorImpl();
		pathname = "/home/felipe/workspace/roboExact/samples/all/phishing";
		saveTo = "/home/felipe/workspace/naive-bayes/src/vocabulary/phishing.dat";
		vocabularyGenerator.extract(pathname, saveTo);

		pathname = "/home/felipe/workspace/roboExact/samples/all/no-phishing";
		saveTo = "/home/felipe/workspace/naive-bayes/src/vocabulary/reliable.dat";
		vocabularyGenerator.extract(pathname, saveTo);
	}

}
