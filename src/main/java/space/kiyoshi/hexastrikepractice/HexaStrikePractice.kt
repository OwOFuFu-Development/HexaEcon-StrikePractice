package space.kiyoshi.hexastrikepractice

import ga.strikepractice.events.DuelEndEvent
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import space.kiyoshi.hexaecon.api.HexaEconAPI


class HexaStrikePractice : JavaPlugin(), Listener {
    private var winnerCoins= config.getLong("WinnerCoins")
    private var coinGrantMessage= config.getString("CoinGrantMessage")
    override fun onEnable() {
        saveDefaultConfig()
        config.options().copyHeader(true)
        config.options().copyDefaults(true)
        server.pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun onFightEnd(event: DuelEndEvent) {
        val p = event.winner
        HexaEconAPI.addMoney(p, winnerCoins)
        p.sendMessage(color(coinGrantMessage?.replace("%coins%", winnerCoins.toString())))
    }

    private fun color(s: String?): String {
        return ChatColor.translateAlternateColorCodes('&', s!!)
    }
}
