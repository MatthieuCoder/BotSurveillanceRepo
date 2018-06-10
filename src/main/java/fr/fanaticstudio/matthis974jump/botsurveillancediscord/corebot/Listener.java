package fr.fanaticstudio.matthis974jump.botsurveillancediscord.corebot;

import fr.fanaticstudio.matthis974jump.botsurveillancediscord.BotObject;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.MainSystem;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.commandsSystem.Roles;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.hikarihikaricp.entities.Account;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.hikarihikaricp.entities.Warn;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Listener implements EventListener {

    private final BotObject bot;
    private BotConfig bc;

    public Listener(BotObject bot) {
        bc = bot.getBotConfig();
        this.bot = bot;
    }

    @Override
    public void onEvent(Event event) {
        new Thread(() -> {
            if (event instanceof GuildMessageReceivedEvent) {
                try {
                    onMessage((GuildMessageReceivedEvent) event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (event instanceof GuildMemberJoinEvent) onJoin((GuildMemberJoinEvent) event);
            if (event instanceof GuildMemberLeaveEvent) onLeave((GuildMemberLeaveEvent) event);
        }).start();

    }

    private void onJoin(GuildMemberJoinEvent event) {
        event.getGuild().getController().addRolesToMember(event.getMember(), event.getGuild().getRoleById("430772734952472581")).complete();

    }

    private void onLeave(GuildMemberLeaveEvent event) {
        bot.getBot().getTextChannelById("445948998650560513").sendMessage(event.getMember().getEffectiveName() + " a quitté le serveur :(").complete();
    }

    private void onMessage(GuildMessageReceivedEvent event) throws Exception {
        if (!(Roles.getUserLevel(event.getMember()).getLevel() > Roles.RESP_ADMIN.getLevel())) {
            String message = event.getMessage().getContentRaw();
            message = message.toLowerCase();
            message = message.replace(" ","");
            Map<String, Integer> worldsExplicit = new HashMap<>();
            for (String s :
                    bc.disallowedWords) {
                while (message.contains(s)) {

                    if (!worldsExplicit.containsKey(s)) worldsExplicit.put(s, 0);
                    worldsExplicit.replace(s, worldsExplicit.get(s) + 1);
                    message = message.replaceFirst(s, "");

                }
            }
            if (worldsExplicit.size() != 0) {
                List<Warn> warns = new ArrayList<>();
                EmbedBuilder emb = new EmbedBuilder();
                emb.setTitle("Vous avez ete warn !");
                emb.setDescription(event.getAuthor().getAsMention() + " a ete warn !");
                worldsExplicit.forEach((k, v) -> warns.add(new Warn("Sending" + v, event.getJDA().getSelfUser().getId())));

                worldsExplicit.forEach((k, v) -> emb.addField("Mot interdit : " + k, "*" + v + " infraction(s)*", true));
                event.getMessage().delete().reason("Explicit").queue();
                event.getMessage().getTextChannel().sendMessage(emb.build()).complete().delete().completeAfter(2, TimeUnit.SECONDS);

            }
        }
    }
}
