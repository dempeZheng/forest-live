import akka.actor._
import com.zhizus.forest.live.bus.{SubchannelBusImpl, LookupBusImpl, MsgEnvelope}

/**
  * Created by Dempe on 2017/1/11 0011.
  */
class DemoActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case msg: String =>
      log.info("msg>>>{}", msg)
  }
}

object Demo extends App {

  private val system: ActorSystem = ActorSystem("demo")
  private val busActor: ActorRef = system.actorOf(Props[DemoActor], "busActor")
  val lookupBus = new LookupBusImpl
  lookupBus.subscribe(busActor, "greetings")
  lookupBus.publish(MsgEnvelope("time", System.currentTimeMillis()))
  lookupBus.publish(MsgEnvelope("greetings", "hello"))
  lookupBus.publish(MsgEnvelope("greetings", "test"))
  lookupBus.publish(MsgEnvelope("test", "6666"))

  val subchannelBus = new SubchannelBusImpl
  subchannelBus.subscribe(busActor, "abc")
  subchannelBus.publish(MsgEnvelope("xyzabc", "x"))
  subchannelBus.publish(MsgEnvelope("bcdef", "b"))
  subchannelBus.publish(MsgEnvelope("abc", "c"))
  subchannelBus.publish(MsgEnvelope("abcdef", "d"))
}