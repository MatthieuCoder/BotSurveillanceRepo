package fr.fanaticstudio.matthis974jump.botsurveillancediscord.corebot;

public interface InitialisableObject {
    void postInit();

    void init() throws Exception;

    boolean isLoaded();
}
