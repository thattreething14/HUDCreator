package tree.hudcreator.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import tree.hudcreator.ui.UIBuilder
import tree.hudcreator.ui.UIRenderer
import tree.hudcreator.utils.CharacterShift

class ShiftCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) {
            sender.sendMessage("Only players can use this command!")
            return false
        }
        if (args == null || args.isEmpty()) {
            sender.sendMessage("Usage: /shift <string> <amount>")
            return false
        }
        val amount = args[0].toIntOrNull()
        val bind = args[1].toIntOrNull()
        if (amount == null || bind == null) {
            sender.sendMessage("Invalid amount!")
            return false
        }
        val uiBuilder = UIBuilder(10, '\uE002')
        val uiString = uiBuilder.build()
        // Render UI
        UIRenderer.startRendering(sender, uiString, amount, bind)
        return true
    }
}
