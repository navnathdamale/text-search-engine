package com.adevinta.textsearch.service;

import com.adevinta.textsearch.constant.TextSearchConstants;
import com.adevinta.textsearch.entity.WordCounter;
import com.adevinta.textsearch.util.Utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Utility class for generating word index for file content
 */
public class FileIndexService {

    private String directoryPath;

    public FileIndexService(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    /**
     * This methods reads all files contents from directoryPath
     *
     * @return A map of stream containing a key as word, and WordCounter in values.
     * @throws IOException if the directory doesn't exists
     */
    public Stream<Map<String, WordCounter>> getFileWordIndex() throws IOException {
        final File indexableDirectory = new File(directoryPath);
        Stream<Path> stream;
        try {
            stream = Files.list(indexableDirectory.toPath());
        } catch (IOException e) {
            return Stream.empty();
        }

        return stream
                .map(file -> {
                    try {
                        return readFilesContent(file);
                    } catch (IOException e) {
                        return Collections.emptyMap();
                    }
                });
    }

    /**
     * This methods reads all files contents from path
     * <p>Example</p>
     * <p>If a file contains : to be or not to be, The method will returns a Map of four word as key and WordCounter {to=2, be=2, or=1,n ot=1} values </p>
     *
     * @param file File containing the word to count
     * @return A map containing a key as word, and WordCounter in values.
     * @throws IOException if directory does'nt exists
     */
    private Map<String, WordCounter> readFilesContent(Path file) throws IOException {
        Map<String, WordCounter> indexWordMap = new HashMap<>();

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(file.toFile())))) {
            String fileName = file.getFileName().toString();
            while (scanner.hasNext()) {
                String word = scanner.next();
                String normalizedWord = Utility.getNormalizeWord(word);
                WordCounter wordCounter = indexWordMap.containsKey(normalizedWord) ? indexWordMap.get(normalizedWord).incrementCount() : new WordCounter(fileName, normalizedWord, 1);
                indexWordMap.put(normalizedWord, wordCounter);
            }
        }
        return indexWordMap;
    }

    /**
     * Show the number of files on console from directory
     *
     * @throws IOException if the directory doesn't exists
     */
    public void showFilesCount() throws IOException {
        long filesCount;
        try (Stream<Path> filesPaths = Files.list(Paths.get(directoryPath))) {
            filesCount = filesPaths
                    .filter(p -> p.toString().endsWith(TextSearchConstants.FILES_EXTENSION))
                    .count();
        }

        System.out.println("\n{" + filesCount + "} " + (filesCount > 1 ? "files" : "file") + " read in directory {" + this.directoryPath + "}");
    }
}
