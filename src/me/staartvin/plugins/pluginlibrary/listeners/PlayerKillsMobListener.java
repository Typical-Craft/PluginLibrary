package me.staartvin.plugins.pluginlibrary.listeners;

import me.staartvin.plugins.pluginlibrary.Library;
import me.staartvin.plugins.pluginlibrary.PluginLibrary;
import me.staartvin.plugins.pluginlibrary.hooks.StatsHook;
import me.staartvin.plugins.pluginlibrary.hooks.customstats.MobKilledStat;

/**
 * This listener will listen to players killing mobs (for custom stat)
 * 
 * @author Staartvin
 * 
 */
public class PlayerKillsMobListener implements Listener {

	private final PluginLibrary plugin;
	private final StatsHook hook;

	public PlayerKillsMobListener(final PluginLibrary instance) {
		plugin = instance;
		hook = (StatsHook) PluginLibrary.getLibrary(Library.STATS);
	}

	@EventHandler
	public void OnKill(final EntityDeathEvent event) {

		// Stats is not available
		if (!plugin.isLibraryLoaded(Library.STATS))
			return;

		final Entity e = event.getEntity();
		if (e.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
			final EntityDamageByEntityEvent nEvent = (EntityDamageByEntityEvent) e
					.getLastDamageCause();
			if (nEvent.getDamager() instanceof Player) {

				String extraType = null;

				if (e instanceof Skeleton) {
					Skeleton ske = (Skeleton) e;

					if (ske.getSkeletonType() == SkeletonType.WITHER) {
						extraType = "WITHER";
					}
				} else if (e instanceof Creeper) {
					Creeper cre = (Creeper) e;

					if (cre.isPowered()) {
						extraType = "POWERED";
					}
				} else if (e instanceof Chicken) {
					Chicken mob = (Chicken) e;

					if (mob.getPassenger() != null) {
						extraType = "JOCKEY";
					}
				} else if (e instanceof Rabbit) {
					Rabbit mob = (Rabbit) e;

					if (mob.getRabbitType() == Type.THE_KILLER_BUNNY) {
						extraType = "KILLER";
					}
				} else if (e instanceof Spider) {
					Spider mob = (Spider) e;

					if (mob.getPassenger() != null) {
						extraType = "JOCKEY";
					}
				} else if (e instanceof Guardian) {
					Guardian mob = (Guardian) e;

					if (mob.isElder()) {
						extraType = "ELDER";
					}
				}

				if (extraType == null) {
					return;
				}

				Stat mobkilled = hook.getStat(MobKilledStat.statName);

				StatsHolder holder = hook.getStatsHolder(nEvent.getDamager()
						.getUniqueId());

				holder.addEntry(mobkilled, new DefaultStatEntry(1,
						new MetadataPair("entityType", e.getType().toString()),
						new MetadataPair("extraType", extraType),
						new MetadataPair("world", e.getLocation().getWorld()
								.getName().toString())));
			}
		}
	}
}
