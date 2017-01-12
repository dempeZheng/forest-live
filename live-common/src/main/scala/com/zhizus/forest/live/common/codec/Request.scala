package com.zhizus.forest.live.common.codec

/**
  * Created by Dempe on 2017/1/12.
  */
trait LiveMessage extends Serializable

case class Payload(uri: String, payload: Array[Byte])

/**
  *
  * @param magic    魔数
  * @param version  协议版本
  * @param uid
  * @param topSid   顶级频道号
  * @param subSid   子频道号
  * @param platform 平台id（ios|android|pc|web)
  * @param service  业务标识
  * @param msgType  消息类型（全频道广播|频道内广播|单播）
  * @param extend   扩展字段
  */
case class Header(magic: Short, version: Byte, uid: Long, topSid: Long, subSid: Long, platform: Byte, service: Int,
                  msgType: Byte, extend: Byte) extends LiveMessage

/**
  * 上行消息
  *
  * @param header
  * @param payload
  */
case class Request(header: Header, payload: Payload)

/**
  * 下行消息
  *
  * @param header
  * @param payload
  */
case class Response(header: Header, payload: Payload)
