package pro.kbgame.flickcount.model;

import java.util.Date;

public class Flick {
    private int id;
    private String cause;
    private Date date;


    public static Flick getFakeFlick() {
        Flick flick = new Flick();
        flick.setId(1);
        flick.setDate(new Date());
        flick.setCause("StCaUSE");
        return  flick;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
