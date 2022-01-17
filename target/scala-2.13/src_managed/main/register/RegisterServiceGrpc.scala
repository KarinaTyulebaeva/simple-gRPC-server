package register

object RegisterServiceGrpc {
  val METHOD_SIGNUP: _root_.io.grpc.MethodDescriptor[register.RegisterData, register.UserId] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("RegisterService", "Signup"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[register.RegisterData])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[register.UserId])
      .setSchemaDescriptor(_root_.scalapb.grpc.ConcreteProtoMethodDescriptorSupplier.fromMethodDescriptor(register.RegisterProto.javaDescriptor.getServices().get(0).getMethods().get(0)))
      .build()
  
  val SERVICE: _root_.io.grpc.ServiceDescriptor =
    _root_.io.grpc.ServiceDescriptor.newBuilder("RegisterService")
      .setSchemaDescriptor(new _root_.scalapb.grpc.ConcreteProtoFileDescriptorSupplier(register.RegisterProto.javaDescriptor))
      .addMethod(METHOD_SIGNUP)
      .build()
  
  trait RegisterService extends _root_.scalapb.grpc.AbstractService {
    override def serviceCompanion = RegisterService
    def signup(request: register.RegisterData): scala.concurrent.Future[register.UserId]
  }
  
  object RegisterService extends _root_.scalapb.grpc.ServiceCompanion[RegisterService] {
    implicit def serviceCompanion: _root_.scalapb.grpc.ServiceCompanion[RegisterService] = this
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = register.RegisterProto.javaDescriptor.getServices().get(0)
    def scalaDescriptor: _root_.scalapb.descriptors.ServiceDescriptor = register.RegisterProto.scalaDescriptor.services(0)
    def bindService(serviceImpl: RegisterService, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition =
      _root_.io.grpc.ServerServiceDefinition.builder(SERVICE)
      .addMethod(
        METHOD_SIGNUP,
        _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[register.RegisterData, register.UserId] {
          override def invoke(request: register.RegisterData, observer: _root_.io.grpc.stub.StreamObserver[register.UserId]): _root_.scala.Unit =
            serviceImpl.signup(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
              executionContext)
        }))
      .build()
  }
  
  trait RegisterServiceBlockingClient {
    def serviceCompanion = RegisterService
    def signup(request: register.RegisterData): register.UserId
  }
  
  class RegisterServiceBlockingStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[RegisterServiceBlockingStub](channel, options) with RegisterServiceBlockingClient {
    override def signup(request: register.RegisterData): register.UserId = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_SIGNUP, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): RegisterServiceBlockingStub = new RegisterServiceBlockingStub(channel, options)
  }
  
  class RegisterServiceStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[RegisterServiceStub](channel, options) with RegisterService {
    override def signup(request: register.RegisterData): scala.concurrent.Future[register.UserId] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_SIGNUP, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): RegisterServiceStub = new RegisterServiceStub(channel, options)
  }
  
  def bindService(serviceImpl: RegisterService, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition = RegisterService.bindService(serviceImpl, executionContext)
  
  def blockingStub(channel: _root_.io.grpc.Channel): RegisterServiceBlockingStub = new RegisterServiceBlockingStub(channel)
  
  def stub(channel: _root_.io.grpc.Channel): RegisterServiceStub = new RegisterServiceStub(channel)
  
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = register.RegisterProto.javaDescriptor.getServices().get(0)
  
}