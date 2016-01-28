package me.staartvin.plugins.pluginlibrary.hooks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;

import me.staartvin.plugins.pluginlibrary.Library;
import me.staartvin.plugins.pluginlibrary.hooks.factions.PLFaction;
import me.staartvin.plugins.pluginlibrary.hooks.factions.PLMPlayer;

/**
 * Factions,
 * <a href="https://www.spigotmc.org/resources/factions.1900/">link</a>.
 * <p>
 * Date created: 20:22:17 12 aug. 2015
 * 
 * @author Staartvin
 * 
 */
public class FactionsHook extends LibraryHook {

	private Factions factions;

	/*
	 * (non-Javadoc)
	 * 
	 * @see me.staartvin.plugins.pluginlibrary.LibraryHook#isAvailable()
	 */
	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub

		return this.getPlugin().getServer().getPluginManager().isPluginEnabled(Library.FACTIONS.getPluginName());
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
	 * Gets the faction by its name. Use the comparison name, see
	 * {@link me.staartvin.plugins.pluginlibrary.hooks.factions.PLFaction#getComparisonName()}
	 * .
	 * 
	 * @param factionName
	 *            Name of the faction. <b>Without colour codes!</b>
	 * @return {@link PLFaction} or null if no faction found.
	 */
	public PLFaction getFactionByName(String factionName) {
		if (factionName == null)
			return null;

		com.massivecraft.factions.entity.Faction fac = FactionColl.get().getByName(factionName);

		if (fac == null)
			return null;

		return new PLFaction(fac);
	}

	/**
	 * Gets the faction a player is in.
	 * 
	 * @param uuid
	 *            UUID of the player.
	 * @return {@link PLFaction}, null if the player doesn't exist or is not in
	 *         a faction.
	 */
	public PLFaction getFactionByUUID(UUID uuid) {
		if (uuid == null)
			return null;

		MPlayer mPlayer = getMPlayer(uuid);

		if (mPlayer == null)
			return null;

		Faction fac = mPlayer.getFaction();

		if (fac == null)
			return null;

		return new PLFaction(fac);
	}

	/**
	 * Gets a faction by its internal Factions id.
	 * 
	 * @param factionId
	 *            Id of the faction
	 * @return {@link PLFaction} or null if id is invalid.
	 */
	public PLFaction getFactionById(String factionId) {
		if (factionId == null)
			return null;

		if (FactionColl.get().containsId(factionId)) {
			com.massivecraft.factions.entity.Faction fac = FactionColl.get().get(factionId);

			if (fac == null)
				return null;

			return new PLFaction(fac);
		}

		return null;
	}

	/**
	 * Gets a list of all factions that currently exist.
	 * 
	 * @return a list of all factions.
	 */
	public List<PLFaction> getAllFactions() {
		List<PLFaction> factions = new ArrayList<PLFaction>();

		for (com.massivecraft.factions.entity.Faction fac : FactionColl.get().getAll()) {
			factions.add(new PLFaction(fac));
		}

		return factions;
	}

	/**
	 * Gets the Wilderness 'faction'.
	 * 
	 * @return the Wilderness faction.
	 */
	public PLFaction getWilderness() {
		com.massivecraft.factions.entity.Faction fac = FactionColl.get().getNone();

		if (fac == null)
			return null;

		return new PLFaction(fac);
	}

	/**
	 * Gets the Safezone 'faction'.
	 * 
	 * @return the Safezone faction.
	 */
	public PLFaction getSafezone() {

		com.massivecraft.factions.entity.Faction fac = FactionColl.get().getSafezone();

		if (fac == null)
			return null;

		return new PLFaction(fac);
	}

	/**
	 * Gets the Warzone 'faction'.
	 * 
	 * @return the Warzone faction.
	 */
	public PLFaction getWarzone() {
		com.massivecraft.factions.entity.Faction fac = FactionColl.get().getWarzone();

		if (fac == null)
			return null;

		return new PLFaction(fac);
	}

	/**
	 * Gets the faction at a specific {@link Location}.
	 * 
	 * @param location
	 *            Location for the faction to be at.
	 * @return A {@link PLFaction} or null if the location does not contain a
	 *         faction.
	 */
	public PLFaction getFactionAt(Location location) {
		if (location == null)
			return null;

		com.massivecraft.factions.entity.Faction fac = BoardColl.get().getFactionAt(PS.valueOf(location));

		if (fac == null)
			return null;

		return new PLFaction(fac);
	}

	/**
	 * Gets the
	 * {@link me.staartvin.plugins.pluginlibrary.hooks.factions.PLMPlayer} for a
	 * player, which represents the player object Factions internally uses.
	 * 
	 * @param uuid
	 *            UUID of the player.
	 * @return {@link me.staartvin.plugins.pluginlibrary.hooks.factions.PLMPlayer}
	 *         or null if player does not exist/is not stored by Factions.
	 */
	public me.staartvin.plugins.pluginlibrary.hooks.factions.PLMPlayer getFactionsPlayer(UUID uuid) {
		MPlayer mPlayer = MPlayer.get(uuid);

		if (mPlayer == null)
			return null;

		return new me.staartvin.plugins.pluginlibrary.hooks.factions.PLMPlayer(mPlayer);
	}
}