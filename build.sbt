name := "scala3"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies += "dev.zio" %% "zio" % "1.0.12"
libraryDependencies += "io.d11" %% "zhttp" % "1.0.0.0-RC17"

PB.targets in Compile := Seq(
  scalapb.gen(grpc = true) -> (sourceManaged in Compile).value,
  scalapb.zio_grpc.ZioCodeGenerator -> (sourceManaged in Compile).value,
)

libraryDependencies ++= Seq(
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
  "io.grpc" % "grpc-netty" % "1.41.0"
)
libraryDependencies += "dev.zio" %% "zio-config" % "1.0.10"
libraryDependencies += "dev.zio" %% "zio-config-magnolia" % "1.0.10"
//libraryDependencies += "dev.zio" %% "zio-config-typesafe" % "1.0.10"