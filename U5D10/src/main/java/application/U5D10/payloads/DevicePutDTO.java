package application.U5D10.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record DevicePutDTO (

        @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 10 caratteri")
        String type,
        @NotEmpty(message = "lo stato è un campo obbligatorio!")
        String status){
}
