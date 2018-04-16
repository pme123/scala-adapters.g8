package server

import javax.inject.{Inject, Named, Singleton}

import akka.actor.{ActorRef, ActorSystem}
import akka.stream.Materializer
import pme123.adapters.server.control.{JobActor, JobCreation}
import pme123.adapters.server.entity.AdaptersContext.settings.jobConfigs
import pme123.adapters.server.entity.ServiceException
import pme123.adapters.shared.JobConfig
import pme123.adapters.shared.JobConfig.JobIdent

import scala.concurrent.ExecutionContext

@Singleton
class $name;format="Camel"$JobCreation @Inject()($name;format="camel"$Job: $name;format="Camel"$Process
                                   , @Named("actorSchedulers") val actorSchedulers: ActorRef
                                   , actorSystem: ActorSystem
                                  )(implicit val mat: Materializer
                                    , val ec: ExecutionContext)
  extends JobCreation {

  private val $name;format="camel"$JobIdent: JobIdent = "$name;format="camel"$Job"

  private lazy val $name;format="camel"$JobRef =
    actorSystem.actorOf(JobActor.props(jobConfigs($name;format="camel"$JobIdent), $name;format="camel"$Job), $name;format="camel"$JobIdent)

  def createJobActor(jobConfig: JobConfig): ActorRef = jobConfig.jobIdent match {
    case x if x == $name;format="camel"$JobIdent => $name;format="camel"$JobRef
    case other => throw ServiceException(s"There is no Job for \$other")
  }

}
