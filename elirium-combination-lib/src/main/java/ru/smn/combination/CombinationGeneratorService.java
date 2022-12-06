package ru.smn.combination;

import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;

public interface CombinationGeneratorService {
    Combination generate(CombinationType combinationType);
}
