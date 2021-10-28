package com.adevinta.textsearch.service;

import com.adevinta.textsearch.entity.WordCounter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseServiceTest {

    protected static final String VALID_TEST_FILE_DIRECTORY_PATH = "src/test/resources/test-dir";
    protected static final String INVALID_TEST_FILE_DIRECTORY_PATH = "src/test/resources/non_existance_directory";

    /**
     * 1. testResourceFile1.txt : "First test file"
     * 2. testResourceFile2.txt : "Second test file"
     *
     * @return
     */
    protected Map<String, List<WordCounter>> getExpectedWordIndexes() {

        Map<String, List<WordCounter>> expectedWordIndexMap = new HashMap<>();
        expectedWordIndexMap.put("first", Arrays.asList(new WordCounter("testResourceFile1.txt", "first", 1)));

        expectedWordIndexMap.put("second", Arrays.asList(new WordCounter("testResourceFile2.txt", "second", 1)));

        expectedWordIndexMap.put("test", Arrays.asList(new WordCounter("testResourceFile1.txt", "test", 1),
                new WordCounter("testResourceFile2.txt", "test", 1)));

        expectedWordIndexMap.put("file", Arrays.asList(new WordCounter("testResourceFile1.txt", "file", 1),
                new WordCounter("testResourceFile2.txt", "file", 1)));

        return expectedWordIndexMap;
    }

    protected Map<String, Long> get100PercentMatchingScore() {
        return new HashMap<String, Long>() {{
            put("testResourceFile1.txt", 100L);
            put("testResourceFile2.txt", 100L);
        }};
    }

    protected Map<String, Long> get50PercentMatchingScore() {
        return new HashMap<String, Long>() {{
            put("testResourceFile1.txt", 50L);
            put("testResourceFile2.txt", 50L);
        }};
    }

    protected Map<String, Long> get25PercentMatchingScore() {
        return new HashMap<String, Long>() {{
            put("testResourceFile1.txt", 25L);
            put("testResourceFile2.txt", 25L);
        }};
    }
}
