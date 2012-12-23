package net.weverwijk.cashflow

import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class TransactionSpec extends Specification {
  "Apply method of Transaction" should {
    "return correct Transaction" in {
      val transaction: Transaction = Transaction("\"0123456789\",\"EUR\",20101201,\"D\",2.96,\"0987654321\",\"Begunstigde\",20101201,\"db\",\"\",\"Omschrijving 1\",\"Omschrijving 2\",\"Omschrijving 3\",\"Omschrijving 4\",\"Omschrijving 5\",\"Omschrijving 6\"")
      transaction.ownAccount must equalTo("0123456789")
      transaction.counterAccount must equalTo("0987654321")
      transaction.toAccountName must equalTo("Begunstigde")
      transaction.description must equalTo("Omschrijving 1\nOmschrijving 2\nOmschrijving 3\nOmschrijving 4\nOmschrijving 5\nOmschrijving 6")
      transaction.amount must equalTo(2.96)
      transaction.debetCredit must equalTo(Debet())

    }
  }
}