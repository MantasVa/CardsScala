package com.mantas.cards

import Game.playGame

object Start extends App {
  val (winner, trumpSuit) = playGame()
  println(winner)
  println(s"Winning cards count: ${winner.winningCards.size}")
  println(trumpSuit)

}