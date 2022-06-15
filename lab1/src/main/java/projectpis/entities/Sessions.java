package projectpis.entities;

import java.util.Date;

public class Sessions {
    private long id;
    private long customer_id;
    private long location_id;
    private Date date;

    public Sessions() {
    }

    public Sessions(long customer_id, long location_id, Date date) {
        this.customer_id = customer_id;
        this.location_id = location_id;
        this.date = date;

    }

    public Sessions(long id, long customer_id, long location_id, Date date) {
        this.id = id;
        this.customer_id = customer_id;
        this.location_id = location_id;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(long location_id) {
        this.location_id = location_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}