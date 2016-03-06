import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by davidwright on 05/03/2016.
 */
public class PastMeetingImplTest {

    private PastMeeting pastMeeting;
    private Set<Contact> contacts;

    @After
    public void tearDown() throws Exception {
        pastMeeting = null;
        contacts = null;
    }

    private Calendar CreateCalendar(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return cal;
    }

    private Set<Contact> CreateContacts(){
        contacts = new HashSet<Contact>();
        contacts.add(new ContactImpl(1,"David Wright","Birkbeck Student"));
        contacts.add(new ContactImpl(2,"Alan Turing","Cambridge Student"));
        contacts.add(new ContactImpl(3,"June Whitfield","Actor"));
        return contacts;
    }

    private void newPastMeeting(int id, Calendar cal, Set<Contact> conts, String notes){
        pastMeeting = new PastMeetingImpl(id,cal,conts,notes);
    }

    @Test
    public void isPastMeetingNull(){
        Assert.assertNull(pastMeeting);
        contacts = CreateContacts();
        newPastMeeting(1,CreateCalendar(31,7,2016),contacts,"A number of issues came up ..");
        Assert.assertNotNull(pastMeeting);
    }

    @Test
    public void isPastMeetingParametersCorrect(){
        contacts = CreateContacts();
        newPastMeeting(1,CreateCalendar(31,7,2016),contacts,"A number of issues came up ..");
        Assert.assertEquals(pastMeeting.getId(),1);
        Assert.assertEquals(pastMeeting.getDate().get(Calendar.DAY_OF_MONTH),31);
        Assert.assertNotNull(pastMeeting.getContacts());
        Assert.assertEquals(pastMeeting.getNotes(),"A number of issues came up ..");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorExceptionThrownWhenContactsEmpty(){
        newPastMeeting(1,CreateCalendar(31,7,2016),new HashSet<Contact>(),"A number of issues came up ..");
    }

    @Test(expected = NullPointerException.class)
    public void PastMeetingConstructorThrowsExceptionForNullNote(){
        contacts = CreateContacts();
        newPastMeeting(2,CreateCalendar(31,7,2016),contacts,null);
    }
}
