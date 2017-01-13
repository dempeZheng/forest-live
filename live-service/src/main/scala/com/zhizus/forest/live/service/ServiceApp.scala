package com.zhizus.forest.live.service

import akka.actor.{ActorSystem, Props}
import akka.event.{Logging, LoggingAdapter}

/**
  * Created by Dempe on 2017/1/12.
  */
object ServiceApp extends App {

  val system: ActorSystem = ActorSystem("serviceApp")
  val log: LoggingAdapter = Logging(system, "serviceApp")
  system.actorOf(Props(new DefaultServiceActor()), name = "serviceDemoActor")
  log.info("init serviceDemoActor")

}
