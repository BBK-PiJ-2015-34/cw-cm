import java.util.Calendar;
import java.util.Set;


/**
 * Created by davidwright on 05/03/2016.
 */
public abstract class MeetingAbs implements Meeting {

    protected int id;
    protected Calendar date;
    protected Set<Contact> contacts;

    public MeetingAbs(int id, Calendar date, Set<Contact> contacts) throws IllegalArgumentException, NullPointerException {
        if (id <= 0){
            throw new IllegalArgumentException();
        }
        if (date == null){
            throw new NullPointerException();
        }
        if (contacts == null){
            throw new NullPointerException();
        }
        if(contacts.isEmpty()){
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.date = date;
        this.contacts = contacts;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Calendar getDate() {
        return date;
    }

    @Override
    public Set<Contact> getContacts() {
        return contacts;
    }

}
