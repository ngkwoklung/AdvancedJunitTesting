package com.sparta.jn.AdvancedJunitTesting;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIf;

import java.time.LocalDate;

public class SpartanTests {
    //Hooks - Block of repeated code
    //BeforeAll, BeforeEach, AfterAll, AfterEach - setup / tearDown
    private Spartan spartan;

    @BeforeAll
    static void initAll(TestInfo testInfo){
        System.out.println(testInfo.getTestClass() + " has started");
    }

    @BeforeEach
    @DisabledIf(value = "checkForSpartan",
    disabledReason = "Code has not been completed yet")
    void init(TestInfo testInfo) {
        spartan = new Spartan(1, "Manish", "Java", LocalDate.of(2022, 12, 12));
        System.out.println(testInfo.getDisplayName() + " executing");
    }

    boolean checkForSpartan() {
        return true;
    }

    @Test
    @Disabled("Waiting for someone to finish his method")
    @DisplayName("Check that the Spartan is Manish executing")
    void checkThatTheSpartanIsManishExecuting() {
        Assertions.assertEquals("Manish", spartan.getName());
    }

    @Tag("valueCheck")
    @RepeatedTest(value = 2, name = RepeatedTest.LONG_DISPLAY_NAME) //test when things is changing, live environment name = "{displayName} is running test {currentRepetition}
    @DisplayName("Check that id is a positive number")
    void checkThatIdIsAPositiveNumber() {
        Assertions.assertTrue(spartan.getId() >= 0);

    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName() + " completed");
    }

    @AfterAll
    static void tearDownAll(TestInfo testInfo) {
        System.out.println(testInfo.getTestClass() + " has finished");
    }
}
