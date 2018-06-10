package fr.fanaticstudio.matthis974jump.botsurveillancediscord.matheog974;


import fr.fanaticstudio.matthis974jump.botsurveillancediscord.commandsSystem.Command;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.commandsSystem.EventListener;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.commandsSystem.Roles;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;


/**
 * @author Matheog974
 * @version 1.0-SNAPSHOT
 * Les Commandes de matheog974 :)
 */
public class MyCommands {

    @Command(name = "createNews", description = "Creer une news", permissionsLevel = Roles.RESP_ADMIN)
    public void newsCommand(EventListener eventListener, TextChannel textChannel, User user) {
        textChannel.sendMessage("**Setup creation news !** Entrez le nom de la news :").complete();

        eventListener.waitingFor(ev1 -> {
            if (ev1 instanceof GuildMessageReceivedEvent) {
                GuildMessageReceivedEvent gm = (GuildMessageReceivedEvent) ev1;
                return gm.getAuthor().getId().equalsIgnoreCase(user.getId());
            }
            return false;
        }, event1 -> {
            String nameNews = ((GuildMessageReceivedEvent) event1).getMessage().getContentRaw();
            textChannel.sendMessage("**Setup creation news !** Entrez le contenu de la news :").complete();
            eventListener.waitingFor(ev2 -> {
                if (ev2 instanceof GuildMessageReceivedEvent) {
                    GuildMessageReceivedEvent gm = (GuildMessageReceivedEvent) ev2;
                    return gm.getAuthor().getId().equalsIgnoreCase(user.getId());
                }
                return false;
            }, event2 -> {
                String content = ((GuildMessageReceivedEvent) event2).getMessage().getContentRaw();
                event2.getJDA().getTextChannelById("438366233654722562").sendMessage(new EmbedBuilder().addField("" + nameNews + "", content, false)
                        .setFooter(user.getName() + "#" + user.getDiscriminator(), user.getEffectiveAvatarUrl()).addBlankField(false).addBlankField(false).addBlankField(false).setColor(Color.green).build()
                ).complete();
                textChannel.sendMessage("**Setup creation news !** Contenu Envoyé !").complete();

            });
        });
    }


}
