package com.github.chkypros.aoc2021.day8;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ValueDecoder {

    private static final Pattern PATTERN = Pattern.compile(
            "(?<i1>[a-g]+) (?<i2>[a-g]+) (?<i3>[a-g]+) (?<i4>[a-g]+) (?<i5>[a-g]+) " +
                    "(?<i6>[a-g]+) (?<i7>[a-g]+) (?<i8>[a-g]+) (?<i9>[a-g]+) (?<i10>[a-g]+) " +
                    "\\| (?<o1>[a-g]+) (?<o2>[a-g]+) (?<o3>[a-g]+) (?<o4>[a-g]+)");
    private static final int OUTPUT_VALUES_NUMBER = 4;
    private static final int PATTERNS_NUMBER = 10;

    private final String entry;
    private final Map<Integer, String> digitPatterns;
    private final Map<Character, Character> segmentLetterMap;
    private final Set<String> patterns;
    private final List<String> outputValues;

    public ValueDecoder(final String entry) {
        this.entry = entry;

        this.digitPatterns = new HashMap<>();
        this.segmentLetterMap = new HashMap<>();
        this.patterns = new HashSet<>(PATTERNS_NUMBER);
        this.outputValues = new ArrayList<>(OUTPUT_VALUES_NUMBER);
    }

    public static boolean isDigitOne(final String pattern) {
        return 2 == pattern.length();
    }

    public static boolean isDigitFour(final String pattern) {
        return 4 == pattern.length();
    }

    public static boolean isDigitSeven(final String pattern) {
        return 3 == pattern.length();
    }

    public static boolean isDigitEight(final String pattern) {
        return 7 == pattern.length();
    }

    public long decode() {
        parseInput();
        populateUniquePatterns();
        findLetterMappings();
        populateRestPatterns();

        return evaluateOutputValue();
    }

    private void parseInput() {
        final Matcher matcher = PATTERN.matcher(entry);
        matcher.find();

        IntStream.rangeClosed(1, PATTERNS_NUMBER)
                .mapToObj(i -> matcher.group("i" + i))
                .map(ValueDecoder::orderedChars)
                .forEach(patterns::add);

        IntStream.rangeClosed(1, OUTPUT_VALUES_NUMBER)
                .mapToObj(i -> matcher.group("o" + i))
                .map(ValueDecoder::orderedChars)
                .forEach(outputValues::add);
    }

    private static String orderedChars(final String pattern) {
        final char[] charArray = pattern.toCharArray();
        Arrays.sort(charArray);
        return String.valueOf(charArray);
    }

    private void populateUniquePatterns() {
        final HashMap<Predicate<String>, Integer> uniquePatternDigitMap = new HashMap<>();
        uniquePatternDigitMap.put(ValueDecoder::isDigitOne, 1);
        uniquePatternDigitMap.put(ValueDecoder::isDigitFour, 4);
        uniquePatternDigitMap.put(ValueDecoder::isDigitSeven, 7);
        uniquePatternDigitMap.put(ValueDecoder::isDigitEight, 8);

        patterns.forEach(pattern -> uniquePatternDigitMap.entrySet().stream()
                .filter(e -> e.getKey().test(pattern))
                .findFirst()
                .map(e -> digitPatterns.put(e.getValue(), pattern)));
    }

    private void findLetterMappings() {
        findLetterA();
        findLettersDAndG();
        findLetterB();
        findLetterF();
        findLetterC();
        findLetterE();
    }

    private void findLetterA() {
        segmentLetterMap.put('A', removeLetters(digitPatterns.get(7), digitPatterns.get(1)).charAt(0));
    }

    private void findLettersDAndG() {
        final List<String> fiveSegmentPatterns = patterns.stream()
                .filter(p -> 5 == p.length())
                .collect(Collectors.toList());

        String commonLetters = fiveSegmentPatterns.get(0);
        int bound = fiveSegmentPatterns.size();
        for (int i = 1; i < bound; i++) {
            commonLetters = findCommonLetters(commonLetters, fiveSegmentPatterns.get(i));
        }

        commonLetters = removeLetters(commonLetters, segmentLetterMap.get('A').toString());
        final char letterForD = findCommonLetters(commonLetters, digitPatterns.get(4)).charAt(0);
        final char letterForG = removeLetters(commonLetters, digitPatterns.get(4)).charAt(0);
        segmentLetterMap.put('D', letterForD);
        segmentLetterMap.put('G', letterForG);
    }

    private void findLetterB() {
        segmentLetterMap.put('B', removeLetters(digitPatterns.get(4), digitPatterns.get(1) + segmentLetterMap.get('D')).charAt(0));
    }

    private void findLetterF() {
        final String digitFivePattern = patterns.stream()
                .filter(p -> 5 == p.length())
                .filter(p -> p.contains(String.valueOf(segmentLetterMap.get('B'))))
                .findFirst().get();
        digitPatterns.put(5, digitFivePattern);

        final String lettersToRemove = String.valueOf(segmentLetterMap.get('A'))
                + segmentLetterMap.get('B')
                + segmentLetterMap.get('D')
                + segmentLetterMap.get('G');
        segmentLetterMap.put('F', removeLetters(digitFivePattern, lettersToRemove).charAt(0));
    }

    private void findLetterC() {
        segmentLetterMap.put('C', removeLetters(digitPatterns.get(1), String.valueOf(segmentLetterMap.get('F'))).charAt(0));
    }

    private void findLetterE() {
        final String lettersToRemove = String.valueOf(segmentLetterMap.get('A'))
                + segmentLetterMap.get('B')
                + segmentLetterMap.get('C')
                + segmentLetterMap.get('D')
                + segmentLetterMap.get('F')
                + segmentLetterMap.get('G');

        segmentLetterMap.put('E', removeLetters(digitPatterns.get(8), lettersToRemove).charAt(0));
    }

    private void populateRestPatterns() {
        digitPatterns.put(0,
                orderedChars(String.valueOf(segmentLetterMap.get('A'))
                        + segmentLetterMap.get('B')
                        + segmentLetterMap.get('C')
                        + segmentLetterMap.get('E')
                        + segmentLetterMap.get('F')
                        + segmentLetterMap.get('G')
                ));
        digitPatterns.put(2,
                orderedChars(String.valueOf(segmentLetterMap.get('A'))
                        + segmentLetterMap.get('C')
                        + segmentLetterMap.get('D')
                        + segmentLetterMap.get('E')
                        + segmentLetterMap.get('G')
                ));
        digitPatterns.put(3,
                orderedChars(String.valueOf(segmentLetterMap.get('A'))
                        + segmentLetterMap.get('C')
                        + segmentLetterMap.get('D')
                        + segmentLetterMap.get('F')
                        + segmentLetterMap.get('G')
                ));
        digitPatterns.put(6,
                orderedChars(String.valueOf(segmentLetterMap.get('A'))
                        + segmentLetterMap.get('B')
                        + segmentLetterMap.get('D')
                        + segmentLetterMap.get('E')
                        + segmentLetterMap.get('F')
                        + segmentLetterMap.get('G')
                ));
        digitPatterns.put(9,
                orderedChars(String.valueOf(segmentLetterMap.get('A'))
                        + segmentLetterMap.get('B')
                        + segmentLetterMap.get('C')
                        + segmentLetterMap.get('D')
                        + segmentLetterMap.get('F')
                        + segmentLetterMap.get('G')
                ));
    }

    private long evaluateOutputValue() {
        return IntStream.range(0, outputValues.size())
                .mapToLong(i -> (long) Math.pow(10, outputValues.size() - 1 - i) * getDigitValue(outputValues.get(i)))
                .sum();
    }

    private long getDigitValue(String pattern) {
        return digitPatterns.entrySet().stream()
                .filter(e -> e.getValue().equals(pattern))
                .map(Map.Entry::getKey)
                .findFirst().get();
    }

    private String removeLetters(final String stringToRemoveFrom, final String stringToRemove) {
        String cleanString = stringToRemoveFrom;
        for (char c : stringToRemove.toCharArray()) {
            cleanString = cleanString.replace(String.valueOf(c), "");
        }
        return cleanString;
    }

    private String findCommonLetters(final String first, final String second) {
        final StringBuilder sb = new StringBuilder();

        for (char c : first.toCharArray()) {
            if (second.contains(String.valueOf(c))) {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
