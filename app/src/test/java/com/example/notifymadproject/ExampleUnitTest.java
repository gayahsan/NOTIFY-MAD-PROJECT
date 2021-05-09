package com.example.notifymadproject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    private NotepadHome notepadHome;

    @Before
    public void setUp() {
        notepadHome = new NotepadHome();
    }

    /*
     * Testcase for Notepad Percentage
     * IT19972794
     */
    @Test
    public void testFreeStorage() {
        int result = notepadHome.checkFreeStorageFull(10, 100.0);
        assertEquals(1, result);
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}