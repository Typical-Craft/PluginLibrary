package me.staartvin.plugins.pluginlibrary.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Util class
 * <p>
 * Date created:  17:38:32
 * 12 aug. 2015
 * @author Staartvin
 *
 */
public class Util {

	/**
	 * Get the name of this food item.
	 * 
	 * @param item ItemStack to get the name of.
	 * @return Name of food, or null if not a valid food item.
	 */
	public static String getFoodName(ItemStack item) {
		// Returns null if not a valid food item
		// Got Materials from https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html

		if (item == null)
			return null;

		switch (item.getType()) {
		case APPLE:
			return "APPLE";
		case BAKED_POTATO:
			return "BAKED_POTATO";
		case BREAD:
			return "BREAD";
		case CAKE_BLOCK: // not working atm
			return "CAKE_BLOCK";
		case CARROT_ITEM:
			return "CARROT_ITEM";
		case COOKED_CHICKEN:
			return "COOKED_CHICKEN";
		case COOKED_FISH: {
			if (item.getDurability() == (short) 1) {
				return "COOKED_SALMON";
			}
			return "COOKED_FISH";
		}
		case COOKED_MUTTON:
			return "COOKED_MUTTON";
		case GRILLED_PORK:
			return "GRILLED_PORK";
		case COOKED_RABBIT:
			return "COOKED_RABBIT";
		case COOKIE:
			return "COOKIE";
		case GOLDEN_APPLE: {
			if (item.getDurability() == (short) 1) {
				return "ENCHANTED_GOLDEN_APPLE";
			}
			return "GOLDEN_APPLE";
		}
		case GOLDEN_CARROT:
			return "GOLDEN_CARROT";
		case MELON:
			return "MELON";
		case MUSHROOM_SOUP:
			return "MUSHROOM_SOUP";
		case RABBIT_STEW:
			return "RABBIT_STEW";
		case RAW_BEEF:
			return "RAW_BEEF";
		case RAW_CHICKEN:
			return "RAW_CHICKEN";
		case RAW_FISH: {
			if (item.getDurability() == (short) 1) {
				return "RAW_SALMON";
			} else if (item.getDurability() == (short) 2) {
				return "CLOWNFISH";
			} else if (item.getDurability() == (short) 3) {
				return "PUFFERFISH";
			}
			return "RAW_FISH";
		}
		case POISONOUS_POTATO:
			return "POISONOUS_POTATO";
		case POTATO:
			return "POTATO";
		case PUMPKIN_PIE:
			return "PUMPKIN_PIE";
		case MUTTON:
			return "MUTTON"; // raw
		case COOKED_BEEF:
			return "COOKED_BEEF";
		case RABBIT:
			return "RABBIT";
		case ROTTEN_FLESH:
			return "ROTTEN_FLESH";
		case SPIDER_EYE:
			return "SPIDER_EYE";
		default:
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public static ItemStack getFoodItemFromName(String name) {
		// Cannot use switch, is only supported in Java 1.7+

		if (name == null)
			return null;

		name = name.toUpperCase();
		name = name.replace(" ", "_");

		if (name.equals("APPLE")) {
			return new ItemStack(Material.APPLE, 1);
		} else if (name.equals("BAKED_POTATO")) {
			return new ItemStack(Material.BAKED_POTATO, 1);
		} else if (name.equals("BREAD")) {
			return new ItemStack(Material.BREAD, 1);
		} else if (name.equals("CAKE_BLOCK")) {
			return new ItemStack(Material.CAKE_BLOCK, 1);
		} else if (name.equals("CARROT_ITEM")) {
			return new ItemStack(Material.CARROT_ITEM, 1);
		} else if (name.equals("COOKED_CHICKEN")) {
			return new ItemStack(Material.COOKED_CHICKEN, 1);
		} else if (name.equals("COOKED_FISH")) {
			return new ItemStack(Material.COOKED_FISH, 1);
		} else if (name.equals("COOKED_SALMON")) {
			return new ItemStack(Material.COOKED_FISH.getId(), 1, (short) 1);
		} else if (name.equals("COOKED_MUTTON")) {
			return new ItemStack(Material.COOKED_MUTTON, 1);
		} else if (name.equals("GRILLED_PORK")) {
			return new ItemStack(Material.GRILLED_PORK, 1);
		} else if (name.equals("COOKED_RABBIT")) {
			return new ItemStack(Material.COOKED_RABBIT, 1);
		} else if (name.equals("COOKIE")) {
			return new ItemStack(Material.COOKIE, 1);
		} else if (name.equals("GOLDEN_APPLE")) {
			return new ItemStack(Material.GOLDEN_APPLE, 1);
		} else if (name.equals("ENCHANTED_GOLDEN_APPLE")) {
			return new ItemStack(Material.GOLDEN_APPLE.getId(), 1, (short) 1);
		} else if (name.equals("GOLDEN_CARROT")) {
			return new ItemStack(Material.GOLDEN_CARROT, 1);
		} else if (name.equals("MELON")) {
			return new ItemStack(Material.MELON, 1);
		} else if (name.equals("MUSHROOM_SOUP")) {
			return new ItemStack(Material.MUSHROOM_SOUP, 1);
		} else if (name.equals("RABBIT_STEW")) {
			return new ItemStack(Material.RABBIT_STEW, 1);
		} else if (name.equals("RAW_BEEF")) {
			return new ItemStack(Material.RAW_BEEF, 1);
		} else if (name.equals("RAW_CHICKEN")) {
			return new ItemStack(Material.RAW_CHICKEN, 1);
		} else if (name.equals("RAW_FISH")) {
			return new ItemStack(Material.RAW_FISH, 1);
		} else if (name.equals("RAW_SALMON")) {
			return new ItemStack(Material.RAW_FISH.getId(), 1, (short) 1);
		} else if (name.equals("CLOWNFISH")) {
			return new ItemStack(Material.RAW_FISH.getId(), 1, (short) 2);
		} else if (name.equals("PUFFERFISH")) {
			return new ItemStack(Material.RAW_FISH.getId(), 1, (short) 3);
		} else if (name.equals("POISONOUS_POTATO")) {
			return new ItemStack(Material.POISONOUS_POTATO, 1);
		} else if (name.equals("POTATO")) {
			return new ItemStack(Material.POTATO, 1);
		} else if (name.equals("PUMPKIN_PIE")) {
			return new ItemStack(Material.PUMPKIN_PIE, 1);
		} else if (name.equals("MUTTON")) {
			return new ItemStack(Material.MUTTON, 1);
		} else if (name.equals("COOKED_BEEF")) {
			return new ItemStack(Material.COOKED_BEEF, 1);
		} else if (name.equals("RABBIT")) {
			return new ItemStack(Material.RABBIT, 1);
		} else if (name.equals("ROTTEN_FLESH")) {
			return new ItemStack(Material.ROTTEN_FLESH, 1);
		} else if (name.equals("SPIDER_EYE")) {
			return new ItemStack(Material.SPIDER_EYE, 1);
		} else
			return null;
	}
}
