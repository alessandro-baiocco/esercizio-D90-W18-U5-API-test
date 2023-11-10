package application.U5D10.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsersPutDTO(
                          @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
                          String nome,
                          @Size(min = 3, max=30, message = "Il cognome deve essere compreso tra 3 e 30 caratteri")
                          String cognome,
                          @Size(min = 3, max=30, message = "l'username deve essere compreso tra 3 e 15 caratteri")
                          String surname,
                          @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
                          String email)  {
}
