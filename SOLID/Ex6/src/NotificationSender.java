public abstract class NotificationSender {

    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) {
        this.audit = audit;
    }

    public final void send(Notification n) {

        if (n == null)
            throw new IllegalArgumentException("notification cannot be null");

        validate(n);      // channel-specific validation
        deliver(n);       // actual sending
        audit.add(auditEntry());
    }

    protected abstract void validate(Notification n);
    protected abstract void deliver(Notification n);
    protected abstract String auditEntry();
}