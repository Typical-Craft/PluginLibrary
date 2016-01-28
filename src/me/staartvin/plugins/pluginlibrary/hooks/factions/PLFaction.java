package me.staartvin.plugins.pluginlibrary.hooks.factions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MFlag;
import com.massivecraft.massivecore.ps.PS;

/**
 * Represents the {@link com.massivecraft.factions.entity.Faction} class. <br>
 * This class mirroring is used so developers don't have to import the API of
 * Factions.
 * <p>
 * Date created: 23:56:42 13 aug. 2015
 * 
 * @author Staartvin
 *
 */
public class PLFaction {
	private final com.massivecraft.factions.entity.Faction faction;

	public PLFaction(com.massivecraft.factions.entity.Faction fac) {
		if (fac == null) {
			throw new NullPointerException("Faction is null!");
		}
		faction = fac;
	}

	/**
	 * Gets the name of the faction. <br>
	 * When using other API calls, always use {@link #getId()} (where
	 * applicable) or {@link #getComparisonName()}. <br>
	 * <b>Only use this method when you want to show the name of the faction to
	 * a player!</b>
	 * 
	 * @return Name of the faction.
	 */
	public String getName() {
		return faction.getName();
	}

	/**
	 * Gets the description of the faction.
	 * 
	 * @return description including colour codes or null if non-existent.
	 */
	public String getDescription() {
		return faction.getDescription();
	}

	/**
	 * Gets the MOTD of the faction.
	 * 
	 * @return String containting the MOTD, null if faction doesn't exist or has
	 *         no MOTD.
	 */
	public String getMotd() {
		return faction.getMotd();
	}

	/**
	 * Gets the time (from UNIX timestamp) when the faction was created.
	 * 
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
	public org.bukkit.Location getHomeLocation() {
		if (faction.getHome() == null)
			return null;

		return faction.getHome().asBukkitLocation();
	}

	/**
	 * Gets the powerboost of the faction.
	 * 
	 * @return the powerboost of the faction or null if 0.
	 */
	public Double getPowerBoost() {
		return faction.getPowerBoost();
	}

	/**
	 * Sets the name of the faction.
	 * 
	 * @param name
	 *            New name for the faction.
	 */
	public void setName(String name) {
		faction.setName(name);
	}

	/**
	 * Sets the description of the faction.
	 * 
	 * @param description
	 *            Description to set.
	 */
	public void setDescription(String description) {
		faction.setDescription(getDescription());
	}

	/**
	 * Sets the MOTD of a faction.
	 * 
	 * @param motd
	 *            MOTD to set to.
	 */
	public void setMotd(String motd) {
		faction.setMotd(motd);
	}

	/**
	 * Sets the time the faction was created at.
	 * 
	 * @param time
	 *            Time (in milliseconds) in UNIX timestamp.
	 */
	public void setCreatedAt(long time) {
		faction.setCreatedAtMillis(time);
	}

	/**
	 * Sets the location of the home of a faction.
	 * 
	 * @param location
	 *            Location to set the home to.
	 */
	public void setHomeLocation(Location location) {
		faction.setHome(PS.valueOf(location));
	}

	/**
	 * Sets the powerboost of the faction.
	 * 
	 * @param powerBoost
	 *            Powerboost to set it to.
	 */
	public void setPowerBoost(Double powerBoost) {
		faction.setPowerBoost(powerBoost);
	}

	/**
	 * Gets the boolean value of the flag specified for this faction.
	 * 
	 * @param flagId
	 *            Name of the flag. You can use: open, monsters, powerloss, pvp,
	 *            friendlyfire, explosions, offlineexplosions, firespread,
	 *            endergrief, permanent, peaceful or infpower.
	 * @return true if flag is true; false if faction does not exist or flag is
	 *         false.
	 * @throws NullPointerException
	 *             If faction does not exist or flagId is invalid.
	 */
	public boolean getFlagValue(String flagId) {
		// throw new NullPointerException("flagId");
		return faction.getFlag(flagId);
	}

	/**
	 * Sets the value of the given flag for a faction.
	 * 
	 * @param flagId
	 *            Name of the flag. For a list of flags, see
	 *            {@link #getFactionFlag(String, String)}.
	 * @param value
	 *            Value to set the flag to.
	 */
	public void setFlagValue(String flagId, boolean value) {
		// throw new NullPointerException("flagId");
		faction.setFlag(flagId, value);
	}

	/**
	 * Gets a list of UUIDs that are invited to this faction.
	 * 
	 * @return a list of UUIDs corresponding to players.
	 */
	public Set<UUID> getInvitedPlayerIds() {
		HashSet<UUID> uuids = new HashSet<UUID>();

		for (String uuid : faction.getInvitedPlayerIds()) {
			uuids.add(UUID.fromString(uuid));
		}

		return uuids;
	}

	/**
	 * Checks whether a player is invited or not.
	 * 
	 * @param uuid
	 *            UUID to check.
	 * @return true if invited; false otherwise.
	 */
	public boolean isInvited(UUID uuid) {
		return this.getInvitedPlayerIds().contains(uuid);
	}

