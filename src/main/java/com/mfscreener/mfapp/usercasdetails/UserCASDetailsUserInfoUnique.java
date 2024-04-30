package com.mfscreener.mfapp.usercasdetails;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.springframework.web.servlet.HandlerMapping;

/**
 * Validate that the id value isn't taken yet.
 */
@Target({FIELD, METHOD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UserCASDetailsUserInfoUnique.UserCASDetailsUserInfoUniqueValidator.class)
public @interface UserCASDetailsUserInfoUnique {

    String message() default "{Exists.userCASDetails.userInfo}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class UserCASDetailsUserInfoUniqueValidator implements ConstraintValidator<UserCASDetailsUserInfoUnique, Long> {

        private final UserCASDetailsService userCASDetailsService;
        private final HttpServletRequest request;

        public UserCASDetailsUserInfoUniqueValidator(
                final UserCASDetailsService userCASDetailsService, final HttpServletRequest request) {
            this.userCASDetailsService = userCASDetailsService;
            this.request = request;
        }

        @Override
        public boolean isValid(final Long value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked")
            final Map<String, String> pathVariables =
                    ((Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null
                    && value.equals(
                            userCASDetailsService.get(Long.parseLong(currentId)).getUserInfo())) {
                // value hasn't changed
                return true;
            }
            return !userCASDetailsService.userInfoExists(value);
        }
    }
}
