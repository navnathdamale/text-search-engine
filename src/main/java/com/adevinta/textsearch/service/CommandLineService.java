package com.adevinta.textsearch.service;

import com.adevinta.textsearch.entity.WordCounter;
import com.adevinta.textsearch.util.Utility;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class for accepting commands from user
 */
public class CommandLineService {

    private static final String NO_MATCH_FOUND = "no matches found";

    private static final String CMD_PROMPT = "search> ";

    private static final String CMD_QUIT = ":quit";


    /**
     * Perform reading, searching and ranking the words.
     * <p>Note : ':quit' is a reserved word. If typed, the program will exit.</p>
     *
     * @param index in-memory representation of files word as key, and List of WordCounter values.
     */
    public void readInputWordsAndDisplayRanking(Map<String, List<WordCounter>> index) {
        while (true) {
            Map<String, Long> fileRankResults = readCommandLineInputAndCalculateRank(index);
            showSearchResults(fileRankResults);
        }
    }

    /**
     * Accept input from user and calculate rank score
     *
     * @param index in-memory representation of files word as key, and List of WordCounter values.
     * @return map of files with respective rank score
     */
    public Map<String, Long> readCommandLineInputAndCalculateRank(Map<String, List<WordCounter>> index) {
        System.out.print(CMD_PROMPT);
        Scanner keyboard = new Scanner(System.in);
        String commandLine = keyboard.nextLine();

        if (CMD_QUIT.equals(commandLine)) {
            System.exit(0);
        }

        String[] commandLineLWords = commandLine.split(" ");
        int searchWordCount = commandLineLWords.length;
        Map<String, Long> commandLineLWordCountMap = Arrays
                .stream(commandLineLWords)
                .map(Utility::getNormalizeWord)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return FileRankService.calculateFileRank(index, commandLineLWordCountMap, searchWordCount);
    }

    /**
     * Display file ranking with respective score on console
     *
     * @param result map of files with respective rank score
     */
    public void showSearchResults(Map<String, Long> result) {
        if (result.isEmpty()) {
            System.out.println(NO_MATCH_FOUND);
        } else {
            result.entrySet().forEach(e ->
                    System.out.println(e.getKey() + " : " + e.getValue() + "%")
            );
        }
    }

}