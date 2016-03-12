import java.util.Calendar;
import java.util.Set;

/**
 * Created by davidwright on 05/03/2016.
 */
public class PastMeetingImpl extends MeetingAbs implements PastMeeting {

    private String notes;


    public PastMeetingImpl(int id, Calendar calendar, Set<Contact> contacts, String notes) throws NullPointerException{
        super(id,calendar,contacts);
        if (notes == null){
            throw new NullPointerException();
        }
        this.notes = notes;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public Calendar getDate() {
        return super.getDate();
    }

    @Override
    public Set<Contact> getContacts() {
        return super.getContacts();
    }

    @Override
    public String getNotes() {
        return notes;
    }
}
