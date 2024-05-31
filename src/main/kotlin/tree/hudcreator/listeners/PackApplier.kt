package tree.hudcreator.listeners


import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import tree.hudcreator.Plugin
import tree.hudcreator.utils.Config
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.InetSocketAddress
import java.net.URL
import java.security.MessageDigest
import java.util.*


object PackApplier : Listener {
    private val RESOURCE_PACK_URL = "http://localhost:${Config.port}/Modelib.zip"
    private var httpServer: HttpServer? = null

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        if (!Config.packApplierEnabled) return
        val player = event.player
        sendResourcePack(player, RESOURCE_PACK_URL)
    }

    private fun sendResourcePack(player: Player, resourcePackUrl: String) {
        try {
            val url = URL(resourcePackUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            val inputStream = connection.inputStream
            val data = inputStream.readAllBytes()

            val hash = calculateHash(data)

            player.setResourcePack(resourcePackUrl, hash)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    private fun calculateHash(data: ByteArray): String {
        val hash = MessageDigest.getInstance("SHA-1").digest(data)
        return Base64.getEncoder().encodeToString(hash)
    }

    fun startHttpServer() {
        httpServer = HttpServer.create(InetSocketAddress(Config.port), 0)
        httpServer!!.createContext("/Modelib.zip", ResourcePackHandler())
        httpServer!!.executor = null
        httpServer!!.start()
        println("HTTP resource pack server started on port ${Config.port}.")
    }

    fun stopHttpServer() {
        httpServer?.stop(0)
        httpServer = null
        println("HTTP resource pack server stopped.")
    }

    private class ResourcePackHandler : HttpHandler {
        override fun handle(exchange: HttpExchange) {
            val responseFile = File("${Plugin.instance!!.dataFolder}/output/Modelib.zip")
            if (!responseFile.exists()) {
                println("Resource pack file not found.")
                exchange.sendResponseHeaders(404, -1)
                return
            }

            exchange.sendResponseHeaders(200, responseFile.length())
            val os = exchange.responseBody
            val fis = FileInputStream(responseFile)
            val buffer = ByteArray(1024)
            var length: Int
            while (fis.read(buffer).also { length = it } != -1) {
                os.write(buffer, 0, length)
            }
            fis.close()
            os.close()
        }
    }
}
