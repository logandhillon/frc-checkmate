package com.logandhillon.frc_checkmate.dev;

import com.logandhillon.frc_checkmate.RobotSystemTest;
import com.logandhillon.frc_checkmate.TestResult;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class SimulatorEntrypoint extends TimedRobot {
    private Command cmd_auton;

    private final RobotContainer container;

    public static void main(String[] args) {
        RobotBase.startRobot(SimulatorEntrypoint::new);
    }

    public static void registerTests() {
        RobotSystemTest.register("Expect a failure", () -> TestResult.fail("Generic failure"));
        RobotSystemTest.register("Expect a pass", TestResult::success);
        RobotSystemTest.register("Expect a pass with msg", () -> TestResult.success("Generic success"));

        RobotSystemTest.register(
                "Random number", () -> Math.random() < 0.5 ? TestResult.success("Passed (50% chance)")
                                                           : TestResult.fail("Failed (50% chance)"));
    }

    public SimulatorEntrypoint() {
        container = new RobotContainer();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void autonomousInit() {
        cmd_auton = container.getAutonomousCommand();
        if (cmd_auton != null) CommandScheduler.getInstance().schedule(cmd_auton);
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        if (cmd_auton != null)
            cmd_auton.cancel();
    }

    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {
    }

    /** This function is called once when the robot is first started up. */
    @Override
    public void simulationInit() {
    }

    /** This function is called periodically whilst in simulation. */
    @Override
    public void simulationPeriodic() {
    }

    private static class RobotContainer {
        public RobotContainer() {
            SimulatorEntrypoint.registerTests();
        }

        public Command getAutonomousCommand() {
            return null;
        }
    }
}
