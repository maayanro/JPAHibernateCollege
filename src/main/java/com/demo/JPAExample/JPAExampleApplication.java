package com.demo.JPAExample;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/*
Add the following queries:
- Present all Students who set a meeting with a given Teacher
- Present all Students who set a meeting with a given Teacher on a certain date
- Present all future meetings of a Student
- Present all future meetings of a Teacher - the same as Student
- Present total number of meetings per Teacher, past and future
- Present total number of meetings per Teacher, past only
 */

public class JPAExampleApplication {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-project");
        EntityManager em = emf.createEntityManager();

        Student s1 = new Student("maayanro", "12344");
        Student s2 = new Student("orelsh", "12345");
        Teacher t = new Teacher("amirkir", "12346");

        s1.addTeacher(t);
        s2.addTeacher(t);
        t.addStudent(s1);
        t.addStudent(s2);

        em.getTransaction().begin();
        em.persist(s1);
        em.persist(s2);
        em.persist(t);

        LocalDateTime start = LocalDateTime.of(2021,8,10,13,00);
        LocalDateTime end = LocalDateTime.of(2021,8,10,13,30);
        MeetingSlot meeting = new MeetingSlot(start, end, s1, t);
        em.persist(meeting);

        start = LocalDateTime.of(2021,3,15,13,00);
        end = LocalDateTime.of(2021,3,15,13,30);
        meeting = new MeetingSlot(start, end, s2, t);
        em.persist(meeting);

        start = LocalDateTime.of(2021,4,28,12,30);
        end = LocalDateTime.of(2021,4,28,13,30);
        meeting = new MeetingSlot(start, end, s2, t);
        em.persist(meeting);

        em.getTransaction().commit();

        findAllStudentsWithMeetingOfTeacher(em, 3L);

        LocalDate meetingDate = LocalDate.of(2021, 3, 1);
        findAllStudentsWithMeetingOfTeacher(em, 3L, meetingDate);

        getAllStudentFutureMeeting(em, 2L);

        getTeacherTotalMeetings(em);

        getTeacherPastTotalMeetings(em);

        em.close();
        emf.close();
    }

    private static void getTeacherPastTotalMeetings(EntityManager em) {

        System.out.println("---getTeacherPastTotalMeetings---");
        Query q = em.createQuery("Select m.teacher, count(m) from MeetingSlot m where m.startTime < CURRENT_TIME group by m.teacher.id");
        List<Object[]> resultList = q.getResultList();
        resultList.forEach(r -> System.out.println(Arrays.toString(r)));
    }

    private static void getTeacherTotalMeetings(EntityManager em) {

        System.out.println("getTeacherTotalMeetings");
        Query q = em.createQuery("Select m.teacher, count(m) from MeetingSlot m group by m.teacher.id");
        List<Object[]> resultList = q.getResultList();
        resultList.forEach(r -> System.out.println(Arrays.toString(r)));
        System.out.println("------------------------------");
    }

    private static void getAllStudentFutureMeeting(EntityManager em, long studentId) {

        System.out.println("getAllStudentFutureMeeting");
        Query q = em.createQuery("Select m from MeetingSlot m where m.student.id=:studentId and m.startTime > CURRENT_TIME");
        q.setParameter("studentId", studentId);
        q.getResultList().forEach(meeting -> System.out.println(meeting));
        System.out.println("------------------------------");
    }

    private static void findAllStudentsWithMeetingOfTeacher(EntityManager em, long teacherId, LocalDate meetingDate) {

        LocalDateTime startDay = meetingDate.atStartOfDay();
        LocalDateTime endDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
        System.out.println("findAllStudentsWithMeetingOfTeacherInCertainDate");
        Query q = em.createQuery("Select distinct m.student from MeetingSlot m where m.teacher.id=:id and m.startTime between :startDay and :endDay");
        q.setParameter("id", teacherId);
        q.setParameter("startDay", startDay);
        q.setParameter("endDay", endDay);
        q.getResultList().forEach(student -> System.out.println(student));
        System.out.println("------------------------------");

    }

    private static void findAllStudentsWithMeetingOfTeacher(EntityManager em, long teacherId) {

        System.out.println("findAllStudentsWithMeetingOfTeacher");
        Query q = em.createQuery("Select distinct m.student from MeetingSlot m where m.teacher.id=:id");
        q.setParameter("id", teacherId);
        List<Student> results = q.getResultList();
        results.forEach(student -> System.out.println(student));
        System.out.println("------------------------------");

    }


}
