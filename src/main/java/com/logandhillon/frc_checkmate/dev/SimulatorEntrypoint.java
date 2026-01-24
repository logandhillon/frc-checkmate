package com.logandhillon.frc_checkmate.dev;

import com.logandhillon.frc_checkmate.Checkmate;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class SimulatorEntrypoint extends TimedRobot {
    public static void main(String[] args) {
        RobotBase.startRobot(SimulatorEntrypoint::new);
    }

    public static void registerTests() {
        Checkmate.register("Expect a failure", () -> Checkmate.TestResult.fail("Generic failure"));
        Checkmate.register("Expect a pass", Checkmate.TestResult::success);
        Checkmate.register("Expect a pass with msg", () -> Checkmate.TestResult.success("Generic success"));

        Checkmate.register(
                "Random number", () -> Math.random() < 0.5
                                       ? Checkmate.TestResult.success("Passed (50% chance)")
                                       : Checkmate.TestResult.fail("Failed (50% chance)"));
    }

    public SimulatorEntrypoint() {
        SimulatorEntrypoint.registerTests();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
    }
}
