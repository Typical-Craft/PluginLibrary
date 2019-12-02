package me.staartvin.plugins.pluginlibrary.hooks;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Containers.CMIUser;
import me.staartvin.plugins.pluginlibrary.Library;
import me.staartvin.plugins.pluginlibrary.hooks.afkmanager.AFKManager;

import java.util.UUID;

/**
 * CMI library,
 * <a href="https://www.spigotmc.org/resources/cmi-270-commands-insane-kits-portals-essentials-economy-mysql-sqlite-much-more.3742/">link</a>.
 * <p>
 * 
 * @author Staartvin
 * 
 */
public class CMIHook extends LibraryHook implements AFKManager {

	/*
	 * (non-Javadoc)
	 * 
	 * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#isAvailable()
	 */
	@Override
	public boolean isAvailable() {
        return this.getPlugin().getServer().getPluginManager().isPluginEnabled(Library.CMI.getInternalPluginName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#hook()
	 */
	@Override
	public boolean hook() {
		// All api calls are done static, so there is no need to get the plugin
		// class.
		// We only check if the plugin is available.
		return isAvailable();
	}

	/**
	 * Check whether a user is AFK or not.
	 * 
	 * @param uuid
	 *            UUID of the player to check.
	 * @return true if the user is AFK, false otherwise.
	 */
	public boolean isAFK(UUID uuid) {
        if (!this.isAvailable()) return false;

        CMIUser user = CMI.getInstance().getPlayerManager().getUser(uuid);

        if (user == null) return false;

	    return user.isAfk();
	}

	@Override
	public boolean hasAFKData() {
		return true;
	}

}
