package ru.smn.poker.combination.utils;

import ru.smn.poker.combination.data.Card;
import ru.smn.poker.combination.data.PowerType;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static ru.smn.poker.combination.data.CardSizeData.COMBINATION_SIZE;

public class CardUtils {

    public static Optional<PowerType> findPowerOfCardWithFilter(List<Card> cards, Predicate<Map.Entry<PowerType, Long>> predicate) {
        return cards.stream()
                .collect(Collectors.groupingBy(Card::getPower, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(predicate)
                .sorted(Map.Entry.comparingByKey())
                .limit(1)
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public static boolean containsAllCardsForFlushRoyal(List<Card> cards) {
        final Optional<Card> ace = findCardByPowerType(cards, PowerType.A_POWER);
        final Optional<Card> king = findCardByPowerType(cards, PowerType.K_POWER);
        final Optional<Card> queen = findCardByPowerType(cards, PowerType.Q_POWER);
        final Optional<Card> jack = findCardByPowerType(cards, PowerType.J_POWER);
        final Optional<Card> ten = findCardByPowerType(cards, PowerType.TEN_POWER);

        return ace.isPresent() && king.isPresent() && queen.isPresent() && jack.isPresent() && ten.isPresent();
    }

    public static List<Card> checkStraitWithAce(List<Card> cards) {
        final List<Card> distinctList = sortByAsc(distinctByPowerType(cards));

        final Optional<Card> ace = findCardByPowerType(distinctList, PowerType.A_POWER);
        final Optional<Card> two = findCardByPowerType(distinctList, PowerType.TWO_POWER);
        final Optional<Card> three = findCardByPowerType(distinctList, PowerType.THREE_POWER);
        final Optional<Card> four = findCardByPowerType(distinctList, PowerType.FOUR_POWER);
        final Optional<Card> five = findCardByPowerType(distinctList, PowerType.FIVE_POWER);

        if (ace.isPresent() && two.isPresent() && three.isPresent() && four.isPresent() && five.isPresent()) {
            return Arrays.asList(ace.get(), two.get(), three.get(), four.get(), five.get());
        }

        return Collections.emptyList();
    }

    private static Optional<Card> findCardByPowerType(List<Card> distinctList, PowerType powerType) {
        return distinctList.stream()
                .filter(cardType -> cardType.getPowerAsInt() == powerType.getPowerAsInt())
                .findAny();
    }

    public static boolean isStrait(List<Card> cards) {
        if (cards.size() != COMBINATION_SIZE) {
            throw new RuntimeException("card size to check straight must be " + COMBINATION_SIZE);
        }

        final List<Card> cardTypes = sortByDesc(cards);

        int lastPower = cardTypes.get(0).getPowerAsInt();

        for (int m = 1; m < cardTypes.size(); m++) {
            if (lastPower - cardTypes.get(m).getPowerAsInt() != 1) {
                return false;
            }
            lastPower = cardTypes.get(m).getPowerAsInt();
        }
        return true;
    }

    public static void removeCardsWithPower(List<Card> cards, PowerType powerType) {
        final List<Card> cardsForDelete = cards.stream()
                .filter(cardType -> cardType.getPower().equals(powerType))
                .toList();

        cards.removeAll(cardsForDelete);
    }

    public static List<Card> distinctByPowerType(List<Card> cards) {
        return cards.stream()
                .filter(StreamUtils.distinctByKey(Card::getPowerAsInt))
                .collect(Collectors.toList());
    }

    public static Card findTheBiggestCardIgnoringFilter(List<Card> cards, PowerType filter) {
        return cards.stream()
                .filter(cardType -> cardType.getPower() != (filter))
                .max(Comparator.comparingInt(Card::getPowerAsInt))
                .orElseThrow(() -> new RuntimeException("cannot find min card"));
    }

    public static Card findTheBiggestCard(List<Card> cards) {
        return cards.stream()
                .max(Comparator.comparingInt(Card::getPowerAsInt))
                .orElseThrow(() -> new RuntimeException("cannot find max card"));
    }

    public static int sumPowerOfCards(List<Card> cards) {
        return cards.stream().map(Card::getPowerAsInt)
                .reduce(Integer::sum)
                .orElseThrow(() -> new RuntimeException("cannot calculate sum"));
    }

    public static Card findTheSmallestCard(List<Card> cards) {
        return cards.stream()
                .min(Comparator.comparingInt(Card::getPowerAsInt))
                .orElseThrow(() -> new RuntimeException("cannot find min card"));
    }

    public static List<Card> cutFlush(List<Card> cards) {
        if (cards.size() > COMBINATION_SIZE) {
            cards.remove(findTheSmallestCard(cards));
            cutFlush(cards);
        }
        return cards;
    }

    public static List<Card> findFlushBySuit(List<Card> cardTypes, Card.SuitType suitType) {
        final List<Card> cards = cardTypes.stream()
                .filter(cardType -> cardType.getSuitType().equals(suitType))
                .collect(Collectors.toList());

        if (cards.size() >= COMBINATION_SIZE) {
            if (cards.size() > COMBINATION_SIZE) {
                return cutFlush(cards);
            }
            return cards;
        }
        return Collections.emptyList();
    }

    public static List<Card> getCardsWithPower(List<Card> cards, PowerType powerType, int count) {
        return cards.stream()
                .filter(cardType -> cardType.getPower().equals(powerType))
                .limit(count)
                .collect(Collectors.toList());
    }

    public static List<Card> sortByDesc(List<Card> cards) {
        return cards.stream()
                .sorted(ComparatorUtils.desc(Card::getPowerAsInt))
                .collect(Collectors.toList());
    }

    public static List<Card> sortByAsc(List<Card> cards) {
        return cards.stream()
                .sorted(ComparatorUtils.asc(Card::getPowerAsInt))
                .collect(Collectors.toList());
    }
}
