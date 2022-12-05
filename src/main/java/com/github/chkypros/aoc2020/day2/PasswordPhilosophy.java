package com.github.chkypros.aoc2020.day2;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
public class PasswordPhilosophy extends AbstractSolution {
    private static final Pattern PATTERN = Pattern.compile("(\\d+)-(\\d+) (\\w): (.*)");

    public static void main(String[] args) throws Exception {
        new PasswordPhilosophy().solve();
    }

    @Override
    public Long solvePartOne(Stream<String> stream) {
        return stream
            .filter(PasswordPhilosophy::isValidPartOne)
            .count();
    }

    private static boolean isValidPartOne(String input) {
        final Matcher matcher = PATTERN.matcher(input);
        matcher.find();
        final int policyMin = Integer.parseInt(matcher.group(1));
        final int policyMax = Integer.parseInt(matcher.group(2));
        final String policyChar = matcher.group(3);
        final String password = matcher.group(4);

        final String reducedPassword = password.replaceAll(policyChar, "");
        final int charIntances = password.length() - reducedPassword.length();

        return policyMin <= charIntances && charIntances <= policyMax;
    }

    public Object solvePartTwo(Stream<String> stream) {
        return stream
            .filter(PasswordPhilosophy::isValidPartTwo)
            .count();
    }

    private static boolean isValidPartTwo(String input) {
        final Matcher matcher = PATTERN.matcher(input);
        matcher.find();
        final int firstPosition = Integer.parseInt(matcher.group(1));
        final int secondPosition = Integer.parseInt(matcher.group(2));
        final char policyChar = matcher.group(3).charAt(0);
        final String password = matcher.group(4);

        return password.charAt(firstPosition - 1) == policyChar
            ^ password.charAt(secondPosition - 1) == policyChar;
    }

}
