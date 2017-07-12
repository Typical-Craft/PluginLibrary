package me.staartvin.plugins.pluginlibrary.hooks;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import me.staartvin.plugins.pluginlibrary.Library;
import org.bukkit.entity.Player;

/**
 * EssentialsX library,
 * <a href="https://www.spigotmc.org/resources/essentialsx.9089/">link</a>.
 * <p>
 * 
 * @author Staartvin
 *
 */
public class EssentialsXHook extends LibraryHook {

	private Essentials essentials;

	/*
	 * (non-Javadoc)
	 * 
	 * @see me.staartvin.plugins.pluginlibrary.LibraryHook#isAvailable()
	 */
	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub

		return this.getPlugin().getServer().getPluginManager().isPluginEnabled(Library.ESSENTIALSX.getPluginName());
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

		essentials = (Essentials) this.getPlugin().getServer().getPluginManager()
				.getPlugin(Library.ESSENTIALSX.getPluginName());

		return essentials != null;
	}

    /**
     * Check whether a player is jailed by EssentialsX.
     * @param player Player to check
     * @return true if the given player is jailed, false otherwise.
     */
    public boolean isJailed(final Player player) {
        if (!isAvailable())
            return false;

        final User user = essentials.getUser(player);

        if (user == null) {
            return false;
        }

        return user.isJailed();
    }

    /**
     * Get the Geo location of the IP of a given player.
     * @param player Player to get the location of.
     * @return the estimated country of the IP of the given player.
     */
    public String getGeoIPLocation(final Player player) {
        if (!isAvailable())
            return null;

        final User user = essentials.getUser(player);

        if (user == null) {
            return null;
        }

        return user.getGeoLocation();
    }

}
