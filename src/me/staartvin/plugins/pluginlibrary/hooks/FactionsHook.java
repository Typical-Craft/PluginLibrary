package me.staartvin.plugins.pluginlibrary.hooks;

import java.util.UUID;

import me.staartvin.plugins.pluginlibrary.Library;
import me.staartvin.plugins.pluginlibrary.hooks.factions.Faction;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;

/**
 * Factions, <a
 * href="https://www.spigotmc.org/resources/factions.1900/">link</a>.
 * <p>
 * Date created: 20:22:17 12 aug. 2015
 * 
 * @author Staartvin
 * 
 */
public class FactionsHook extends LibraryHook {

	private Factions factions;

	/* (non-Javadoc)
	 * @see me.staartvin.plugins.pluginlibrary.LibraryHook#isAvailable()
	 */
	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub

		return this.getPlugin().getServer().getPluginManager()
				.isPluginEnabled(Library.FACTIONS.getPluginName());
	}

	/* (non-Javadoc)
	 * @see me.staartvin.plugins.pluginlibrary.LibraryHook#hook()
	 */
	@Override
	public boolean hook() {
		// TODO Auto-generated method stub

		if (!isAvailable())
			return false;

		factions = (Factions) this.getPlugin().getServer().getPluginManager()
				.getPlugin(Library.FACTIONS.getPluginName());

		return factions != null;
	}

	/* Util methods */

	private MPlayer getMPlayer(UUID uuid) {
		return MPlayer.get(uuid);
	}

	/* Faction vars */

	/**
	 * Gets the faction by its name.
	 * @param factionName Name of the faction.
	 * @return {@link Faction} or null if no faction found.
	 */
	public Faction getFactionByName(String factionName) {
		if (factionName == null)
			return null;

		com.massivecraft.factions.entity.Faction fac = FactionColl.get()
				.getByName(factionName);

		if (fac == null)
			return null;

		return new Faction(fac);
	}

	/**
	 * Gets the faction a player is in.
	 * 
	 * @param uuid UUID of the player.
	 * @return {@link Faction}, null if the player doesn't exist or is not in a
	 *         faction.
	 */
	public Faction getFactionByUUID(UUID uuid) {
		if (uuid == null)
			return null;

		MPlayer mPlayer = getMPlayer(uuid);

		if (mPlayer == null)
			return null;

		com.massivecraft.factions.entity.Faction fac = mPlayer.getFaction();

		if (fac == null)
			return null;

		return new Faction(fac);
	}
	
	/**
	 * Gets a faction by its internal Factions id.
	 * @param factionId Id of the faction
	 * @return {@link Faction} or null if id is invalid.
	 */
	public Faction getFactionById(String factionId) {
		if (factionId == null) return null;
		
		if (FactionColl.get().containsId(factionId))
		{
			return new Faction(FactionColl.get().get(factionId));
		}
		
		return null;
	}
}