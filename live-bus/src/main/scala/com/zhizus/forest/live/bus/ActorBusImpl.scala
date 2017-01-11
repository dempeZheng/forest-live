package com.zhizus.forest.live.bus

/**
  * Created by Dempe on 2017/1/11 0011.
  */

import akka.actor.ActorRef
import akka.event.ActorEventBus
import akka.event.ActorClassification
import akka.event.ActorClassifier

case class Notification(ref: ActorRef, id: Int)

class ActorBusImpl extends ActorEventBus with ActorClassifier with ActorClassification {
  type Event = Notification

  // is used for extracting the classifier from the incoming events
  override protected def classify(event: Event): ActorRef = event.ref

  // determines the initial size of the index data structure
  // used internally (i.e. the expected number of different classifiers)
  override protected def mapSize: Int = 128
}