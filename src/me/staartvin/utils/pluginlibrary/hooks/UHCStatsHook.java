package me.staartvin.utils.pluginlibrary.hooks;

import com.gmail.mezymc.stats.*;
import me.staartvin.utils.pluginlibrary.Library;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

/**
 * UHCStats,
 * <a href="https://www.spigotmc.org/resources/uhcstats-uhccore-addon.73351/">link</a>.
 * <p>
 *
 * @author Staartvin
 *
 */
public class UHCStatsHook extends LibraryHook {

	private UhcStats uhcStats;

	/*
	 * (non-Javadoc)
	 *
	 * @see me.staartvin.plugins.pluginlibrary.LibraryHook#isAvailable()
	 */
	@Override
	public boolean isAvailable() {
        Plugin plugin = this.getServer().getPluginManager().getPlugin(Library.UHCSTATS
                .getInternalPluginName());

        return plugin != null && plugin.isEnabled();
    }

	/*
	 * (non-Javadoc)
	 *
	 * @see me.staartvin.plugins.pluginlibrary.LibraryHook#hook()
	 */
	@Override
	public boolean hook() {
		if (!isAvailable())
			return false;

        Plugin plugin = this.getServer().getPluginManager()
                .getPlugin(Library.UHCSTATS.getInternalPluginName());

        if (!(plugin instanceof UhcStats))
            return false;

        uhcStats = (UhcStats) plugin;

        return uhcStats.isEnabled();
    }

	private StatsPlayer getStatsPlayer(UUID uuid) {
	    return StatsManager.getStatsManager().getStatsPlayer(uuid.toString(), true, true);
    }

    /**
     * Get the number of kills of a player according to UHCStats
     * @param uuid UUID of the player
     * @return number of kills of the player or -1 if it could not be found.
     */
	public int getNumberOfKills(UUID uuid) {
        if (!this.isAvailable()) return -1;

        StatsPlayer statsPlayer = this.getStatsPlayer(uuid);

        if (statsPlayer == null) {
            return -1;
        }

        return statsPlayer.getGameModeStats(GameMode.DEFAULT).getOrDefault(StatType.KILL, -1);
    }

    /**
     * Get the number of deaths of a player according to UHCStats
     * @param uuid UUID of the player
     * @return number of deaths of the player or -1 if it could not be found.
     */
    public int getNumberOfDeaths(UUID uuid) {
        if (!this.isAvailable()) return -1;

        StatsPlayer statsPlayer = this.getStatsPlayer(uuid);

        if (statsPlayer == null) {
            return -1;
        }

        return statsPlayer.getGameModeStats(GameMode.DEFAULT).getOrDefault(StatType.DEATH, -1);
    }

    /**
     * Get the number of wins of a player according to UHCStats
     * @param uuid UUID of the player
     * @return number of wins of the player or -1 if it could not be found.
     */
    public int getNumberOfWins(UUID uuid) {
        if (!this.isAvailable()) return -1;

        StatsPlayer statsPlayer = this.getStatsPlayer(uuid);

        if (statsPlayer == null) {
            return -1;
        }

        return statsPlayer.getGameModeStats(GameMode.DEFAULT).getOrDefault(StatType.WIN, -1);
    }


}