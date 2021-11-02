package io.github.blugon09.mention

import net.kyori.adventure.text.Component
import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.plugin.java.JavaPlugin

class Mention : JavaPlugin(),Listener {

    override fun onEnable() {
        logger.info("Plugin Enable")
        Bukkit.getPluginManager().registerEvents(this, this)
    }

    override fun onDisable() {
        logger.info("Plugin Disable")
    }

    @EventHandler
    fun mention(event : PlayerChatEvent) {
        val player = event.player
        val message = event.message

        if(message[0] == '@') {
            if(Bukkit.getPlayer(message.replace("@", "").replace(" ", "")) != null) {
                val mPlayer = Bukkit.getPlayer(message.replace("@", "").replace(" ", ""))!!
                mPlayer.playSound(mPlayer.location, Sound.ENTITY_ARROW_HIT_PLAYER, 1f, 0.5f)
                event.message = "${ChatColor.BLUE}${event.message.replace(" ", "")}"
                mPlayer.sendActionBar(Component.text("${ChatColor.DARK_GREEN}${player.name}님이 당신을 멘션했습니다!"))
            } else {
                player.sendActionBar(Component.text("${ChatColor.RED}알 수 없는 플레이어 입니다"))
            }
        }
    }
}