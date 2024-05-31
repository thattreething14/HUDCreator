package tree.hudcreator.utils

import org.bukkit.Bukkit


class Logger {
    init {
        throw IllegalStateException("Utility class")
    }
    companion object {
        private val LOGGER = Bukkit.getLogger()
        fun info(message: String) {
            LOGGER.info("[HUDCreator] $message")
        }
        fun warn(message: String) {
            LOGGER.warning("[HUDCreator] $message")
        }
    }
}