package by.training.final_task.action;

import java.io.Serializable;

public class MenuItem implements Serializable {

    private String url;
    private String name;

    public MenuItem(final String newUrl, final String newName) {
        url = newUrl;
        name = newName;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
