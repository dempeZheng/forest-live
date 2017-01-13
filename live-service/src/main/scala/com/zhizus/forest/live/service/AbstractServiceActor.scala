package com.zhizus.forest.live.service

import akka.actor.{Actor, ActorLogging, ActorSelection}
import com.zhizus.forest.live.common.codec.Request

/**
  * Created by Dempe on 2017/1/12.
  */
abstract class AbstractServiceActor extends Actor with ActorLogging {
  // 根据uri自动路由到具体方法，由框架统一调用封装返回，避免业务使用者阻塞actor
  override def receive: Receive = {
    case req: Request => {
      log.info("service req:{}", req)
      val uri: String = req.payload.uri
      // 异步将处理消息，并将返回写过发送给busActor
      execute(uri, req, context.actorSelection("akka.tcp://busApp@127.0.0.1:3552/user/busActor"))
    }
  }

  def execute(uri: String, req: Request, selection: ActorSelection);
}
