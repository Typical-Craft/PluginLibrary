package me.staartvin.plugins.pluginlibrary.hooks;

import org.bukkit.entity.Player;

import me.clip.ezrankspro.EZRanksPro;
import me.staartvin.plugins.pluginlibrary.Library;

/**
 * EZRanksPro library, <a href="https://www.spigotmc.org/resources/ezrankspro.10731/">link</a>.
 * <p>
 * Date created:  14:21:44
 * 14 aug. 2015
 * @author extended_clip
 *
 */
public class EZRanksProHook extends LibraryHook {

	private EZRanksPro ezranks;
	
	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return this.getPlugin().getServer().getPluginManager().isPluginEnabled(Library.EZRANKSPRO.getPluginName());
	}

	@Override
	public boolean hook() {
		// TODO Auto-generated method stub
		if (!isAvailable()) return false;
		
		ezranks = (EZRanksPro) this.getPlugin().getServer().getPluginManager().getPlugin(Library.EZRANKSPRO.getPluginName());
		
		return ezranks != null;
	}
	
	/**
	 * Check if a player has a rankup available.
	 * @param player {@link Player} to check.
	 * @return true if a player has a rankup, otherwise false.
	 */
	public boolean hasRankup(Player player) {
		return me.clip.ezrankspro.rankdata.Rankup.getRankup(player) != null;
	}
	
	/**
	 * Obtain the cost for the players next rankup.
	 * @param player {@link Player} to obtain the rankup cost for.
	 * @return Cost to rankup if a rankup is available, otherwise 0.0 .
	 */
	public double getRankupCost(Player player) {
		return me.clip.ezrankspro.rankdata.Rankup.getRankup(player) != null ? me.clip.ezrankspro.rankdata.Rankup.getRankup(player).getCost() : 0.0;
	}
	
	/**
	 * Obtain the String prefix for the players current rank.
	 * @param player {@link Player} to obtain the String prefix for.
	 * @return prefix for the players current rank if the player has a rankup, null otherwise.
	 */
	public String getRankPrefix(Player player) {
		return me.clip.ezrankspro.rankdata.Rankup.getRankup(player) != null ? me.clip.ezrankspro.rankdata.Rankup.getRankup(player).getPrefix() : null;
	}
	
	/**
	 * Obtain the formatted cost for the players next rankup.
	 * @param player {@link Player} to obtain the formatted rankup cost for.
	 * @return Formatted cost to rankup if a rankup is available, otherwise null.
	 */
	public String getRankupCostFormatted(Player player) {
		if (hasRankup(player)) {
			return me.clip.ezrankspro.util.EcoUtil.fixMoney(me.clip.ezrankspro.rankdata.Rankup.getRankup(player).getCost());
		}
		return null;
	}
}
