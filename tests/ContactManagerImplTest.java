import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by davidwright on 06/03/2016.
 */
public class ContactManagerImplTest {
    private ContactManager contactManager;
    private Set<Contact> contacts;

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

    @Test
    public void isAddNewContactSaved(){
        CreateContactManager();
        Integer id = contactManager.addNewContact("David Wright","A novice programmer");
        Assert.assertTrue(id >= 1);
        contacts = contactManager.getContacts("David Wright");
        Assert.assertEquals(contacts.isEmpty(),false);

        id = contactManager.addNewContact("Alan Turing","He started it all...");
        contacts = contactManager.getContacts("Alan Turing");
        Assert.assertTrue(contacts.size() == 1);
    }

    //TODO create private method iterates contacts and can check
    // if I am getting a back a certain contact value

}
