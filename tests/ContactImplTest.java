import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by davidwright on 21/02/2016.
 */
public class ContactImplTest {
    private Contact contact;


    @After
    public void tearDown() throws Exception {
        contact = null;
    }

    @Test
    public void isContactNull(){
        Assert.assertNull(contact);
        newContact(1,"David Wright","Hello World");
        Assert.assertNotNull(contact);
    }

    @Test
    public void isContactDetailsCorrect(){
        newContact(1,"David Wright","Hello World");
    }

    @Test
    public void testGetId() throws Exception {

    }

    @Test
    public void testGetName() throws Exception {

    }

    @Test
    public void testGetNotes() throws Exception {

    }

    @Test
    public void testAddNotes() throws Exception {

    }

    private void newContact(int id, String name, String note){
        contact = new ContactImpl(id, name, note);
    }
}