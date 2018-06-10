package fr.fanaticstudio.matthis974jump.botsurveillancediscord.commandsSystem;

import net.dv8tion.jda.core.events.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventListener implements net.dv8tion.jda.core.hooks.EventListener {
    private Map<CheckInterface, Execute> waitingFor = new HashMap<>();

    @Override
    public void onEvent(Event event) {
        List<CheckInterface> toDelete = new ArrayList<>();
        for (Map.Entry<CheckInterface, Execute> dd :
                waitingFor.entrySet()) {
            if (dd.getKey().checkIfIs(event)) {
                try {
                    dd.getValue().run(event);
                    toDelete.add(dd.getKey());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        for (CheckInterface Delete : toDelete) waitingFor.remove(Delete);
    }

    public void waitingFor(CheckInterface checkInterface, Execute execute) {
        this.waitingFor.put(checkInterface, execute);
    }
}
