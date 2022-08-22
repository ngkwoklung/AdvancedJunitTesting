package com.sparta.jn.AdvancedJunitTesting;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.params.ParameterizedTest;

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
        spartan = new Spartan(11, "Manish", "Java", LocalDate.of(2020, 12, 12));
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
        Assumptions.assumeTrue(spartan.getId() == 12); // if failed test will be ignored
        Assertions.assertTrue(spartan.getId() >= 0);

    }

    @Test
    @DisplayName("Check if the date joined ")
    void checkIfTheDateJoined() {
        Assertions.assertTrue(spartan.getStartDate().isBefore(LocalDate.now()));
    }

    @Test
    @DisplayName("Test that id is between 10 and 10_000 inclusive")
    void testThatIdIsBetween10And10000Inclusive() {
        Assertions.assertTrue(spartan.getId() >=10 && spartan.getId() <= 10_000);
    }

    @Nested
    @DisplayName("Nested Tests")
    class NestedTest {

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
