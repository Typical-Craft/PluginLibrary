package me.staartvin.plugins.pluginlibrary.hooks;


import com.massivecraft.factions.P;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import me.staartvin.plugins.pluginlibrary.Library;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * SavageFactions,
 * <a href="https://www.spigotmc.org/resources/savagefactions-factionsuuid-reimagined-production-server-ready.52891/">link</a>.
 * <p>
 * Date created: 20:22:17 12 aug. 2015
 *
 * @author Staartvin
 */
public class SavageFactionsHook extends LibraryHook {

    private P savageFactions;

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.plugins.pluginlibrary.LibraryHook#isAvailable()
     */
    @Override
    public boolean isAvailable() {
        // TODO Auto-generated method stub

        return this.getPlugin().getServer().getPluginManager().isPluginEnabled(Library.SAVAGE_FACTIONS
                .getInternalPluginName());
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

        Plugin plugin = this.getPlugin().getServer().getPluginManager()
                .getPlugin(Library.SAVAGE_FACTIONS.getInternalPluginName());

        if (!(plugin instanceof P))
            return false;

        savageFactions = (P) plugin;

        // Unfortunately, the SavageFactions plugin is nearly identical to the 'regular' Factions.
        // However, we know that ProSavage has put its name in the authors array.
        return savageFactions != null && savageFactions.getDescription().getAuthors().contains("ProSavage");
    }

    /* Util methods */

    private MPlayer getMPlayer(UUID uuid) {
        if (!this.isAvailable()) return null;

        return MPlayer.get(uuid);
    }

    /* Faction vars */

    /**
     * Gets the faction by its comparison name. Use the comparison name, see
     * {@link Faction#getComparisonName()}
     * .
     *
     * @param factionName Name of the faction. <b>Without colour codes!</b>
     *
     * @return {@link Faction} or null if no faction found.
     */
    public Faction getFactionByName(String factionName) {

        if (!this.isAvailable()) return null;

        if (factionName == null)
            return null;

        Faction fac = FactionColl.get().getByName(factionName);

        if (fac == null)
            return null;

        return fac;
    }

    /**
     * Gets the faction a player is in.
     *
     * @param uuid UUID of the player.
     *
     * @return {@link Faction}, null if the player doesn't exist or is not in
     * a faction.
     */
    public Faction getFactionByUUID(UUID uuid) {

        if (!this.isAvailable()) return null;

        if (uuid == null)
            return null;

        MPlayer mPlayer = getMPlayer(uuid);

        if (mPlayer == null)
            return null;

        Faction fac = mPlayer.getFaction();

        if (fac == null)
            return null;

        return fac;
    }

    /**
     * Gets a faction by its internal Factions id.
     *
     * @param factionId Id of the faction
     *
     * @return {@link Faction} or null if id is invalid.
     */
    public Faction getFactionById(String factionId) {

        if (!this.isAvailable()) return null;

        if (factionId == null)
            return null;

        if (FactionColl.get().containsId(factionId)) {
            Faction fac = FactionColl.get().get(factionId);

            if (fac == null)
                return null;

            return fac;
        }

        return null;
    }

    /**
     * Gets a list of all savageFactions that currently exist.
     *
     * @return a list of all savageFactions.
     */
    public List<Faction> getAllFactions() {

        List<Faction> factions = new ArrayList<Faction>();

        if (!this.isAvailable()) return factions;

        for (Faction fac : FactionColl.get().getAll()) {
            factions.add(fac);
        }

        return factions;
    }

    /**
     * Gets the Wilderness 'faction'.
     *
     * @return the Wilderness faction.
     */
    public Faction getWilderness() {

        if (!this.isAvailable()) return null;

        Faction fac = FactionColl.get().getNone();

        if (fac == null)
            return null;

        return fac;
    }

    /**
     * Gets the Safezone 'faction'.
     *
     * @return the Safezone faction.
     */
    public Faction getSafezone() {

        if (!this.isAvailable()) return null;

        Faction fac = FactionColl.get().getSafezone();

        if (fac == null)
            return null;

        return fac;
    }

    /**
     * Gets the Warzone 'faction'.
     *
     * @return the Warzone faction.
     */
    public Faction getWarzone() {

        if (!this.isAvailable()) return null;

        Faction fac = FactionColl.get().getWarzone();

        if (fac == null)
            return null;

        return fac;
    }

    /**
     * Gets the faction at a specific {@link Location}.
     *
     * @param location Location for the faction to be at.
     *
     * @return A {@link Faction} or null if the location does not contain a
     * faction.
     */
    public Faction getFactionAt(Location location) {

        if (!this.isAvailable()) return null;

        if (location == null)
            return null;

        Faction fac = BoardColl.get().getFactionAt(PS.valueOf(location));

        if (fac == null)
            return null;

        return fac;
    }

    /**
     * Gets the
     * {@link MPlayer} for a
     * player, which represents the player object Factions internally uses.
     *
     * @param uuid UUID of the player.
     *
     * @return {@link MPlayer}
     * or null if player does not exist/is not stored by Factions.
     */
    public MPlayer getFactionsPlayer(UUID uuid) {

        if (!this.isAvailable()) return null;

        MPlayer mPlayer = MPlayer.get(uuid);

        if (mPlayer == null)
            return null;

        return mPlayer;
    }

    /**
     * Get the power of the faction of a player. Will return -1 if player has no faction.
     *
     * @param uuid UUID of the player to check
     *
     * @return power of the faction, or -1 if not found.
     */
    public double getFactionPower(UUID uuid) {

        if (!this.isAvailable()) return -1;

        if (uuid == null) return -1;

        MPlayer mPlayer = getFactionsPlayer(uuid);

        if (mPlayer == null) return -1;

        if (!mPlayer.hasFaction()) return -1;

        return mPlayer.getFaction().getPower();
    }
}