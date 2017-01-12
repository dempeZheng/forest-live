package com.zhizus.forest.live.service

import akka.actor.ActorSelection
import com.zhizus.forest.live.common.codec.Request
import com.zhizus.forest.live.service.processor.ServiceProcessor
import org.springframework.context.support.ClassPathXmlApplicationContext

/**
  * Created by Dempe on 2017/1/12.
  */
class DefaultServiceActor(actorSelection: ActorSelection) extends AbstractServiceActor(actorSelection: ActorSelection) {

  val springContext: ClassPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-context.xml")

  private val processor: ServiceProcessor = new ServiceProcessor(springContext, actorSelection)

  override def execute(uri: String, req: Request): Unit = {
    processor.execute(uri, req)
  }
}
