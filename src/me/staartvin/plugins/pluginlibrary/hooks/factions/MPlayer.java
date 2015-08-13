package me.staartvin.plugins.pluginlibrary.hooks.factions;

import java.util.UUID;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.FactionColl;

/**
 * Represents the {@link com.massivecraft.factions.entity.MPlayer} class.
 * <br>This class mirroring is used so developers don't have to import the API of Factions.
 * <br>This can either be a console or a real player. You can check with {@link #isConsole()} and {@link #isPlayer()}.
 * <p>
 * Date created:  23:56:36
 * 13 aug. 2015
 * @author Staartvin
 *
 */
public class MPlayer {

	private final com.massivecraft.factions.entity.MPlayer mPlayer;
	
	public MPlayer(com.massivecraft.factions.entity.MPlayer mPlayer) {
		if (mPlayer == null) {
			throw new NullPointerException("MPlayer is null!");
		}
		
		this.mPlayer = mPlayer;
	}
	
	/**
	 * Gets the faction id of the faction that the player is part of.
	 * <br><b>This will never be null.</b> If a player does not belong to a 'human-made' faction,
	 * <br>it will return the id of the wilderness, warzone or safezone.
	 * @return A faction id corresponding to the faction the player is currently in.
	 */
	public String getFactionId() {
		return mPlayer.getFactionId();
	}
	
	/**
	 * Gets the faction the player is part of.
	 * <br><b>This will never be null.</b> See {@link #getFactionId()} for more info.
	 * @return The faction a player is part of.
	 */
	public Faction getFaction() {		
		return new Faction(mPlayer.getFaction());
	}
	
	/**
	 * Gets the role of the player in his current faction.
	 * <br><b>This will never be null</b> as the player is always in a faction. See {@link #getFactionId()} for more info.
	 * <br>Roles can be LEADER, OFFICER, MEMBER and RECRUIT.
	 * @return The player's role in the current faction.
	 */
	public String getRole() {
		Rel rel = mPlayer.getRole();
		
		return rel.toString();
	}
	
	/**
	 * Sets the role of a player in his current faction.
	 * <br>See {@link #getRole()} for more info about the roles.
	 * @param role Role type.
	 */
	public void setRole(String role) {
		Rel rel = Rel.valueOf(role);
		
		if (rel == null) return;
		
		mPlayer.setRole(rel);
	}
	
	/**
	 * Gets the title of the player in his current faction.
	 * <br>The title is just for fun. It's not connected to any game mechanic.
	 * @return the title of the player.
	 */
	public String getTitle() {
		return mPlayer.getTitle();
	}
	
	/**
	 * Sets the title of the player in his current faction.
	 * <br>The title is just for fun. It's not connected to any game mechanic.
	 * @param title String to change the title to.
	 */
	public void setTitle(String title) {
		mPlayer.setTitle(title);
	}
	
	/**
	 * Gets the power boost of this player.
	 * @return Power boost value.
	 */
	public double getPowerBoost() {
		return mPlayer.getPowerBoost();
	}
	
	/**
	 * Sets the power boost of this player.
	 * @param boost Boost to set it to.
	 */
	public void setPowerBoost(double boost) {
		mPlayer.setPower(boost);
	}
	
	/**
	 * Gets the maximum power this player can have.
	 * @return Maximum power.
	 */
	public double getPowerMax() {
		return mPlayer.getPowerMax();
	}

	/**
	 * Gets the minimum power this player can have.
	 * @return Minimum power.
	 */
	public double getPowerMin() {
		return mPlayer.getPowerMin();
	}
	
	/**
	 * Gets the amount of power this player gets per hour.
	 * @return Amount of power gained per hour.
	 */
	public double getPowerPerHour() {
		return mPlayer.getPowerMax();
	}

	/**
	 * Gets the amount of power this player loses when he dies.
	 * @return Amount of power lost on death.
	 */
	public double getPowerPerDeath() {
		return mPlayer.getPowerMin();
	}
	
