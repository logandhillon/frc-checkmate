# FRC Checkmate

**FRC Checkmate** is a lightweight, flexible system testing framework for FRC robots, built on top of WPILib.

## Documentation

See the [FRC Checkmate official documentation](docs/index.md) for more.

## Features

- Easily create full system tests
- Integrates with Shuffleboard
- Immediate feedback for test results
- End-to-end robot tests

## Installation

### Using the FRC Checkmate Installer

FRC Checkmate comes with an installer that automatically builds and installs the source code to your FRC robot's code.

To use it, run the following command:

#### macOS/Linux (sh)

```sh
python3 <(curl -sS https://raw.githubusercontent.com/logandhillon/frc-checkmate/main/install.py)
```

#### Windows (PowerShell)

```pwsh
python - <<'PY'
Invoke-Expression (Invoke-WebRequest https://raw.githubusercontent.com/logandhillon/frc-checkmate/main/install.py).Content
PY
```

## Quick Example

```java
RobotSystemTest.register("Gyro calibration", () -> {
    Gyro gyro = new Gyro();
    return gyro.isCalibrated()
        ? TestResult.success("Gyro calibrated successfully")
        : TestResult.fail("Gyro failed to calibrate");
});
```

For more examples, see [Examples](docs/examples.md).
