package fr.fanaticstudio.matthis974jump.botsurveillancediscord.commandsSystem;

import fr.fanaticstudio.matthis974jump.botsurveillancediscord.BotObject;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.matheog974.FaireUneCommande;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.matheog974.MyCommands;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.awt.*;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.List;

/**
 * @author By Matthis974Jump and Matheog974
 * @version 1.0-SNAPSHOT
 * Le gestionaire de commandes
 */
public final class CommandMap {


    /**
     * L'eventWaiter
     *
     * @see fr.fanaticstudio.matthis974jump.botsurveillancediscord.commandsSystem.EventListener
     */
    public final EventListener eventWaiter;
    /**
     * Le prefix des commandes
     */
    private final String tag;
    /**
     * La map contenant les SimpleCommandes representant les commandes
     *
     * @see SimpleCommand
     */
    private final Map<String, SimpleCommand> commands = new HashMap<>();
    /**
     * L'objet represenatant le bot
     *
     * @see BotObject
     */
    private final BotObject bot;

    /**
     * Le constructeur du gestionaire de commandes
     *
     * @param botDiscord L'objet representant le bot
     */
    public CommandMap(BotObject botDiscord) {
        this.bot = botDiscord;
        tag = botDiscord.getBotConfig().tag;
        registerCommands(this, new MyCommands(), new FaireUneCommande(),new fr.fanaticstudio.matthis974jump.botsurveillancediscord.wtf.MyCommands());
        eventWaiter = new EventListener();

    }

    /**
     * Commande de base
     *
     * @param user    Utulisateur utulisant la commande
     * @param channel Le channer ou la commande est executee
     */
    @Command(name = "help", description = "Affiche l'aide")
    private void help(User user, MessageChannel channel, Guild guild, JDA jda) {
        EmbedBuilder emb = new EmbedBuilder();
        emb.setTitle("Aide - " + jda.getSelfUser().getName()).setColor(Color.RED);
        emb.setAuthor(bot.getBotConfig().name + " by Matthis974Jump").setDescription("Aide pour les commandes de " + jda.getSelfUser().getName())
                .setFooter("Bot Priv\u00e9 Alive Creations [STUDIO]", jda.getSelfUser().getAvatarUrl()).setTimestamp(Calendar.getInstance().toInstant());
        emb.addField("General information", "Prefix : " + tag + " \r Money system : A Venir", false);
        this.commands.forEach((k, v) -> {
            if (v.getPermissionLevel().getLevel() <= Roles.getUserLevel(guild.getMember(user)).getLevel()) {
                emb.addField("__*** " + v.getName() + " ***__",
                        "**Prix :** : " + v.getCost() + "\r" +
                                "**Description :** " + v.getDescription()
                        , false);
            }
        });

        user.openPrivateChannel().complete().sendMessage(emb.build()).queue();
        channel.sendMessage(user.getAsMention() + " je t'ai envoy\u00e9 l'aide en priv\u00e9 !").complete();

    }

    @Command(name = "status", description = "Change le jeu du bot !", permissionsLevel = Roles.ADMIN)
    private void changeStatus(EventListener eventListener, User user, MessageChannel channel) {
        channel.sendMessage("Ok je change mon jeu mais je mais quoi ? ").complete();
        eventListener.waitingFor(ev1 -> {
            if (ev1 instanceof GuildMessageReceivedEvent) {
                GuildMessageReceivedEvent gm = (GuildMessageReceivedEvent) ev1;
                return gm.getAuthor().getId().equalsIgnoreCase(user.getId());
            }
            return false;
        }, event -> {
            GuildMessageReceivedEvent guildMessageReceivedEvent = (GuildMessageReceivedEvent) event;
            bot.getBot().getPresence().setGame(Game.watching(guildMessageReceivedEvent.getMessage().getContentRaw()));
            channel.sendMessage("Texte chang\u00e9 en " + guildMessageReceivedEvent.getMessage().getContentRaw()).complete();

        });

    }

