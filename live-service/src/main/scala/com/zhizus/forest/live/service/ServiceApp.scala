package com.zhizus.forest.live.service

import akka.actor.{ActorSelection, ActorSystem, Props}

/**
  * Created by Dempe on 2017/1/12.
  */
object ServiceApp extends App {

  private val system: ActorSystem = ActorSystem("serviceApp")
  val busActorPath = "akka.tcp://busApp@127.0.0.1:3552/user/busActor";
  private val busActor: ActorSelection = system.actorSelection(busActorPath)
  system.actorOf(Props(new DefaultServiceActor(busActor)), name = "serviceDemoActor")

}
