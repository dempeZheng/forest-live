import akka.actor._

/**
  * Created by Dempe on 2017/1/11 0011.
  */
class PublishActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case msg: String =>
      log.info("publish actor receive msg:{}", msg)
  }
}

object Demo extends App {
  private val system: ActorSystem = ActorSystem("demo")
  private val publishActor: ActorRef = system.actorOf(Props[PublishActor], "publishActorTest")
  publishActor ! "hello msg"

  val lookupBus = new LookupBusImpl
  lookupBus.subscribe(testActor, "greetings")
  lookupBus.publish(MsgEnvelope("time", System.currentTimeMillis()))
  lookupBus.publish(MsgEnvelope("greetings", "hello"))
  expectMsg("hello")

}