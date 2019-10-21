package by.training.final_task.service.parser;

import by.training.final_task.action.Action;
import by.training.final_task.entity.CompanyProvider;
import by.training.final_task.service.validator.CompanyParametersValidator;

import java.util.List;
import java.util.Map;

public class CompanyFormParser extends FormParser<CompanyProvider> {

    @Override
    public CompanyProvider parse(final Action newAction,
                                 final List<String> newParameters)
            throws InvalidFormDataException {
        if (!newParameters.isEmpty() && !newParameters.contains(null)
            && !newParameters.contains("")) {
            CompanyParametersValidator validator = new CompanyParametersValidator();
            Map<String, Boolean> validationMap = validator.validate(newParameters);
            for (Map.Entry<String, Boolean> entry : validationMap.entrySet()) {
                if (entry.getValue()) {
                    throw new InvalidFormDataException(entry.getKey());
                }
            }
            String companyName = newParameters.get(validator.getCompanyNameIndex());
            String companyAddress = newParameters.get(validator.getCompanyAddressIndex());
            Integer companyPhone = Integer.parseInt(newParameters
                    .get(validator.getCompanyPhoneIndex()));
            return new CompanyProvider(0, companyAddress, companyName,
                    companyPhone, false);
        }
        throw new InvalidFormDataException("fillAllFields");
    }
}
