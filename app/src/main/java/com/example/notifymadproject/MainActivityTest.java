package com.example.notifymadproject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testng.annotations.AfterTest;

import static org.testng.Assert.assertEquals;

public class MainActivityTest {
    private MainActivity mainActivity;

    @Before
    public void setUp() {
        mainActivity = new MainActivity();
    }

    @Test
    public void testMultiNumbers() {
        int res = mainActivity.multiplyNumbers(3, 4);
        assertEquals(12, res);
    }
}
