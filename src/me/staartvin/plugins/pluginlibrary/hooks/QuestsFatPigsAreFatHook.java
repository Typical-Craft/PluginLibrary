package me.staartvin.plugins.pluginlibrary.hooks;


import me.blackvein.quests.Quests;
import me.fatpigsarefat.quests.Quests;
import me.fatpigsarefat.quests.utils.QuestData;
import me.staartvin.plugins.pluginlibrary.Library;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.UUID;

/**
 * Quests,
 * <a href="https://www.spigotmc.org/resources/%E2%96%B6-quests-%E2%97%80-set-up-goals-for-players.23696/">link</a>.
 * <p>
 *
 * @author Staartvin
 */
public class QuestsFatPigsAreFatHook extends LibraryHook {

    private Quests quests;

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.plugins.pluginlibrary.LibraryHook#isAvailable()
     */
    @Override
    public boolean isAvailable() {
        // TODO Auto-generated method stub

        return this.getPlugin().getServer().getPluginManager().isPluginEnabled(Library.QUESTS_FATPIGSAREFAT
                .getInternalPluginName());
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

        Plugin plugin = this.getPlugin().getServer().getPluginManager()
                .getPlugin(Library.QUESTS_FATPIGSAREFAT.getInternalPluginName());

        if (!(plugin instanceof Quests))
            return false;

        quests = (Quests) plugin;

        return quests != null && quests instanceof me.fatpigsarefat.quests.Quests;
    }

    /**
     * Get the number of quests a player has completed.
     *
     * @param uuid UUID of the player
     *
     * @return the number of completed quests or -1 if no data was available
     */
    public int getNumberOfCompletedQuests(UUID uuid) {
        if (!this.isAvailable()) return -1;

        QuestData questData = Quests.getInstance().getQuestData();

        return questData.getAmountOfCompletedQuests(uuid);
    }

    /**
     * Get the number of quests a player has currently active
     *
     * @param uuid UUID of the player
     *
     * @return the number of active quests or -1 if no data was available
     */
    public int getNumberOfActiveQuests(UUID uuid) {
        if (!this.isAvailable()) return -1;

        QuestData questData = Quests.getInstance().getQuestData();

        List<String> startedQuests = questData.getStartedQuests(uuid);

        if (startedQuests == null) {
            return 0;
        }

        return startedQuests.size();
    }

    /**
     * Check whether a player has completed a quest.
     *
     * @param uuid      UUID of the player
     * @param questName Name of the quest to check
     *
     * @return true if the player has completed the quest, false otherwise.
     */
    public boolean isQuestCompleted(UUID uuid, String questName) {
        if (!this.isAvailable()) return false;

        QuestData questData = Quests.getInstance().getQuestData();

        return questData.hasCompletedQuestBefore(questName, uuid);
    }


}