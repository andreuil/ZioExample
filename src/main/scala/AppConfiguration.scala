import zio.{Has, Layer}
import zio.config.magnolia.descriptor
import zio.config._

import java.io.File

case class AppConfiguration(database: String)

object AppConfiguration {
  val live = {
    val currentPath = getClass.getResource("").getPath
    val appConfigurationPath = currentPath + File.separatorChar + "AppConfiguration.json"
    println(s"Configuration path: $appConfigurationPath")
    val appConfigurationDescriptor = descriptor[AppConfiguration]
    val appConfiguration = ZConfig.fromPropertiesFile(appConfigurationPath, appConfigurationDescriptor)
    appConfiguration
  }
}