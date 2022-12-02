package com.github.chkypros.aoc2021.day16.evaluator;

import com.github.chkypros.aoc2021.day16.BitsPacketType;

import java.util.HashMap;
import java.util.Map;

public class OperatorEvaluatorFactory {

    private OperatorEvaluatorFactory() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    private static final Map<BitsPacketType, OperatorEvaluator> OPERATORS;

    static {
        OPERATORS = new HashMap<>();
        OPERATORS.put(BitsPacketType.SUM, new SumOperatorEvaluator());
        OPERATORS.put(BitsPacketType.PRODUCT, new ProductOperatorEvaluator());
        OPERATORS.put(BitsPacketType.MIN, new MinOperatorEvaluator());
        OPERATORS.put(BitsPacketType.MAX, new MaxOperatorEvaluator());
        OPERATORS.put(BitsPacketType.GREATER, new GreaterOperatorEvaluator());
        OPERATORS.put(BitsPacketType.LESSER, new LesserOperatorEvaluator());
        OPERATORS.put(BitsPacketType.EQUAL, new EqualOperatorEvaluator());
    }

    public static OperatorEvaluator get(BitsPacketType type) {
        return OPERATORS.get(type);
    }
}
