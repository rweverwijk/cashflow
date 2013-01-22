package net.weverwijk.cashflow

import io.Source
import java.text.SimpleDateFormat


class TransactionDAO {

  val formatMonth: SimpleDateFormat = new SimpleDateFormat("yyyyMM")
  val formatDay: SimpleDateFormat = new SimpleDateFormat("yyyyMMdd")

  def getTransactions : Seq[Transaction] = {
    Source.fromFile("/Users/rvanweverwijk/Documents/prive/rekeninggegevens.csv", "UTF-8").getLines().map(x => Transaction(x)).toSeq
  }

  def getSumByMoth = {
    getTransactions map(x => (formatMonth.format(x.date) -> x.amount)) groupBy(x => x._1) mapValues(_.map(_._2).sum)
  }

  def getBalanceByMonth : Seq[(String, Transaction)]= {
    getTransactionsWithBalance filter(x => x.ownAccount equalsIgnoreCase "0159274230") map(x => (formatMonth.format(x.date) -> x)) groupBy(x => x._1) flatMap(e=> List(e._2.head)) toSeq
  }

  def getTransactionsWithBalance = {
    val transactions: Seq[Transaction] = getTransactions.reverse
    val transactionsCheckingAccount = transactions.filter(x => x.ownAccount equalsIgnoreCase "0159274230")
    val transactionsSavingAccount = transactions.filter(x => x.ownAccount equalsIgnoreCase "1592177026")

    def calculateBalance(transactionsToMap : Seq[Transaction]) : Seq[Transaction] = {
      var seq = Seq[Transaction]()
      seq = seq :+ transactionsToMap.head
      var balance = transactionsToMap.head.balance - transactionsToMap.head.amount
      transactionsToMap.tail foreach (x => {
        seq = seq :+ (x update balance)
        balance = balance - x.amount
      })
      seq.reverse
    }
    calculateBalance(transactionsCheckingAccount) ++ calculateBalance(transactionsSavingAccount)
  }
}