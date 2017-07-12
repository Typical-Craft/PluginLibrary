package me.staartvin.plugins.pluginlibrary;

import me.staartvin.plugins.pluginlibrary.hooks.*;

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
	ULTIMATECORE("UltimateCore", new UltimateCoreHook()),
	STATZ("Statz", new StatzHook());

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
	 * Get a library programmaticaly. This method is the same as valueOf(), but
	 * is case-insensitive.
	 * 
	 * @param value
	 *            name of the library
	 * @return the Library objec.
	 * @throws IllegalArgumentException When no library with the given name was found.
	 */
	public static Library getEnum(String value) throws IllegalArgumentException {
		for (Library e : Library.values()) {
			if (e.getPluginName().equalsIgnoreCase(value))
				return e;
		}

		throw new IllegalArgumentException("There is no library called '" + value + "'!");
	}
}
