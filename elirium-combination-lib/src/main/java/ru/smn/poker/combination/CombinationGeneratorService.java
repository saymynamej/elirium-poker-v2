package ru.smn.poker.combination;

import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;

public interface CombinationGeneratorService {
    Combination generate(CombinationType combinationType);
}
