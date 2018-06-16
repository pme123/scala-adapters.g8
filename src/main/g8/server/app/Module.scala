import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport
import pme123.adapters.server.boundary.NoAccessControl
import pme123.adapters.server.control._
import pme123.adapters.shared.AccessControl
import $package$.$name;format="Camel"$JobCreation

class Module extends AbstractModule with AkkaGuiceSupport {

  override def configure(): Unit = {
    bind(classOf[JobCreation])
      .to(classOf[$name;format="Camel"$JobCreation])
      .asEagerSingleton()

    bind(classOf[AccessControl])
      .to(classOf[NoAccessControl])
  }
}
