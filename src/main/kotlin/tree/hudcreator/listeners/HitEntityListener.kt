package tree.hudcreator.listeners

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerToggleSneakEvent
import org.bukkit.util.Vector
import tree.hudcreator.ui.UIBuilder
import tree.hudcreator.ui.UIRenderer

class HitEntityListener : Listener {
    private val playerUIs = mutableMapOf<Player, UIBuilder>()

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        playerUIs[player] = UIBuilder(10, '\uE002')
    }

    @EventHandler
    fun onDamage(event: EntityDamageByEntityEvent) {
        val damager = event.damager as? Player ?: return
        val uiBuilder = playerUIs.getOrPut(damager) { UIBuilder(10, '\uE002') }

        // Update UI
        var foundEmpty = false
        for (i in 9 downTo 0) { // Iterating in reverse order
            val icon = uiBuilder.build()[i]
            if (icon == '\uE002' && !foundEmpty) {
                uiBuilder.setElement(i, '\uE001', "icon_half")
                foundEmpty = true
            } else if (icon == '\uE001') {
                uiBuilder.setElement(i, '\uE000', "icon_full")
                break
            }
        }

        // Pause old rendering task if exists
        UIRenderer.pauseRendering(damager)

        // Render UI
        UIRenderer.startRendering(damager, uiBuilder.build(), 101, -2)
    }

    @EventHandler
    fun onPlayerToggleSneak(event: PlayerToggleSneakEvent) {
        val player = event.player
        val uiBuilder = playerUIs[player] ?: return

        // Check if the bar is full
        val isBarFull = uiBuilder.build().all { it == '\uE000' }
        if (isBarFull && player.isSneaking) {
            // Sonic boom effect
            val nearbyEntities = player.getNearbyEntities(20.0, 20.0, 20.0)
            for (entity in nearbyEntities) {
                if (entity != player) {
                    val direction = entity.location.toVector().subtract(player.location.toVector()).normalize()
                    entity.velocity = direction.multiply(20)
                }
            }

            // Reset the bar to 0
            for (i in 0 until 10) {
                uiBuilder.setElement(i, '\uE002', "icon_empty")
            }

            // Pause old rendering task if exists
            UIRenderer.pauseRendering(player)

            // Render UI
            UIRenderer.startRendering(player, uiBuilder.build(), 101, -2)
        }
    }
}
