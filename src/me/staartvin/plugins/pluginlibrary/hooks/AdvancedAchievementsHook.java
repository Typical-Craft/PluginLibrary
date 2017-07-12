package me.staartvin.plugins.pluginlibrary.hooks;

import com.hm.achievement.AdvancedAchievements;
import me.staartvin.plugins.pluginlibrary.Library;

import java.util.UUID;

/**
 * AdvancedAchievements library,
 * <a href="https://www.spigotmc.org/resources/advanced-achievements.6239/">link</a>.
 * <p>
 * 
 * @author Staartvin
 * 
 */
public class AdvancedAchievementsHook extends LibraryHook {

    private AdvancedAchievements advancedAchievements;

	/*
	 * (non-Javadoc)
	 * 
	 * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#isAvailable()
	 */
	@Override
	public boolean isAvailable() {
		return this.getPlugin().getServer().getPluginManager().isPluginEnabled(Library.ADVANCEDACHIEVEMENTS.getPluginName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#hook()
	 */
	@Override
	public boolean hook() {

        if (!isAvailable())
            return false;

        advancedAchievements = (AdvancedAchievements) this.getPlugin().getServer().getPluginManager()
                .getPlugin(Library.ADVANCEDACHIEVEMENTS.getPluginName());

        return advancedAchievements != null;
	}


    /**
     * Check whether a player has obtained a given achievement.
     * @param uuid UUID of the player
     * @param achievementName Name of the achievement
     * @return true if the player has obtained the given achievement, false otherwise.
     */
    public boolean hasAchievement(UUID uuid, String achievementName) {
        if (!this.isAvailable()) {
            return false;
        }

        return advancedAchievements.getDatabaseManager().hasPlayerAchievement(uuid, achievementName);
    }

    /**
     * Get the number of achievements a player has obtained.
     * @param uuid UUID of the player
     * @return number of achievements of a player or -1 if no data is available.
     */
    public int getNumberOfAchievements(UUID uuid) {
        if (!this.isAvailable()) {
            return -1;
        }

        return advancedAchievements.getDatabaseManager().getPlayerAchievementsAmount(uuid);
    }

}
