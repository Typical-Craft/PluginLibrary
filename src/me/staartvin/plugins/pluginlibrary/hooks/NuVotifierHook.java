package me.staartvin.plugins.pluginlibrary.hooks;

import com.vexsoftware.votifier.NuVotifierBukkit;
import me.staartvin.plugins.pluginlibrary.Library;
import me.staartvin.plugins.pluginlibrary.listeners.VoteListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

/**
 * NuVotifier library, <a href="https://www.spigotmc.org/resources/nuvotifier.13449/">link</a>.
 * <p>
 *
 * @author Staartvin
 */
public class NuVotifierHook extends LibraryHook {

    private NuVotifierBukkit api;

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#isAvailable()
     */
    @Override
    public boolean isAvailable() {
        return this.getPlugin().getServer().getPluginManager().isPluginEnabled(Library.NUVOTIFIER.getInternalPluginName());
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#hook()
     */
    @Override
    public boolean hook() {

        if (!isAvailable()) {
            return false;
        }

        final Plugin plugin = this.getPlugin().getServer().getPluginManager()
                .getPlugin(Library.NUVOTIFIER.getInternalPluginName());

        try {
            // May not be loaded
            if (plugin == null || !(plugin instanceof NuVotifierBukkit)) {
                return false;
            }
        } catch (NoClassDefFoundError e) {
            // Votifier was not found, maybe try NuVotifier
            return false;
        }

        api = (NuVotifierBukkit) plugin;

        // Set up listener for listening to players voting.
        setupVoteListener();

        return true;
    }

    private boolean setupVoteListener() {
        Bukkit.getPluginManager().registerEvents(new VoteListener(),
                Objects.requireNonNull(Bukkit.getPluginManager().getPlugin(
                "PluginLibrary")));

        return true;
    }


}
