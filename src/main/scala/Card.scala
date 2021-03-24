package com.mantas.cards

import enumerations._

case class Card(suit: Suite, cardValue: Rank) {
  def getValue(trumpSuit: Suite): Int = {
    val trumpAddedValue = 100
    val base = cardValue match {
      case Two => 2
      case Three => 3
      case Four => 4
      case Five => 5
      case Six => 6
      case Seven => 7
      case Eight => 8
      case Nine => 9
      case Ten => 10
      case Jack => 11
      case Queen => 12
      case King => 13
      case Ace => 14
    }
    if (suit == trumpSuit) base + trumpAddedValue else base
  }
}
