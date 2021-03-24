package com.mantas.cards

case class Player(hand:List[Card], winningCards:List[Card] = List.empty) {
  def takeCard: (Card, Player) = (hand.head, Player(hand.drop(1), winningCards))
  def getCardIndex(card:Card):Int = hand.indexOf(card)
  def addWinningCards(cards: List[Card]): List[Card] = winningCards ++ cards
}
