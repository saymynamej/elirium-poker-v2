package ru.smn.poker.combination.strategy.generator;

import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;

import java.util.HashMap;
import java.util.Map;

public class GeneratorAssistant {
    private final static Map<CombinationType, GeneratorStrategy> generateStrategies = new HashMap<>();

    static {
        generateStrategies.put(CombinationType.HIGH_CARD, new HighCardStrategyGenerator());
        generateStrategies.put(CombinationType.ONE_PAIR, new OnePairStrategyGenerator());
        generateStrategies.put(CombinationType.TWO_PAIR, new TwoPairStrategyGenerator());
        generateStrategies.put(CombinationType.THREE_CARDS, new ThreeStrategyGenerator());
        generateStrategies.put(CombinationType.STRAIGHT, new StraightStrategyGenerator());
        generateStrategies.put(CombinationType.FLUSH, new FlushStrategyGenerator());
        generateStrategies.put(CombinationType.FULL_HOUSE, new FullHouseStrategyGenerator());
        generateStrategies.put(CombinationType.KARE, new KareStrategyGenerator());
        generateStrategies.put(CombinationType.STRAIGHT_FLUSH, new StraightFlushStrategyGenerator());
        generateStrategies.put(CombinationType.FLUSH_ROYAL, new FlushRoyalStrategyGenerator());
    }

    public static Combination generate(CombinationType combinationType) {
        return generateStrategies.get(combinationType).generate();
    }
}
