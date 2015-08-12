package me.staartvin.plugins.pluginlibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class of PluginLibrary
 * <p>
 * Date created: 14:06:30 12 aug. 2015
 * 
 * @author Staartvin
 * 
 */
public class PluginLibrary extends JavaPlugin {

	private final List<Library> loadedLibraries = new ArrayList<Library>();
	
	@Override
	public void onEnable() {
		
		loadedLibraries.clear();
		
		logMessage(ChatColor.GOLD + "***== Loading libraries ==***");
		logMessage(ChatColor.GOLD + "***== Loaded " + ChatColor.WHITE + loadLibraries() + ChatColor.GOLD + " libraries! ==***");
		
		logMessage(ChatColor.GREEN + "*** Ready for plugins to send/retrieve data. ***");
		
		logMessage(this.getDescription().getFullName() + " is now enabled!");
	}
	
	@Override
	public void onDisable() {
		
		loadedLibraries.clear();
		
		logMessage(this.getDescription().getFullName() + " is now disabled!");
	}
	
	/**
	 * Load all libraries, this will be done automatically by the plugin.
	 * @return how many libraries were loaded.
	 */
	public int loadLibraries() {
		int count = 0;
		
		for (Library l: Library.values()) {
			if (l.getHook().isAvailable()) {
				
				// One more library loaded.
				if (l.getHook().hook()) {
					loadedLibraries.add(l);
					count++;
				}
				
			}
		}
		
		return count;	
	}
	
	public void logMessage(String message) {
		// This makes sure it can support colours.
		this.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "[PluginLibrary] " + message);
	}
	
	/**
	 * Get a list of all loaded libraries.
	 * <br>This list is unmodifiable and when you try to alter it, it will give an {@link UnsupportedOperationException}.
	 * @return a list of loaded libraries.
	 */
	public List<Library> getLoadedLibraries() {
		return Collections.unmodifiableList(loadedLibraries);
	}
	
	public static Library getLibrary(String pluginName) {
		return Library.getEnum(pluginName);
	}
}