	/**
	 * Gets the current power of the faction.
	 * 
	 * @return power of the faction.
	 */
	public double getPower() {
		return faction.getPower();
	}

	/**
	 * Gets the leader of the faction.
	 * 
	 * @return {@link PLMPlayer} that is the leader of this faction or null if
	 *         not found.
	 */
	public PLMPlayer getLeader() {

		if (faction.getLeader() == null)
			return null;

		return new PLMPlayer(faction.getLeader());
	}

	/**
	 * Gets the chunks the faction has claimed.
	 * 
	 * @return a list of claimed chunks.
	 */
	public List<Chunk> getChunks() {
		Set<PS> chunks = BoardColl.get().getChunks(faction);
		List<Chunk> realChunks = new ArrayList<Chunk>();

		for (PS chunk : chunks) {
			if (chunk == null)
				continue;

			realChunks.add(chunk.asBukkitChunk());
		}

		return realChunks;
	}

	/**
	 * Gets the relation wish of this faction to another faction. <br>
	 * Will return a relationship type. This can be ALLY, TRUCE, NEUTRAL and
	 * ENEMY.
	 * 
	 * @param otherFaction
	 *            Other faction.
	 * @return A relationship type or null if the other faction was not found.
	 */
	public String getRelationWish(PLFaction otherFaction) {
		com.massivecraft.factions.entity.Faction otherFac = FactionColl.get().get(otherFaction.getId());

		if (otherFac == null)
			return null;

		Rel rel = faction.getRelationWish(otherFac);

		if (rel == null)
			return null;

		return rel.toString();
	}

	/**
	 * Sets the relation wish of this faction to another faction. <br>
	 * For a list of relation types, see {@link #getRelationWish(PLFaction)}
	 * <br>
	 * <b>NOTE:</b> You can only try to set the relation as a wish, as the other
	 * faction has to accept it. <br>
	 * There is no setRelation().
	 * 
	 * @param otherFaction
	 *            The other faction to set the relation to.
	 * @param relation
	 *            Relation type.
	 */
	public void setRelationWish(PLFaction otherFaction, String relation) {
		com.massivecraft.factions.entity.Faction otherFac = FactionColl.get().get(otherFaction.getId());

		if (otherFac == null)
			return;

		Rel rel = Rel.valueOf(relation.toUpperCase());

		if (rel == null)
			return;

		faction.setRelationWish(otherFac, rel);
	}

	/**
	 * Gets the relation of this faction to another faction. <br>
	 * Will return a relationship type. This can be ALLY, TRUCE, NEUTRAL and
	 * ENEMY.
	 * 
	 * @param otherFaction
	 *            Other faction.
	 * @return A relationship type or null if the other faction was not found.
	 */
	public String getRelationTo(PLFaction otherFaction) {
		com.massivecraft.factions.entity.Faction otherFac = FactionColl.get().get(otherFaction.getId());

		if (otherFac == null)
			return null;

		Rel rel = faction.getRelationTo(otherFac);

		if (rel == null)
			return null;

		return rel.toString();
	}

	/**
	 * Gets a list of online players that are member of this faction.
	 * 
	 * @return a list containing all online-member players.
	 */
	public List<Player> getOnlinePlayers() {
		return faction.getOnlinePlayers();
	}

	/**
	 * Gets the amount of chunks owned by this faction.
	 * 
	 * @return amount of chunks owned.
	 */
	public int getLandCount() {
		return this.getChunks().size();
	}

	/**
	 * Sends a message to all online players of this faction.
	 * 
	 * @param message
	 *            Message to send.
	 */
	public void sendMessage(String message) {
		faction.sendMessage(message);
	}

	/**
	 * Gets the maximum amount of power this faction may have.
	 * 
	 * @return maximum power amount or null if it doesn't exist. Can also return
	 *         999999 if max power is infinite (due to flag).
	 */
	public Double getPowerMax() {
		return faction.getPowerMax();
	}

	/**
	 * Whether or not this faction is the Wilderness.
	 * 
	 * @return true if the Wilderness; false otherwise.
	 */
	public boolean isWilderness() {
		return faction.isNone();
	}

	/**
	 * Whether or not this faction is the Safezone.
	 * 
	 * @return true if the Safezone; false otherwise.
	 */
	public boolean isSafezone() {
		return !faction.getFlag(MFlag.ID_PVP);
	}

	/**
	 * Whether or not this faction is the Warzone.
	 * 
	 * @return true if the Warzone; false otherwise.
	 */
	public boolean isWarzone() {
		// Faction allows pvp and is not normal
		return faction.getFlag(MFlag.ID_PVP) && !faction.isNormal();
	}

	/**
	 * Gets the id of this faction. <br>
	 * Internally used to reference unique factions.
	 * 
	 * @return id of the faction.
	 */
	public String getId() {
		return faction.getId();
	}

	/**
	 * Gets the 'comparison name' of the faction. <br>
	 * When retrieving the name of the faction via {@link #getName()}, it will
	 * also include colour codes. <br>
	 * This method will remove those colour codes and is internally used by
	 * Factions to reference each seperate Faction.
	 * 
	 * @return Unique name of Faction without colour codes.
	 */
	public String getComparisonName() {
		return faction.getComparisonName();
	}
}