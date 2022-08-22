package com.sparta.jn.AdvancedJunitTesting;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;

public class HamcrestTests {
    private Spartan spartan;

    @BeforeEach
    void setup() {
        spartan = new Spartan(1, "David", "Data", LocalDate.of(2022, 12,12));

    }

    @Test
    @DisplayName("Check Spartan is called Manish")
    void checkSpartanIsCalledManish() {
        MatcherAssert.assertThat(spartan.getName(), equalTo("Manish"));
    }

    @Test
    @DisplayName("Check that spartan has an id field")
    void checkThatSpartanHasAnIdField() {
        MatcherAssert.assertThat(spartan, hasProperty("id", equalTo(1)));
    }

    @Test
    @DisplayName("Check that courses are the available courses only")
    void checkThatCoursesAreTheAvailableCoursesOnly() {
        ArrayList<String> courseList = new ArrayList<>(Arrays.asList("Java", "C#", "Data", "DevOps", "Cyber-Security"
                , "Business"));
        MatcherAssert.assertThat(spartan.getCourse(), isIn(courseList));

    }

//    @Test
//    @DisplayName("Hamcrest String methods")
//    void hamcrestStringMethods() {
//        MatcherAssert.assertThat((spartan.getName(), Matchers.));
//    }
}
