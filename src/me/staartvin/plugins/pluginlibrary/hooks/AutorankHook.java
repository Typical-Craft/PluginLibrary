package me.staartvin.plugins.pluginlibrary.hooks;

import java.util.List;
import java.util.UUID;

import me.armar.plugins.autorank.Autorank;
import me.armar.plugins.autorank.playerchecker.requirement.Requirement;
import me.armar.plugins.autorank.playerchecker.result.Result;
import me.staartvin.plugins.pluginlibrary.Library;

import org.bukkit.entity.Player;

/**
 * Autorank library, <a href="https://www.spigotmc.org/resources/autorank.3239/">link</a>.
 * <p>
 * Date created:  14:21:44
 * 12 aug. 2015
 * @author Staartvin
 *
 */
public class AutorankHook extends LibraryHook {

	private Autorank autorank;
	
	/* (non-Javadoc)
	 * @see me.staartvin.plugins.pluginlibrary.LibraryHook#isAvailable()
	 */
	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		
		return this.getPlugin().getServer().getPluginManager().isPluginEnabled(Library.AUTORANK.getPluginName());
	}

	/* (non-Javadoc)
	 * @see me.staartvin.plugins.pluginlibrary.LibraryHook#hook()
	 */
	@Override
	public boolean hook() {
		// TODO Auto-generated method stub
		
		if (!isAvailable()) return false;
		
		autorank = (Autorank) this.getPlugin().getServer().getPluginManager().getPlugin(Library.AUTORANK.getPluginName());
		
		return autorank != null;
	}
	
	/**
	 * Gets playtime of a player in minutes on this local server.
	 * @param uuid UUID of the player
	 * @return amount of minutes a player has played.
	 */
	public int getLocalPlayTime(UUID uuid) {
		return autorank.getAPI().getLocalPlayTime(uuid);
	}
	
	/**
	 * When Autorank is set up on multiple servers in the same network, it will sum a player's time across all servers.
	 * <br>If a player plays on server A for 10 minutes and on server B for 15 minutes, the global time will be 25 minutes.
	 * <br>This method allows you to get the global time of a player in minutes.
	 * @param uuid UUID of the player
	 * @return amount of minutes a player has played across all servers in the network.
	 */
	public int getGlobalPlayTime(UUID uuid) {
		return autorank.getAPI().getGlobalPlayTime(uuid);
	}
	
	/**
	 * When a player is in a permission group that has requirements before the player can rank up,
	 * <br>this method will show all the requirements that the player should complete before he ranks up.
	 * <br>This method does not take into account which requirements are already completed by the player.
	 * <br>If you only want the requirements that have yet to be completed by the player,
	 * <br>use {@link #getFailedRequirements(Player)}.
	 * <br>This method merely copies the config of Autorank and is used for getting all the requirements of a player's permission group.
	 * @param player Player to get the requirements for.
	 * @return A list of all requirements 
	 */
	public List<Requirement> getAllRequirements(Player player) {
		return autorank.getAPI().getAllRequirements(player);
	}
	
	/**
	 * See {@link #getAllRequirements(Player)} for more info.
	 * <br>This method only returns the requirements that have yet to be completed by the player.
	 * @param player Player to get the requirements for.
	 * @return A list of all requirements that should still be completed.
	 */
	public List<Requirement> getFailedRequirements(Player player) {
		return autorank.getAPI().getFailedRequirements(player);
	}
	
	/**
	 * Gets the name of the database that Autorank uses to store its global times.
	 * @return name of database or null if MySQL is not used by Autorank.
	 */
	public String getMySQLDatabase() {
		return autorank.getAPI().getMySQLDatabase();
	}
	
	/**
	 * Gets a list of all permission groups a player is currently in.
	 * <br>Most permission plugins only allow a player to be in one group at the time, but some allow multiple.
	 * @param player Player to get the groups of.
	 * @return a list of groups a player is part of.
	 */
	public List<String> getPermissionGroups(Player player) {
		return autorank.getAPI().getPermissionGroups(player);
	}
	
	/**
	 * Gets the primary permission group a player is in.
	 * <br>Some permission plugins allow players to be in multiple permission groups at the same time.
	 * <br>Autorank uses some clever ways to determine which of those groups is the 'primary group'.
	 * @param player Player to get the primary group of.
	 * @return the name of the primary permission group or null if nothing was found.
	 */
	public String getPrimaryGroup(Player player) {
		return autorank.getAPI().getPrimaryGroup(player);
	}
	
	/**
	 * Registers a custom requirement to Autorank. 
	 * <br>Users of Autorank can use different requirements already coded by the author,
	 * <br>but it's also possible to add your own requirements.
	 * <br>For more info about custom requirements and results, see:
	 * <br><a href="https://github.com/Armarr/Autorank-2/wiki/Developer's-API#custom-requirements">https://github.com/Armarr/Autorank-2/wiki/Developer's-API#custom-requirements</a>
	 * @param requirementName Name of the requirement.
	 * @param req The custom requirement class for Autorank to use.
	 */
	public void registerRequirement(String requirementName, Class<? extends Requirement> req) {
		autorank.getAPI().registerRequirement(requirementName, req);
	}
	
	/**
	 * @see #registerRequirement(String, Class)
	 * @param resultName The name of the result.
	 * @param res The custom result class for Autorank to use.
	 */
	public void registerResult(String resultName, Class<? extends Result> res) {
		autorank.getAPI().registerResult(resultName, res);
	}

}
