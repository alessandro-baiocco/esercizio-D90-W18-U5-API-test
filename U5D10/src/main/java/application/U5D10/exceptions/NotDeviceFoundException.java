package application.U5D10.exceptions;

public class NotDeviceFoundException extends RuntimeException {
    public NotDeviceFoundException(int id){
        super("il dispositivo con id " + id + " non è stato trovato");
}}
