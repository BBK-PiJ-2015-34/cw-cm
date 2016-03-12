import java.util.Calendar;
import java.util.Set;

/**
 * Created by davidwright on 05/03/2016.
 */
public class FutureMeetingImpl extends MeetingAbs implements FutureMeeting {


    public FutureMeetingImpl(int id, Calendar calendar, Set<Contact> contacts){
        super(id,calendar,contacts);
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
}
