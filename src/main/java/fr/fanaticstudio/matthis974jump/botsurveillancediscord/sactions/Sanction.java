package fr.fanaticstudio.matthis974jump.botsurveillancediscord.sactions;

import net.dv8tion.jda.core.entities.Member;

public interface Sanction {
    void sanctionExecute(Member member);

    void sanctionLeave(Member member);

    void sanctionEnd(Member member);

    void getEntity(Member member);
}
