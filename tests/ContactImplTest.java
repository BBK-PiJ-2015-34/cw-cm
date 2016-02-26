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
    public void isContactDetailsWith3ParametersCorrect(){
        newContact(1,"David Wright","Hello World");
        Assert.assertEquals(1, contact.getId());
        Assert.assertEquals("David Wright", contact.getName());
        Assert.assertEquals("Hello World", contact.getNotes());
    }

    @Test
    public void isContactDetailsWith2ParametersCorrect(){
        newContact2(2,"June Whitfield");
        Assert.assertEquals(2, contact.getId());
        Assert.assertEquals("June Whitfield", contact.getName());
        Assert.assertEquals("", contact.getNotes());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsExceptionBecauseZero(){
        newContact(0,"Max Zero","I am below 1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsExceptionBecauseBelowZero(){
        newContact(-2,"Below Zero","I am below 0");
    }

    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionBecauseNameIsNull(){
        newContact(44,null,"I am nothing");

    }

    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionBecauseNotesIsNull(){
        newContact(44,"Null Notes", null);

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

    private void newContact2(int id, String name){
        contact = new ContactImpl(id, name);
    }
}