package com.mantas.cards

case class Deck(cardDeck:List[Card]){
  def shuffle() :(Boolean, Deck) = (true, Deck(scala.util.Random.shuffle(cardDeck)))
  def split():(List[Card], List[Card]) = cardDeck.splitAt(cardDeck.size / 2)
}
