package application.U5D10.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record NewUserDTO(
        @NotEmpty(message = "Il nome è un campo obbligatorio!")
        @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
        String nome,
        @NotEmpty(message = "Il cognome è un campo obbligatorio!")
        @Size(min = 3, max=30, message = "Il cognome deve essere compreso tra 3 e 30 caratteri")
        String cognome,
        @NotEmpty(message = "l'username è un campo obbligatorio!")
        @Size(min = 3, max=30, message = "l'username deve essere compreso tra 3 e 15 caratteri")
        String surname,
        @NotEmpty(message = "L'email è un campo obbligatorio!")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
        String email) {
}
