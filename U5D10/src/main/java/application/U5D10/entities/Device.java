package application.U5D10.entities;

import application.U5D10.enums.DeviceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    @Id
    @GeneratedValue
    private int id;
    @Enumerated(EnumType.STRING)
    private DeviceStatus status;
    private String type;
    private boolean disponibile;
    @ManyToOne
    @JoinColumn(nullable = true)
    private User user;

    @Override
    public String toString() {
        return "Device{" +
                "status=" + status +
                ", type='" + type + '\'' +
                ", disponibile=" + disponibile +
                '}';
    }
}
