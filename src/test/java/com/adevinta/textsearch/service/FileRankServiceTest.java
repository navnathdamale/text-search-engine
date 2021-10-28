package com.adevinta.textsearch.service;

import com.adevinta.textsearch.entity.WordCounter;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FileRankServiceTest extends BaseServiceTest {

    @Test
    public void testCalculateFileRankWithExistingWords_shouldReturn100PercentScore() {
        Map<String, List<WordCounter>> wordIndexMap = getExpectedWordIndexes();
        Map<String, Long> searchWords = new HashMap<>();
        searchWords.put("test", 1L);

        Map<String, Long> actualRanking = FileRankService.calculateFileRank(wordIndexMap, searchWords, 1);
        assertNotNull(actualRanking);
        assertEquals(actualRanking, get100PercentMatchingScore());
    }

    @Test
    public void testCalculateFileRankWithExistingWords_shouldReturn50PercentScore() {
        Map<String, List<WordCounter>> wordIndexMap = getExpectedWordIndexes();
        Map<String, Long> searchWords = new HashMap<>();
        searchWords.put("first", 1L);
        searchWords.put("second", 1L);

        Map<String, Long> actualRanking = FileRankService.calculateFileRank(wordIndexMap, searchWords, 2);
        assertNotNull(actualRanking);
        assertEquals(actualRanking, get50PercentMatchingScore());
    }

    @Test
    public void testCalculateFileRankWithExistingWords_shouldReturn25PercentScore() {
        Map<String, List<WordCounter>> wordIndexMap = getExpectedWordIndexes();
        Map<String, Long> searchWords = new HashMap<>();
        searchWords.put("first", 2L);
        searchWords.put("second", 2L);

        Map<String, Long> actualRanking = FileRankService.calculateFileRank(wordIndexMap, searchWords, 4);
        assertNotNull(actualRanking);
        assertEquals(actualRanking, get25PercentMatchingScore());
    }

    @Test
    public void testCalculateFileRankWithNonExistingWords_shouldReturnNothing() {
        Map<String, List<WordCounter>> wordIndexMap = getExpectedWordIndexes();
        Map<String, Long> searchWords = new HashMap<>();
        searchWords.put("Non existing words", 1L);

        Map<String, Long> actualRanking = FileRankService.calculateFileRank(wordIndexMap, searchWords, 1);
        assertEquals(0, actualRanking.size());
    }

}
