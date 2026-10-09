
import javafx.application.Application;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void mainExtendsJavaFXApplication() {
        assertTrue(Application.class.isAssignableFrom(Main.class));
    }

    @Test
    void mainMethodExists() throws NoSuchMethodException {
        Method mainMethod =
                Main.class.getMethod("main", String[].class);

        assertTrue(
                java.lang.reflect.Modifier.isStatic(
                        mainMethod.getModifiers()
                )
        );
    }

    @Test
    void startMethodExists() throws NoSuchMethodException {
        assertNotNull(
                Main.class.getMethod(
                        "start",
                        javafx.stage.Stage.class
                )
        );
    }
}
