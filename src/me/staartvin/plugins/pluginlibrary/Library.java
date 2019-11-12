package me.staartvin.plugins.pluginlibrary;

import me.staartvin.plugins.pluginlibrary.hooks.*;

/**
 * This class holds all libraries PluginLibrary has.
 * <p>
 * Date created: 14:12:35 12 aug. 2015
 *
 * @author Staartvin
 */
public enum Library {

    AUTORANK("Autorank", new AutorankHook(), "Staartvin"),
    MCMMO("mcMMO", new McMMOHook(), "t00thpick1"),
    FACTIONS("Factions", new FactionsHook(), "Cayorion"),
    ONTIME("OnTime", new OnTimeHook(), "Edge209"),
    AFKTERMINATOR("afkTerminator", new AFKTerminatorHook(), "Edge209"),
    ROYALCOMMANDS("RoyalCommands", new RoyalCommandsHook(), "WizardCM"),
    ULTIMATECORE("UltimateCore", new UltimateCoreHook(), "Bammerbom"),
    STATZ("Statz", new StatzHook(), "Staartvin"),
    ACIDISLAND("AcidIsland", new AcidIslandHook(), "tastybento"),
    ADVANCEDACHIEVEMENTS("AdvancedAchievements", new AdvancedAchievementsHook(), "DarkPyves"),
    ASKYBLOCK("ASkyBlock", new ASkyBlockHook(), "tastybento"),
    BATTLELEVELS("BattleLevels", new BattleLevelsHook(), "RobiRami"),
    GRIEFPREVENTION("GriefPrevention", new GriefPreventionHook(), "RoboMWM"),
    JOBS("Jobs", new JobsHook(), "Zrips"),
    RPGME("RPGme", new RPGmeHook(), "Flamedek"),
    USKYBLOCK("uSkyBlock", new uSkyBlockHook(), "R4zorax"),
    VAULT("Vault", new VaultHook(), "Kainzo"),
    WORLDGUARD("WorldGuard", new WorldGuardHook(), "sk89q"),
    ESSENTIALSX("Essentials", "EssentialsX", new EssentialsXHook(), "drtshock"),
    QUESTS("Quests", new QuestsHook(), "PikaMug"),
    STATS("Stats", new StatsHook(), "Lolmewn"),
    QUESTS_ALTERNATIVE("Quests", new QuestsAlternative(), "LMBishop"),
    SAVAGE_FACTIONS("Factions", "SavageFactions", new SavageFactionsHook(), "ProSavage"),
    PLAYERPOINTS("PlayerPoints", new PlayerPointsHook(), "Blackixx");

    private String internalPluginName, humanPluginName, authorName;
    private LibraryHook hook;

    Library(String pluginName, String humanPluginName, LibraryHook hook, String authorName) {
        this.internalPluginName = pluginName;
        this.humanPluginName = humanPluginName;
        this.hook = hook;
        this.authorName = authorName;
    }

    Library(String pluginName, LibraryHook hook, String authorName) {
        this.internalPluginName = pluginName;
        this.hook = hook;
        this.authorName = authorName;
    }

    /**
     * Get a library programmaticaly. This method is the same as valueOf(), but is case-insensitive.
     *
     * @param value name of the library
     *
     * @return the Library objec.
     *
     * @throws IllegalArgumentException When no library with the given name was found.
     */
    public static Library getEnum(String value) throws IllegalArgumentException {
        for (Library e : Library.values()) {
            if (e.getInternalPluginName().equalsIgnoreCase(value))
                return e;
        }

        throw new IllegalArgumentException("There is no library called '" + value + "'!");
    }

    public String getInternalPluginName() {
        return internalPluginName;
    }

    public LibraryHook getHook() {
        return hook;
    }

    public String getAuthor() {
        return authorName;
    }

    public String getHumanPluginName() {
        if (humanPluginName == null) {
            return internalPluginName;
        }

        return humanPluginName;
    }
}
