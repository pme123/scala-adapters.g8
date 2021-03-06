// Comment to get more information during initialization
logLevel := Level.Warn

// Resolvers
resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += Resolver.url("heroku-sbt-plugin-releases",
  url("https://dl.bintray.com/heroku/sbt-plugins/"))(Resolver.ivyStylePatterns)

resolvers += "jitpack" at "https://jitpack.io"

// Sbt plugins
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "$play_version$")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")

addSbtPlugin("com.vmunier" % "sbt-web-scalajs" % "1.0.6")

addSbtPlugin("com.typesafe.sbt" % "sbt-gzip" % "1.0.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.1.3")

// see https://github.com/portable-scala/sbt-crossproject
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "$scalajs_version$")
addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "0.4.0")

// version infos
addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.7.0")
