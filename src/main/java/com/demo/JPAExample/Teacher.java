package com.demo.JPAExample;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher extends User implements Serializable {

    @ManyToMany
    private List<Student> students;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
    private List<MeetingSlot> meetingSlots;

    protected Teacher() {
    }

    public Teacher(String username, String password) {
        super(username, password);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        if(students == null) {
            students = new ArrayList<>();
        }
        this.students.add(student);
    }

    public boolean createAMeetingSlot(MeetingSlot meetingSlot) {
        if(meetingSlots == null) {
            meetingSlots = new ArrayList<>();
        }
        this.meetingSlots.add(meetingSlot);
        return true;
    }

    public List<MeetingSlot> getMeetingSlots() {
        return meetingSlots;
    }
}
