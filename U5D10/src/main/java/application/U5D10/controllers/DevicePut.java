package application.U5D10.controllers;

import application.U5D10.enums.DeviceStatus;
import lombok.Getter;


@Getter
public class DevicePut {
    private DeviceStatus status;
    private String type;
    private boolean disponibile;
    private int user;
}
