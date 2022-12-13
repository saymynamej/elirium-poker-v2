package ru.smn.poker.combination;

import org.springframework.stereotype.Service;
import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;
import ru.smn.poker.combination.strategy.generator.GeneratorAssistant;

@Service
public class ClassicCombinationGeneratorService implements CombinationGeneratorService {

    @Override
    public Combination generate(CombinationType combinationType) {
        return GeneratorAssistant.generate(combinationType);
    }
}
