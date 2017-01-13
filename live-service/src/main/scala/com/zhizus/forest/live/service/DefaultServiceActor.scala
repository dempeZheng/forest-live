package com.zhizus.forest.live.service

import akka.actor.ActorSelection
import com.zhizus.forest.live.common.codec.Request
import com.zhizus.forest.live.service.processor.ServiceProcessor
import org.springframework.context.support.ClassPathXmlApplicationContext

/**
  * Created by Dempe on 2017/1/12.
  */
class DefaultServiceActor extends AbstractServiceActor {

  var processor: ServiceProcessor = null;

  @scala.throws[Exception](classOf[Exception])
  override def preStart(): Unit = {
    val springContext: ClassPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-context.xml")
    processor = new ServiceProcessor(springContext)
  }

  override def execute(uri: String, req: Request, selection: ActorSelection): Unit = {
    processor.execute(uri, req, selection)
  }
}
