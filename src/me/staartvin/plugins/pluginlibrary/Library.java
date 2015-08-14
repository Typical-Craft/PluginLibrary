package me.staartvin.plugins.pluginlibrary;

import me.staartvin.plugins.pluginlibrary.hooks.AFKTerminatorHook;
import me.staartvin.plugins.pluginlibrary.hooks.AutorankHook;
import me.staartvin.plugins.pluginlibrary.hooks.FactionsHook;
import me.staartvin.plugins.pluginlibrary.hooks.LibraryHook;
import me.staartvin.plugins.pluginlibrary.hooks.McMMOHook;
import me.staartvin.plugins.pluginlibrary.hooks.OnTimeHook;
import me.staartvin.plugins.pluginlibrary.hooks.RoyalCommandsHook;
import me.staartvin.plugins.pluginlibrary.hooks.StatsHook;
import me.staartvin.plugins.pluginlibrary.hooks.UltimateCoreHook;

/**
 * This class holds all libraries PluginLibrary has.
 * <p>
 * Date created: 14:12:35 12 aug. 2015
 * 
 * @author Staartvin
 * 
 */
public enum Library {

	AUTORANK("Autorank", new AutorankHook()),
	STATS("Stats", new StatsHook()),
	MCMMO("mcMMO", new McMMOHook()),
	FACTIONS("Factions", new FactionsHook()),
	ONTIME("OnTime", new OnTimeHook()),
	AFKTERMINATOR("afkTerminator", new AFKTerminatorHook()),
	ROYALCOMMANDS("RoyalCommands", new RoyalCommandsHook()),
	ULTIMATECORE("UltimateCore", new UltimateCoreHook());

	private String pluginName;
	private LibraryHook hook;

	Library(String pluginName, LibraryHook hook) {
		this.pluginName = pluginName;
		this.hook = hook;
	}

	public String getPluginName() {
		return pluginName;
	}

	public LibraryHook getHook() {
		return hook;
	}

	/**
	 * Get a library programmaticaly. This method is the same as valueOf(), but is case-insensitive.
	 * @param value name of the library
	 * @return the Library object or null if not found.
	 */
	public static Library getEnum(String value) {
		for (Library e : Library.values()) {
			if (e.getPluginName().equalsIgnoreCase(value))
				return e;
		}

		throw new IllegalArgumentException("There is no library called '" + value + "'!");
	}
}