    @Command(name = "spamAll", description = "Change le jeu du bot !", permissionsLevel = Roles.ADMIN)
    private void spamAll(EventListener eventListener, User user, MessageChannel channel, Guild g) {
        channel.sendMessage("Je spam quoi ? ").complete();
        eventListener.waitingFor(ev1 -> {
            if (ev1 instanceof GuildMessageReceivedEvent) {
                GuildMessageReceivedEvent gm = (GuildMessageReceivedEvent) ev1;
                return gm.getAuthor().getId().equalsIgnoreCase(user.getId());
            }
            return false;
        }, event -> {

            for (Member m :
                    g.getMembers()) {
                if (!m.getUser().isBot())
                    m.getUser().openPrivateChannel().complete().sendMessage(((GuildMessageReceivedEvent) event).getMessage().getContentRaw()).complete();
            }

        });

    }
    /**
     * @return Le tag
     */
    String getTag() {
        return tag;
    }

    /**
     * Enregistere plusieures commandes
     *
     * @param objects Les commandes a enregistrer
     */
    private void registerCommands(Object... objects) {
        for (Object object : objects) {
            registerCommand(object);
        }
    }

    /**
     * Enregistrer un commande
     *
     * @param object LA commande a enregistrer
     */
    private void registerCommand(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);
                method.setAccessible(true);
                SimpleCommand simpleCommand = new SimpleCommand(command.name(), command.description(), object, method, command.aliasses(), command.permissionsLevel(), command.cost());
                commands.put(command.name(), simpleCommand);
            }
        }
    }

    /**
     * Quand une commande est executee par un utulisateur
     *
     * @param command Commande executee
     * @param message Message envoye
     * @return Si la commande est executee
     */
    boolean commandUser(String command, Message message) {
        try {
            String[] commandSplit = command.split(" ");
            SimpleCommand[] object = getCommand(commandSplit[0]);
            if (object.length == 0) throw new Exception("INTROUVABLE (404)");
            String[] args = new String[commandSplit.length - 1];
            Roles r = Roles.getUserLevel(message.getMember());
            if (object[0].getPermissionLevel().getLevel() <= r.getLevel()) {
                execute(object[0], commandSplit[0], args, message);
            return true;
            } else {
                message.getTextChannel().sendMessage("Erreur de commande : ** Pas les permissions n\u00e9cessaires** Message : **Minimum acces level " + object[0].getPermissionLevel() + "**" +
                        " Votre level : **" + r + "**").queue();
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            message.getTextChannel().sendMessage("Erreur de commande : **" + exception.getClass().getSimpleName() + "** Message : **" + exception.getMessage() + "**").queue();
            return false;
        }
    }

    /**
     * Recherche une commande dans la liste
     *
     * @return La ou les commandes recherchees
     */
    private SimpleCommand[] getCommand(String commandSplit) {
        List<SimpleCommand> commands = new ArrayList<>();
        this.commands.forEach((k, v) -> {
            if (k.equalsIgnoreCase(commandSplit)) {
                commands.add(v);
            } else {
                for (String s :
                        v.getAliasses()) {
                    if (s.equalsIgnoreCase(commandSplit)) {
                        commands.add(v);
                    }
                }
            }
        });
        return commands.toArray(new SimpleCommand[]{});
    }

    /**
     * Execute la methode pour executer la commande
     *
     * @param simpleCommand La commande a executer
     * @param command       La commande executee
     * @param args          Les arguments de la commande
     * @param message       Le message
     * @throws Exception Si une erreur s'est produite
     */
    private void execute(SimpleCommand simpleCommand, String command, String[] args, Message message) throws Exception {
        Parameter[] parameters = simpleCommand.getMethod().getParameters();
        Object[] objects = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].getType() == String[].class) objects[i] = args;
            else if (parameters[i].getType() == User.class) objects[i] = message == null ? null : message.getAuthor();
            else if (parameters[i].getType() == TextChannel.class)
                objects[i] = message == null ? null : message.getTextChannel();
            else if (parameters[i].getType() == PrivateChannel.class)
                objects[i] = message == null ? null : message.getPrivateChannel();
            else if (parameters[i].getType() == Guild.class) objects[i] = message == null ? null : message.getGuild();
            else if (parameters[i].getType() == String.class) objects[i] = command;
            else if (parameters[i].getType() == Message.class) objects[i] = message;
            else if (parameters[i].getType() == JDA.class) objects[i] = bot.getBot();
            else if (parameters[i].getType() == MessageChannel.class)
                objects[i] = message == null ? null : message.getChannel();
            else if (parameters[i].getType() == EventListener.class) objects[i] = this.eventWaiter;
        }
        simpleCommand.getMethod().invoke(simpleCommand.getObject(), objects);
    }
}
