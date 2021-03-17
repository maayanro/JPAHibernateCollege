package com.demo.JPAExample;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student extends User {

    //a student can has multiple teachers, and a teacher can teach  multiple students
    //hibernate define for us
    /*@ManyToMany
    @JoinTable(
            name = "TEACHERS_STUDENTS",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    )*/
    @ManyToMany
    private List<Teacher> teachers;

    //it's a good practice to put the owning side of a relationship in the class where the foreign key
    //we still want to have access to student's meetingSlot so we define a bidirectional relationship with mapped key
    //JPA defines fetch = FetchType.LAZY by default
    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST)
    private List<MeetingSlot> meetingSlots;

    protected Student() {
    }

    public Student(String username, String password) {
        super(username, password);
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeacher(Teacher teacher) {
        if(teachers == null) {
            teachers = new ArrayList<>();
        }
        this.teachers.add(teacher);
    }

    public List<MeetingSlot> getMeetingSlots() {
        return meetingSlots;
    }
}
