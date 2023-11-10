package application.U5D10.payloads;

import application.U5D10.enums.DeviceStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record DevicePutUser (
        @NotNull
        int user){}
