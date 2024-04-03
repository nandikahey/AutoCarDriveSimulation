package org.carsim.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InputValidatorTest {

    private InputValidator inputValidator;

    @BeforeEach
    void setupInputValidator() {
        inputValidator = new InputValidator();
    }

    @ParameterizedTest
    @CsvSource({
            ", false",
            "A B, false",
            "-1 2, false",
            "1 0, false",
            "20 12, true",
            "2 2, true"
    })
    void validateGridInput_ParametrizedValues(String userInput, boolean expected) {
        boolean isValid = inputValidator.validateGridInput(userInput);
        Assertions.assertEquals(expected, isValid);
    }

    @ParameterizedTest
    @CsvSource({
            ", false",
            "A 1 S, false",
            "1 -1 S, false",
            "1 1 X, false",
            "11 -1 S, false",
            "11 10 N, true",
            "0 0 N, true"
    })
    void validateCarPositionAndOrientation_ParametrizedValues(String userInput, boolean expected) {
        boolean isValid = inputValidator.validateCarPositionAndOrientation(userInput);
        Assertions.assertEquals(expected, isValid);
    }

    @ParameterizedTest
    @CsvSource({
            ", false",
            "FLRX, false",
            "FL RR, false",
            "FL RX, false",
            "FL1R, false",
            "FFLR, true"
    })
    void validateCommands_ParametrizedValues(String userInput, boolean expected) {
        boolean isValid = inputValidator.validateCommands(userInput);
        Assertions.assertEquals(expected, isValid);
    }

    @ParameterizedTest
    @CsvSource({
            ", false",
            "1, false",
            "#, false",
            "A, true",
            "B, true"
    })
    void validateVehicleLabel_ParametrizedValues(String userInput, boolean expected) {
        boolean isValid = inputValidator.validateVehicleLabel(userInput);
        Assertions.assertEquals(expected, isValid);
    }

    @ParameterizedTest
    @CsvSource({
            ", false",
            "A, false",
            "#, false",
            "30, true",
            "1, true",
            "2, true"
    })
    void validateVehicleCount_ParametrizedValues(String userInput, boolean expected) {
        boolean isValid = inputValidator.validateVehicleCount(userInput);
        Assertions.assertEquals(expected, isValid);
    }


    @ParameterizedTest
    @CsvSource({
            ", false",
            "A, false",
            "#, false",
            "3, false",
            "1, true",
            "2, true"
    })
    void validateSimulationType_ParametrizedValues(String userInput, boolean expected) {
        boolean isValid = inputValidator.validateSimulationType(userInput);
        Assertions.assertEquals(expected, isValid);
    }


}
