package com.adevinta.textsearch.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilityTest {

    @Test
    public void testWordContainsSpecialCharacter_shouldNormalizeWord() {
        String word = "äö:testNomrmalize$$";
        String actualWord = Utility.getNormalizeWord(word);
        assertEquals("testnomrmalize", actualWord);
    }

    @Test
    public void testWordContainsCommand_shouldNormalizeWord() {
        String word = ":quit";
        String actualWord = Utility.getNormalizeWord(word);
        assertEquals("quit", actualWord);
    }

    @Test
    public void testNullCommand_shouldReturnEmptyString() {
        String word = null;
        String actualWord = Utility.getNormalizeWord(word);
        assertEquals("", actualWord);
    }
}
