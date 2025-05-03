package com.abapayway.sdk.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;
import java.util.Map;

public class ReturnDeeplinkValidator implements ConstraintValidator<ValidReturnDeeplink, String> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) return true; // skip null, use @NotBlank if required

        try {
            byte[] decodedBytes = Base64.getDecoder().decode(value);
            Map<?, ?> json = mapper.readValue(decodedBytes, Map.class);

            return json.containsKey("ios_scheme") && json.containsKey("android_scheme") && !String.valueOf(json.get("ios_scheme")).isBlank() && !String.valueOf(json.get("android_scheme")).isBlank();
        } catch (Exception e) {
            return false;
        }
    }
}