package helloWorld

object HelloWorldGrpc {
  val METHOD_SAY_HELLO: _root_.io.grpc.MethodDescriptor[helloWorld.HelloRequest, helloWorld.HelloResponse] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("HelloWorld", "SayHello"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[helloWorld.HelloRequest])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[helloWorld.HelloResponse])
      .setSchemaDescriptor(_root_.scalapb.grpc.ConcreteProtoMethodDescriptorSupplier.fromMethodDescriptor(helloWorld.HelloWorldProto.javaDescriptor.getServices().get(0).getMethods().get(0)))
      .build()
  
  val SERVICE: _root_.io.grpc.ServiceDescriptor =
    _root_.io.grpc.ServiceDescriptor.newBuilder("HelloWorld")
      .setSchemaDescriptor(new _root_.scalapb.grpc.ConcreteProtoFileDescriptorSupplier(helloWorld.HelloWorldProto.javaDescriptor))
      .addMethod(METHOD_SAY_HELLO)
      .build()
  
  /** The greeting service definition.
    */
  trait HelloWorld extends _root_.scalapb.grpc.AbstractService {
    override def serviceCompanion = HelloWorld
    /** Sends a greeting
      */
    def sayHello(request: helloWorld.HelloRequest): scala.concurrent.Future[helloWorld.HelloResponse]
  }
  
  object HelloWorld extends _root_.scalapb.grpc.ServiceCompanion[HelloWorld] {
    implicit def serviceCompanion: _root_.scalapb.grpc.ServiceCompanion[HelloWorld] = this
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = helloWorld.HelloWorldProto.javaDescriptor.getServices().get(0)
    def scalaDescriptor: _root_.scalapb.descriptors.ServiceDescriptor = helloWorld.HelloWorldProto.scalaDescriptor.services(0)
    def bindService(serviceImpl: HelloWorld, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition =
      _root_.io.grpc.ServerServiceDefinition.builder(SERVICE)
      .addMethod(
        METHOD_SAY_HELLO,
        _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[helloWorld.HelloRequest, helloWorld.HelloResponse] {
          override def invoke(request: helloWorld.HelloRequest, observer: _root_.io.grpc.stub.StreamObserver[helloWorld.HelloResponse]): _root_.scala.Unit =
            serviceImpl.sayHello(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
              executionContext)
        }))
      .build()
  }
  
  /** The greeting service definition.
    */
  trait HelloWorldBlockingClient {
    def serviceCompanion = HelloWorld
    /** Sends a greeting
      */
    def sayHello(request: helloWorld.HelloRequest): helloWorld.HelloResponse
  }
  
  class HelloWorldBlockingStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[HelloWorldBlockingStub](channel, options) with HelloWorldBlockingClient {
    /** Sends a greeting
      */
    override def sayHello(request: helloWorld.HelloRequest): helloWorld.HelloResponse = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_SAY_HELLO, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): HelloWorldBlockingStub = new HelloWorldBlockingStub(channel, options)
  }
  
  class HelloWorldStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[HelloWorldStub](channel, options) with HelloWorld {
    /** Sends a greeting
      */
    override def sayHello(request: helloWorld.HelloRequest): scala.concurrent.Future[helloWorld.HelloResponse] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_SAY_HELLO, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): HelloWorldStub = new HelloWorldStub(channel, options)
  }
  
  def bindService(serviceImpl: HelloWorld, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition = HelloWorld.bindService(serviceImpl, executionContext)
  
  def blockingStub(channel: _root_.io.grpc.Channel): HelloWorldBlockingStub = new HelloWorldBlockingStub(channel)
  
  def stub(channel: _root_.io.grpc.Channel): HelloWorldStub = new HelloWorldStub(channel)
  
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = helloWorld.HelloWorldProto.javaDescriptor.getServices().get(0)
  
}