package com.adevinta.textsearch.service;

import com.adevinta.textsearch.entity.WordCounter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class for performing in-memory indexing
 */
public class WordIndexService {

    private FileIndexService fileIndexService;

    public WordIndexService(FileIndexService fileIndexService) {
        this.fileIndexService = fileIndexService;
    }

    /**
     * Generate index of file content from given directory path.
     * <p>Merging the maps of duplicate keys </p>
     *
     * @return in-memory representation of files word as key, and List of WordCounter values.
     * @throws IOException if directory does'nt exists.
     */
    public Map<String, List<WordCounter>> generateWordIndex() throws IOException {
        return fileIndexService.getFileWordIndex()
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));
    }
}
