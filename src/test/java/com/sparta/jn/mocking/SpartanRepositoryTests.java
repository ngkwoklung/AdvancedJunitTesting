package com.sparta.jn.mocking;

import com.sparta.jn.AdvancedJunitTesting.Spartan;
import com.sparta.jn.AdvancedJunitTesting.SpartanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

public class SpartanRepositoryTests {
    private Spartan mockSpartan;
    private Spartan spartan;
    private Spartan spySpartan;

    @BeforeEach
    void init() {
        mockSpartan = Mockito.mock(Spartan.class);

        spartan = new Spartan(1,"Manish", "Java,", LocalDate.now());
        spySpartan = Mockito.spy(spartan); //partial Mock
    }

    @Test
    @DisplayName("Testing PSartan Repositiry Print Method")
    void testingPSartanRepositiryPrintMethod() {
        System.out.println(mockSpartan.getClass());
        SpartanRepository.addSpartan(mockSpartan);
        Mockito.when(mockSpartan.toString()).thenReturn("Found mock Spartan");
        Mockito.doReturn("Found mock Spartan").when(mockSpartan.toString());
        Mockito.doThrow(NullPointerException.class).when(mockSpartan).setId(Mockito.anyInt());
        System.out.println(SpartanRepository.getAllSpartans());
        Assertions.assertEquals("Found mock Spartan" + "\n", SpartanRepository.getAllSpartans());

    }

    @Test
    @DisplayName("Testing Method Order")
    void testingMethodOrder() {
        Mockito.when(mockSpartan.getStartDate())
                .thenReturn(LocalDate.now())
                .thenReturn(LocalDate.MAX);

        System.out.println(mockSpartan.getStartDate());
        System.out.println(mockSpartan.getStartDate());

    }
}
