import java.util.*;

public class EligibilityEngine {

    private final FakeEligibilityStore store;
    private final List<EligibilityRule> rules;

    public EligibilityEngine(FakeEligibilityStore store) {
        this.store = store;

        // Order preserved exactly as before
        this.rules = List.of(
                new DisciplinaryRule(),
                new CgrRule(8.0),
                new AttendanceRule(75),
                new CreditsRule(20)
        );
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s);
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {

        List<String> reasons = new ArrayList<>();

        for (EligibilityRule rule : rules) {
            String result = rule.validate(s);
            if (result != null) {
                reasons.add(result);
                break; // preserve original "else-if" behavior
            }
        }

        String status = reasons.isEmpty() ? "ELIGIBLE" : "NOT_ELIGIBLE";

        return new EligibilityEngineResult(status, reasons);
    }
}