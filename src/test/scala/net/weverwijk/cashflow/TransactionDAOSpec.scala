package net.weverwijk.cashflow

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TransactionDAOSpec extends Specification{
  "TransactionDAO should return list of transactions" should {
    "getTransactions contains all transactions" in {
      new TransactionDAO().getTransactions.size must equalTo(966)
    }
  }
}