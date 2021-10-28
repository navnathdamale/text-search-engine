package com.adevinta.textsearch.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class FileIndexServiceTest extends BaseServiceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStream() {
        System.setOut(originalOut);
    }

    @Test
    public void testShowFileCountForValidDirectory_shouldDisplayDirectoryFilesCount() throws IOException {
        FileIndexService fileIndexService = getFileIndexServiceInstance(VALID_TEST_FILE_DIRECTORY_PATH);
        fileIndexService.showFilesCount();

        assertEquals("\n{2} files read in directory {src/test/resources/test-dir}\n", outContent.toString());
    }

    @Test(expected = IOException.class)
    public void testShowFileCountForInvalidDirectory_shouldThrowIOException() throws IOException {
        FileIndexService fileIndexService = getFileIndexServiceInstance(INVALID_TEST_FILE_DIRECTORY_PATH);
        fileIndexService.showFilesCount();
    }

    private FileIndexService getFileIndexServiceInstance(String directoryPath) {
        return new FileIndexService(directoryPath);
    }


}