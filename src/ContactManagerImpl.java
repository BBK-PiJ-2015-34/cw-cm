import java.util.*;

/**
 * Created by davidwright on 06/03/2016.
 */
public class ContactManagerImpl implements ContactManager {

    private HashMap<Integer, Contact> contactsMap;
    private int contactsMapIndex;

    public ContactManagerImpl(){
        contactsMap = new HashMap<>();
        contactsMapIndex = 1;
    }

    @Override
    public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
        return 0;
    }

    @Override
    public PastMeeting getPastMeeting(int id) {
        return null;
    }

    @Override
    public FutureMeeting getFutureMeeting(int id) {
        return null;
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
}
