package com.mantas.cards

import enumerations._
import scala.util.Random

object Game {

  private val suites = List(Spade, Heart, Club, Diamond)
  private val ranks = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)
  private val trumpSuit = suites(scala.util.Random.nextInt(4))

  def playGame(): (Player, Suite) = {
    val optionShuffledDeck: Option[Deck] = Deck(Random.shuffle(for (r <- ranks; s <- suites) yield Card(s, r)))
    val shuffledDeck = optionShuffledDeck match {
      case Some(shuffledDeck) => shuffledDeck
      case None => throw new IllegalArgumentException("Illegal deck")
    }

    val (player1Hand, player2Hand) = shuffledDeck.split()
    val (player1, player2) = checkCards(Player(player1Hand), Player(player2Hand))
    if (player1.winningCards.size > player2.winningCards.size) {
      (player1, trumpSuit)
    } else {
      (player2, trumpSuit)
    }
  }

  private def checkCards(player1: Player, player2: Player): (Player, Player) = {

    val battles = player1.hand.zip(player2.hand)
    battles.foldLeft((player1, player2))((players, cards) => {
      val firstCardValue = cards._1.getValue(trumpSuit)
      val secondCardValue = cards._2.getValue(trumpSuit)

      if (firstCardValue > secondCardValue) {
        (players._1.addWinningCards(List(cards._1, cards._2)), players._2.removeTopCard())
      } else if (firstCardValue < secondCardValue) {
        (players._1.removeTopCard(), players._2.addWinningCards(List(cards._1, cards._2)))
      } else {
        (players._1.addWinningCards(List(cards._1)), players._2.addWinningCards(List(cards._2)))
      }
    })
  }
}
