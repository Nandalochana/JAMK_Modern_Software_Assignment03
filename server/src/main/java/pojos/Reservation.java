package pojos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Reservation {

    private int id;
    private int customerId;
    private int barberId;

    private Date time;

    private Date date;

    private int status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBarberId() {
        return barberId;
    }

    public void setBarberId(int barberId) {
        this.barberId = barberId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = this.time = new Date();;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
