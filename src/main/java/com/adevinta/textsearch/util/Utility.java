package com.adevinta.textsearch.util;

/**
 * Text Search Utility
 */
public class Utility {

    private Utility() {
    }

    /**
     * Remove special symbol/character from word
     * <p>Note : word will be use for Map index to in-memory store of files contents.</p>
     *
     * @param word searchable/indexed word
     * @return normalized word without special/unicode character
     */
    public static String getNormalizeWord(String word) {
        return word == null ? "" : word.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }
}