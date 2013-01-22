package net.weverwijk.cashflow

import org.scalatra.ScalatraServlet
import net.liftweb.json._
import net.liftweb.json.Extraction._
import org.scalatra.scalate.ScalateSupport

/**
 * Created by IntelliJ IDEA.
 * User: rvanweverwijk
 * Date: 4/28/12
 * Time: 21:33 PM
 * To change this template use File | Settings | File Templates.
 */


// ~;container:start; container:reload /
class WebApp extends ScalatraServlet with ScalateSupport {

  implicit val formats = Serialization.formats(FullTypeHints(List(classOf[Transaction])))

  get("/hello/:name") {
    <html>
      <head>
        <title>Hello!</title>
      </head> <body>
      <h1>Hello
        {params("name")}
      </h1>
    </body>
    </html>
  }

  get("/transactions") {
    val fs = new TransactionDAO().getTransactionsWithBalance
    val json = decompose(fs)
    pretty(render(json))
  }

  get("/sumByMonth") {
    val fs = new TransactionDAO().getSumByMoth
    val json = decompose(fs)
    pretty(render(json))
  }

  get("/balanceByMonth") {
    val fs = new TransactionDAO().getBalanceByMonth sortBy(r=> (r._1))
    val json = decompose(fs);
    pretty(render(json))
  }

  get("/transactionsHTML") {
    contentType="text/html"
    mustache("index", ("transactions" -> new TransactionDAO().getTransactionsWithBalance))
  }

  get("/balanceHTML") {
    contentType="text/html"
    mustache("balance")
  }

}