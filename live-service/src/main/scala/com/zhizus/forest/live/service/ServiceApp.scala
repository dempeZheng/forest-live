package com.zhizus.forest.live.service

import akka.actor.{ActorSystem, Props}

/**
  * Created by Dempe on 2017/1/12.
  */
object ServiceApp extends App {

  private val system: ActorSystem = ActorSystem("serviceApp")
  system.actorOf(Props(new DefaultServiceActor()), name = "serviceDemoActor")

}
