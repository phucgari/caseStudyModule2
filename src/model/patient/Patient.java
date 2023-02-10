package model.patient;

import java.time.LocalDateTime;

public class Patient {
    private LocalDateTime sessionTime =LocalDateTime.now();

    public LocalDateTime getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(LocalDateTime sessionTime) {
        this.sessionTime = sessionTime;
    }
}
