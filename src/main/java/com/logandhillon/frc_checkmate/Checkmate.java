package com.logandhillon.frc_checkmate;

import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj2.command.Commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.logging.Level;

/**
 * <h3>Framework to create robot system tests.</h3>
 * <p>
 * Such tests can be run to ensure proper functionality of the component it is designed for.
 *
 * @author logandhillon.com
 * @apiNote Do not instantiate nor extend this class.
 * @version 2026.0.0-rc.1
 */
public final class Checkmate {
    private static final ShuffleboardTab    TAB     = Shuffleboard.getTab("System Tests");
    private static final List<String>       TESTS   = new ArrayList<>();
    private static final ShuffleboardLayout WIDGETS = TAB
            .getLayout("System Tests", BuiltInLayouts.kList)
            .withSize(3, 3)
            .withProperties(Map.of("Label position", "TOP"));

    /**
     * Do not create instances of this class
     */
    private Checkmate() {}

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
        log(Level.INFO, "Registering new test '%s'", name);

        if (TESTS.contains(name))
            throw new IllegalArgumentException(String.format(
                    "RobotSystemTest with name %s already exists! (#%s)", name, TESTS.indexOf(name)));

        create(WIDGETS, name, runnable);
        TESTS.add(name);
    }

    /**
     * Creates and adds a test widget to the specified Shuffleboard layout.
     * <p>
     * This widget provides:
     * <ul>
     * <li>A boolean box showing test success or failure.</li>
     * <li>A text view displaying the test message.</li>
     * <li>A button to execute the provided test function.</li>
     * </ul>
     *
     * <p>
     * When executed, this widget will:
     * <ol>
     * <li>Run the supplied test function.</li>
     * <li>Display the result status (PASS/FAIL) in a boolean
     * box.</li>
     * <li>Show the result message in a text field.</li>
     * </ol>
     *
     * @param parent   the parent {@link ShuffleboardLayout} to which this widget belongs
     * @param id       a unique identifier or name for the test (displayed on the widget)
     * @param runnable a {@link Supplier} that runs the test and returns a {@link TestResult} indicating whether the
     *                 test passed or failed, and any message to display
     */
    public static void create(ShuffleboardLayout parent, String id, Supplier<TestResult> runnable) {
        ShuffleboardLayout layout = parent.getLayout(id, BuiltInLayouts.kGrid)
                                          .withSize(3, 1).withProperties(Map.of(
                        "Number of columns", 3,
                        "Label position", "HIDDEN"));

        try (SimpleWidget isOk = layout.add(id + " OK?", true)
                                       .withSize(1, 1)
                                       .withWidget(BuiltInWidgets.kBooleanBox)
                                       .withPosition(1, 0);
             SimpleWidget msg = layout.add(id + " Output", "")
                                      .withSize(1, 1)
                                      .withPosition(2, 0)
                                      .withWidget(BuiltInWidgets.kTextView)
        ) {
            layout.add(
                          "Execute " + id,
                          Commands.runOnce(() -> {
                                      log(Level.INFO, "Running test '%s'", id);
                                      TestResult result = runnable.get();
                                      isOk.getEntry().setBoolean(result.ok);
                                      msg.getEntry().setString(String.format(
                                              "%s: %s", result.ok ? "PASS" : "FAIL",
                                              Objects.requireNonNullElse(result.message, "no message"
                                              )));
                                  })
                                  .withName("Execute")
                                  .ignoringDisable(false))
                  .withSize(1, 1)
                  .withPosition(0, 0)
                  .withWidget("Command");
        }
    }

    public static void log(Level level, String format, Object... args) {
        System.out.printf("[Checkmate] (" + level.getName() + ") " + format + "%n", args);
    }

    public static final class TestResult {
        private final boolean ok;
        private final String  message;

        private TestResult(boolean ok, String message) {
            this.ok = ok;
            this.message = message;
        }

        /**
         * Creates a passing test result with no message.
         *
         * @return a successful {@link TestResult}
         */
        public static TestResult success() {
            return new TestResult(true, null);
        }

        /**
         * Creates a passing test result with an optional message.
         *
         * @param message a note or additional information about the test
         *
         * @return a successful {@link TestResult}
         */
        public static TestResult success(String message) {
            return new TestResult(true, message);
        }

        /**
         * Creates a failing test result with a reason message.
         *
         * @param message the reason for the test failure
         *
         * @return a failed {@link TestResult}
         */
        public static TestResult fail(String message) {
            return new TestResult(false, message);
        }
    }
}
