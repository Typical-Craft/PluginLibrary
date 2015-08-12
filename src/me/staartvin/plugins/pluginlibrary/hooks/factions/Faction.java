package me.staartvin.plugins.pluginlibrary.hooks.factions;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;

import com.massivecraft.factions.Rel;
import com.massivecraft.massivecore.ps.PS;

public class Faction {
	private final com.massivecraft.factions.entity.Faction faction;
	
	public Faction(com.massivecraft.factions.entity.Faction fac) {
		faction = fac;
	}
	
	/**
	 * Gets the name of the faction.
	 * @return Name of the faction.
	 */
	public String getName() {
		return faction.getName();
	}
	
	/**
	 * Gets the description of the faction.
	 * @return description including colour codes or null if non-existent.
	 */
	public String getDescription() {
		return faction.getDescription();
	}
	
	/**
	 * Gets the MOTD of the faction.
	 * @return String containting the MOTD, null if faction doesn't exist or has no MOTD.
	 */
	public String getMotd() {
		return faction.getMotd();
	}
	
	/**
	 * Gets the time (from UNIX timestamp) when the faction was created.
	 * @return time in milliseconds of creation (UNIX)
	 */
	public long getCreatedAt() {
		return faction.getCreatedAtMillis();
	}
	
	/**
	 * Gets the location of the home of a faction.
	 * 
	 * @return A {@link Location} or null if faction is non-existent or the home
	 *         is not set.
	 */
	public Location getHomeLocation() {
		if (faction.getHome() == null) return null;
		
		return faction.getHome().asBukkitLocation();
	}
	
	/**
	 * Gets the powerboost of the faction.
	 * @return the powerboost of the faction or null if 0.
	 */
	public Double getPowerBoost() {
		return faction.getPowerBoost();
	}
	
	/**
	 * Sets the name of the faction.
	 * 
	 * @param name New name for the faction.
	 */
	public void setName(String name) {
		faction.setName(name);
	}
	
	/**
	 * Sets the description of the faction.
	 * @param description Description to set.
	 */
	public void setDescription(String description) {
		faction.setDescription(getDescription());
	}
	
	/**
	 * Sets the MOTD of a faction.
	 * @param motd MOTD to set to.
	 */
	public void setMotd(String motd) {
		faction.setMotd(motd);
	}
	
	public void setCreatedAt(long time) {
		faction.setCreatedAtMillis(time);
	}
	
	/**
	 * Sets the location of the home of a faction.
	 * 
	 * @param location Location to set the home to.
	 */
	public void setHomeLocation(Location location) {
		faction.setHome(PS.valueOf(location));
	}
	
	/**
	 * Sets the powerboost of the faction.
	 * @param powerBoost Powerboost to set it to.
	 */
	public void setPowerBoost(Double powerBoost) {
		faction.setPowerBoost(powerBoost);
	}
	
	/**
	 * Gets the boolean value of the flag specified for this faction.
	 * 
	 * @param flagId Name of the flag. You can use: open, monsters, powerloss,
	 *            pvp, friendlyfire, explosions, offlineexplosions, firespread,
	 *            endergrief, permanent, peaceful or infpower.
	 * @return true if flag is true; false if faction does not exist or flag is
	 *         false.
	 * @throws NullPointerException If faction does not exist or flagId is
	 *             invalid.
	 */
	public boolean getFlagValue(String flagId) {
		// throw new NullPointerException("flagId");
		return faction.getFlag(flagId);
	}
	
	/**
	 * Sets the value of the given flag for a faction.
	 * 
	 * @param flagId Name of the flag. For a list of flags, see
	 *            {@link #getFactionFlag(String, String)}.
	 * @param value Value to set the flag to.
	 */
	public void setFlagValue(String flagId, boolean value) {
		// throw new NullPointerException("flagId");
		faction.setFlag(flagId, value);
	}
	
	/**
	 * Gets a list of UUIDs that are invited to this faction.
	 * @return a list of UUIDs corresponding to players.
	 */
	public Set<UUID> getInvitedPlayerIds() {
		HashSet<UUID> uuids = new HashSet<UUID>();
		
		for (String uuid: faction.getInvitedPlayerIds()) {
			uuids.add(UUID.fromString(uuid));
		}
		
		return uuids;
	}
	
	/**
	 * Checks whether a player is invited or not.
	 * @param uuid UUID to check.
	 * @return true if invited; false otherwise.
	 */
	public boolean isInvited(UUID uuid) {
		return this.getInvitedPlayerIds().contains(uuid);
	}
	
	// TODO relation ships
	public Map<String, Rel> getRelationWishes()
	{
		return faction.getRelationWishes();
	}
}