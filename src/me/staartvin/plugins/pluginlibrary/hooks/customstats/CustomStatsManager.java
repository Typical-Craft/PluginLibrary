package me.staartvin.plugins.pluginlibrary.hooks.customstats;

import me.staartvin.plugins.pluginlibrary.Library;
import me.staartvin.plugins.pluginlibrary.PluginLibrary;
import me.staartvin.plugins.pluginlibrary.hooks.StatsHook;
import me.staartvin.plugins.pluginlibrary.listeners.PlayerEatsFoodListener;
import me.staartvin.plugins.pluginlibrary.listeners.PlayerKillsMobListener;

/**
 * Manages all custom stats that Autorank creates with Stats.
 * <p>
 * Date created: 15:00:27 15 jul. 2015
 * 
 * @author Staartvin
 * 
 */
public class CustomStatsManager {

	private final PluginLibrary plugin;

	public CustomStatsManager(PluginLibrary instance) {
		this.plugin = instance;
	}

	public void registerCustomStats() {
		
		// Stats is not available
		if (!plugin.isLibraryLoaded(Library.STATS))
			return;
		
		StatsHook hook = (StatsHook) PluginLibrary.getLibrary(Library.STATS);

		hook.addStat(new MobKilledStat());
		/*plugin.debugMessage("Registered '" + MobKilledStat.statName
				+ "' to Stats.");*/

		hook.addStat(new FoodEatenStat());
		/*plugin.debugMessage("Registered '" + FoodEatenStat.statName
				+ "' to Stats.");*/

		// Register listeners
		plugin.getServer().getPluginManager()
				.registerEvents(new PlayerEatsFoodListener(plugin), plugin);
		plugin.getServer().getPluginManager()
				.registerEvents(new PlayerKillsMobListener(plugin), plugin);
	}
}
