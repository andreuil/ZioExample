// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package helloWorld

object HelloWorldProto extends _root_.scalapb.GeneratedFileObject {
  lazy val dependencies: Seq[_root_.scalapb.GeneratedFileObject] = Seq.empty
  lazy val messagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] =
    Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]](
      helloWorld.HelloRequest,
      helloWorld.HelloResponse
    )
  private lazy val ProtoBytes: _root_.scala.Array[Byte] =
      scalapb.Encoding.fromBase64(scala.collection.immutable.Seq(
  """ChBoZWxsb1dvcmxkLnByb3RvIl0KDEhlbGxvUmVxdWVzdBIiCgRuYW1lGAEgASgJQgniPwYSBG5hbWVIAFIEbmFtZYgBARIgC
  gVlbWFpbBgCIAEoCUIK4j8HEgVlbWFpbFIFZW1haWxCBwoFX25hbWUiNwoNSGVsbG9SZXNwb25zZRImCgdtZXNzYWdlGAEgASgJQ
  gziPwkSB21lc3NhZ2VSB21lc3NhZ2UyOQoKSGVsbG9Xb3JsZBIrCghTYXlIZWxsbxINLkhlbGxvUmVxdWVzdBoOLkhlbGxvUmVzc
  G9uc2UiAGIGcHJvdG8z"""
      ).mkString)
  lazy val scalaDescriptor: _root_.scalapb.descriptors.FileDescriptor = {
    val scalaProto = com.google.protobuf.descriptor.FileDescriptorProto.parseFrom(ProtoBytes)
    _root_.scalapb.descriptors.FileDescriptor.buildFrom(scalaProto, dependencies.map(_.scalaDescriptor))
  }
  lazy val javaDescriptor: com.google.protobuf.Descriptors.FileDescriptor = {
    val javaProto = com.google.protobuf.DescriptorProtos.FileDescriptorProto.parseFrom(ProtoBytes)
    com.google.protobuf.Descriptors.FileDescriptor.buildFrom(javaProto, _root_.scala.Array(
    ))
  }
  @deprecated("Use javaDescriptor instead. In a future version this will refer to scalaDescriptor.", "ScalaPB 0.5.47")
  def descriptor: com.google.protobuf.Descriptors.FileDescriptor = javaDescriptor
}