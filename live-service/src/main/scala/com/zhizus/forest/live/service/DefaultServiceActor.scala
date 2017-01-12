package com.zhizus.forest.live.service

import akka.actor.ActorSelection
import com.forest.zhizus.live.service.processor.ServiceProcessor
import com.zhizus.forest.live.common.codec.Request
import org.springframework.context.support.ClassPathXmlApplicationContext

/**
  * Created by Dempe on 2017/1/12.
  */
class DefaultServiceActor(actorSelection: ActorSelection) extends AbstractServiceActor(actorSelection: ActorSelection) {

  private val context: ClassPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-context.xml")

  private val processor: ServiceProcessor = new ServiceProcessor(context, actorSelection)

  override def execute(uri: Any, req: Request): Unit = {
    processor.execute(uri, req)
  }
}
