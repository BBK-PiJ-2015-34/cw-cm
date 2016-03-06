import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by davidwright on 05/03/2016.
 */
public class MeetingImplTest {

    private FutureMeeting futureMeeting;
    private Set<Contact> contacts;

    @After
    public void tearDown() throws Exception {
        futureMeeting = null;
        contacts = null;
    }

    @Test
    public void isMeetingNull(){
        Assert.assertNull(futureMeeting);
        contacts = CreateContacts();
        newMeeting(1,CreateCalendar(31,7,2016),contacts);
        Assert.assertNotNull(futureMeeting);
    }

    private Calendar CreateCalendar(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return cal;
    }

    @Test
    public void isMeetingCalendarParametersCorrect(){
        contacts = CreateContacts();
        newMeeting(1,CreateCalendar(31,7,2016),contacts);
        Assert.assertEquals(futureMeeting.getId(),1);
        Assert.assertEquals(futureMeeting.getDate().get(Calendar.MONTH),7);
        Assert.assertNotNull(futureMeeting.getContacts());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsExceptionForIDZero(){
        contacts = CreateContacts();
        newMeeting(0,CreateCalendar(31,7,2016),contacts);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsExceptionForIDBelowZero(){
        contacts = CreateContacts();
        newMeeting(-1,CreateCalendar(31,7,2016),contacts);
    }

    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionForNullDate(){
        contacts = CreateContacts();
        newMeeting(2,null,contacts);
    }

    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionForNullContacts(){
        contacts = CreateContacts();
        newMeeting(2,CreateCalendar(31,7,2016),null);
    }

    private Set<Contact> CreateContacts(){
        contacts = new HashSet<Contact>();
        contacts.add(new ContactImpl(1,"David Wright","Birkbeck Student"));
        contacts.add(new ContactImpl(2,"Alan Turing","Cambridge Student"));
        contacts.add(new ContactImpl(3,"June Whitfield","Actor"));
        return contacts;
    }

    private void newMeeting(int id, Calendar cal, Set<Contact> conts){
        futureMeeting = new FutureMeetingImpl(id,cal,conts);
    }

}
