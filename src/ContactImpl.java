/**
 * Created by davidwright on 21/02/2016.
 */
public class ContactImpl implements Contact {

    private int id;
    private String name;
    private String notes;



    /**
     * @throws IllegalArgumentException if id is <= 1.
     * @throws NullPointerException if name is null
     * @throws NullPointerException if notes is null
     */
    public ContactImpl(int id, String name, String notes) throws IllegalArgumentException, NullPointerException {
        if (id <= 0){
            throw new IllegalArgumentException();
        }
        if(name == null){
            throw new NullPointerException();
        }
        if(notes == null){
            throw new NullPointerException();
        }
        this.id = id;
        this.name = name;
        this.notes = notes;
    }

    public ContactImpl(int id, String name){
        this(id, name, "");
    }

    @Override
    public int getId() {

        return id;
    }

    @Override
    public String getName() {

        return name;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public void addNotes(String note) {
        this.notes += note;
    }
}
