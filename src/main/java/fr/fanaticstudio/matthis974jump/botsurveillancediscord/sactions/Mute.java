package fr.fanaticstudio.matthis974jump.botsurveillancediscord.sactions;

import fr.fanaticstudio.matthis974jump.botsurveillancediscord.MainSystem;
import net.dv8tion.jda.core.entities.Member;

public class Mute implements Sanction {
    public String muteRoleID;

    @Override
    public void sanctionExecute(Member member) {
        member.getGuild().getController().addRolesToMember(member, member.getJDA().getRoleById(muteRoleID)).complete();
    }

    @Override
    public void sanctionLeave(Member member) {

    }

    @Override
    public void sanctionEnd(Member member) {
        member.getGuild().getController().removeRolesFromMember(member, member.getJDA().getRoleById(muteRoleID)).complete();
    }

    @Override
    public void getEntity(Member member) {

    }
}
