package com.mantas.cards

import enumerations._

object Game {

  private val suites = List(Spade, Heart, Club, Diamond)
  private val ranks = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)
  private val trumpSuit = suites(scala.util.Random.nextInt(4))

  def playGame(): (Player, Suite) = {
    val (isShuffled, shuffledDeck) = Deck(for(r <- ranks; s <- suites) yield Card(s,r)).shuffle()
    val deckSize = shuffledDeck.cardDeck.size

    if(deckSize % 2 == 1 || deckSize == 0 || !isShuffled){
      throw new Exception("Deck is empty or not shuffled or size is not equal number")
    }

    val (player1Hand, player2Hand) = shuffledDeck.split()
    val (player1, player2) = checkCards(Player(player1Hand), Player(player2Hand))
    if(player1.winningCards.size > player2.winningCards.size){
      (player1,trumpSuit)
    } else {
      (player2,trumpSuit)
    }
  }

  private def checkCards(player1:Player, player2:Player): (Player, Player) = {

    val player1WonCards = player1.hand.filter( card => {
      card.getValue(trumpSuit) >= player2.hand(player1.getCardIndex(card)).getValue(trumpSuit)
    })
    val finalPlayer1 = Player(List.empty , player1WonCards ++ player1WonCards
      .map(c => player1.getCardIndex(c)).map(player2.hand(_))
      .filter(x => x.getValue(trumpSuit) != player1.hand(player2.getCardIndex(x)).getValue(trumpSuit)))

    val player2WonCards = player2.hand.filter( card => {
      card.getValue(trumpSuit) >= player1.hand(player2.hand.indexOf(card)).getValue(trumpSuit)
    })
    val finalPlayer2 = Player(List.empty, player2WonCards ++ player2WonCards
      .map(c => player2.getCardIndex(c)).map(player1.hand(_))
      .filter(x => x.getValue(trumpSuit) != player2.hand(player1.getCardIndex(x)).getValue(trumpSuit)))

    (finalPlayer1, finalPlayer2)
  }
}
