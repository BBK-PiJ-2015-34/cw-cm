import java.util.*;

/**
 * Created by davidwright on 06/03/2016.
 */
public class ContactManagerImpl implements ContactManager {

    private HashMap<Integer, Contact> contactsMap;
    private int contactsMapIndex;
    private HashMap<Integer, FutureMeeting> futureMeetingMap;
    private int futureMeetingMapIndex;
    private Calendar theDate;

    public ContactManagerImpl(){
        contactsMap = new HashMap<>();
        contactsMapIndex = 1;
        futureMeetingMap = new HashMap<>();
        futureMeetingMapIndex = 1;
        theDate = null;
    }

    @Override
    public int addFutureMeeting(Set<Contact> contacts, Calendar date)  throws IllegalArgumentException, NullPointerException {

        if(date == null){
            throw new NullPointerException();
        }

        Calendar todaysDate = Calendar.getInstance();

        if(todaysDate.after(date)){
            throw new IllegalArgumentException();
        }

        Iterator iterator = contacts.iterator();
        while (iterator.hasNext()){
            if (contactsMap.containsValue(iterator.next()) == false ){
                throw new IllegalArgumentException();
            }
        }

        futureMeetingMap.put(futureMeetingMapIndex, new FutureMeetingImpl(futureMeetingMapIndex,date,contacts));
        return futureMeetingMapIndex++;
    }

    @Override
    public PastMeeting getPastMeeting(int id) {
        return null;
    }

    @Override
    public FutureMeeting getFutureMeeting(int id) {

        return futureMeetingMap.get(id);
    }

    @Override
    public Meeting getMeeting(int id) {

        return null;
    }

    @Override
    public List<Meeting> getFutureMeetingList(Contact contact) {
        return null;
    }

    @Override
    public List<Meeting> getMeetingListOn(Calendar date) {
        return null;
    }

    @Override
    public List<PastMeeting> getPastMeetingListFor(Contact contact) {
        return null;
    }

    @Override
    public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {

    }

    @Override
    public PastMeeting addMeetingNotes(int id, String text) {
        return null;
    }

    @Override
    public int addNewContact(String name, String notes) throws IllegalArgumentException, NullPointerException {
        if(name == ""){
            throw new IllegalArgumentException();
        }
        if(name == null){
            throw new NullPointerException();
        }

        if (notes == ""){
            throw new IllegalArgumentException();
        }

        if (notes == null){
            throw new NullPointerException();
        }
        contactsMap.put(contactsMapIndex,new ContactImpl(contactsMapIndex,name,notes));
        return contactsMapIndex++;
    }

    @Override
    public Set<Contact> getContacts(String name) throws NullPointerException {
        if (name == null){
            throw new NullPointerException();
        }

        Set<Contact> sc = new HashSet<>();

        Iterator<Map.Entry<Integer,Contact>> iterator = contactsMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer,Contact> contactEntry = iterator.next();
            if(contactEntry.getValue().getName().contains(name)) {
                sc.add(contactEntry.getValue());
            }
        }
        return sc;
    }

    @Override
    public Set<Contact> getContacts(int... ids) throws IllegalArgumentException {
        if (ids.length == 0){
            throw new IllegalArgumentException();
        }
        Set<Contact> sc = new HashSet<>();
        for (int i =0; i < ids.length; i++){
            int id = ids[i];
            if (contactsMap.containsKey(id)){
                sc.add(contactsMap.get(id));}
            else {
                throw new IllegalArgumentException();
            }

        }

        return sc;
    }

    @Override
    public void flush() {

    }

    /**
     * Returns the currentDate, or the current value of theDate if theDate is not null
     *
     * @return void
     */

    private Calendar getTheDate(){
        if (theDate == null){
            theDate = Calendar.getInstance();
        }

        return theDate;
    }


    /**
     * Sets the theDate field to a particular date.
     *
     * This method should only be called for testing purposes
     *
     * @param day an int between 1 and 31
     * @param month an int between 1 and 12
     * @param year an
     * @return void
     */
    public void setTheDate(int day, int month, int year){
        theDate = Calendar.getInstance();
        theDate.set(year,month,day);
    }
}
