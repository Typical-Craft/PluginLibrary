package me.staartvin.plugins.pluginlibrary.hooks;

import me.blackvein.quests.Quester;
import me.blackvein.quests.Quests;
import me.staartvin.plugins.pluginlibrary.Library;

import java.util.UUID;

/**
 * Quests,
 * <a href="https://www.spigotmc.org/resources/quests.3711/">link</a>.
 * <p>
 *
 * @author Staartvin
 *
 */
public class QuestsHook extends LibraryHook {

	private Quests quests;

	/*
	 * (non-Javadoc)
	 *
	 * @see me.staartvin.plugins.pluginlibrary.LibraryHook#isAvailable()
	 */
	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub

		return this.getPlugin().getServer().getPluginManager().isPluginEnabled(Library.QUESTS.getPluginName());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see me.staartvin.plugins.pluginlibrary.LibraryHook#hook()
	 */
	@Override
	public boolean hook() {
		// TODO Auto-generated method stub

		if (!isAvailable())
			return false;

		quests = (Quests) this.getPlugin().getServer().getPluginManager()
				.getPlugin(Library.QUESTS.getPluginName());

        return quests != null && quests instanceof Quests;
    }

	private Quester getQuester(UUID uuid) {
	    return quests.getQuester(uuid);
    }

    /**
     * Get the number of quests a player has completed.
     * @param uuid UUID of the player
     * @return the number of completed quests or -1 if no data was available
     */
	public int getNumberOfCompletedQuests(UUID uuid) {
        if (!this.isAvailable()) return -1;

        Quester quester = getQuester(uuid);

        if (quester == null) {
            return -1;
        }

        if (quester.completedQuests == null) {
            return -1;
        }

        return quester.completedQuests.size();
    }

    /**
     * Get the number of quests a player has currently active
     * @param uuid UUID of the player
     * @return the number of active quests or -1 if no data was available
     */
    public int getNumberOfActiveQuests(UUID uuid) {
        if (!this.isAvailable()) return -1;

        Quester quester = getQuester(uuid);

        if (quester == null) {
            return -1;
        }

        if (quester.currentQuests == null) {
            return -1;
        }

        return quester.currentQuests.size();
    }

    /**
     * Get the points achieved by completing quests.
     * @param uuid UUID of the player
     * @return the number of questspoints the player has or -1 if no data was available
     */
    public int getQuestsPoints(UUID uuid) {
        if (!this.isAvailable()) return -1;

        Quester quester = getQuester(uuid);

        if (quester == null) {
            return -1;
        }

        if (!quester.hasData()) {
            return -1;
        }

        return quester.getBaseData().getInt("quest-points");
    }

    /**
     * Check whether a player has completed a quest.
     * @param uuid UUID of the player
     * @param questName Name of the quest to check
     * @return true if the player has completed the quest, false otherwise.
     */
    public boolean isQuestCompleted(UUID uuid, String questName) {
        if (!this.isAvailable()) return false;

        Quester quester = getQuester(uuid);

        if (quester == null) {
            return false;
        }

        if (quester.completedQuests == null) {
            return false;
        }

        return quester.completedQuests.contains(questName);
    }


}