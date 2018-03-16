package server

import java.time.LocalDateTime
import javax.inject.Inject

import akka.actor.ActorRef
import akka.stream.Materializer
import pme123.adapters.server.control.demo.DemoService.toISODateTimeString
import pme123.adapters.server.control.{JobProcess, LogService}
import pme123.adapters.shared.LogLevel.{DEBUG, ERROR, INFO, WARN}
import pme123.adapters.shared._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

trait $name;format="Camel"$Process extends JobProcess {

  def jobLabel: String

  def createInfo(): ProjectInfo = // same version as the adapters!
    createInfo(pme123.adapters.version.BuildInfo.version)

  // the process fakes some long taking tasks that logs its progress
  def runJob(user: String)
            (implicit logService: LogService
             , jobActor: ActorRef): Future[LogService] = {
    Future {
      logService.startLogging()
      val results =
        for {
          i <- 2 to 3
          k <- 1 to 5
        } yield $name;format="Camel"$Result(s"Image Gallery \$i - \$k"
          , s"https://www.gstatic.com/webp/gallery\$i/\$k.png"
          , toISODateTimeString(LocalDateTime.now().minusHours(Random.nextInt(100))))

      results.foreach(doSomeWork)

      logService
    }
  }

  protected def doSomeWork(dr : $name;format="Camel"$Result)
                          (implicit logService: LogService): LogEntry = {
    Thread.sleep(500)
    val ll = Random.shuffle(List(DEBUG, DEBUG, INFO, INFO, INFO, WARN, WARN, ERROR)).head
    val detail = List(None, Some(s"Details for \$jobLabel \$ll: \${dr.name}")
                      , Some("Scala Adapters\nA simple framework to implement batch jobs - provides standard UI-clients to monitor and test them.\nLatest release 1.0.0"))(Random.nextInt(3))
    logService.log(ll, s"Job: \$jobLabel \$ll: \${dr.name}", detail)
  }
}

class $name;format="Camel"$JobProcess @Inject()()(implicit val mat: Materializer, val ec: ExecutionContext)
  extends $name;format="Camel"$Process {
  override def jobLabel: String = "$name;format="Camel"$ Job"
}


