package com.zhizus.forest.live.service

import akka.actor.{Actor, ActorLogging, ActorSelection}
import com.zhizus.forest.live.common.codec.{Request, Response}

/**
  * Created by Dempe on 2017/1/12.
  */
abstract class AbstractServiceActor(busActor: ActorSelection) extends Actor with ActorLogging {
  override def receive: Receive = {
    case req: Request => {
      log.info("service req:{}", req)
      val uri: String = req.payload.uri
      busActor ! route(uri)
    }
  }

  def route(uri: String): Response;
}
