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

### Using Gradle (build.gradle)

1. Add the JitPack maven repository

```groovy
    maven { url "https://jitpack.io"  }
```

2. Add this to the root of your `settings.gradle`:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

3. Add the dependency to your `build.gradle`

```groovy
dependencies {
    implementation 'com.github.logandhillon:frc-checkmate:v2025.1.0-rc.2'
}
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

For more examples, see [Examples](examples.md).
