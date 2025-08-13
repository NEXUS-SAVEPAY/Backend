package savepay.savepay.global.security.resolver;

import io.swagger.v3.oas.annotations.Hidden;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Hidden
public @interface UserInjection {
}
