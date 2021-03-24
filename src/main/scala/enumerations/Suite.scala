package com.mantas.cards
package enumerations

sealed trait Suite
case object Heart extends Suite
case object Diamond extends Suite
case object Club extends Suite
case object Spade extends  Suite
