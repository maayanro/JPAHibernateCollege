package com.demo.JPAExample;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student extends User {

    @ManyToMany
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
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

    public boolean registerToMeetingSlot(MeetingSlot meeting) {
        if(meetingSlots == null) {
            meetingSlots = new ArrayList<>();
        }
        this.meetingSlots.add(meeting);
        return true;
    }

    public List<MeetingSlot> getMeetingSlots() {
        return meetingSlots;
    }
}
