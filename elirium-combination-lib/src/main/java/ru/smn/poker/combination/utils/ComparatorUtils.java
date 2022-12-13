package ru.smn.poker.combination.utils;

import java.util.Comparator;
import java.util.function.ToIntFunction;

public class ComparatorUtils {

    public static <T> Comparator<? super T> desc(ToIntFunction<? super T> keyExtractor) {
        return Comparator.comparingInt(keyExtractor).reversed();
    }

    public static <T> Comparator<T> asc(ToIntFunction<? super T> keyExtractor) {
        return Comparator.comparingInt(keyExtractor);
    }

}
