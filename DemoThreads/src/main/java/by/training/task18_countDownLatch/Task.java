package by.training.task18_countDownLatch;

/**Represent the task for student.*/
public class Task {
    /**Content.*/
    private String content;
    /**Answer.*/
    private String answer;
    /**Mark.*/
    private int mark;
    /**@param contentNew -new content of the task.*/
    public Task(final String contentNew) {
        this.content = contentNew;
    }
    /**@return task content.*/
    public String getContent() {
        return content;
    }
    /**@param contentNew -new content */
    public void setContent(final String contentNew) {
        this.content = contentNew;
    }
    /**@return answer*/
    public String getAnswer() {
        return answer;
    }
    /**@param answerNew -new answer. */
    public void setAnswer(final String answerNew) {
        this.answer = answerNew;
    }
    /**@return mark.*/
    public int getMark() {
        return mark;
    }
    /**@param markNew -new mark.*/
    public void setMark(final int markNew) {
        this.mark = markNew;
    }
}
