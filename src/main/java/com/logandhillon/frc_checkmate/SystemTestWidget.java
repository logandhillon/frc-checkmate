package com.logandhillon.frc_checkmate;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.logging.Level;

import com.logandhillon.frc_checkmate.util.Logger;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.Commands;

public class SystemTestWidget {
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
     * @param parent   the parent {@link ShuffleboardLayout} to which this widget
     *                 belongs
     * @param id       a unique identifier or name for the test (displayed on the
     *                 widget)
     * @param runnable a {@link Supplier} that runs the test and returns a
     *                 {@link TestResult}
     *                 indicating whether the test passed or failed, and any message
     *                 to display
     */
    public static void create(ShuffleboardLayout parent, String id, Supplier<TestResult> runnable) {
        ShuffleboardLayout layout = parent.getLayout(id, BuiltInLayouts.kGrid)
                .withSize(3, 1).withProperties(Map.of(
                        "Number of columns", 3,
                        "Label position", "HIDDEN"));

        GenericEntry isOk = layout.add(id + " OK?", true)
                .withSize(1, 1)
                .withWidget(BuiltInWidgets.kBooleanBox)
                .withPosition(1, 0)
                .getEntry();

        GenericEntry msg = layout.add(id + " Output", "")
                .withSize(1, 1)
                .withPosition(2, 0)
                .withWidget(BuiltInWidgets.kTextView)
                .getEntry();

        layout.add(
                "Execute " + id,
                Commands.runOnce(() -> {
                    Logger.log(Level.INFO, "Running test '%s'", id);
                    TestResult result = runnable.get();
                    isOk.setBoolean(result.ok);
                    msg.setString(String.format("%s: %s", result.ok ? "PASS" : "FAIL",
                            Objects.requireNonNullElse(result.message, "no message")));
                })
                        .withName("Execute")
                        .ignoringDisable(false))
                .withSize(1, 1)
                .withPosition(0, 0)
                .withWidget("Command");
    }
}