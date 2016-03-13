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

    private void AddContacts(){
        contactManager.addNewContact("David Beckham","A sporty chap");
        contactManager.addNewContact("David Wright","A novice programmer");
        contactManager.addNewContact("Alan Turing","He started it all...");
        contactManager.addNewContact("Alan Rickman","A fine actor");
        contactManager.addNewContact("Grace Hopper","No Grace, no Java...");
        contactManager.addNewContact("Grace Kelly","The graceful actor");
        contactManager.addNewContact("Ada Lovelace","The first programmer");
        contactManager.addNewContact("Niklaus Wirth","Created the Pascal programming language");
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

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenNameEmpty(){
        CreateContactManager();
        contactManager.addNewContact("","A novice programmer");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenNotesEmpty(){
        CreateContactManager();
        contactManager.addNewContact("Fred Dread","");
    }

    @Test(expected = NullPointerException.class)
    public void throwsExceptionWhenNameNull(){
        CreateContactManager();
        contactManager.addNewContact(null,"A novice programmer");
    }

    @Test(expected = NullPointerException.class)
    public void throwsExceptionWhenNotesNull(){
        CreateContactManager();
        contactManager.addNewContact("Fred Dread",null);
    }

    @Test
    public void isGetContactsReturningSubstringContacts(){
        CreateContactManager();
        AddContacts();
        contacts = contactManager.getContacts("David");
        Assert.assertTrue(contacts.size() > 1);
    }

    @Test
    public void doesEmptyStringReturnAllContacts(){
        CreateContactManager();
        AddContacts();
        contacts = contactManager.getContacts("");
        Assert.assertTrue(contacts.size() == 8);
    }

    @Test(expected = NullPointerException.class)
    public void doesNullReturnException(){
        //This test passed but the exception wasn't thrown deliberately by my code
        //Have made changes to getContacts to throw exception
        CreateContactManager();
        AddContacts();
        String nullString = null;
        contacts = contactManager.getContacts(nullString);
    }

    @Test
    public void emptyContactsListReturnedIfNameNotInContacts(){
        CreateContactManager();
        AddContacts();
        contacts = contactManager.getContacts("Fred");
        Assert.assertTrue(contacts.size() == 0);
    }

    @Test
    public void returnSingleContactFrom1Id(){
        CreateContactManager();
        AddContacts();
        contacts = contactManager.getContacts(1);
        Assert.assertTrue(contacts.size() == 1);
    }

    @Test
    public void returnMultipleContactsFromManyIds(){
        CreateContactManager();
        AddContacts();
        contacts = contactManager.getContacts(1,2,3);
        Assert.assertTrue(contacts.size() == 3);

        contacts = contactManager.getContacts(4,5,6,7);
        Assert.assertTrue(contacts.size() == 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionIfNoIdProvided(){
        CreateContactManager();
        AddContacts();
        contacts = contactManager.getContacts();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionIfIdNotValid(){
        CreateContactManager();
        AddContacts();
        contacts = contactManager.getContacts(2000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionIfOneOfTheIdsNotValid(){
        CreateContactManager();
        AddContacts();
        contacts = contactManager.getContacts(1,2,3,2000);
    }

    //TODO create private method iterates contacts and can check
    // if I am getting a back a certain contact value

}
