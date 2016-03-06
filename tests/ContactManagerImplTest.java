import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by davidwright on 06/03/2016.
 */
public class ContactManagerImplTest {
    private ContactManager contactManager;
    private Contact contact;

    private void CreateContactManager(){
        contactManager = new ContactManagerImpl();
    }

    @After
    public void tearDown() throws Exception {
        contactManager = null;
    }

    @Test
    public void isContactManagerNull(){
        Assert.assertNull(contactManager);
        CreateContactManager();
        Assert.assertNotNull(contactManager);
    }

}
