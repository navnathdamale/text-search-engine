package com.adevinta.textsearch.service;

import com.adevinta.textsearch.entity.WordCounter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

public class CommandLineServiceTest extends BaseServiceTest {

    private CommandLineService commandLineService;

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        commandLineService = new CommandLineService();
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testReadWordsAndDisplayRanking_shouldDisplayRankingOnConsole() {
        Map<String, List<WordCounter>> wordIndexMap = getExpectedWordIndexes();
        String searchWord = "First Second";
        provideInput(searchWord);
        Map<String, Long> fileRankingMap = commandLineService.readCommandLineInputAndCalculateRank(wordIndexMap);
        Assert.assertEquals(fileRankingMap, get50PercentMatchingScore());

        commandLineService.showSearchResults(fileRankingMap);
        Assert.assertTrue(getOutput().contains("testResourceFile2.txt : 50%\n" +
                "testResourceFile1.txt : 50%"));
    }

    @Test
    public void testReadEmptyWordsAndDisplayRanking_shouldDisplayNothing() {
        Map<String, List<WordCounter>> wordIndexMap = getExpectedWordIndexes();
        String searchWord = " ";
        provideInput(searchWord);
        Map<String, Long> fileRankingMap = commandLineService.readCommandLineInputAndCalculateRank(wordIndexMap);
        Assert.assertEquals(0, fileRankingMap.size());

        commandLineService.showSearchResults(fileRankingMap);
        Assert.assertTrue(getOutput().contains("no matches found"));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }
}
