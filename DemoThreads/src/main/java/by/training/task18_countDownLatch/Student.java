package by.training.task18_countDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**Represent a student.*/
public class Student extends Thread {
    /**ID.*/
    private Integer idStudent;
    /**Tasks.*/
    private List<Task> taskList;
    /**Latch.*/
    private CountDownLatch countDown;
    /**@param id -ID.
     * @param tasks -task number.*/
    public Student(final Integer id, final int tasks) {
        this.idStudent = id;
        this.countDown = new CountDownLatch(tasks);
        this.taskList = new ArrayList<Task>(tasks);
    }
    /**@return  ID.*/
    public Integer getIdStudent() {
        return idStudent;
    }
    /**@param id -ID.*/
    public void setIdStudent(final Integer id) {
        this.idStudent = id;
    }
    /**@return latch.*/
    public CountDownLatch getCountDownLatch() {
        return countDown;
    }
    /**@return list of tasks.*/
    public List<Task> getTaskList() {
        return taskList;
    }
    /**@param task -new task.*/
    public void addTask(final Task task) {
        taskList.add(task);
    }
    /**Runner meth.*/
    public void run() {
        int i = 0;
        for (Task inWork : taskList) {
            //time to complete the task
            try {
                Thread.sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // send the answer
            inWork.setAnswer("Answer #" + ++i);
            System.out.println("Answer #" + i + " from " + idStudent);
        }
        try {
            countDown.await(); // waiting for an answer
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // calculate average mark
        float averageMark = 0;
        for (Task inWork : taskList) {
            averageMark += inWork.getMark(); // send the answer
        }
        averageMark /= taskList.size();
        System.out.println("Student " + idStudent + ": Average mark = "
                + averageMark);
    }
}
