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

import java.io.IOException;
import java.util.List;

import naivebayes.vocabulary.Vocabulary;

/**
 * @author Felipe Pierin <fpierin@uolinc.com>
 *
 */
public interface VocabularyGenerator {

	void extract(String sourceFolder, String targetFile) throws IOException;

	Vocabulary extractVocabularyFromFile(String string) throws IOException;

	List<String> extractWordsFromFile(String string) throws IOException;

}
