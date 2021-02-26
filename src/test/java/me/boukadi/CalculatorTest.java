package me.boukadi;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // create only one instance of CalculatorTest class
@DisplayName("When running CalculatorTest")
class CalculatorTest {

    Calculator calculator;

    @BeforeAll // it must be static so that its executed by JUNIT
    static void runBeforeAll() { // we can removre static if we are creating one instance of the test class
        System.out.println("First methods that runs");
    }

    @BeforeEach
    void init() {
        calculator = new Calculator();
    }


    // NESTED TEST CLASS
    @Nested
    @DisplayName("when running AddTest")
    class AddTest {
        @Test
        @DisplayName("when running addPositive")
        void addPositive() {
            int actual = calculator.add(2, 2);
            int expected = 4;
            assertEquals(expected, actual, "should return the right sum");
            //assertArrayEquals();
            //assertIterableEquals();
        }

        @Test
        @DisplayName("when running addNegative")
        void addNegative() {
            int actual = calculator.add(-2, -2);
            int expected = -4;
            assertEquals(expected, actual, "should return the right sum");
            //assertArrayEquals();
            //assertIterableEquals();
        }
    }

    @AfterEach
    void afterEachMethod() {
        System.out.println("CLean Up...");
    }


    @Test
    void circleArea() {
        assertEquals(314.1592653589793, calculator.circleArea(10), "should compute circle area");
    }

    // see if a function throws the right exception
    @Test
    //@EnabledOnOs(OS.LINUX) // run test only on linux
    void testDivide() {
        boolean isServerUp = false;
        assumeTrue(isServerUp); // assume the server is up to run the test, conditional execution
        assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0), "should throw Arithmetic exception.");
    }

    @Test
    @DisplayName("Just to test @Disabled")
    @Disabled
    void testDisabled() {
        fail("testDisabled fail");
    }
    // what is Test life cycle ?
    // the period in which the Test instance is created, managed and destoroyed
    // JUNIT5 creates new instance of CalculatorTest class every time we call a test method.
    // anti-pattern: defining and using variables outside the test methods

    // we can use a hook to initialize something before the test class gets instanciated (BeforeAll)
    // we can use a hook to tear down after all methods are done (AfterAll)
    // we can use a hook to initialize something before a method (BeforeEach)
    // we can use a hook to tear down after each method (AfterEach)

    @Test
    @DisplayName("Multiply test")
    void testMultiply() {
        // allows to execute a collection of assertEquals
        assertAll(
                () -> assertEquals(4, calculator.multiply(2, 2)),
                () -> assertEquals(25, calculator.multiply(5, 5)),
                () -> assertEquals(36, calculator.multiply(6, 6))
        );
    }

    // @Tag allows you to run only certain tests: e.g Unit Tests or Integration Tests
    // TestInfo interface info about the running test
    // TestReporter interface allows you to write the Junit output

}