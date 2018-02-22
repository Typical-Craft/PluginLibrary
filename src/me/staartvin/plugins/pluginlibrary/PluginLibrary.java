package me.staartvin.plugins.pluginlibrary;

import me.staartvin.plugins.pluginlibrary.hooks.LibraryHook;
import me.staartvin.plugins.pluginlibrary.hooks.customstats.CustomStatsManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

/**
 * Main class of PluginLibrary
 * <p>
 * Date created: 14:06:30 12 aug. 2015
 *
 * @author Staartvin
 */
public class PluginLibrary extends JavaPlugin {

    private final static List<Library> loadedLibraries = new ArrayList<Library>();
    public HashMap<UUID, Long> requestTimes = new HashMap<>();
    private CustomStatsManager customStatsManager;

    /**
     * Gets the library for a specific plugin. <br> Will throw a {@link IllegalArgumentException} when there is no
     * library with the given name.
     *
     * @param pluginName Name of the plugin. Case-insensitive!
     * @return {@link me.staartvin.plugins.pluginlibrary.Library} class or an error.
     * @throws IllegalArgumentException When no plugin with the given name was found.
     */
    public static LibraryHook getLibrary(String pluginName) throws IllegalArgumentException {
        return Library.getEnum(pluginName).getHook();
    }

    /**
     * <br>Returns the same as {@link #getLibrary(String)}.
     *
     * @param lib Library enum to get the library hook for.
     * @return {@link me.staartvin.plugins.pluginlibrary.Library} class or an error.
     * @see {@linkplain#getLibrary(String)}
     */
    public static LibraryHook getLibrary(Library lib) {
        return lib.getHook();
    }

    /**
     * Checks to see whether the library is loaded and thus ready for use.
     *
     * @param lib Library to check.
     * @return true if the library is loaded; false otherwise.
     */
    public static boolean isLibraryLoaded(Library lib) {
        return loadedLibraries.contains(lib);
    }

    @Override
    public void onEnable() {

        loadedLibraries.clear();

        logMessage(ChatColor.GOLD + "***== Loading libraries ==***");

        int loadedLibraries = loadLibraries();

        logMessage(ChatColor.GOLD + "***== Loaded " + ChatColor.WHITE + loadedLibraries + ChatColor.GOLD
                + " libraries! ==***");

        if (PluginLibrary.isLibraryLoaded(Library.STATS)) {
            // Register custom stats so that Stats has special mobs and food
            // eaten requirement.
            setCustomStatsManager(new CustomStatsManager(this));
            this.getCustomStatsManager().registerCustomStats();
        }

        if (loadedLibraries > 0) {
            logMessage(ChatColor.GOLD + "Loaded libraries: " + getLoadedLibrariesAsString());
        }

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
     *
     * @return how many libraries were loaded.
     */
    public int loadLibraries() {
        int count = 0;


        for (Library l : Library.values()) {

            LibraryHook hook = l.getHook();

            if (hook.isAvailable()) {
                // One more library loaded.
                if (hook.hook()) {
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
     * Get a list of all loaded libraries. <br> This list is unmodifiable and when you try to alter it, it will give an
     * {@link UnsupportedOperationException}.
     *
     * @return a list of loaded libraries.
     */
    public List<Library> getLoadedLibraries() {
        return Collections.unmodifiableList(loadedLibraries);
    }

    public CustomStatsManager getCustomStatsManager() {
        return customStatsManager;
    }

    public void setCustomStatsManager(CustomStatsManager customStatsManager) {
        this.customStatsManager = customStatsManager;
    }

    private String getLoadedLibrariesAsString() {
        StringBuilder builder = new StringBuilder("");

        for (int i = 0, l = loadedLibraries.size(); i < l; i++) {
            if (i == 0) {
                builder.append(ChatColor.DARK_AQUA + loadedLibraries.get(i).getPluginName() + ChatColor.RESET);
            } else if (i == (l - 1)) {
                builder.append(ChatColor.GRAY + " and "
                        + (ChatColor.DARK_AQUA + loadedLibraries.get(i).getPluginName() + ChatColor.RESET));
            } else {
                builder.append(ChatColor.GRAY + ", "
                        + (ChatColor.DARK_AQUA + loadedLibraries.get(i).getPluginName() + ChatColor.RESET));
            }
        }

        return builder.toString();
    }
}
