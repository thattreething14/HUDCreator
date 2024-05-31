package tree.hudcreator.ui

import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import tree.hudcreator.Plugin
import tree.hudcreator.utils.CharacterShift


object UIRenderer {
    private val renderTasks = mutableMapOf<Player, BukkitRunnable>()
    fun startRendering(player: Player, uiString: String, shiftAmount: Int, bindAmount: Int) {
        if (renderTasks.containsKey(player)) {
            renderTasks[player]?.cancel()
        }

        val task = object : BukkitRunnable() {
            override fun run() {
                val bindedUI = bindUI(uiString, bindAmount)
                val shiftedUI = shiftUI(bindedUI, shiftAmount)
                player.sendActionBar(shiftedUI)
            }
        }

        renderTasks[player] = task
        task.runTaskTimer(Plugin.instance!!, 0L, 1L)
    }
    fun pauseRendering(player: Player) {
        renderTasks[player]?.cancel()
    }

    private fun bindUI(uiString: String, amount: Int): String {
        val result = StringBuilder()
        result.append(uiString[0])

        // Bind for each character besides the first one
        for (i in 1 until uiString.length) {
            val shiftedChar = CharacterShift.shift(uiString[i].toString(), amount)
            result.append(shiftedChar)
        }
        return result.toString()
    }
    private fun shiftUI(uiString: String, amount: Int): String {
        return CharacterShift.shift(uiString, amount)
    }
    fun shiftChar(uiString: String, charToShift: Char, shiftAmount: Int): String {
        val result = StringBuilder(uiString)
        for (i in result.indices) {
            if (result[i] == charToShift) {
                result[i] = CharacterShift.shift(charToShift.toString(), shiftAmount).first()
            }
        }
        return result.toString()
    }

}