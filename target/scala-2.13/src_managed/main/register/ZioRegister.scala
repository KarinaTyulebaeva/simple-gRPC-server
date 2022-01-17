package register

import scala.language.implicitConversions

object ZioRegister {
  trait ZRegisterService[-R, -Context] extends scalapb.zio_grpc.ZGeneratedService[R, Context, ZRegisterService] {
    self =>
    def signup(request: register.RegisterData): _root_.zio.ZIO[R with Context, io.grpc.Status, register.UserId]
  }
  type RegisterService = ZRegisterService[Any, Any]
  type RRegisterService[R] = ZRegisterService[R, Any]
  type RCRegisterService[R] = ZRegisterService[R, zio.Has[scalapb.zio_grpc.RequestContext]]
  
  object ZRegisterService {
    implicit val transformableService: scalapb.zio_grpc.TransformableService[ZRegisterService] = new scalapb.zio_grpc.TransformableService[ZRegisterService] {
      def transform[R, Context, R1, Context1](self: ZRegisterService[R, Context], f: scalapb.zio_grpc.ZTransform[R with Context, io.grpc.Status, R1 with Context1]): register.ZioRegister.ZRegisterService[R1, Context1] = new register.ZioRegister.ZRegisterService[R1, Context1] {
        def signup(request: register.RegisterData): _root_.zio.ZIO[R1 with Context1, io.grpc.Status, register.UserId] = f.effect(self.signup(request))
      }
    }
    implicit def ops[R, C](service: register.ZioRegister.ZRegisterService[R, C]): scalapb.zio_grpc.TransformableService.TransformableServiceOps[register.ZioRegister.ZRegisterService, R, C] = new scalapb.zio_grpc.TransformableService.TransformableServiceOps[register.ZioRegister.ZRegisterService, R, C](service)
    implicit val genericBindable: scalapb.zio_grpc.GenericBindable[register.ZioRegister.ZRegisterService] = new scalapb.zio_grpc.GenericBindable[register.ZioRegister.ZRegisterService] {
      def bind[R, C](serviceImpl: register.ZioRegister.ZRegisterService[R, C], env: zio.Has[scalapb.zio_grpc.RequestContext] => R with C): zio.URIO[R, _root_.io.grpc.ServerServiceDefinition] =
        zio.ZIO.runtime[Any].map {
          runtime =>
            _root_.io.grpc.ServerServiceDefinition.builder(register.RegisterServiceGrpc.SERVICE)
            .addMethod(
              register.RegisterServiceGrpc.METHOD_SIGNUP,
              _root_.scalapb.zio_grpc.server.ZServerCallHandler.unaryCallHandler(runtime, (t: register.RegisterData)=>serviceImpl.signup(t).provideSome(env))
            )
            .build()
        }
      }
  }
  
  type RegisterServiceClient = _root_.zio.Has[RegisterServiceClient.Service]
  
  // accessor methods
  class RegisterServiceAccessors[Context: zio.Tag](callOptions: zio.IO[io.grpc.Status, io.grpc.CallOptions]) extends scalapb.zio_grpc.CallOptionsMethods[RegisterServiceAccessors[Context]] {
    def this() = this(zio.ZIO.succeed(io.grpc.CallOptions.DEFAULT))
    def signup(request: register.RegisterData): _root_.zio.ZIO[zio.Has[RegisterServiceClient.ZService[Any, Context]] with Context, io.grpc.Status, register.UserId] = _root_.zio.ZIO.accessM(_.get.withCallOptionsM(callOptions).signup(request))
    def mapCallOptionsM(f: io.grpc.CallOptions => zio.IO[io.grpc.Status, io.grpc.CallOptions]) = new RegisterServiceAccessors(callOptions.flatMap(f))
  }
  
  object RegisterServiceClient extends RegisterServiceAccessors[Any](zio.ZIO.succeed(io.grpc.CallOptions.DEFAULT)) {
    trait ZService[R, Context] extends scalapb.zio_grpc.CallOptionsMethods[ZService[R, Context]] {
      def signup(request: register.RegisterData): _root_.zio.ZIO[R with Context, io.grpc.Status, register.UserId]
      
      // Returns a copy of the service with new default metadata
      def withMetadataM[C](headersEffect: zio.ZIO[C, io.grpc.Status, scalapb.zio_grpc.SafeMetadata]): ZService[R, C]
      def withCallOptionsM(callOptions: zio.IO[io.grpc.Status, io.grpc.CallOptions]): ZService[R, Context]
    }
    type Service = ZService[Any, Any]
    type Accessors[Context] = register.ZioRegister.RegisterServiceAccessors[Context]
    
    
    private[this] class ServiceStub[R, Context](channel: scalapb.zio_grpc.ZChannel[R], options: zio.IO[io.grpc.Status, io.grpc.CallOptions], headers: zio.ZIO[Context, io.grpc.Status, scalapb.zio_grpc.SafeMetadata])
        extends RegisterServiceClient.ZService[R, Context] {
      def signup(request: register.RegisterData): _root_.zio.ZIO[R with Context, io.grpc.Status, register.UserId] = headers.zip(options).flatMap { case (headers, options) => scalapb.zio_grpc.client.ClientCalls.unaryCall(
        channel, register.RegisterServiceGrpc.METHOD_SIGNUP, options,
        headers,
        request
      )}
      def mapCallOptionsM(f: io.grpc.CallOptions => zio.IO[io.grpc.Status, io.grpc.CallOptions]): ZService[R, Context] = new ServiceStub(channel, options.flatMap(f), headers)
      override def withMetadataM[C](headersEffect: zio.ZIO[C, io.grpc.Status, scalapb.zio_grpc.SafeMetadata]): ZService[R, C] = new ServiceStub(channel, options, headersEffect)
      def withCallOptionsM(callOptions: zio.IO[io.grpc.Status, io.grpc.CallOptions]): ZService[R, Context] = new ServiceStub(channel, callOptions, headers)
    }
    
    def managed[R, Context](managedChannel: scalapb.zio_grpc.ZManagedChannel[R], options: zio.IO[io.grpc.Status, io.grpc.CallOptions] = zio.ZIO.succeed(io.grpc.CallOptions.DEFAULT), headers: zio.ZIO[Context, io.grpc.Status, scalapb.zio_grpc.SafeMetadata]=scalapb.zio_grpc.SafeMetadata.make): zio.Managed[Throwable, RegisterServiceClient.ZService[R, Context]] = managedChannel.map {
      channel => new ServiceStub[R, Context](channel, options, headers)
    }
    
    def live[R, Context: zio.Tag](managedChannel: scalapb.zio_grpc.ZManagedChannel[R], options: zio.IO[io.grpc.Status, io.grpc.CallOptions]=zio.ZIO.succeed(io.grpc.CallOptions.DEFAULT), headers: zio.ZIO[Context, io.grpc.Status, scalapb.zio_grpc.SafeMetadata] = scalapb.zio_grpc.SafeMetadata.make): zio.ZLayer[R, Throwable, zio.Has[RegisterServiceClient.ZService[Any, Context]]] = zio.ZLayer.fromFunctionManaged((r: R) => managed[Any, Context](managedChannel.map(_.provide(r)), options, headers))
  }
}