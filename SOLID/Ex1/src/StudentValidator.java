import java.util.*;

public class StudentValidator {

    private final Set<String> allowedPrograms;

    public StudentValidator(Set<String> allowedPrograms) {
        this.allowedPrograms = allowedPrograms;
    }

    public List<String> validate(ParsedStudent s) {
        List<String> errors = new ArrayList<>();

        if (s.name.isBlank())
            errors.add("name is required");

        if (s.email.isBlank() || !s.email.contains("@"))
            errors.add("email is invalid");

        if (s.phone.isBlank() || !s.phone.chars().allMatch(Character::isDigit))
            errors.add("phone is invalid");

        if (!allowedPrograms.contains(s.program))
            errors.add("program is invalid");

        return errors;
    }
}