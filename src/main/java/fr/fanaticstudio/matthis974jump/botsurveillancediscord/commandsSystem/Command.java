package fr.fanaticstudio.matthis974jump.botsurveillancediscord.commandsSystem;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Command {
    String name();

    String description() default "Sans d\u00e9scription.";
    int cost() default 10;

    Roles permissionsLevel() default Roles.SIMPLE_USER;

    String[] aliasses() default {};
}
