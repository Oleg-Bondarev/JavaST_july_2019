package by.training.final_task.service.validator;

import java.util.List;
import java.util.Map;

public interface Valid {
    Map<String, Boolean> validate(List<String> parameters);
}
