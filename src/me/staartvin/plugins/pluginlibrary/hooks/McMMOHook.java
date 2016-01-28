package me.staartvin.plugins.pluginlibrary.hooks;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.gmail.nossr50.mcMMO;
import com.gmail.nossr50.api.AbilityAPI;
import com.gmail.nossr50.api.ChatAPI;
import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.api.PartyAPI;
import com.gmail.nossr50.api.SkillAPI;
import com.gmail.nossr50.api.exceptions.InvalidFormulaTypeException;
import com.gmail.nossr50.api.exceptions.InvalidPlayerException;
import com.gmail.nossr50.api.exceptions.InvalidSkillException;
import com.gmail.nossr50.api.exceptions.InvalidXPGainReasonException;
import com.gmail.nossr50.datatypes.party.Party;

import me.staartvin.plugins.pluginlibrary.Library;

/**
 * mcMMO library,
 * <a href="https://www.spigotmc.org/resources/mcmmo.2445/">link</a>.
 * <p>
 * Date created: 18:25:13 12 aug. 2015
 * 
 * @author Staartvin
 * 
 */
public class McMMOHook extends LibraryHook {

	private mcMMO api;

	/*
	 * (non-Javadoc)
	 * 
	 * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#isAvailable()
	 */
	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub

		return this.getPlugin().getServer().getPluginManager().isPluginEnabled(Library.MCMMO.getPluginName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#hook()
	 */
	@Override
	public boolean hook() {
		// TODO Auto-generated method stub

		if (!isAvailable())
			return false;

		api = (mcMMO) this.getPlugin().getServer().getPluginManager().getPlugin(Library.MCMMO.getPluginName());

		return api != null;
	}

	/* ExperienceAPI below */

	/**
	 * Checks whether given string is a valid type of skill suitable for the
	 * other API calls in mcMMO. </br>
	 * 
	 * @param skillType
	 *            String to check.
	 * @return true if this is a valid mcMMO skill.
	 */
	public boolean isValidSkillType(String skillType) {
		return ExperienceAPI.isValidSkillType(skillType);
	}

	/**
	 * Checks whether the given skill type string is both valid and not a child
	 * skill. (Child skills have no XP of their own, and their level is derived
	 * from the parent(s).) </br>
	 * 
	 * @param skillType
	 *            the skill to check
	 * @return true if this is a valid, non-child mcMMO skill
	 */
	public boolean isNonChildSkill(String skillType) {
		return ExperienceAPI.isNonChildSkill(skillType);
	}

	/**
	 * Adds raw XP to the player. </br>
	 * 
	 * @param player
	 *            The player to add XP to
	 * @param skillType
	 *            The skill to add XP to
	 * @param XP
	 *            The amount of XP to add
	 * @param xpGainReason
	 *            The reason to gain XP, choose from PVP, PVE, VAMPIRISM,
	 *            SHARED_PVP, SHARED_PVE, COMMAND or UNKNOWN.
	 * @param isUnshared
	 *            true if the XP cannot be shared with party members
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws InvalidXPGainReasonException
	 *             if the given xpGainReason is not valid
	 */
	public void addRawXP(Player player, String skillType, float XP, String xpGainReason, boolean isUnshared) {
		ExperienceAPI.addRawXP(player, skillType, XP, xpGainReason, isUnshared);
	}

	/**
	 * Adds raw XP to an offline player. </br>
	 * 
	 * @param uuid
	 *            The UUID of player to add XP to
	 * @param skillType
	 *            The skill to add XP to
	 * @param XP
	 *            The amount of XP to add
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws InvalidPlayerException
	 *             if the given player does not exist in the database
	 */

	public void addRawXPOffline(UUID uuid, String skillType, float XP) {
		ExperienceAPI.addRawXPOffline(uuid, skillType, XP);
	}

	/**
	 * Adds XP to the player, calculates for XP Rate and skill modifier. </br>
	 * 
	 * @param player
	 *            The player to add XP to
	 * @param skillType
	 *            The skill to add XP to
	 * @param XP
	 *            The amount of XP to add
	 * @param xpGainReason
	 *            The reason to gain XP, choose from PVP, PVE, VAMPIRISM,
	 *            SHARED_PVP, SHARED_PVE, COMMAND or UNKNOWN.
	 * @param isUnshared
	 *            true if the XP cannot be shared with party members
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws InvalidXPGainReasonException
	 *             if the given xpGainReason is not valid
	 */
	public void addModifiedXP(Player player, String skillType, int XP, String xpGainReason, boolean isUnshared) {
		ExperienceAPI.addModifiedXP(player, skillType, XP, xpGainReason, isUnshared);
	}

	/**
	 * Adds XP to the player, calculates for XP Rate only. </br>
	 * 
	 * @param player
	 *            The player to add XP to
	 * @param skillType
	 *            The skill to add XP to
	 * @param XP
	 *            The amount of XP to add
	 * @param xpGainReason
	 *            The reason to gain XP, choose from PVP, PVE, VAMPIRISM,
	 *            SHARED_PVP, SHARED_PVE, COMMAND or UNKNOWN.
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws InvalidXPGainReasonException
	 *             if the given xpGainReason is not valid
	 */
	public void addMultipliedXP(Player player, String skillType, int XP, String xpGainReason) {
		ExperienceAPI.addMultipliedXP(player, skillType, XP, xpGainReason);
	}

	/**
	 * Adds XP to an offline player, calculates for XP Rate only. </br>
	 * 
	 * @deprecated Unknown reason
	 * 
	 * @param playerName
	 *            The player to add XP to
	 * @param skillType
	 *            The skill to add XP to
	 * @param XP
	 *            The amount of XP to add
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws InvalidPlayerException
	 *             if the given player does not exist in the database
	 */
	@Deprecated
	public void addMultipliedXPOffline(String playerName, String skillType, int XP) {
		ExperienceAPI.addMultipliedXPOffline(playerName, skillType, XP);
	}

	/**
	 * Adds XP to an offline player, calculates for XP Rate and skill
	 * modifier. </br>
	 * 
	 * @deprecated Unknown reason
	 * 
	 * @param playerName
	 *            The player to add XP to
	 * @param skillType
	 *            The skill to add XP to
	 * @param XP
	 *            The amount of XP to add
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws InvalidPlayerException
	 *             if the given player does not exist in the database
	 */
	@Deprecated
	public void addModifiedXPOffline(String playerName, String skillType, int XP) {
		ExperienceAPI.addModifiedXPOffline(playerName, skillType, XP);
	}

	/**
	 * Adds XP to the player, calculates for XP Rate, skill modifiers, perks,
	 * child skills, and party sharing. </br>
	 * 
	 * @param player
	 *            The player to add XP to
	 * @param skillType
	 *            The skill to add XP to
	 * @param XP
	 *            The amount of XP to add
	 * @param xpGainReason
	 *            The reason to gain XP, choose from PVP, PVE, VAMPIRISM,
	 *            SHARED_PVP, SHARED_PVE, COMMAND or UNKNOWN.
	 * @param isUnshared
	 *            true if the XP cannot be shared with party members
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws InvalidXPGainReasonException
	 *             if the given xpGainReason is not valid
	 */
	public void addXP(Player player, String skillType, int XP, String xpGainReason, boolean isUnshared) {
		ExperienceAPI.addXP(player, skillType, XP, xpGainReason, isUnshared);
	}

	/**
	 * Get the amount of XP a player has in a specific skill. </br>
	 * 
	 * @param player
	 *            The player to get XP for
	 * @param skillType
	 *            The skill to get XP for
	 * @return the amount of XP in a given skill
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws UnsupportedOperationException
	 *             if the given skill is a child skill
	 */
	public int getXP(Player player, String skillType) {
		return ExperienceAPI.getXP(player, skillType);
	}

	/**
	 * Get the amount of XP an offline player has in a specific skill. </br>
	 * 
	 * @param uuid
	 *            The player to get XP for
	 * @param skillType
	 *            The skill to get XP for
	 * @return the amount of XP in a given skill
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws InvalidPlayerException
	 *             if the given player does not exist in the database
	 * @throws UnsupportedOperationException
	 *             if the given skill is a child skill
	 */
	public int getOfflineXP(UUID uuid, String skillType) {
		return ExperienceAPI.getOfflineXP(uuid, skillType);
	}

	/**
	 * Get the raw amount of XP a player has in a specific skill. </br>
	 * 
	 * @param player
	 *            The player to get XP for
	 * @param skillType
	 *            The skill to get XP for
	 * @return the amount of XP in a given skill
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws UnsupportedOperationException
	 *             if the given skill is a child skill
	 */
	public float getXPRaw(Player player, String skillType) {
		return ExperienceAPI.getXPRaw(player, skillType);
	}

	/**
	 * Get the raw amount of XP an offline player has in a specific skill. </br>
	 * 
	 * @param uuid
	 *            The player to get XP for
	 * @param skillType
	 *            The skill to get XP for
	 * @return the amount of XP in a given skill
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws InvalidPlayerException
	 *             if the given player does not exist in the database
	 * @throws UnsupportedOperationException
	 *             if the given skill is a child skill
	 */
	public float getOfflineXPRaw(UUID uuid, String skillType) {
		return ExperienceAPI.getOfflineXPRaw(uuid, skillType);
	}

	/**
	 * Get the total amount of XP needed to reach the next level. </br>
	 * 
	 * @param player
	 *            The player to get the XP amount for
	 * @param skillType
	 *            The skill to get the XP amount for
	 * @return the total amount of XP needed to reach the next level
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws UnsupportedOperationException
	 *             if the given skill is a child skill
	 */
	public int getXPToNextLevel(Player player, String skillType) {
		return ExperienceAPI.getXPToNextLevel(player, skillType);
	}

	/**
	 * Get the total amount of XP an offline player needs to reach the next
	 * level. </br>
	 * 
	 * @param uuid
	 *            The player to get XP for
	 * @param skillType
	 *            The skill to get XP for
	 * @return the total amount of XP needed to reach the next level
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws InvalidPlayerException
	 *             if the given player does not exist in the database
	 * @throws UnsupportedOperationException
	 *             if the given skill is a child skill
	 */
	public int getOfflineXPToNextLevel(UUID uuid, String skillType) {
		return ExperienceAPI.getOfflineXPToNextLevel(uuid, skillType);
	}

	/**
	 * Get the amount of XP remaining until the next level. </br>
	 * 
	 * @param player
	 *            The player to get the XP amount for
	 * @param skillType
	 *            The skill to get the XP amount for
	 * @return the amount of XP remaining until the next level
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws UnsupportedOperationException
	 *             if the given skill is a child skill
	 */
	public int getXPRemaining(Player player, String skillType) {
		return ExperienceAPI.getXPRemaining(player, skillType);
	}

	/**
	 * Get the amount of XP an offline player has left before leveling up. </br>
	 * 
	 * @param uuid
	 *            The player to get XP for
	 * @param skillType
	 *            The skill to get XP for
	 * @return the amount of XP needed to reach the next level
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws InvalidPlayerException
	 *             if the given player does not exist in the database
	 * @throws UnsupportedOperationException
	 *             if the given skill is a child skill
	 */
	public float getOfflineXPRemaining(UUID uuid, String skillType) {
		return ExperienceAPI.getOfflineXPRemaining(uuid, skillType);
	}

	/**
	 * Add levels to a skill. </br>
	 * 
	 * @param player
	 *            The player to add levels to
	 * @param skillType
	 *            Type of skill to add levels to
	 * @param levels
	 *            Number of levels to add
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 */
	public void addLevel(Player player, String skillType, int levels) {
		ExperienceAPI.addLevel(player, skillType, levels);
	}

	/**
	 * Add levels to a skill for an offline player. </br>
	 * .
	 * 
	 * @param uuid
	 *            The player to add levels to
	 * @param skillType
	 *            Type of skill to add levels to
	 * @param levels
	 *            Number of levels to add
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws InvalidPlayerException
	 *             if the given player does not exist in the database
	 */
	public void addLevelOffline(UUID uuid, String skillType, int levels) {
		ExperienceAPI.addLevelOffline(uuid, skillType, levels);
	}

	/**
	 * Get the level a player has in a specific skill. </br>
	 * 
	 * @param player
	 *            The player to get the level for
	 * @param skillType
	 *            The skill to get the level for
	 * @return the level of a given skill
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 */
	public int getLevel(Player player, String skillType) {
		return ExperienceAPI.getLevel(player, skillType);
	}

	/**
	 * Get the level an offline player has in a specific skill. </br>
	 * 
	 * @param uuid
	 *            The player to get the level for
	 * @param skillType
	 *            The skill to get the level for
	 * @return the level of a given skill
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws InvalidPlayerException
	 *             if the given player does not exist in the database
	 */
	public int getLevelOffline(UUID uuid, String skillType) {
		return ExperienceAPI.getLevelOffline(uuid, skillType);
	}

	/**
	 * Gets the power level of a player. </br>
	 * 
	 * @param player
	 *            The player to get the power level for
	 * @return the power level of the player
	 */
	public int getPowerLevel(Player player) {
		return ExperienceAPI.getPowerLevel(player);
	}

	/**
	 * Gets the power level of an offline player. </br>
	 * 
	 * @param uuid
	 *            The player to get the power level for
	 * @return the power level of the player
	 * 
	 * @throws InvalidPlayerException
	 *             if the given player does not exist in the database
	 */
	public int getPowerLevelOffline(UUID uuid) {
		return ExperienceAPI.getPowerLevelOffline(uuid);
	}

	/**
	 * Get the level cap of a specific skill. </br>
	 * 
	 * @param skillType
	 *            The skill to get the level cap for
	 * @return the level cap of a given skill
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 */
	public int getLevelCap(String skillType) {
		return ExperienceAPI.getLevelCap(skillType);
	}

	/**
	 * Get the power level cap. </br>
	 * 
	 * @return the overall power level cap
	 */
	public int getPowerLevelCap() {
		return ExperienceAPI.getPowerLevelCap();
	}

	/**
	 * Get the position on the leaderboard of a player. </br>
	 * 
	 * @param uuid
	 *            The name of the player to check
	 * @param skillType
	 *            The skill to check
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws InvalidPlayerException
	 *             if the given player does not exist in the database
	 * @throws UnsupportedOperationException
	 *             if the given skill is a child skill
	 * 
	 * @return the position on the leaderboard
	 */
	public int getPlayerRankSkill(UUID uuid, String skillType) {
		return ExperienceAPI.getPlayerRankSkill(uuid, skillType);
	}

	/**
	 * Get the position on the power level leaderboard of a player. </br>
	 * 
	 * @param uuid
	 *            The name of the player to check
	 * 
	 * @throws InvalidPlayerException
	 *             if the given player does not exist in the database
	 * 
	 * @return the position on the power level leaderboard
	 */
	public int getPlayerRankOverall(UUID uuid) {
		return ExperienceAPI.getPlayerRankOverall(uuid);
	}

	/**
	 * Sets the level of a player in a specific skill type. </br>
	 * 
	 * @param player
	 *            The player to set the level of
	 * @param skillType
	 *            The skill to set the level for
	 * @param skillLevel
	 *            The value to set the level to
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 */
	public void setLevel(Player player, String skillType, int skillLevel) {
		ExperienceAPI.setLevel(player, skillType, skillLevel);
	}

	/**
	 * Sets the level of an offline player in a specific skill type. </br>
	 * 
	 * @param uuid
	 *            The player to set the level of
	 * @param skillType
	 *            The skill to set the level for
	 * @param skillLevel
	 *            The value to set the level to
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws InvalidPlayerException
	 *             if the given player does not exist in the database
	 */
	public void setLevelOffline(UUID uuid, String skillType, int skillLevel) {
		ExperienceAPI.setLevelOffline(uuid, skillType, skillLevel);
	}

	/**
	 * Sets the XP of a player in a specific skill type. </br>
	 * 
	 * @param player
	 *            The player to set the XP of
	 * @param skillType
	 *            The skill to set the XP for
	 * @param newValue
	 *            The value to set the XP to
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws UnsupportedOperationException
	 *             if the given skill is a child skill
	 */
	public void setXP(Player player, String skillType, int newValue) {
		ExperienceAPI.setXP(player, skillType, newValue);
	}

	/**
	 * Sets the XP of an offline player in a specific skill type. </br>
	 * 
	 * @param uuid
	 *            The player to set the XP of
	 * @param skillType
	 *            The skill to set the XP for
	 * @param newValue
	 *            The value to set the XP to
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws InvalidPlayerException
	 *             if the given player does not exist in the database
	 * @throws UnsupportedOperationException
	 *             if the given skill is a child skill
	 */
	public void setXPOffline(UUID uuid, String skillType, int newValue) {
		ExperienceAPI.setXPOffline(uuid, skillType, newValue);
	}

	/**
	 * Removes XP from a player in a specific skill type. </br>
	 * 
	 * @param player
	 *            The player to change the XP of
	 * @param skillType
	 *            The skill to change the XP for
	 * @param xp
	 *            The amount of XP to remove
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws UnsupportedOperationException
	 *             if the given skill is a child skill
	 */
	public void removeXP(Player player, String skillType, int xp) {
		ExperienceAPI.removeXP(player, skillType, xp);
	}

	/**
	 * Removes XP from an offline player in a specific skill type. </br>
	 * 
	 * @param uuid
	 *            The player to change the XP of
	 * @param skillType
	 *            The skill to change the XP for
	 * @param xp
	 *            The amount of XP to remove
	 * 
	 * @throws InvalidSkillException
	 *             if the given skill is not valid
	 * @throws InvalidPlayerException
	 *             if the given player does not exist in the database
	 * @throws UnsupportedOperationException
	 *             if the given skill is a child skill
	 */
	public void removeXPOffline(UUID uuid, String skillType, int xp) {
		ExperienceAPI.removeXPOffline(uuid, skillType, xp);
	}

	/**
	 * Check how much XP is needed for a specific level with the selected level
	 * curve. </br>
	 * 
	 * @param level
	 *            The level to get the amount of XP for
	 * 
	 * @throws InvalidFormulaTypeException
	 *             if the given formulaType is not valid
	 */
	public int getXpNeededToLevel(int level) {
		return ExperienceAPI.getXpNeededToLevel(level);
	}

	/**
	 * Check how much XP is needed for a specific level with the provided level
	 * curve. </br>
	 * 
	 * @param level
	 *            The level to get the amount of XP for
	 * @param formulaType
	 *            The formula type to get the amount of XP for
	 * 
	 * @throws InvalidFormulaTypeException
	 *             if the given formulaType is not valid
	 */
	public int getXpNeededToLevel(int level, String formulaType) {
		return ExperienceAPI.getXpNeededToLevel(level, formulaType);
	}

	/* AbilityAPI below */

	/**
	 * Checks whether Berserk is enabled for a specific player.
	 * 
	 * @param player
	 *            Player to check for
	 * @return true if enabled; false otherwise.
	 */
	public boolean berserkEnabled(Player player) {
		return AbilityAPI.berserkEnabled(player);
	}

	/**
	 * Checks whether GigaDrill Breaker is enabled for a specific player.
	 * 
	 * @param player
	 *            Player to check for
	 * @return true if enabled; false otherwise.
	 */
	public boolean gigaDrillBreakerEnabled(Player player) {
		return AbilityAPI.gigaDrillBreakerEnabled(player);
	}

	/**
	 * Checks whether Green Terra is enabled for a specific player.
	 * 
	 * @param player
	 *            Player to check for
	 * @return true if enabled; false otherwise.
	 */
	public boolean greenTerraEnabled(Player player) {
		return AbilityAPI.greenTerraEnabled(player);
	}

	/**
	 * Checks whether Serrated Strikes is enabled for a specific player.
	 * 
	 * @param player
	 *            Player to check for
	 * @return true if enabled; false otherwise.
	 */
	public boolean serratedStrikesEnabled(Player player) {
		return AbilityAPI.serratedStrikesEnabled(player);
	}

	/**
	 * Checks whether Skull Splitter is enabled for a specific player.
	 * 
	 * @param player
	 *            Player to check for
	 * @return true if enabled; false otherwise.
	 */
	public boolean skullSplitterEnabled(Player player) {
		return AbilityAPI.skullSplitterEnabled(player);
	}

	/**
	 * Checks whether Super Breaker is enabled for a specific player.
	 * 
	 * @param player
	 *            Player to check for
	 * @return true if enabled; false otherwise.
	 */
	public boolean superBreakerEnabled(Player player) {
		return AbilityAPI.superBreakerEnabled(player);
	}

	/**
	 * Checks whether Tree Feller is enabled for a specific player.
	 * 
	 * @param player
	 *            Player to check for
	 * @return true if enabled; false otherwise.
	 */
	public boolean treeFellerEnabled(Player player) {
		return AbilityAPI.treeFellerEnabled(player);
	}

	/**
	 * Checks whether any special ability is enabled for a specific player.
	 * 
	 * @param player
	 *            Player to check for
	 * @return true if enabled; false otherwise.
	 */
	public boolean isAnyAbilityEnabled(Player player) {
		return AbilityAPI.isAnyAbilityEnabled(player);
	}

	/**
	 * Resets all ability cooldowns for a player so he/she can use it again
	 * immediately.
	 * 
	 * @param player
	 *            Player to reset cooldowns for.
	 */
	public void resetCooldowns(Player player) {
		AbilityAPI.resetCooldowns(player);
	}

	/**
	 * Set the cooldown of Berserk of a specific player.
	 * 
	 * @param player
	 *            Player to set the cooldown for.
	 * @param cooldown
	 *            Cooldown (in seconds) for the ability.
	 */
	public void setBerserkCooldown(Player player, long cooldown) {
		AbilityAPI.setBerserkCooldown(player, cooldown);
	}

	/**
	 * Set the cooldown of GigaDrill Breaker of a specific player.
	 * 
	 * @param player
	 *            Player to set the cooldown for.
	 * @param cooldown
	 *            Cooldown (in seconds) for the ability.
	 */
	public void setGigaDrillBreakerCooldown(Player player, long cooldown) {
		AbilityAPI.setGigaDrillBreakerCooldown(player, cooldown);
	}

	/**
	 * Set the cooldown of Green Terra of a specific player.
	 * 
	 * @param player
	 *            Player to set the cooldown for.
	 * @param cooldown
	 *            Cooldown (in seconds) for the ability.
	 */
	public void setGreenTerraCooldown(Player player, long cooldown) {
		AbilityAPI.setGreenTerraCooldown(player, cooldown);
	}

	/**
	 * Set the cooldown of Serrated Strikes of a specific player.
	 * 
	 * @param player
	 *            Player to set the cooldown for.
	 * @param cooldown
	 *            Cooldown (in seconds) for the ability.
	 */
	public void setSerratedStrikesCooldown(Player player, long cooldown) {
		AbilityAPI.setSerratedStrikesCooldown(player, cooldown);
	}

	/**
	 * Set the cooldown of Skull Splitter of a specific player.
	 * 
	 * @param player
	 *            Player to set the cooldown for.
	 * @param cooldown
	 *            Cooldown (in seconds) for the ability.
	 */
	public void setSkullSplitterCooldown(Player player, long cooldown) {
		AbilityAPI.setSkullSplitterCooldown(player, cooldown);
	}

	/**
	 * Set the cooldown of Super Breaker of a specific player.
	 * 
	 * @param player
	 *            Player to set the cooldown for.
	 * @param cooldown
	 *            Cooldown (in seconds) for the ability.
	 */
	public void setSuperBreakerCooldown(Player player, long cooldown) {
		AbilityAPI.setSuperBreakerCooldown(player, cooldown);
	}

	/**
	 * Set the cooldown of Tree Feller of a specific player.
	 * 
	 * @param player
	 *            Player to set the cooldown for.
	 * @param cooldown
	 *            Cooldown (in seconds) for the ability.
	 */
	public void setTreeFellerCooldown(Player player, long cooldown) {
		AbilityAPI.setTreeFellerCooldown(player, cooldown);
	}

	/**
	 * Checks whether the given entity is bleeding.
	 * 
	 * @param entity
	 *            Entity to check.
	 * @return true if bleeding; false otherwise.
	 */
	public boolean isBleeding(LivingEntity entity) {
		return AbilityAPI.isBleeding(entity);
	}

	/* ChatAPI below */

	/**
	 * Send a message to all members of a party </br>
	 * 
	 * 
	 * @param plugin
	 *            The plugin sending the message
	 * @param sender
	 *            The name of the sender
	 * @param displayName
	 *            The display name of the sender
	 * @param party
	 *            The name of the party to send to
	 * @param message
	 *            The message to send
	 */
	public void sendPartyChat(Plugin plugin, String sender, String displayName, String party, String message) {
		ChatAPI.sendPartyChat(plugin, sender, displayName, party, message);
	}

	/**
	 * Send a message to all members of a party </br>
	 * 
	 * 
	 * @param plugin
	 *            The plugin sending the message
	 * @param sender
	 *            The name of the sender to display in the chat
	 * @param party
	 *            The name of the party to send to
	 * @param message
	 *            The message to send
	 */
	public void sendPartyChat(Plugin plugin, String sender, String party, String message) {
		ChatAPI.sendPartyChat(plugin, sender, party, message);
	}

	/**
	 * Send a message to administrators </br>
	 * 
	 * 
	 * @param plugin
	 *            The plugin sending the message
	 * @param sender
	 *            The name of the sender
	 * @param displayName
	 *            The display name of the sender
	 * @param message
	 *            The message to send
	 */
	public void sendAdminChat(Plugin plugin, String sender, String displayName, String message) {
		ChatAPI.sendAdminChat(plugin, sender, displayName, message);
	}

	/**
	 * Send a message to administrators </br>
	 * 
	 * 
	 * @param plugin
	 *            The plugin sending the message
	 * @param sender
	 *            The name of the sender to display in the chat
	 * @param message
	 *            The message to send
	 */
	public void sendAdminChat(Plugin plugin, String sender, String message) {
		ChatAPI.sendAdminChat(plugin, sender, message);
	}

	/**
	 * Check if a player is currently talking in party chat.
	 * 
	 * @param player
	 *            The player to check
	 * @return true if the player is using party chat, false otherwise
	 */
	public boolean isUsingPartyChat(Player player) {
		return ChatAPI.isUsingPartyChat(player);
	}

	/**
	 * Check if a player is currently talking in party chat.
	 * 
	 * @param playerName
	 *            The name of the player to check
	 * @return true if the player is using party chat, false otherwise
	 */
	public boolean isUsingPartyChat(String playerName) {
		return ChatAPI.isUsingPartyChat(playerName);
	}

	/**
	 * Check if a player is currently talking in admin chat.
	 * 
	 * @param player
	 *            The player to check
	 * @return true if the player is using admin chat, false otherwise
	 */
	public boolean isUsingAdminChat(Player player) {
		return ChatAPI.isUsingAdminChat(player);
	}

	/**
	 * Check if a player is currently talking in admin chat.
	 * 
	 * @param playerName
	 *            The name of the player to check
	 * @return true if the player is using admin chat, false otherwise
	 */
	public boolean isUsingAdminChat(String playerName) {
		return ChatAPI.isUsingAdminChat(playerName);
	}

	/**
	 * Toggle the party chat mode of a player.
	 * 
	 * @param player
	 *            The player to toggle party chat on.
	 */
	public void togglePartyChat(Player player) {
		ChatAPI.togglePartyChat(player);
	}

	/**
	 * Toggle the party chat mode of a player.
	 * 
	 * @param playerName
	 *            The name of the player to toggle party chat on.
	 */
	public void togglePartyChat(String playerName) {
		ChatAPI.togglePartyChat(playerName);
	}

	/**
	 * Toggle the admin chat mode of a player.
	 * 
	 * @param player
	 *            The player to toggle admin chat on.
	 */
	public void toggleAdminChat(Player player) {
		ChatAPI.toggleAdminChat(player);
	}

	/**
	 * Toggle the admin chat mode of a player.
	 * 
	 * @param playerName
	 *            The name of the player to toggle party chat on.
	 */
	public void toggleAdminChat(String playerName) {
		ChatAPI.toggleAdminChat(playerName);
	}

	/* PartyAPI below */

	/**
	 * Get the name of the party a player is in. </br>
	 *
	 * 
	 * @param player
	 *            The player to check the party name of
	 * @return the name of the player's party, or null if not in a party
	 */
	public String getPartyName(Player player) {
		return PartyAPI.getPartyName(player);
	}

	/**
	 * Checks if a player is in a party. </br>
	 *
	 * 
	 * @param player
	 *            The player to check
	 * @return true if the player is in a party, false otherwise
	 */
	public boolean inParty(Player player) {
		return PartyAPI.inParty(player);
	}

	/**
	 * Check if two players are in the same party. </br>
	 *
	 * 
	 * @param playera
	 *            The first player to check
	 * @param playerb
	 *            The second player to check
	 * @return true if the two players are in the same party, false otherwise
	 */
	public boolean inSameParty(Player playera, Player playerb) {
		return PartyAPI.inSameParty(playera, playerb);
	}

	/**
	 * Get a list of all current parties. </br>
	 *
	 * 
	 * @return the list of parties.
	 */
	public List<Party> getParties() {
		return PartyAPI.getParties();
	}

	/**
	 * Add a player to a party. </br>
	 *
	 * 
	 * @param player
	 *            The player to add to the party
	 * @param partyName
	 *            The party to add the player to
	 */
	public void addToParty(Player player, String partyName) {
		PartyAPI.addToParty(player, partyName);
	}

	/**
	 * Remove a player from a party. </br>
	 *
	 * 
	 * @param player
	 *            The player to remove
	 */
	public void removeFromParty(Player player) {
		PartyAPI.removeFromParty(player);
	}

	/**
	 * Get the leader of a party. </br>
	 *
	 * 
	 * @param partyName
	 *            The party name
	 * @return the leader of the party
	 */
	public String getPartyLeader(String partyName) {
		return PartyAPI.getPartyLeader(partyName);
	}

	/**
	 * Set the leader of a party. </br>
	 * 
	 * @deprecated Unknown reason.
	 *
	 * @param partyName
	 *            The name of the party to set the leader of
	 * @param playerName
	 *            The playerName to set as leader
	 */
	@Deprecated
	public void setPartyLeader(String partyName, String playerName) {
		PartyAPI.setPartyLeader(partyName, playerName);
	}

	/**
	 * Get a list of all players in this player's party. </br>
	 * 
	 * @deprecated Unknown reason.
	 *
	 * @param player
	 *            The player to check
	 * @return all the players in the player's party
	 */
	@Deprecated
	public List<OfflinePlayer> getOnlineAndOfflineMembers(Player player) {
		return PartyAPI.getOnlineAndOfflineMembers(player);
	}

	/**
	 * Get a list of all player names in this player's party. </br>
	 * 
	 * @deprecated Unknown reason.
	 *
	 * @param player
	 *            The player to check
	 * @return all the player names in the player's party
	 */
	@Deprecated
	public LinkedHashSet<String> getMembers(Player player) {
		return PartyAPI.getMembers(player);
	}

	/**
	 * Get a list of all player names and uuids in this player's party. </br>
	 *
	 * 
	 * @param player
	 *            The player to check
	 * @return all the player names and uuids in the player's party
	 */
	public LinkedHashMap<UUID, String> getMembersMap(Player player) {
		return PartyAPI.getMembersMap(player);
	}

	/**
	 * Get a list of all online players in this party. </br>
	 *
	 * 
	 * @param partyName
	 *            The party to check
	 * @return all online players in this party
	 */
	public List<Player> getOnlineMembers(String partyName) {
		return PartyAPI.getOnlineMembers(partyName);
	}

	/**
	 * Get a list of all online players in this player's party. </br>
	 *
	 * 
	 * @param player
	 *            The player to check
	 * @return all online players in the player's party
	 */
	public List<Player> getOnlineMembers(Player player) {
		return PartyAPI.getOnlineMembers(player);
	}

	/**
	 * Whether the given party has an ally.
	 * 
	 * @param partyName
	 *            Party to check.
	 * @return true if the party has an ally; false otherwise.
	 */
	public boolean hasAlly(String partyName) {
		return PartyAPI.hasAlly(partyName);
	}

	/**
	 * Get the ally of a party.
	 * 
	 * @param partyName
	 *            Party to get the ally from.
	 * @return Name of the ally or null if not found.
	 */
	public String getAllyName(String partyName) {
		return PartyAPI.getAllyName(partyName);
	}

	/* SkillAPI below */

	/**
	 * Returns a list of strings with mcMMO's skills This includes parent and
	 * child skills </br>
	 *
	 * 
	 * @return a list of strings with valid skill names
	 */
	public List<String> getSkills() {
		return SkillAPI.getSkills();
	}

	/**
	 * Returns a list of strings with mcMMO's skills This only includes parent
	 * skills </br>
	 *
	 * 
	 * @return a list of strings with valid skill names
	 */
	public List<String> getNonChildSkills() {
		return SkillAPI.getNonChildSkills();
	}

	/**
	 * Returns a list of strings with mcMMO's skills This only includes child
	 * skills </br>
	 *
	 * 
	 * @return a list of strings with valid skill names
	 */
	public List<String> getChildSkills() {
		return SkillAPI.getChildSkills();
	}

	/**
	 * Returns a list of strings with mcMMO's skills This only includes combat
	 * skills </br>
	 *
	 * 
	 * @return a list of strings with valid skill names
	 */
	public List<String> getCombatSkills() {
		return SkillAPI.getCombatSkills();
	}

	/**
	 * Returns a list of strings with mcMMO's skills This only includes
	 * gathering skills </br>
	 *
	 * 
	 * @return a list of strings with valid skill names
	 */
	public List<String> getGatheringSkills() {
		return SkillAPI.getGatheringSkills();
	}

	/**
	 * Returns a list of strings with mcMMO's skills This only includes misc
	 * skills </br>
	 *
	 * 
	 * @return a list of strings with valid skill names
	 */
	public List<String> getMiscSkills() {
		return SkillAPI.getMiscSkills();
	}

}
