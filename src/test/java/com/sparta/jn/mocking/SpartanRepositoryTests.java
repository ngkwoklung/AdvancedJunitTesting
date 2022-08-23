package com.sparta.jn.mocking;

import com.sparta.jn.AdvancedJunitTesting.Spartan;
import com.sparta.jn.AdvancedJunitTesting.SpartanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
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
        Mockito.when(mockSpartan.toString()).thenReturn("Found mock Spartan"); //use for mocks
        Mockito.doReturn("Found mock Spartan").when(mockSpartan.toString()); //use for spies
        Mockito.doThrow(NullPointerException.class).when(mockSpartan).setId(Mockito.anyInt());
        System.out.println(SpartanRepository.getAllSpartans());
//        Assertions.assertEquals("Found mock Spartan" + "\n", SpartanRepository.getAllSpartans());
//        Mockito.verify(mockSpartan, Mockito.times(1)).toString();

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

    @Test
    @DisplayName("Check that the getId method is called on our mock")
    void checkThatTheGetIdMethodIsCalledOnOurMock() {
        SpartanRepository.addSpartan(mockSpartan);
        boolean present = SpartanRepository.findSpartan(1).isPresent();
        Mockito.verify(mockSpartan, Mockito.times(1)).getId(); //checks getId being called
        // Dont mixed assertion and verify
        //Assertions - when real code is working
    }

    @Test
    @DisplayName("Check that spartan methods are called in the right order")
    void checkThatSpartanMethodsAreCalledInTheRightOrder() {
        mockSpartan.setName("Manish");
        mockSpartan.setCourse("C#");
        System.out.println(mockSpartan.getName() + " " + mockSpartan.getCourse());

        InOrder inOrder = Mockito.inOrder(mockSpartan);
        inOrder.verify(mockSpartan).setName("Manish");
        inOrder.verify(mockSpartan).setCourse("C#");
        inOrder.verify(mockSpartan).getName();
        inOrder.verify(mockSpartan).getCourse();
    }


}
