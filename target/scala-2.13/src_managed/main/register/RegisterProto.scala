// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package register

object RegisterProto extends _root_.scalapb.GeneratedFileObject {
  lazy val dependencies: Seq[_root_.scalapb.GeneratedFileObject] = Seq.empty
  lazy val messagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] =
    Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]](
      register.UUID,
      register.RegisterData,
      register.UserId
    )
  private lazy val ProtoBytes: _root_.scala.Array[Byte] =
      scalapb.Encoding.fromBase64(scala.collection.immutable.Seq(
  """Cg5yZWdpc3Rlci5wcm90byIoCgRVVUlEEiAKBXZhbHVlGAEgASgDQgriPwcSBXZhbHVlUgV2YWx1ZSJbCgxSZWdpc3RlckRhd
  GESIAoFbG9naW4YASABKAlCCuI/BxIFbG9naW5SBWxvZ2luEikKCHBhc3N3b3JkGAIgASgJQg3iPwoSCHBhc3N3b3JkUghwYXNzd
  29yZCI1CgZVc2VySWQSKwoHdXNlcl9pZBgBIAEoCzIFLlVVSURCC+I/CBIGdXNlcklkUgZ1c2VySWQyNQoPUmVnaXN0ZXJTZXJ2a
  WNlEiIKBlNpZ251cBINLlJlZ2lzdGVyRGF0YRoHLlVzZXJJZCIAYgZwcm90bzM="""
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