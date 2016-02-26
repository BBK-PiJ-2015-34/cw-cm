import org.junit.After;
import org.junit.Test;

/**
 * Created by davidwright on 21/02/2016.
 */
public class ContactImplTest {
    private Contact contact;


    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetId() throws Exception {
        newContact();
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