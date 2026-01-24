package com.logandhillon.frc_checkmate;

public class TestResult {
    protected final boolean ok;
    protected final String message;

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
     * @return a successful {@link TestResult}
     */
    public static TestResult success(String message) {
        return new TestResult(true, message);
    }

    /**
     * Creates a failing test result with a reason message.
     *
     * @param message the reason for the test failure
     * @return a failed {@link TestResult}
     */
    public static TestResult fail(String message) {
        return new TestResult(false, message);
    }
}