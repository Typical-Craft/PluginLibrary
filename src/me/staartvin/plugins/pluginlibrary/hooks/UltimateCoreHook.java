package me.staartvin.plugins.pluginlibrary.hooks;

import bammerbom.ultimatecore.bukkit.UltimateCore;
import bammerbom.ultimatecore.bukkit.api.UC;
import bammerbom.ultimatecore.bukkit.api.UPlayer;
import me.staartvin.plugins.pluginlibrary.Library;

import java.util.UUID;

/**
 * UltimateCore library,
 * <a href="http://dev.bukkit.org/bukkit-plugins/ultimatecore/">link</a>.
 * <p>
 * Date created: 17:30:19 14 aug. 2015
 * 
 * @author Staartvin
 *
 */
public class UltimateCoreHook extends LibraryHook {

	private UltimateCore api;

	/*
	 * (non-Javadoc)
	 * 
	 * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#isAvailable()
	 */
	@Override
	public boolean isAvailable() {
		return this.getPlugin().getServer().getPluginManager().isPluginEnabled(Library.ULTIMATECORE.getPluginName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#hook()
	 */
	@Override
	public boolean hook() {
		if (!isAvailable())
			return false;

		api = (UltimateCore) this.getPlugin().getServer().getPluginManager()
				.getPlugin(Library.ULTIMATECORE.getPluginName());

		return api != null;
	}

    /**
     * Check whether a player is AFK.
     * @param uuid UUID of the player to check.
     * @return true if the player is AFK, false otherwise.
     */
	public boolean isAFK(UUID uuid) {

	    if (!isAvailable()) {
	        return false;
        }

        UPlayer player = UC.getPlayer(uuid);

        if (player == null) {
            return false;
        }

        return player.isAfk();
    }
}
