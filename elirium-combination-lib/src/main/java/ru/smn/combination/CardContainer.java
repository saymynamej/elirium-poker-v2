package ru.smn.combination;

import ru.smn.combination.data.Card;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class CardContainer {
    private final List<Card> cards;
    private final Random random;

    public CardContainer() {
        this.cards = Arrays.stream(Card.values())
                .collect(Collectors.toList());

        this.random = new Random();
    }

    public CardContainer(List<Card> cards, Random random) {
        this.cards = cards;
        this.random = random;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public Card retrieveRandomCard() {
        final Card card = cards.get(this.random.nextInt(cards.size()));
        cards.remove(card);
        return card;
    }
}
