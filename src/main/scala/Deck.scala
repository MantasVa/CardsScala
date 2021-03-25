package com.mantas.cards

case class Deck private (cardDeck: List[Card]) {
  def split(): (List[Card], List[Card]) = cardDeck.splitAt(cardDeck.size / 2)
}

object Deck {
  def apply(cardDeck: List[Card]): Option[Deck] = {
    if (cardDeck.size == 52 && cardDeck.distinct.size == cardDeck.size) {
      Some(new Deck(cardDeck))
    } else {
      None
    }
  }
}
