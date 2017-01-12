package com.zhizus.forest.live.service

import akka.actor.{ActorSelection, ActorSystem, Props}
import com.zhizus.forest.live.common.codec.Response

/**
  * Created by Dempe on 2017/1/12.
  */
object ServiceApp extends App{
  private val system: ActorSystem = ActorSystem("serviceApp")
  val busActorPath = "";
  private val busActor: ActorSelection = system.actorSelection(busActorPath)
  system.actorOf(Props(new AbstractServiceActor(busActor) {
    override def route(uri: String): Response = {
      println(">>>>>>>" + uri)
      new Response(null, null);
    }
  }))

}
