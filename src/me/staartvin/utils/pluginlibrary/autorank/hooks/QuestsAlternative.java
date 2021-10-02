package me.staartvin.utils.pluginlibrary.autorank.hooks;


import com.leonardobishop.quests.bukkit.BukkitQuestsPlugin;
import com.leonardobishop.quests.common.player.QPlayer;
import com.leonardobishop.quests.common.player.questprogressfile.QuestProgress;
import com.leonardobishop.quests.common.quest.Quest;

import me.staartvin.utils.pluginlibrary.autorank.Library;
import org.bukkit.plugin.Plugin;

import java.util.Map;
import java.util.UUID;

/**
 * Quests,
 * <a href="https://www.spigotmc.org/resources/%E2%96%B6-quests-%E2%97%80-set-up-goals-for-players.23696/">link</a>.
 * <p>
 *
 * @author Staartvin
 */
public class QuestsAlternative extends LibraryHook {

    private BukkitQuestsPlugin questsPlugin;

    @Override
    public boolean isHooked() {
        return questsPlugin != null;
    }

    //

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.plugins.pluginlibrary.LibraryHook#hook()
     */
    @Override
    public boolean hook() {
        if (!isPluginAvailable(Library.QUESTS_ALTERNATIVE))
            return false;

        Plugin plugin = this.getServer().getPluginManager()
                .getPlugin(Library.QUESTS_ALTERNATIVE.getInternalPluginName());

        if (!(plugin instanceof BukkitQuestsPlugin ))
            return false;

        questsPlugin = (BukkitQuestsPlugin) plugin;

        return questsPlugin != null;
    }

    /**
     * Get the number of quests a player has completed.
     *
     * @param uuid UUID of the player
     *
     * @return the number of completed quests or -1 if no data was available
     */
    public int getNumberOfCompletedQuests(UUID uuid) {
        if (!this.isHooked()) return -1;

        Map<String, Quest> quests = questsPlugin.getQuestManager().getQuests();

        int completedQuests = 0;

        for (Map.Entry<String, Quest> questEntry : quests.entrySet()) {
            if (this.isQuestCompleted(uuid, questEntry.getKey())) {
                completedQuests++;
            }
        }

        return completedQuests;
    }

    /**
     * Get the number of quests a player has currently active
     *
     * @param uuid UUID of the player
     *
     * @return the number of active quests or -1 if no data was available
     */
    public int getNumberOfActiveQuests(UUID uuid) {
        if (!this.isHooked()) return -1;

        QPlayer playerData = questsPlugin.getPlayerManager().getPlayer(uuid);

        return playerData.getQuestProgressFile().getStartedQuests().size();
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
        if (!this.isHooked()) return false;

        Quest questData = questsPlugin.getQuestManager().getQuestById(questName);
        QPlayer playerData = questsPlugin.getPlayerManager().getPlayer(uuid);

        QuestProgress progress = playerData.getQuestProgressFile().getQuestProgress(questData);

        // No progress tracked of this quest (for this player), so it cannot have completed the quest.
        if (progress == null) {
            return false;
        }

        return progress.isCompletedBefore();
    }


}