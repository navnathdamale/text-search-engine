package com.adevinta.textsearch.service;

import com.adevinta.textsearch.constant.TextSearchConstants;
import com.adevinta.textsearch.entity.WordCounter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility class for calculating rank for files
 */
public class FileRankService {

    private FileRankService() {
    }

    /**
     * Generate map of files with rank score
     *
     * @param index           in-memory representation of files word as key, and List of WordCounter values.
     * @param searchWords     map of unique searchable word with respective count
     * @param searchWordCount count of searchable word
     * @return map of files with respective rank score
     */
    public static Map<String, Long> calculateFileRank(Map<String, List<WordCounter>> index, Map<String, Long> searchWords, int searchWordCount) {
        Map<String, Long> foundWords = findWordsAndRankThem(index, searchWords);
        final double rankPerWord = TextSearchConstants.MAX_SEARCH_RANKING_SCALE / searchWordCount;
        return sortByAndNormalizeRank(foundWords, rankPerWord);
    }

    /**
     * Generate map of files with respective word count
     *
     * @param index       in-memory representation of files word as key, and List of WordCounter values.
     * @param searchWords MAX_SEARCH_RANKING_SCALE/(Searchable word count)
     * @return map of files with respective rank score
     */
    private static Map<String, Long> findWordsAndRankThem(Map<String, List<WordCounter>> index, Map<String, Long> searchWords) {
        Map<String, Long> wordsWithRank = new HashMap<>();

        index.entrySet()
                .stream()
                .filter(es -> searchWords.containsKey(es.getKey()))
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .forEach(e -> {
                    long currentWordsMatched = Math.min(e.getCount(), searchWords.get(e.getWord()));
                    Long previousWordsMatched = wordsWithRank.getOrDefault(e.getFileName(), 0L);
                    wordsWithRank.put(e.getFileName(), currentWordsMatched + previousWordsMatched);
                });
        return wordsWithRank;
    }


    /**
     * Calculate file rank and sort based on score and
     * return list of the top 10 matching filenames in rank order.
     * <p>Example</p>
     * <p>file1.txt : to be or not to be</p>
     * <p>searchable word : to be</p>
     * <p>rankPerWord : 100/2 </p>
     * <p>score : rankPerWord * word_count</p>
     * <p>result : file1.txt=100</p>
     *
     * @param words       the words contained in the file.
     * @param rankPerWord MAX_SEARCH_RANKING_SCALE/(Searchable word count)
     * @return sorted map of files with respective rank score
     */
    private static Map<String, Long> sortByAndNormalizeRank(Map<String, Long> words, double rankPerWord) {
        return words.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(TextSearchConstants.MAX_SEARCH_RESULTS)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> Math.round(e.getValue() * rankPerWord),
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }
}