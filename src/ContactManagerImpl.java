import java.util.*;


/**
 * Created by davidwright on 06/03/2016.
 */
public class ContactManagerImpl implements ContactManager {

    private HashMap<Integer, Contact> contactsMap;
    private int contactsMapIndex;
    private HashMap<Integer, FutureMeeting> futureMeetingMap;
    private int MeetingMapIndex;
    private HashMap<Integer, PastMeeting> pastMeetingMap;
    private Calendar theDate;

    public ContactManagerImpl(){
        contactsMap = new HashMap<>();
        contactsMapIndex = 1;
        futureMeetingMap = new HashMap<>();
        MeetingMapIndex = 1;
        pastMeetingMap = new HashMap<>();
        theDate = null;
    }

    @Override
    public int addFutureMeeting(Set<Contact> contacts, Calendar date) throws IllegalArgumentException, NullPointerException {

        if(date == null){
            throw new NullPointerException();
        }

        Calendar todaysDate = Calendar.getInstance();

        if(todaysDate.after(date)){
            throw new IllegalArgumentException();
        }

        if(contactExists(contacts) == false){
            throw new IllegalArgumentException();
        }

        futureMeetingMap.put(MeetingMapIndex, new FutureMeetingImpl(MeetingMapIndex,date,contacts));
        return MeetingMapIndex++;
    }



    @Override
    public PastMeeting getPastMeeting(int id) throws IllegalStateException{
        if(futureMeetingMap.containsKey(id)){
            throw new IllegalStateException();
        }
        if(pastMeetingMap.containsKey(id)){
            return pastMeetingMap.get(id);
        }

        return null;
    }

    @Override
    public FutureMeeting getFutureMeeting(int id) throws IllegalArgumentException {
        if(pastMeetingMap.containsKey(id)){
            throw new IllegalArgumentException();
        }

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
    public List<Meeting> getMeetingListOn(Calendar date) throws NullPointerException {
        if(date == null){
            throw new NullPointerException();
        }

        List<Meeting> me = new ArrayList<Meeting>();

        Iterator<Map.Entry<Integer,PastMeeting>> iterator = pastMeetingMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer,PastMeeting> meetingEntry = iterator.next();
            if(meetingEntry.getValue().getDate().compareTo(date) == 0) {
                me.add(meetingEntry.getValue());
            }
        }
        Collections.sort(me,new DateComparator());
        return me;
    }

    @Override
    public List<PastMeeting> getPastMeetingListFor(Contact contact) {
        return null;
    }

    @Override
    public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) throws IllegalArgumentException, NullPointerException {

        if((contactExists(contacts) == false) || contacts.size() == 0){
            throw new IllegalArgumentException();
        }
        if (contacts == null || date == null || text == null){
            throw new NullPointerException();
        }

        pastMeetingMap.put(MeetingMapIndex, new PastMeetingImpl(MeetingMapIndex,date,contacts,text));
        MeetingMapIndex++;
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

    private boolean contactExists(Set<Contact> contacts) {
        Iterator iterator = contacts.iterator();
        while (iterator.hasNext()){
            if (contactsMap.containsValue(iterator.next()) == false ){
                return false;
            }
        }
        return true;
    }
}


class DateComparator implements Comparator<Meeting>{

    @Override
    public int compare(Meeting o1, Meeting o2) {
        if(o1.getDate().after(o2.getDate())){
            return -1;
        } else if (o1.getDate().before(o2.getDate())){
            return 1;
        }
        return 0;
    }
}
