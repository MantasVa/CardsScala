package com.mantas.cards

case class Player(hand: List[Card], winningCards: List[Card] = List.empty) {
  def removeTopCard():Player = this.copy(hand.tail, winningCards)

  def addWinningCards(cards: List[Card]): Player = this.copy(hand, winningCards ++ cards)
}
