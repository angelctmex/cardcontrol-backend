package com.aiti.preauthorizer.dto.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Forma que se encarga de activar
 *      un codigo para enrolar
 *
 * @author Angel Contreras Torres
 * @since  11-10-2016
 */
public class ChangePassForm {

    @NotNull
    @Size(min = 6, max = 6, message = "Longitud Invalida")
    @Pattern(regexp = "^[\\s\\d]+$", message = "Este campo solo acepta números.")
    private String pin;

    @NotNull
    @Size(min = 6, max = 6, message = "Longitud Invalida")
    @Pattern(regexp = "^[\\s\\d]+$", message = "Este campo solo acepta números.")
    private String validatePin;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getValidatePin() {
        return validatePin;
    }

    public void setValidatePin(String validatePin) {
        this.validatePin = validatePin;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CodeActivationForm [pin=");
        builder.append(pin);
        builder.append(", validatePin=");
        builder.append(validatePin);
        builder.append("]");
        return builder.toString();
    }
}
