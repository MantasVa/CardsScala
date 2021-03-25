package com.mantas.cards

case class Player(hand: List[Card], winningCards: List[Card] = List.empty) {
  def addWinningCards(cards: List[Card]): Player = this.copy(hand, winningCards ++ cards)
}
