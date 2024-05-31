package tree.hudcreator.utils

import org.bukkit.configuration.file.FileConfiguration
import java.io.File


object Config {
    var packApplierEnabled: Boolean = true
    var port: Int = 6970
    fun initializeConfig() {
        val file: File = DirectoryHelper.fileCreator("config.yml")
        val fileConfiguration: FileConfiguration = DirectoryHelper.fileConfigurationCreator(file)!!
        packApplierEnabled = DirectoryHelper.setBoolean(
            listOf(
                "Sets whether you'd like to automatically send the resource pack in outputs directory to the client."
            ),
            fileConfiguration, "autoPackApplier.enabled", true
        )
        port = DirectoryHelper.setInt(
            listOf(
                "Specifies the port number for the automatic resource pack applier HTTP server."
            ),
            fileConfiguration, "autoPackApplier.port", 6970
        )
        DirectoryHelper.fileSaverOnlyDefaults(fileConfiguration, file)
    }
}