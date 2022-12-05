package com.github.chkypros.aoc2022.day5;

import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
public class Cargo {
    private static final Pattern CRATE_PATTERN = Pattern.compile("[A-Z]");

    private List<Stack<Character>> stacks;

    public Cargo(List<String> initLines) {
        for (int i = initLines.size() - 2; i >= 0; i--) {
            var matcher = CRATE_PATTERN.matcher(initLines.get(i));
            while (matcher.find()) {
                var matchIndex = matcher.start();
                var stackindex = 1 + matchIndex / 4;
                stacks.get(stackindex).push(matcher.group().charAt(1));
            }
        }
    }
}
