package fr.fanaticstudio.matthis974jump.botsurveillancediscord.matheog974;

import fr.fanaticstudio.matthis974jump.botsurveillancediscord.commandsSystem.Command;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.commandsSystem.Roles;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

public class FaireUneCommande {
    @Command(name = "e", description = "Ajoute de la money a un utulisateur", cost = 0, aliasses = {"user", "casier"}, permissionsLevel = Roles.MODO)
    public void onSaveCommand(Message message, User user, Guild g, String... args) {
        for (String arg :
                args) {
            System.out.println(arg);
        }
    }
}
