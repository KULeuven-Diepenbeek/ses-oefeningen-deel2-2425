package records.oef03;

public record Email(
        String address) {
    public Email {
        if (address == null)
            throw new NullPointerException("address");
        if (address.indexOf('@') == -1)
            throw new IllegalArgumentException("address");
        if (address.lastIndexOf('@') !=
            address.indexOf('@'))
            throw new IllegalArgumentException("address");
        if (!address.endsWith(".com") &&
            !address.endsWith(".be")) {
            throw new IllegalArgumentException("address");
        }
    }
}
