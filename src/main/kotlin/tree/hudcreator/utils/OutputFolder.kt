package tree.hudcreator.utils


import tree.hudcreator.Plugin
import java.io.File

object OutputFolder {
    fun initializeConfig() {
        DirectoryHelper.directoryCreator("output")
        DirectoryHelper.directoryCreator("output" + File.separatorChar + "HUDCreator")
        DirectoryHelper.directoryCreator("output" + File.separatorChar + "HUDCreator" + File.separatorChar + "assets")
        DirectoryHelper.directoryCreator("output" + File.separatorChar + "HUDCreator" + File.separatorChar + "assets" + File.separatorChar + "hudcreator")
        DirectoryHelper.directoryCreator("output" + File.separatorChar + "HUDCreator" + File.separatorChar + "assets" + File.separatorChar + "hudcreator" + File.separatorChar + "textures")
        DirectoryHelper.directoryCreator("output" + File.separatorChar + "HUDCreator" + File.separatorChar + "assets" + File.separatorChar + "hudcreator" + File.separatorChar + "textures" + File.separatorChar + "custom")
        DirectoryHelper.directoryCreator("output" + File.separatorChar + "HUDCreator" + File.separatorChar + "assets" + File.separatorChar + "minecraft")
        DirectoryHelper.directoryCreator("output" + File.separatorChar + "HUDCreator" + File.separatorChar + "assets" + File.separatorChar + "minecraft" + File.separatorChar + "font")
        DirectoryHelper.directoryCreator("output" + File.separatorChar + "HUDCreator" + File.separatorChar + "assets" + File.separatorChar + "space")
        DirectoryHelper.directoryCreator("output" + File.separatorChar + "HUDCreator" + File.separatorChar + "assets" + File.separatorChar + "space" + File.separatorChar + "font")
        DirectoryHelper.directoryCreator("output" + File.separatorChar + "HUDCreator" + File.separatorChar + "assets" + File.separatorChar + "space" + File.separatorChar + "lang")
        DirectoryHelper.directoryCreator("output" + File.separatorChar + "HUDCreator" + File.separatorChar + "assets" + File.separatorChar + "space" + File.separatorChar + "textures")
        DirectoryHelper.directoryCreator("output" + File.separatorChar + "HUDCreator" + File.separatorChar + "assets" + File.separatorChar + "space" + File.separatorChar + "textures" + File.separatorChar + "font")
        Plugin.instance!!.saveResource(
            "output" + File.separatorChar + "HUDCreator" + File.separatorChar + "pack.mcmeta",
            true
        )
        Plugin.instance!!.saveResource(
            "output" + File.separatorChar + "HUDCreator" + File.separatorChar + "pack.png",
            true
        )
        Plugin.instance!!.saveResource(
            "output" + File.separatorChar + "HUDCreator" + File.separatorChar + "assets" + File.separatorChar + "space" + File.separatorChar + "textures" + File.separatorChar + "font" + File.separatorChar + "space_nosplit.png",
            true
        )
        Plugin.instance!!.saveResource(
            "output" + File.separatorChar + "HUDCreator" + File.separatorChar + "assets" + File.separatorChar + "space" + File.separatorChar + "textures" + File.separatorChar + "font" + File.separatorChar + "space_split.png",
            true
        )
        Plugin.instance!!.saveResource(
            "output" + File.separatorChar + "HUDCreator" + File.separatorChar + "assets" + File.separatorChar + "space" + File.separatorChar + "font" + File.separatorChar + "default.json",
            true
        )
        Plugin.instance!!.saveResource(
            "output" + File.separatorChar + "HUDCreator" + File.separatorChar + "assets" + File.separatorChar + "space" + File.separatorChar + "lang" + File.separatorChar + "en_us.json",
            true
        )
        Plugin.instance!!.saveResource(
            "output" + File.separatorChar + "HUDCreator" + File.separatorChar + "assets" + File.separatorChar + "minecraft" + File.separatorChar + "font" + File.separatorChar + "default.json",
            true
        )
    }
}