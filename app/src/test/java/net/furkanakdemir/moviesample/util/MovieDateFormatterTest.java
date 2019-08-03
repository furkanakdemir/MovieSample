package net.furkanakdemir.moviesample.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieDateFormatterTest {


    private DateFormatter dateFormatter;
    public static final String EMPTY = "";
    public static final String DATE_CORRECT = "2019-08-03";
    public static final String DATE_CORRECT_EXPECTED = "August 2019";
    public static final String DATE_INCORRECT_0 = "2019-083";
    public static final String DATE_INCORRECT_1 = "2019/8/3";
    public static final String DATE_INCORRECT_2 = "2019.8.3";
    public static final String DATE_INCORRECT_3 = "2019-15-03";
    public static final String DATE_INCORRECT_4 = "19-03-35";

    @Before
    public void setup() {
        dateFormatter = new MovieDateFormatter();
    }

    @Test
    public void testEmptyDate() {

        String formatted = dateFormatter.formatDate(EMPTY);

        assertEquals(EMPTY, formatted);
    }

    @Test
    public void testCorrectDate() {

        String formatted = dateFormatter.formatDate(DATE_CORRECT);

        assertEquals(DATE_CORRECT_EXPECTED, formatted);
    }

    @Test
    public void testInCorrectDate() {

        String formatted = dateFormatter.formatDate(DATE_INCORRECT_0);

        assertEquals(EMPTY, formatted);

        formatted = dateFormatter.formatDate(DATE_INCORRECT_1);

        assertEquals(EMPTY, formatted);

        formatted = dateFormatter.formatDate(DATE_INCORRECT_2);

        assertEquals(EMPTY, formatted);

        formatted = dateFormatter.formatDate(DATE_INCORRECT_3);

        assertEquals(EMPTY, formatted);

        formatted = dateFormatter.formatDate(DATE_INCORRECT_4);

        assertEquals(EMPTY, formatted);
    }
}