import java.util.*;

public class OnboardingService {

    private final StudentRepository repo;
    private final StudentInputParser parser;
    private final StudentValidator validator;
    private final OnboardingPrinter printer;

    public OnboardingService(StudentRepository repo) {
        this.repo = repo;
        this.parser = new StudentInputParser();
        this.validator = new StudentValidator(
                Set.of("CSE", "AI", "SWE")
        );
        this.printer = new OnboardingPrinter();
    }

    public void registerFromRawInput(String raw) {

        printer.printInput(raw);

        ParsedStudent parsed = parser.parse(raw);

        List<String> errors = validator.validate(parsed);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(repo.count());

        StudentRecord rec = new StudentRecord(
                id,
                parsed.name,
                parsed.email,
                parsed.phone,
                parsed.program
        );

        repo.save(rec);

        printer.printSuccess(rec, repo.count());
    }
}