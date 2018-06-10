package fr.fanaticstudio.matthis974jump.botsurveillancediscord;


import fr.fanaticstudio.matthis974jump.botsurveillancediscord.commandsSystem.CommandMap;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.commandsSystem.ListenerCommand;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.commandsSystem.Roles;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.corebot.BotConfig;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.corebot.InitialisableObject;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.corebot.Listener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

/**
 * @author By Matthis974Jump and Matheog974
 * @version 1.0-SNAPSHOT
 * Luncher of the bot(s)
 */
public class BotObject implements InitialisableObject, Runnable {

    /**
     * La configuration du bot
     *
     * @see BotConfig
     */
    private final BotConfig bc;
    /**
     * Le logger du bot selon son nom
     *
     * @see Logger
     */
    private Logger botLogger;
    /**
     * Le constructeur de JDA https://github.com/DV8FromTheWorld/JDA
     *
     * @see JDABuilder
     */
    private JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT);
    /**
     * <a href="https://github.com/DV8FromTheWorld/JDA">JDA, lib de connection a Discord </a>
     *
     * @see JDA
     */
    private JDA bot = null;
    /**
     * Le gestionaire de commandes
     *
     * @see CommandMap
     */
    private CommandMap commandMap;
    /**
     * @param bc La configuration des bots
     * @see MainSystem
     * Sert a l'instanciation d'un bot, represente un bot, Constructeur
     */
    BotObject(BotConfig bc) {
        this.bc = bc;
    }

    /**
     * Sert a l'post-initialisation du bot ( construction du bot et build de son identitee )
     */
    @Override
    public void postInit() {

        botLogger = LoggerFactory.getLogger(bc.name + " Log");
        jdaBuilder.setToken(bc.token).setGame(Game.watching(getBotConfig().game));
        jdaBuilder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        commandMap = new CommandMap(this);
        jdaBuilder.addEventListener(commandMap.eventWaiter);
        jdaBuilder.addEventListener(new Listener(this));
        jdaBuilder.addEventListener(new ListenerCommand(this));

        Roles.SIMPLE_USER.setRoleId("430772734952472581");
        Roles.USER_VIP.setRoleId("447449389141852160");
        Roles.USER_FRIEND.setRoleId("447430245029511168");
        Roles.USER_DONNATOR.setRoleId("447426124868354068");
        Roles.MODO.setRoleId("447449309701734402");
        Roles.RESP_MODO.setRoleId("447449234770362378");
        Roles.ADMIN.setRoleId("447449156487741450");
        Roles.RESP_ADMIN.setRoleId("447449039504670720");
        Roles.FONDATEUR.setRoleId("430740820690468864");
    }

    /**
     * @return String retourne le nom du bot selon le fichier lde configuration
     * @see BotConfig
     * Sert a retourner le nom du bot
     */
    String getName() {
        return bc.name;
    }


    /**
     * Sert a demarer le bot et a entamer une connection avec l'api Discord
     */
    @Override
    public void run() {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws InterruptedException Si interrompu
     * @throws LoginException       Si erreur de login
     *                              Sert a actionner la connection a Discord,
     *                              actionne par la methode run
     */
    @Override
    public void init() throws Exception {
        bot = jdaBuilder.buildBlocking();
        bot.getGuilds().forEach((guild) -> {
            guild.getMembers().forEach((m) -> {
            });
        });
    }

    @Override
    public boolean isLoaded() {
        return bot != null;
    }

    /**
     * Stoppe la connection entre le programme et Discord
     */
    void stop() {
        bot.shutdownNow();
    }

    /**
     * @return Donne le logger du bot
     */
    public Logger getBotLogger() {
        return botLogger;
    }

    /**
     * @return La configuration du bot
     */
    public BotConfig getBotConfig() {
        return bc;
    }

    /**
     * @return Retourne l'instance de JDA
     */
    public JDA getBot() {
        return bot;
    }

    /**
     * @return Retourne le gestionaire de commandes
     */
    public CommandMap getCommandMap() {
        return commandMap;
    }
}
