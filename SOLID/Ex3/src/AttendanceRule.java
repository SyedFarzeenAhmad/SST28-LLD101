public class AttendanceRule implements EligibilityRule {

    private final int minAttendance;

    public AttendanceRule(int minAttendance) {
        this.minAttendance = minAttendance;
    }

    public String validate(StudentProfile s) {
        if (s.attendancePct < minAttendance) {
            return "attendance below " + minAttendance;
        }
        return null;
    }
}