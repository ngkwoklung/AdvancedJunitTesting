package com.sparta.jn.AdvancedJunitTesting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.NullString;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class ParameterizedTests {
    @ParameterizedTest(name = "{index} of {argumentsWithNames}")
    @ValueSource(ints = {1,20,30,5,7,15})
    @DisplayName("Check for int higher than 10")
    void checkForIntHigherThan10(int num) {
        Assertions.assertTrue(num > 10);

    }

    @ParameterizedTest()
    @CsvSource({"David", "Manish"})
//    @CsvFileSource(resources = "/names.csv")
    @NullAndEmptySource
    @DisplayName("Using CSV source for test")
    void runCSVTests(String name) {
        Assertions.assertEquals(5, name.length());
    }

    @ParameterizedTest()
    @MethodSource("sourceMethod")
    @DisplayName("Using a method source")
    void usingAMethodSource() {
        Assertions.assertEquals(1,1);

    }

    public static Stream<Arguments> sourceMethod() {
        return Stream.of(
                Arguments.of(1, "Manish"),
                Arguments.of(2, "David"));

    }

    @ParameterizedTest()
    @ValueSource(ints = {1,4,5,7,8,10})
    @DisplayName("Testing for exceptions")
    void testingForExceptions(int number) {
        ArrayList<Integer> numberList = new ArrayList<>(Arrays.asList(1,2,3));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> numberList.get(number));
    }
//    @Tag("courseCheck")
//    @ParameterizedTest()
//    @ValueSource(strings = {"Java", "C#", "DevOps", "Cyber-Security", "Business"})
//    @DisplayName("Testing course is only the ones available")
//    void testingCourseIsOnlyTheOnesAvailable(String course) {
//        Assertions.assertTrue();
//    }

    @ParameterizedTest
    @CsvSource({"Java", "C#", "Data", "DevOps", "Cyber-Security", "Business"})
    @DisplayName("Test that course are correct")
    void testThatCourseAreCorrect(String courses) {
        Assertions.assertTrue(courses.contains("Java"));
//        Assertions.assertTrue(courses.contains("C#"));
//        Assertions.assertTrue(courses.contains("Data"));
//        Assertions.assertTrue(courses.contains("DevOps"));
//        Assertions.assertTrue(courses.contains("Cyber-Security"));
//        Assertions.assertTrue(courses.contains("Business"));
    }

//    @ParameterizedTest()
//    @CsvFileSource(resources = "/courses.csv")
//    @DisplayName("Check if course is one of the following")
//    void checkIfCourseIsOneOfTheFollowing(String course) {
//        Assertions.assertTrue(course.contains("Java"));
//    }

}
