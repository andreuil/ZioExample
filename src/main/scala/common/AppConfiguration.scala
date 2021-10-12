package common

import zio.ZIO
import zio.config.magnolia.descriptor
import zio.config.typesafe.TypesafeConfig

case class AppConfiguration(database: String)

object AppConfiguration {
  val live = {
    val appConfigurationDescriptor = descriptor[AppConfiguration]

//    ZIO.effect(getClass.getResource("/AppConfiguration.json").getPath)
//      .map(path => TypesafeConfig.fromHoconFile(new java.io.File(path), appConfigurationDescriptor))
//      .toLayer

    val path = getClass.getResource("/AppConfiguration.json").getPath
    TypesafeConfig.fromHoconFile(new java.io.File(path), appConfigurationDescriptor)
  }
}