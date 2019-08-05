package by.training.task02_threadRunnablePersone;

/**Present a person.*/
public class Person {
    /**Person name.*/
    private String name;
    /**Constr.
     * @param newName - name.*/
    public Person(final String newName) {
        this.name = newName;
    }
    /**Getter.
     * @return name.*/
    public String getName() {
        return name;
    }
}
