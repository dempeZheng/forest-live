package com.zhizus.forest.live.bus

import akka.actor.{ActorSelection, ActorSystem}
import com.alibaba.fastjson.JSONObject
import com.typesafe.config.ConfigFactory
import com.zhizus.forest.live.common.codec.{Header, Payload, Request}

/**
  * Created by Dempe on 2017/1/12.
  */
object ClientSimulator extends App {

  private val system: ActorSystem = ActorSystem("simulator", ConfigFactory.load().getConfig("simulator"))

  val busActorPath = "akka.tcp://busApp@127.0.0.1:3552/user/busActor";
  private val busActor: ActorSelection = system.actorSelection(busActorPath)

  val req = makeDefReq();
  busActor ! req;


  def makeDefReq(): Request = {
    val json: JSONObject = new JSONObject()
    json.put("name", "akka service")
    new Request(new Header(1, 1, 5555, 6666, 7777, 1, 1, 1, 0), new Payload("demo/hello", json.toJSONString.getBytes))
  }

}
