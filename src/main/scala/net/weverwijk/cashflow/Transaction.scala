package net.weverwijk.cashflow

import java.util.Date
import java.lang.String
import java.text.SimpleDateFormat

object Transaction {
  def apply(line: String): Transaction = {
    val format: SimpleDateFormat = new SimpleDateFormat("yyyyMMdd")
    val columns: Array[String] = line split (",") map (_.replace("\"", ""))
    val debetCredit =
      columns(3) match {
      case "D" => Debet()
      case _ => Credit()
    }
    val amount : Double =
      columns(3) match {
      case "D" => - (columns(4) toDouble)
      case _ => columns(4).toDouble
    }
    var balance : Double = -1
    try {
//      println(columns(16) + " line: " + (columns foreach (x => print( x))) )
      balance = columns(16).toDouble
    } catch {
      case e : ArrayIndexOutOfBoundsException =>
    }

    new Transaction(columns(0), columns(5), format.parse(columns(2)), debetCredit, amount, columns(6), columns(10) + "\n" + columns(11) + "\n" + columns(12) + "\n" + columns(13) + "\n" + columns(14) + "\n" + columns(15), columns(8), balance)
  }
}

case class Transaction(ownAccount: String,
                       counterAccount: String,
                       date: Date,
                       debetCredit: DebetCredit,
                       amount: Double,
                       toAccountName: String,
                       description: String,
                       code: String,
                       balance: Double) {
  def update(balance: Double = balance) : Transaction = {
  		Transaction(ownAccount, counterAccount, date, debetCredit, amount, toAccountName, description, code, balance)
  	}
}

sealed abstract class DebetCredit

case class Debet() extends DebetCredit

case class Credit() extends DebetCredit