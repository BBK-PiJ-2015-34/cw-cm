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
    public void contactIsNull(){
        Assert.assertNull(contact);
    }

    @Test
    public void contactIsNotNull(){
        newContact();
        Assert.assertNotNull(contact);
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

    private void newContact(){
        contact = new ContactImpl(1,"David Wright","Hello World"); {
        }
    }
}