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

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")*/
    @ManyToOne
    private Student student;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id"*/
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
