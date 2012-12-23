
organization:= "net.weverwijk"

name:=  "cashflow"

scalaVersion := "2.9.1"

seq(webSettings :_*)

libraryDependencies ++= Seq(
	"org.scalatra" %% "scalatra" % "2.0.4",
	"org.scalatra" %% "scalatra-scalate" % "2.0.4",
	"net.liftweb" %% "lift-json" % "2.4",
	"org.slf4j" % "slf4j-simple" % "1.6.4",
	"org.specs2" %% "specs2" % "1.9" % "test",
	"junit" % "junit" % "4.7" % "test",
	"org.mortbay.jetty" % "jetty" % "6.1.22" % "container",
	"org.mortbay.jetty" % "servlet-api" % "2.5-20081211"
)

resolvers ++= Seq("snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
                    "releases"  at "https://oss.sonatype.org/content/repositories/releases",
                    "Java.net Maven2 Repository" at "http://download.java.net/maven/2/")