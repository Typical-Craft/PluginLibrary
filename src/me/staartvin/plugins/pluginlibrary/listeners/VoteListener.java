package me.staartvin.plugins.pluginlibrary.listeners;

import com.vexsoftware.votifier.model.VotifierEvent;
import me.staartvin.plugins.pluginlibrary.events.PlayerVotedEvent;
import me.staartvin.statz.datamanager.player.PlayerStat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 * Listen for votes for NuVotifier and Votifier and create our own event to let developers know a vote happened.
 */
public class VoteListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onVote(final VotifierEvent event) {

        final PlayerStat stat = PlayerStat.VOTES;

        String userName = event.getVote().getUsername();

        // Get player
        Player player = Bukkit.getServer().getPlayer(userName);

        // Player is not online, so ignore it.
        if (player == null) {
            // Player is null, so we never show that the player voted.
            return;
        }

        // Create our own event to let developers know that a player has voted.
        PlayerVotedEvent playerVotedEvent = new PlayerVotedEvent(player);

        // Call event!
        Bukkit.getPluginManager().callEvent(playerVotedEvent);
    }
}
