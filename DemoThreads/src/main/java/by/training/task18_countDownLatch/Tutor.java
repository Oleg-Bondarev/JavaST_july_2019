package by.training.task18_countDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**Represent the tutor.*/
public class Tutor  extends Thread {
    /**ID.*/
    private Integer idTutor;
    /**List of students.*/
    private List<Student> list;
    /**Constructor.*/
    public Tutor() {
        this.list = new ArrayList<>();
    }
    /**@param listNew -new list of students.*/
    public Tutor(final List<Student> listNew) {
        this.list = listNew;
    }
    /**@return tutor ID.*/
    public Integer getIdTutor() {
        return idTutor;
    }
    /**@param id -new tutor ID.*/
    public void setIdTutor(final Integer id) {
        this.idTutor = id;
    }
    /**Runner meth.*/
    public void run() {
        for (Student st : list) {
            // проверить, выданы ли студенту задания
            List<Task> tasks = st.getTaskList();
            for (Task current : tasks) {
                // проверить наличие ответа!
                int mark = 3 + new Random().nextInt(7);
                current.setMark(mark);
                System.out.println(mark + " for student N "
                        + st.getIdStudent());
                st.getCountDownLatch().countDown();
            }
            System.out.println("All estimates made for " + st.getIdStudent());
        }
    }
}
