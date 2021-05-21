package cn.t.util.validator.test;

import cn.t.util.validator.FieldError;
import cn.t.util.validator.ValidateUtil;
import org.junit.Test;

import java.util.Set;

/**
 * test
 *
 * @author yj
 * @since 2020-03-05 17:06
 **/
public class ValidateUtilTest {

    @Test
    public void validateTest() {
        User user = new User();
        Set<FieldError> fieldErrorSet = ValidateUtil.validate(user, true);
        System.out.println(fieldErrorSet);
    }

}
