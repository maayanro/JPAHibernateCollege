package com.demo.JPAExample;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class MeetingSlot {
    @Id @GeneratedValue
    private long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    //a meeting slot can have only one student - many slots to one student
    //hibernate define for us
    //@ManyToOne
    //@JoinColumn(name = "student_id", referencedColumnName = "id")
    //fetch = FetchType.EAGER by default
    @ManyToOne
    private Student student;

    //a meeting slot can have only one teacher - many slots to one teacher
    //hibernate define for us
    //@ManyToOne
    //@JoinColumn(name = "teacher_id", referencedColumnName = "id")
    //fetch = FetchType.EAGER by default
    @ManyToOne
    private Teacher teacher;

    protected MeetingSlot() {
    }

    public MeetingSlot(LocalDateTime startTime, LocalDateTime endTime, Student s, Teacher t) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.student = s;
        this.teacher = t;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "MeetingSlot{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
