package tree.hudcreator

import org.bukkit.plugin.java.JavaPlugin
import tree.hudcreator.commands.ShiftCommand
import tree.hudcreator.listeners.HitEntityListener
import tree.hudcreator.listeners.PackApplier
import tree.hudcreator.ui.BitmapConfig
import tree.hudcreator.ui.JsonUpdater
import tree.hudcreator.utils.Config
import tree.hudcreator.utils.OutputFolder
import tree.hudcreator.utils.UiFolder
import tree.hudcreator.utils.ZipFile
import java.io.File

class HUDCreatorPlugin : JavaPlugin() {
    override fun onEnable() {
        Plugin.instance = this
        if (!dataFolder.exists()) {
            dataFolder.mkdir()
        }
        Config.initializeConfig()
        OutputFolder.initializeConfig()
        UiFolder.initializeConfig()
        ZipFile.zip(
            File(Plugin.instance!!.dataFolder.absolutePath + File.separatorChar + "output" + File.separatorChar + "HUDCreator"),
            Plugin.instance!!.dataFolder.absolutePath + File.separatorChar + "output" + File.separatorChar + "HUDCreator.zip"
        )
        PackApplier.startHttpServer()
        getCommand("shift")?.setExecutor(ShiftCommand())
        server.pluginManager.registerEvents(HitEntityListener(), this)
    }
    override fun onDisable() {
        PackApplier.stopHttpServer()
    }
}