	/**
	 * Gets the power this player currently has.
	 * @return Current power of player.
	 */
	public double getPower() {
		return mPlayer.getPower();
	}
	
	/**
	 * Sets the power this player currently has.
	 * @param power Amount to change it to.
	 */
	public void setPower(double power) {
		mPlayer.setPower(power);
	}
	
	/**
	 * Whether this player is using the admin mode of Factions.
	 * @return True if using admin mode; false otherwise.
	 */
	public boolean isUsingAdminMode() {
		return mPlayer.isUsingAdminMode();
	}
	
	/**
	 * Sets whether the player is using admin mode.
	 * @param value Value to set the use of admin mode to.
	 */
	public void setUsingAdminMode(boolean value) {
		mPlayer.setUsingAdminMode(value);
	}
	
	/**
	 * Whether or not this player is in its own territory (land of its faction).
	 * @return true if the player is; false otherwise.
	 */
	public boolean isInOwnTerritory() {
		return mPlayer.isInOwnTerritory();
	}
	
	/**
	 * Whether or not this player is in enemy territory (land of another faction).
	 * @return true if the player is; false otherwise.
	 */
	public boolean isInEnemyTerritory() {
		return mPlayer.isInEnemyTerritory();
	}
	
	/**
	 * Gets the UUID of this player.
	 * @return UUID of the player.
	 */
	public UUID getUUID() {
		return mPlayer.getUuid();
	}
	
	/**
	 * Gets the relation from this player towards another player.
	 * <br>The relation types it can return are: LEADER, OFFICER, MEMBER, RECRUIT, ALLY, TRUCE, NEUTRAL and ENEMY.
	 * @param otherPlayer Other player.
	 * @return A relation type or null if the other player was not found.
	 */
	public String getRelationTo(MPlayer otherPlayer) {
		com.massivecraft.factions.entity.MPlayer player = com.massivecraft.factions.entity.MPlayer.get(otherPlayer.getUUID());
		
		if (player == null) return null;
		
		Rel rel = mPlayer.getRelationTo(player);
		
		if (rel == null) return null;
		
		return rel.toString();
	}
	
	/**
	 * Gets the relation from this player towards a faction.
	 * <br>The relation types it can return are: LEADER, OFFICER, MEMBER, RECRUIT, ALLY, TRUCE, NEUTRAL and ENEMY.
	 * @param faction A faction.
	 * @return A relation type or null if the faction was not found.
	 */
	public String getRelationTo(Faction faction) {
		if (faction == null) return null;
		
		Rel rel = mPlayer.getRelationTo(FactionColl.get().get(faction.getId()));
		
		if (rel == null) return null;
		
		return rel.toString();
	}
	
	/**
	 * Whether this player is currently auto claiming land for his faction.
	 * @return true if he is; false otherwise.
	 */
	public boolean isAutoClaiming() {
		return mPlayer.getAutoClaimFaction() != null;
	}
	
	/**
	 * Whether this player is currently part of a non-default (so no Wilderness, Safezone or Warzone) faction.
	 * @return true if the player is; false otherwise.
	 */
	public boolean hasFaction() {
		return mPlayer.hasFaction();
	}
	
	/**
	 * Wipes all faction data of this player.
	 * <br>Will delete the current faction he's in, his title, his role and sets auto claiming to false.
	 * <br><b>Take caution with this method, I've not tested it.</b>
	 */
	public void resetFactionData() {
		mPlayer.resetFactionData();
	}
	
	/**
	 * Gets the name of this player.
	 * @return Name of the player.
	 */
	public String getName() {
		return mPlayer.getName();
	}
	
	/**
	 * Whether this player is the console.
	 * @return true if this player is the console; false otherwise.
	 */
	public boolean isConsole() {
		return mPlayer.isConsole();
	}
	
	/**
	 * Whether this player is the a real player.
	 * @return true if this player is a real player; false otherwise.
	 */
	public boolean isPlayer() {
		return mPlayer.isPlayer();
	}
	
}
