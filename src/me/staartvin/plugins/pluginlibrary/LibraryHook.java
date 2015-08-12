package me.staartvin.plugins.pluginlibrary;

import org.bukkit.Bukkit;

/**
 * Represents a hook to another plugin
 * <p>
 * Date created:  14:13:45
 * 12 aug. 2015
 * @author Staartvin
 *
 */
public abstract class LibraryHook {

	public PluginLibrary getPlugin() {
		return (PluginLibrary) Bukkit.getServer().getPluginManager().getPlugin("PluginLibrary");
	}
	
	/**
	 * Whether or not the plugin is available.
	 * @return true when plugin is available to use; false otherwise.
	 */
	public abstract boolean isAvailable();
	
	/**
	 * Hook the plugin to make sure data can be retrieved.
	 * @return true if PluginLibrary could successfully hook; false otherwise.
	 */
	public abstract boolean hook();
}
