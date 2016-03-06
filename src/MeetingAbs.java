import java.util.Calendar;
import java.util.Date;
import java.util.Set;


/**
 * Created by davidwright on 05/03/2016.
 */
public abstract class MeetingAbs implements Meeting {

    private int id;
    private Calendar date;
    private Set<Contact> contacts;

    public MeetingAbs(int id, Date date, Set<Contact> contacts){
        this.id = id;
        this.date.setTime(date);
        this.contacts = contacts;
    }
}
