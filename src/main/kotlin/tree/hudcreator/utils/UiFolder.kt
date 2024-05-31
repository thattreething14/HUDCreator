package tree.hudcreator.utils

import tree.hudcreator.Plugin
import tree.hudcreator.ui.BitmapConfig
import tree.hudcreator.ui.JsonUpdater
import java.io.File

object UiFolder {
    private var charCounter = 0

    fun initializeConfig() {
        val uiFolder = File(Plugin.instance!!.dataFolder, "ui")
        if (!uiFolder.exists()) {
            uiFolder.mkdirs()
            Plugin.instance!!.logger.info("UI folder created successfully.")
        } else {
            Plugin.instance!!.logger.info("UI folder already exists.")
        }

        val imageFiles = uiFolder.listFiles { _, name -> name.endsWith(".png") }
        if (imageFiles.isNullOrEmpty()) {
            Plugin.instance!!.logger.warning("No image files found in the UI folder.")
            return
        }

        for (imageFile in imageFiles) {
            val imageName = imageFile.nameWithoutExtension
            val unicode = '\uE000' + charCounter
            JsonUpdater.addBitmapEntry(BitmapConfig(imageName, 8, 8, unicode))
            charCounter++
        }
    }
}
