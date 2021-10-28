package com.adevinta.textsearch;

import com.adevinta.textsearch.exception.TextSearchArgumentException;
import com.adevinta.textsearch.exception.TextSearchFileException;
import org.junit.Test;

public class TextSearchApplicationTests {

    @Test(expected = TextSearchArgumentException.class)
    public void testEmptyArgs_shouldThrowTextSearchArgumentException() {
        String[] emptyArgs = new String[]{};
        TextSearchApplication.main(emptyArgs);
    }

    @Test(expected = TextSearchFileException.class)
    public void testNonExistanceDirectory_shouldThrowTextSearchFileException() {
        String[] args = new String[]{"src/test/non_existance_directory"};
        TextSearchApplication.main(args);
    }
}