package com.demo.JPAExample;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher extends User {

    //a student can have multiple teachers, and a teacher can teach  multiple students
    @ManyToMany
    private List<Student> students = new ArrayList<>();

    //when we persist a meetingSlot hibernate will persist the meetingSlot of the teacher also
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.PERSIST)
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
        this.students.add(student);
    }

    public List<MeetingSlot> getMeetingSlots() {
        return meetingSlots;
    }
}
