package com.zhizus.forest.live.service

import akka.actor.{Actor, ActorLogging, ActorSelection}
import com.zhizus.forest.live.common.codec.Request

/**
  * Created by Dempe on 2017/1/12.
  */
abstract class AbstractServiceActor(busActor: ActorSelection) extends Actor with ActorLogging {
  // 根据uri自动路由到具体方法，由框架统一调用封装返回，避免业务使用者阻塞actor
  override def receive: Receive = {
    case req: Request => {
      log.info("service req:{}", req)
      val uri: String = req.payload.uri
      execute(uri, req)
    }
  }

  def execute(uri: String, req: Request);
}
