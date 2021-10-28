package com.adevinta.textsearch.constant;

/**
 * This class contains global application constants
 */
public final class TextSearchConstants {

    public static final String FILES_EXTENSION = "txt";
    public static final int MAX_SEARCH_RESULTS = 10;
    public static final double MAX_SEARCH_RANKING_SCALE = 100;


    /**
     * Exception Messages
     **/
    public static final String ILLEGAL_ARGUMENT_EXCEPTION = "No directory given to index. Please enter valid directory source.";
    public static final String FILE_IO_EXCEPTION = "Exception while reading files from directory.";
}
