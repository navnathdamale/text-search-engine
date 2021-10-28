package com.adevinta.textsearch.service;

import com.adevinta.textsearch.entity.WordCounter;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class WordIndexServiceTest extends BaseServiceTest {


    @Test
    public void testNonExistanceDirectory_shouldReturnEmptyIndexes() throws IOException {
        WordIndexService wordIndexService = getWordIndexServiceInstance(INVALID_TEST_FILE_DIRECTORY_PATH);
        Map<String, List<WordCounter>> wordIndexMap = wordIndexService.generateWordIndex();
        assertTrue(wordIndexMap.isEmpty());
    }

    @Test
    public void testExistanceDirectory_shouldReturnIndexes() throws IOException {
        WordIndexService wordIndexService = getWordIndexServiceInstance(VALID_TEST_FILE_DIRECTORY_PATH);
        Map<String, List<WordCounter>> wordIndexMap = wordIndexService.generateWordIndex();
        assertNotNull(wordIndexMap);
        assertEquals(wordIndexMap, getExpectedWordIndexes());
    }


    private WordIndexService getWordIndexServiceInstance(String directoryPath) {
        FileIndexService fileIndexService = new FileIndexService(directoryPath);
        return new WordIndexService(fileIndexService);
    }
}
