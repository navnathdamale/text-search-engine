package com.adevinta.textsearch.entity;

import java.util.Objects;

/**
 * Store the word occurannce count in file.
 * <p>This class contains file name, word and count</p>
 */
public class WordCounter {
    private String fileName;
    private String word;
    private int count;

    public WordCounter(String fileName, String word, int count) {
        this.fileName = fileName;
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public String getFileName() {
        return fileName;
    }

    public int getCount() {
        return count;
    }

    public WordCounter incrementCount() {
        this.count++;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WordCounter)) return false;
        WordCounter that = (WordCounter) o;
        return getCount() == that.getCount() &&
                Objects.equals(getFileName(), that.getFileName()) &&
                Objects.equals(getWord(), that.getWord());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFileName(), getWord(), getCount());
    }
}
