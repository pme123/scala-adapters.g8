import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport
import pme123.adapters.server.boundary.{AccessControl, NoAccessControl}
import pme123.adapters.server.control._
import server.$name;format="Camel"$JobCreation

class Module extends AbstractModule with AkkaGuiceSupport {

  override def configure(): Unit = {
    bind(classOf[JobCreation])
      .to(classOf[$name;format="Camel"$JobCreation])
      .asEagerSingleton()

    bind(classOf[AccessControl])
      .to(classOf[NoAccessControl])
  }
}
