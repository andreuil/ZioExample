package helloWorld

import scala.language.implicitConversions

object ZioHelloWorld {
  trait ZHelloWorld[-R, -Context] extends scalapb.zio_grpc.ZGeneratedService[R, Context, ZHelloWorld] {
    self =>
    def sayHello(request: helloWorld.HelloRequest): _root_.zio.ZIO[R with Context, io.grpc.Status, helloWorld.HelloResponse]
  }
  type HelloWorld = ZHelloWorld[Any, Any]
  type RHelloWorld[R] = ZHelloWorld[R, Any]
  type RCHelloWorld[R] = ZHelloWorld[R, zio.Has[scalapb.zio_grpc.RequestContext]]
  
  object ZHelloWorld {
    implicit val transformableService: scalapb.zio_grpc.TransformableService[ZHelloWorld] = new scalapb.zio_grpc.TransformableService[ZHelloWorld] {
      def transform[R, Context, R1, Context1](self: ZHelloWorld[R, Context], f: scalapb.zio_grpc.ZTransform[R with Context, io.grpc.Status, R1 with Context1]): helloWorld.ZioHelloWorld.ZHelloWorld[R1, Context1] = new helloWorld.ZioHelloWorld.ZHelloWorld[R1, Context1] {
        def sayHello(request: helloWorld.HelloRequest): _root_.zio.ZIO[R1 with Context1, io.grpc.Status, helloWorld.HelloResponse] = f.effect(self.sayHello(request))
      }
    }
    implicit def ops[R, C](service: helloWorld.ZioHelloWorld.ZHelloWorld[R, C]): scalapb.zio_grpc.TransformableService.TransformableServiceOps[helloWorld.ZioHelloWorld.ZHelloWorld, R, C] = new scalapb.zio_grpc.TransformableService.TransformableServiceOps[helloWorld.ZioHelloWorld.ZHelloWorld, R, C](service)
    implicit val genericBindable: scalapb.zio_grpc.GenericBindable[helloWorld.ZioHelloWorld.ZHelloWorld] = new scalapb.zio_grpc.GenericBindable[helloWorld.ZioHelloWorld.ZHelloWorld] {
      def bind[R, C](serviceImpl: helloWorld.ZioHelloWorld.ZHelloWorld[R, C], env: zio.Has[scalapb.zio_grpc.RequestContext] => R with C): zio.URIO[R, _root_.io.grpc.ServerServiceDefinition] =
        zio.ZIO.runtime[Any].map {
          runtime =>
            _root_.io.grpc.ServerServiceDefinition.builder(helloWorld.HelloWorldGrpc.SERVICE)
            .addMethod(
              helloWorld.HelloWorldGrpc.METHOD_SAY_HELLO,
              _root_.scalapb.zio_grpc.server.ZServerCallHandler.unaryCallHandler(runtime, (t: helloWorld.HelloRequest)=>serviceImpl.sayHello(t).provideSome(env))
            )
            .build()
        }
      }
  }
  
  type HelloWorldClient = _root_.zio.Has[HelloWorldClient.Service]
  
  // accessor methods
  class HelloWorldAccessors[Context: zio.Tag](callOptions: zio.IO[io.grpc.Status, io.grpc.CallOptions]) extends scalapb.zio_grpc.CallOptionsMethods[HelloWorldAccessors[Context]] {
    def this() = this(zio.ZIO.succeed(io.grpc.CallOptions.DEFAULT))
    def sayHello(request: helloWorld.HelloRequest): _root_.zio.ZIO[zio.Has[HelloWorldClient.ZService[Any, Context]] with Context, io.grpc.Status, helloWorld.HelloResponse] = _root_.zio.ZIO.accessM(_.get.withCallOptionsM(callOptions).sayHello(request))
    def mapCallOptionsM(f: io.grpc.CallOptions => zio.IO[io.grpc.Status, io.grpc.CallOptions]) = new HelloWorldAccessors(callOptions.flatMap(f))
  }
  
  object HelloWorldClient extends HelloWorldAccessors[Any](zio.ZIO.succeed(io.grpc.CallOptions.DEFAULT)) {
    trait ZService[R, Context] extends scalapb.zio_grpc.CallOptionsMethods[ZService[R, Context]] {
      def sayHello(request: helloWorld.HelloRequest): _root_.zio.ZIO[R with Context, io.grpc.Status, helloWorld.HelloResponse]
      
      // Returns a copy of the service with new default metadata
      def withMetadataM[C](headersEffect: zio.ZIO[C, io.grpc.Status, scalapb.zio_grpc.SafeMetadata]): ZService[R, C]
      def withCallOptionsM(callOptions: zio.IO[io.grpc.Status, io.grpc.CallOptions]): ZService[R, Context]
    }
    type Service = ZService[Any, Any]
    type Accessors[Context] = helloWorld.ZioHelloWorld.HelloWorldAccessors[Context]
    
    
    private[this] class ServiceStub[R, Context](channel: scalapb.zio_grpc.ZChannel[R], options: zio.IO[io.grpc.Status, io.grpc.CallOptions], headers: zio.ZIO[Context, io.grpc.Status, scalapb.zio_grpc.SafeMetadata])
        extends HelloWorldClient.ZService[R, Context] {
      def sayHello(request: helloWorld.HelloRequest): _root_.zio.ZIO[R with Context, io.grpc.Status, helloWorld.HelloResponse] = headers.zip(options).flatMap { case (headers, options) => scalapb.zio_grpc.client.ClientCalls.unaryCall(
        channel, helloWorld.HelloWorldGrpc.METHOD_SAY_HELLO, options,
        headers,
        request
      )}
      def mapCallOptionsM(f: io.grpc.CallOptions => zio.IO[io.grpc.Status, io.grpc.CallOptions]): ZService[R, Context] = new ServiceStub(channel, options.flatMap(f), headers)
      override def withMetadataM[C](headersEffect: zio.ZIO[C, io.grpc.Status, scalapb.zio_grpc.SafeMetadata]): ZService[R, C] = new ServiceStub(channel, options, headersEffect)
      def withCallOptionsM(callOptions: zio.IO[io.grpc.Status, io.grpc.CallOptions]): ZService[R, Context] = new ServiceStub(channel, callOptions, headers)
    }
    
    def managed[R, Context](managedChannel: scalapb.zio_grpc.ZManagedChannel[R], options: zio.IO[io.grpc.Status, io.grpc.CallOptions] = zio.ZIO.succeed(io.grpc.CallOptions.DEFAULT), headers: zio.ZIO[Context, io.grpc.Status, scalapb.zio_grpc.SafeMetadata]=scalapb.zio_grpc.SafeMetadata.make): zio.Managed[Throwable, HelloWorldClient.ZService[R, Context]] = managedChannel.map {
      channel => new ServiceStub[R, Context](channel, options, headers)
    }
    
    def live[R, Context: zio.Tag](managedChannel: scalapb.zio_grpc.ZManagedChannel[R], options: zio.IO[io.grpc.Status, io.grpc.CallOptions]=zio.ZIO.succeed(io.grpc.CallOptions.DEFAULT), headers: zio.ZIO[Context, io.grpc.Status, scalapb.zio_grpc.SafeMetadata] = scalapb.zio_grpc.SafeMetadata.make): zio.ZLayer[R, Throwable, zio.Has[HelloWorldClient.ZService[Any, Context]]] = zio.ZLayer.fromFunctionManaged((r: R) => managed[Any, Context](managedChannel.map(_.provide(r)), options, headers))
  }
}