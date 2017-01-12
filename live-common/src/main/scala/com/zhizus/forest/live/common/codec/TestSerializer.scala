package com.zhizus.forest.live.common.codec

/**
  * Created by Dempe on 2017/1/12.
  */

import akka.serialization._

object TestSerializer extends TestSerializer;
class TestSerializer  extends Serializer {
  val nullAsBytes = Array[Byte]()
  def includeManifest: Boolean = false
  def identifier = 0
  def toBinary(o: AnyRef) = nullAsBytes
  def fromBinary(bytes: Array[Byte], clazz: Option[Class[_]]): AnyRef = null
}
