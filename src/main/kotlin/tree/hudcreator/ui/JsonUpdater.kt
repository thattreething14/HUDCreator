package tree.hudcreator.ui
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import tree.hudcreator.Plugin
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.StandardCopyOption

object JsonUpdater {
    private val gson: Gson = GsonBuilder().setPrettyPrinting().create()
    private val usedChars = mutableSetOf<Char>()

    fun addBitmapEntry(config: BitmapConfig) {
        if (!isUniqueChar(config.unicode)) {
            Plugin.instance!!.logger.warning("The character ${String.format("\\\\u%04X", config.unicode.code)} is already used.")
            return
        }

        val resourceFile = File(Plugin.instance!!.dataFolder, "output/HUDCreator/assets/minecraft/font/default.json")
        val json = gson.fromJson(FileReader(resourceFile), JsonObject::class.java)
        val newEntry = createBitmapEntry(config)

        if (json.has("entries")) {
            json.getAsJsonArray("entries").add(newEntry)
        } else {
            val entriesArray = JsonArray()
            entriesArray.add(newEntry)
            json.add("entries", entriesArray)
        }

        FileWriter(resourceFile).use {
            gson.toJson(json, it)
        }

        // Copy image file
        copyImageFile(config.imageName)

        usedChars.add(config.unicode)
    }


    private fun createBitmapEntry(config: BitmapConfig): JsonObject {
        val charsArray = JsonArray()
        val unicodeString = config.unicode
        charsArray.add(unicodeString)

        // Create the JsonObject and populate it
        return JsonObject().apply {
            addProperty("type", "bitmap")
            addProperty("file", "hudcreator:textures/${config.imageName}.png")
            addProperty("ascent", config.ascent)
            addProperty("height", config.height)
            add("chars", charsArray)
        }
    }
    private fun isUniqueChar(unicode: Char): Boolean {
        return !usedChars.contains(unicode)
    }

    private fun copyImageFile(imageName: String) {
        val sourceFile = File(Plugin.instance!!.dataFolder, "ui/$imageName.png")
        val destFile = File(Plugin.instance!!.dataFolder, "output/HUDCreator/assets/hudcreator/textures/$imageName.png")

        if (!sourceFile.exists()) {
            Plugin.instance!!.logger.warning("Source image file $imageName.png does not exist in ui folder.")
            return
        }

        destFile.parentFile.mkdirs()
        Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
    }
}
