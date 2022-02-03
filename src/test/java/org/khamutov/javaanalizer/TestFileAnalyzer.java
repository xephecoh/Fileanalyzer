/*
package org.khamutov.javaanalizer;


import org.junit.jupiter.api.Test;
import org.khamutov.javaanalyzer.FileAnalyzer;

import java.io.IOException;
import java.util.regex.Pattern;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFileAnalyzer {
    private static FileAnalyzer fa = new FileAnalyzer();
    @Test
    public void testCounter() throws IOException {
        assertEquals(fa.getSentences("src/main/resources/lisbon.txt","ark"),7);
    }
    @Test
    public void testCounterLocally(){
        assertEquals(2,fa.getCounter(
                "To be, or not to be, that is the question.","to"));
        assertEquals(2,fa.getCounter(
                "To Be, or not to be, that is the question.","be"));
    }
    @Test
    public void testSentenceWithSearchWordByNumber() throws IOException {
        fa.getSentences("src/main/resources/lisbon.txt","ark");
        assertEquals(fa.getSentencesList().get(1),
                "It was an ark.");
        fa.getSentences("src/main/resources/lisbon.txt","ship");
        assertEquals(fa.getSentencesList().get(2),
                "Every ship that left Europe ark in those months of the year 1942 was an ark.");
    }
    @Test
    public void testPunctuate(){
        assertEquals("element",fa.resolveThePunctuation("element."));
    }
    @Test
    public void testCheckIfMatches(){
        assertTrue(fa.checkIfMatches("TeStWoRD","tEsTwOrd", Pattern.CASE_INSENSITIVE));
    }
}
*/
