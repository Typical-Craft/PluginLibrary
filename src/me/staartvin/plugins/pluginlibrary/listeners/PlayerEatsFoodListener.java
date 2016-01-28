package me.staartvin.plugins.pluginlibrary.listeners;

import me.staartvin.plugins.pluginlibrary.Library;
import me.staartvin.plugins.pluginlibrary.PluginLibrary;
import me.staartvin.plugins.pluginlibrary.hooks.StatsHook;
import me.staartvin.plugins.pluginlibrary.hooks.customstats.FoodEatenStat;
import me.staartvin.plugins.pluginlibrary.util.Util;

/**
 * This listener will listen to players eating food (for custom stat)
 * 
 * @author Staartvin
 * 
 */
public class PlayerEatsFoodListener implements Listener {

	private final PluginLibrary plugin;
	private final StatsHook hook;

	public PlayerEatsFoodListener(final PluginLibrary instance) {
		plugin = instance;
		hook = (StatsHook) PluginLibrary.getLibrary(Library.STATS);
	}

	@EventHandler
	public void OnEat(final PlayerItemConsumeEvent event) {

		if (event.isCancelled())
			return;

		// Stats is not available
		if (!plugin.isLibraryLoaded(Library.STATS))
			return;

		Player p = event.getPlayer();

		String foodName = Util.getFoodName(event.getItem());

		if (foodName == null)
			return;

		Stat stat = hook.getStat(FoodEatenStat.statName);

		StatsHolder holder = hook.getStatsHolder(p.getUniqueId());

		holder.addEntry(stat, new DefaultStatEntry(1, new MetadataPair(
				"foodType", foodName), new MetadataPair("world", p.getWorld()
				.getName())));

	}

}
