package ru.smn.poker.combination;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.smn.poker.combination.data.Card;

import java.util.Random;

public class CardContainerTest {
    private final CardContainer cardContainer = new CardContainer();

    @Test
    public void shouldRetrieveRandomCard() {
        int random = new Random().nextInt(Card.values().length);
        for (int i = 0; i < random; i++) {
            cardContainer.retrieveRandomCard();
        }
        Assertions.assertEquals(Card.values().length - random, cardContainer.getCards().size());
    }
}
