package $package$

import com.typesafe.config.{Config, ConfigFactory}
import pme123.adapters.server.entity.AdaptersContextPropsImplicits
import pme123.adapters.shared.AdaptersContextProp

/**
  * created by pascal.mengelt
  * This config uses the small framework typesafe-config.
  * See here the explanation: https://github.com/typesafehub/config
  * The configuration can be overridden direct in distribution:
  *  - {DIST_BASE}/conf/reference.conf
  * see here the base: http://stackoverflow.com/questions/6201349/how-to-read-properties-file-placed-outside-war
  *
  * The defaults are described in reference.conf
  */
object $name;format="Camel"$Settings {
  val configPath = "$package$"

  val helloProp = "hello"
  val numberProp = "number"
  val passwordProp = "password"
  val titleShortProp = "title.short"
  val titleLongProp = "title.long"

  def config(): Config = ConfigFactory.load()

}

// this settings will be validated on startup
class $name;format="Camel"$Settings(config: Config) {

  import $name;format="Camel"$Settings._

  // checkValid(), just as in the plain SimpleLibContext
  config.checkValid(ConfigFactory.defaultReference(), configPath)
  val projConfig: Config = config.getConfig(configPath)

  val hello: String = projConfig.getString(helloProp)
  val number: Int = projConfig.getInt(numberProp)
  val password: String = projConfig.getString(passwordProp)
  val titleShort: String = projConfig.getString(titleShortProp)
  val titleLong: String = projConfig.getString(titleLongProp)
}

// This is a different way to do $name;format="Camel"$Context, using the
// $name;format="Camel"$Settings class to encapsulate and validate the
// settings on startup
class $name;format="Camel"$Context(val config: Config)
  extends AdaptersContextPropsImplicits {

  import $name;format="Camel"$Settings._

  val name = "$name;format="Camel"$"

  val settings = new $name;format="Camel"$Settings(config)

  lazy val props: Seq[AdaptersContextProp] = {
    Seq(
      AdaptersContextProp(helloProp, settings.hello)
      , AdaptersContextProp(numberProp, settings.number) // to string by AdaptersContextPropsImplicits
      , AdaptersContextProp(passwordProp, pwd(settings.password)) // make password invisible
      , AdaptersContextProp(titleShortProp, settings.titleShort)
      , AdaptersContextProp(titleLongProp, settings.titleLong)
    )
  }
}

// default Configuration
object $name;format="Camel"$Context extends $name;format="Camel"$Context($name;format="Camel"$Settings.config())
