package com.zhizus.forest.live.bus

import akka.actor.{Actor, ActorLogging, ActorSelection, ActorSystem, Props}
import com.zhizus.forest.live.common.codec.{Constants, Request, Response}

/**
  * 仅透传消息，不处理业务逻辑
  * 根据{req.header.service}透传消息
  * todo 失败监听 降级策略 平滑重启 隔离策略
  * Created by Dempe on 2017/1/12.
  */
class BusActor(actorSystem: ActorSystem) extends Actor with ActorLogging {
  def receive: Receive = {

    // 处理上行消息
    case req: Request => {

      log.info("req:{}", req)

      //根据协议的service来分发到不同的业务进程
      val path = buildPath(req)

      // todo 负载均衡
      val selection: ActorSelection = actorSystem.actorSelection(path)

      // 透传消息到子进程
      selection ! req;

    }
    // 处理下行消息
    case rsp: Response => {

      log.info("rsp:{}", rsp)

      val msgType: Byte = rsp.header.msgType
      //　todo 组装广播|单播消息逻辑


    }
  }

  def buildPath(request: Request): String = {
    Constants.actorPath + select(request) + "/user/" + request.header.service
  }

  def select(request: Request): String = {
    "127.0.0.1:2552"
  }
}

object BusApp extends App {
  private val system: ActorSystem = ActorSystem("busApp")
  system.actorOf(Props(new BusActor(system)), name = "busActor")

}
