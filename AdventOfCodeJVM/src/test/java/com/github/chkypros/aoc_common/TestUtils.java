package com.github.chkypros.aoc_common;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.stream.Stream;

import static com.github.chkypros.aoc_common.Utils.getInputFilePath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestUtils {
    private TestUtils() {
        // Utility class
    }

    public static void checkAnswer(Object expectedAnswer, Object answer) {
        assertNotNull("Answer should not be null", answer);
        assertEquals(String.valueOf(expectedAnswer), String.valueOf(answer));
    }

    public static Stream<String> getLabeledInput(Object object, String label) throws IOException, URISyntaxException {
        return Files.lines(getInputFilePath(object, label));
    }
}
