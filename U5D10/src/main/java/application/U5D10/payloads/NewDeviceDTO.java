package application.U5D10.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public record NewDeviceDTO(
        @NotEmpty(message = "il tipo è un campo obbligatorio!")
        @Size(min = 3, max=15, message = "Il nome del dispositivo deve essere compreso tra 3 e 15 caratteri")
        String type,
        @NotEmpty(message = "lo stato è un campo obbligatorio!")
        String status) {}

