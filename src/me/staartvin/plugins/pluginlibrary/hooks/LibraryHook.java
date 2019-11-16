package me.staartvin.plugins.pluginlibrary.hooks;

import me.staartvin.plugins.pluginlibrary.Library;
import me.staartvin.plugins.pluginlibrary.PluginLibrary;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * Represents a hook to another plugin
 * <p>
 * Date created: 14:13:45 12 aug. 2015
 * 
 * @author Staartvin
 *
 */
public abstract class LibraryHook {

	protected PluginLibrary getPlugin() {
		return (PluginLibrary) Bukkit.getServer().getPluginManager().getPlugin("PluginLibrary");
	}

	/**
	 * Whether or not the plugin is available. Checking whether a plugin is available may require some additional
	 * checks. This method can be used in conjunction with the {@link #isPluginAvailable(Library)} method to check for
	 * availability of a plugin.
	 * 
	 * @return true when plugin is available to use; false otherwise.
	 */
	public abstract boolean isAvailable();

	/**
	 * Check if the given library is available. This means that it exists in the plugins folder and is enabled.
	 * @param library Library to check
	 * @return true if it exists and is started, false otherwise.
	 */
	public static boolean isPluginAvailable(Library library) {
		Plugin plugin =  Bukkit.getServer().getPluginManager().getPlugin(library.getInternalPluginName());

//        System.out.println("Library " + library.getHumanPluginName() + " plugin: " + plugin);
//
//        if (plugin != null) {
//            System.out.println("Plugin enabled: " + plugin.isEnabled());
//        } else {
//            System.out.println("Plugin enabled: false");
//        }

		if (plugin == null) return false;

//        System.out.println("Library has main class: " + library.hasMainClass());
//
//        if (library.hasMainClass()) {
//            System.out.println("(Should be) Library main class: " + library.getMainClass());
//            System.out.println("(Is) Library main class: " + plugin.getDescription().getMain());
//        }

		// Check if plugin has a main class defined.
		// If so, check if the main class is equal to that of the enabled plugin to make sure we have the correct one.
		// Some plugins have the same name, but are of different authors. Checking the main class path makes sure we
		// have the correct one.
		return !library.hasMainClass() || plugin.getDescription().getMain().equalsIgnoreCase(library.getMainClass());
	}

	/**
	 * Hook the plugin to make sure data can be retrieved.
	 * 
	 * @return true if PluginLibrary could successfully hook; false otherwise.
	 */
	public abstract boolean hook();
}
