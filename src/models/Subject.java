package models;

public class Subject {
    private String name;
    private double note;
    private String classroom;

    public Subject(double note, String name) {
        this.note = note;
        this.name = name;
    }
    public Subject(String name, String classroom) {
        this.name = name;
        this.classroom = classroom;
    }

    public double getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean itsApproved(){
        if(note >= 3.5){
            return true;
        }
        else{
            return false;
        }
    }
}
