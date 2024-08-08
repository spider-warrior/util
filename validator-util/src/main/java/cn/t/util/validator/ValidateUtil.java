package cn.t.util.validator;

import cn.t.util.common.CollectionUtil;
import cn.t.util.common.StringUtil;
import cn.t.util.internationalize.MessageUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.HibernateValidator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 验证工具
 *
 * @author yj
 * @since 2020-03-05 16:18
 **/
public class ValidateUtil {

    private static final Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();
    private static final String BUNDLE_NAME = "i18n/field-error-msg";

    public static <T> Set<FieldError> validate(T param, boolean failFast) {
        Set<ConstraintViolation<T>> violations = validator.validate(param);
        if(!CollectionUtil.isEmpty(violations)) {
            Set<FieldError> fieldErrorSet = new HashSet<>();
            for(ConstraintViolation<T> violation: violations) {
                String property = violation.getPropertyPath().toString();
                String msg = violation.getMessage();
                if(!StringUtil.isEmpty(msg)) {
                    String i18n = MessageUtil.getString(BUNDLE_NAME, msg);
                    if(!StringUtil.isEmpty(i18n)) {
                        msg = i18n;
                    }
                }
                fieldErrorSet.add(new FieldError(property, msg));
            }
            return fieldErrorSet;
        } else {
            return Collections.emptySet();
        }
    }
}
