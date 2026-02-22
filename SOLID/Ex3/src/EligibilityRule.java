public interface EligibilityRule {
    /**
     * @return reason if rule fails, otherwise null
     */
    String validate(StudentProfile s);
}