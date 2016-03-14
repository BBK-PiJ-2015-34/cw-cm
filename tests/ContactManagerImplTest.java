import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.*;

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

    @Test
    public void doesAddFutureMeetingReturnId(){
        CreateContactManager();
        AddContacts();
        contacts = contactManager.getContacts(1,2,3,4);
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE,1);
        int id = contactManager.addFutureMeeting(contacts, date);
        Assert.assertTrue(id == 1);
    }

    @Test
    public void doesIdReturnFutureMeeting(){
        CreateContactManager();
        AddContacts();
        contacts = contactManager.getContacts(1,2,3,4);
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE,2);
        int id = contactManager.addFutureMeeting(contacts, date);
        FutureMeeting fm = contactManager.getFutureMeeting(id);
        Assert.assertNotNull(fm);
    }

    @Test(expected = NullPointerException.class)
    public void throwsExceptionWhenDateNull(){
        CreateContactManager();
        AddContacts();
        contacts = contactManager.getContacts(1,2,3,4);
        int id = contactManager.addFutureMeeting(contacts, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenDateInPast(){
        CreateContactManager();
        AddContacts();
        contacts = contactManager.getContacts(1,2,3,4);
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE,-1);
        int id = contactManager.addFutureMeeting(contacts, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenContactUnknown(){
        CreateContactManager();
        AddContacts();
        contacts = contactManager.getContacts(1,2,3,4);
        contacts.add(new ContactImpl(66,"Fred Bloggs"));
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE,1);
        int id = contactManager.addFutureMeeting(contacts, date);
    }

    @Test
    public void areNewPastMeetingsBeingAdded(){
        CreateContactManager();
        AddContacts();

        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE,-3);

        contacts = contactManager.getContacts(1,2,3,4);
        contactManager.addNewPastMeeting(contacts,date,"An excellent meeting");

        contacts = contactManager.getContacts(3,4,5,6);
        contactManager.addNewPastMeeting(contacts,date,"Another excellent meeting");

        Calendar anotherDate = Calendar.getInstance();
        contacts = contactManager.getContacts(8,2,1,5);
        contactManager.addNewPastMeeting(contacts,anotherDate,"Another excellent meeting");

        List<Meeting> meetings = contactManager.getMeetingListOn(date);
        Assert.assertNotNull(meetings);
        Assert.assertEquals(meetings.size(),2);


    }

    @Test(expected = NullPointerException.class)
    public void addNewPastMeetingThrowsExceptionWhenDateNull(){
        CreateContactManager();
        AddContacts();

        contacts = contactManager.getContacts(1,2,3,4);
        contactManager.addNewPastMeeting(contacts,null,"An excellent meeting");
    }

    @Test(expected = NullPointerException.class)
    public void addNewPastMeetingThrowsExceptionWhenNullContacts(){
        CreateContactManager();
        AddContacts();
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE,-1);

        contacts = contactManager.getContacts(1,2,3,4);
        contactManager.addNewPastMeeting(null,date,"An excellent meeting");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNewPastMeetingThrowsExceptionWhenEmptyContacts(){
        CreateContactManager();
        AddContacts();
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE,-1);

        contacts = contactManager.getContacts(1,2,3,4);
        contacts.clear();
        contactManager.addNewPastMeeting(contacts,date,"An excellent meeting");
    }

    @Test(expected = NullPointerException.class)
    public void addNewPastMeetingThrowsExceptionWhenNullNotes(){
        CreateContactManager();
        AddContacts();
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE,-1);

        contacts = contactManager.getContacts(1,2,3,4);
        contactManager.addNewPastMeeting(contacts,date,null);
    }

    @Test
    public void getPastMeetingReturnsMeetingFromId(){
        CreateContactManager();
        AddContacts();

        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE,-3);

        contacts = contactManager.getContacts(1,2,3,4);
        contactManager.addNewPastMeeting(contacts,date,"An excellent meeting");

        List<Meeting> meetings = contactManager.getMeetingListOn(date);
        int id = meetings.get(0).getId();
        Assert.assertEquals(contactManager.getPastMeeting(id).getNotes(),"An excellent meeting");
    }

    @Test
    public void getPastMeetingReturnsNullForNonExistentId(){
        CreateContactManager();
        Assert.assertNull(contactManager.getPastMeeting(3000));
    }

    @Test(expected = IllegalStateException.class)
    public void getPastMeetingThrowsExceptionWhenFutureMeetingId(){
        CreateContactManager();
        AddContacts();
        contacts = contactManager.getContacts(1,2,3,4);
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE,1);
        int id = contactManager.addFutureMeeting(contacts, date);
        contactManager.getPastMeeting(id);
    }

    @Test
    public void getFutureMeetWithId(){
        CreateContactManager();
        AddContacts();
        contacts = contactManager.getContacts(1,2,3,4);
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE,1);
        int id = contactManager.addFutureMeeting(contacts, date);
        Assert.assertNotNull(contactManager.getFutureMeeting(id));
    }

    @Test
    public void getFutureMeetingWithNonExistentId(){
        CreateContactManager();
        Assert.assertNull(contactManager.getFutureMeeting(4000));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getFutureMeetingThrowsExceptionForPastMeetingId(){
        CreateContactManager();
        AddContacts();

        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE,-3);

        contacts = contactManager.getContacts(1,2,3,4);
        contactManager.addNewPastMeeting(contacts,date,"An excellent meeting");

        List<Meeting> meetings = contactManager.getMeetingListOn(date);
        int id = meetings.get(0).getId();
        contactManager.getFutureMeeting(id);
    }

    @Test
    public void getMeetingWithID(){
        CreateContactManager();
        AddContacts();
        contacts = contactManager.getContacts(1,2,3,4);
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE,1);
        int id = contactManager.addFutureMeeting(contacts, date);

        Assert.assertNotNull(contactManager.getMeeting(id));

        date = Calendar.getInstance();
        date.add(Calendar.DATE,-3);

        contacts = contactManager.getContacts(1,2,3,4);
        contactManager.addNewPastMeeting(contacts,date,"An excellent meeting");

        Assert.assertNotNull(contactManager.getMeeting(2));
    }

    @Test
    public void getFutureMeetingListNotNull(){
        CreateContactManager();
        AddContacts();

        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE,2);

        contacts = contactManager.getContacts(1,2,3,4);
        contactManager.addFutureMeeting(contacts,date);

        date = Calendar.getInstance();
        date.add(Calendar.DATE,4);

        contacts = contactManager.getContacts(3,4,5,6);
        contactManager.addFutureMeeting(contacts,date);

        Contact cons = contactManager.getContacts(3).iterator().next();

        List<Meeting> meetings = contactManager.getFutureMeetingList(cons);

        Assert.assertNotNull(meetings);
        Assert.assertEquals(contactManager.getFutureMeetingList(cons).size(),2);
    }

}
