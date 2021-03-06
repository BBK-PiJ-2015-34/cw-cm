import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by davidwright on 05/03/2016.
 */
public class FutureMeetingImplTest {

    private FutureMeeting futureMeeting;
    private Set<Contact> contacts;

    @After
    public void tearDown() throws Exception {
        futureMeeting = null;
        contacts = null;
    }

    @Test
    public void isFutureMeetingNull(){
        Assert.assertNull(futureMeeting);
        contacts = CreateContacts();
        newFutureMeeting(1,CreateCalendar(31,7,2016),contacts);
        Assert.assertNotNull(futureMeeting);
    }

    static public Calendar CreateCalendar(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return cal;
    }

    @Test
    public void isFutureMeetingParametersCorrect(){
        contacts = CreateContacts();
        newFutureMeeting(1,CreateCalendar(31,7,2016),contacts);
        Assert.assertEquals(futureMeeting.getId(),1);
        Assert.assertEquals(futureMeeting.getDate().get(Calendar.MONTH),7);
        Assert.assertNotNull(futureMeeting.getContacts());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorExceptionThrownWhenContactsEmpty(){
        newFutureMeeting(1,CreateCalendar(31,7,2016),new HashSet<Contact>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void FutureMeetingConstructorThrowsExceptionForIDZero(){
        contacts = CreateContacts();
        newFutureMeeting(0,CreateCalendar(31,7,2016),contacts);
    }

    @Test(expected = IllegalArgumentException.class)
    public void FutureMeetingConstructorThrowsExceptionForIDBelowZero(){
        contacts = CreateContacts();
        newFutureMeeting(-1,CreateCalendar(31,7,2016),contacts);
    }

    @Test(expected = NullPointerException.class)
    public void FutureMeetingConstructorThrowsExceptionForNullDate(){
        contacts = CreateContacts();
        newFutureMeeting(2,null,contacts);
    }

    @Test(expected = NullPointerException.class)
    public void FutureMeetingConstructorThrowsExceptionForNullContacts(){
        contacts = CreateContacts();
        newFutureMeeting(2,CreateCalendar(31,7,2016),null);
    }

    private Set<Contact> CreateContacts(){
        contacts = new HashSet<Contact>();
        contacts.add(new ContactImpl(1,"David Wright","Birkbeck Student"));
        contacts.add(new ContactImpl(2,"Alan Turing","Cambridge Student"));
        contacts.add(new ContactImpl(3,"June Whitfield","Actor"));
        return contacts;
    }

    private void newFutureMeeting(int id, Calendar cal, Set<Contact> conts){
        futureMeeting = new FutureMeetingImpl(id,cal,conts);
    }

}
