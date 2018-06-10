package fr.fanaticstudio.matthis974jump.botsurveillancediscord.commandsSystem;

import fr.fanaticstudio.matthis974jump.botsurveillancediscord.BotObject;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

/**
 * @author By Matthis974Jump and Matheog974
 * @version 1.0-SNAPSHOT
 * Ecouteur d'evenements du bot
 */
public class ListenerCommand implements net.dv8tion.jda.core.hooks.EventListener {
    /**
     * Le gestionaire de commande
     */
    private CommandMap commandMap;

    /**
     * @param bo L'objet du bot
     */
    public ListenerCommand(BotObject bo) {
        commandMap = bo.getCommandMap();
    }

    /**
     * Event listener basique
     */
    @Override
    public void onEvent(Event event) {
        if (event instanceof GuildMessageReceivedEvent) onMessage((GuildMessageReceivedEvent) event);
    }

    /**
     * @param event L'Event permetant l'execution d'une commande via Discord
     */
    private void onMessage(GuildMessageReceivedEvent event) {
        if (Objects.equals(event.getAuthor(), event.getJDA().getSelfUser())) return;

        String message = event.getMessage().getContentRaw();
        if (message.startsWith(commandMap.getTag())) {
            message = message.replaceFirst(commandMap.getTag(), "");
            if (commandMap.commandUser(message, event.getMessage())) {
                event.getMessage().addReaction("\uD83C\uDD97").complete();
            } else {
                event.getMessage().addReaction("\u274C").complete();
            }
            }
        }
    }

