public class CreditsRule implements EligibilityRule {

    private final int minCredits;

    public CreditsRule(int minCredits) {
        this.minCredits = minCredits;
    }

    public String validate(StudentProfile s) {
        if (s.earnedCredits < minCredits) {
            return "credits below " + minCredits;
        }
        return null;
    }
}