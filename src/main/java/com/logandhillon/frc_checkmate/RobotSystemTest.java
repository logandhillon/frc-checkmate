package com.logandhillon.frc_checkmate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Level;

import com.logandhillon.frc_checkmate.util.Logger;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/**
 * <h3>Framework to create robot system tests.</h3>
 *
 * Such tests can be run to ensure proper functionality
 * of the component it is designed for.
 *
 * @apiNote Do not instantiate nor extend this class.
 * @author logandhillon.com
 */
public final class RobotSystemTest {
    private static final ShuffleboardTab TAB = Shuffleboard.getTab("System Tests");
    private static final List<String> TESTS = new ArrayList<>();
    private static final ShuffleboardLayout WIDGETS = TAB
            .getLayout("System Tests", BuiltInLayouts.kList)
            .withSize(3, 3)
            .withProperties(Map.of("Label position", "TOP"));

    /**
     * Do not create instances of this class
     */
    private RobotSystemTest() {
    }

    /**
     * Creates a new robot test that can be run via the debug tab on shuffleboard.
     * 
     * @param name     Unique test name
     * @param runnable Test function. Returns: {@link TestResult}
     */
    public static void register(String name, Supplier<TestResult> runnable) {
        if (name.isBlank())
            throw new IllegalArgumentException("Cannot register an unnamed test.");

        String stack = Thread.currentThread().getStackTrace()[2].getClassName();
        name = (stack.substring(stack.lastIndexOf(".") + 1) + ":" + name).replaceAll(" ", "_");
        Logger.log(Level.INFO, "Registering new test '%s'", name);

        if (TESTS.contains(name))
            throw new IllegalArgumentException(String.format(
                    "RobotSystemTest with name %s already exists! (#%s)", name, TESTS.indexOf(name)));

        SystemTestWidget.create(WIDGETS, name, runnable);
        TESTS.add(name);
    }
}
