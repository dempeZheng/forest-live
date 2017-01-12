package com.zhizus.forest.live.service

import akka.actor.{ActorSelection, ActorSystem, Props}
import com.zhizus.forest.live.common.codec.{Header, Payload, Response}

/**
  * Created by Dempe on 2017/1/12.
  */
object ServiceApp extends App {

  private val system: ActorSystem = ActorSystem("serviceApp")
  val busActorPath = "akka.tcp://busApp@127.0.0.1:3552/user/busActor";
  private val busActor: ActorSelection = system.actorSelection(busActorPath)
  system.actorOf(Props(new AbstractServiceActor(busActor) {
    override def route(uri: String): Response = {
      println(">>>>>>>" + uri)
      makeDefRsp()
    }
  }), name = "serviceDemoActor")

  def makeDefRsp(): Response = {
    new Response(new Header(1, 1, 5555, 6666, 7777, 1, 1, 1, 0), new Payload("", new Array[Byte](0)))
  }

}
