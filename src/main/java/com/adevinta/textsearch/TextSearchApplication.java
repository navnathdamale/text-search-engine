package com.adevinta.textsearch;


import com.adevinta.textsearch.entity.WordCounter;
import com.adevinta.textsearch.exception.TextSearchArgumentException;
import com.adevinta.textsearch.exception.TextSearchFileException;
import com.adevinta.textsearch.service.CommandLineService;
import com.adevinta.textsearch.service.FileIndexService;
import com.adevinta.textsearch.service.WordIndexService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.adevinta.textsearch.constant.TextSearchConstants.FILE_IO_EXCEPTION;
import static com.adevinta.textsearch.constant.TextSearchConstants.ILLEGAL_ARGUMENT_EXCEPTION;

public class TextSearchApplication {
    private static WordIndexService wordIndexService;
    private static FileIndexService fileIndexService;
    private static CommandLineService commandLineService;

    /**
     * Main program
     *
     * @param args argument containing directory path
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new TextSearchArgumentException(ILLEGAL_ARGUMENT_EXCEPTION);
        }
        initSearchEngine(args[0]);
    }

    /**
     * Method will start searching and indexing algorithm
     *
     * @param dirPath argument containing directory path
     */
    private static void initSearchEngine(String dirPath) {
        fileIndexService = new FileIndexService(dirPath);
        wordIndexService = new WordIndexService(fileIndexService);
        commandLineService = new CommandLineService();
        try {
            fileIndexService.showFilesCount();
            Map<String, List<WordCounter>> wordIndexMap = wordIndexService.generateWordIndex();
            commandLineService.readInputWordsAndDisplayRanking(wordIndexMap);
        } catch (IOException e) {
            throw new TextSearchFileException(FILE_IO_EXCEPTION, e);
        }
    }
}