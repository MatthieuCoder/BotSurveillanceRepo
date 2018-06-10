package fr.fanaticstudio.matthis974jump.botsurveillancediscord.wtf;

import fr.fanaticstudio.matthis974jump.botsurveillancediscord.commandsSystem.Command;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.commandsSystem.Roles;
import net.dv8tion.jda.core.entities.Message;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class MyCommands {
    @Command(name = "rip",description = "Rip quel qu'un !",cost = 100,permissionsLevel = Roles.SIMPLE_USER,aliasses = {"tmort","mort"})
    public void onRipCommand(Message message) throws IOException {
        message.getTextChannel().sendTyping().complete();
        System.out.println(message.getAuthor().getEffectiveAvatarUrl());
        Connection.Response resultImageResponse = Jsoup.connect("http://192.168.1.32/imageGen.php?imgUrl="+message.getAuthor().getEffectiveAvatarUrl())
                .ignoreContentType(true).execute();
        message.getTextChannel().sendFile(resultImageResponse.bodyAsBytes(),"rip.png").complete();
    }


}
