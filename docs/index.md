# FRC Checkmate

**FRC Checkmate** is a lightweight, flexible system testing framework for FRC robots, built on top of WPILib.

It provides a consistent way to verify subsystems, sensors, and robot logic both on the robot and in simulation.

FRC Checkmate allows teams to define repeatable **system-level tests** for critical robot functions.

Tests can be executed automatically or manually via Shuffleboard widgets, and each test provides a clear **pass/fail**
result with an optional message.

The framework is designed to:

- Catch hardware or configuration issues **before** matches.
- Standardize subsystem testing across codebases.
- Allow quick diagnosis through **on-dashboard feedback**.

## Features

- Easily create full system tests
- Integrates with Shuffleboard
- Immediate feedback for test results
- End-to-end robot tests

## Installation

See the [README's installation section](/README.md#installation) for details on this.

## Quick Example

```java
void main() {
    // register gyro calibration test
    RobotSystemTest.register(
            "Gyro calibration", () -> {
                Gyro gyro = new Gyro();
                return gyro.isCalibrated()
                       ? TestResult.success("Gyro calibrated successfully")
                       : TestResult.fail("Gyro failed to calibrate");
            });
}
```

For more examples, see [Examples](examples.md).
