package records.oef03;


public record Email(String address)  {
    public Email {
        if (address == null)
            throw new NullPointerException("address must not be null");
        if (!address.contains("@"))
            throw new IllegalArgumentException("address must contain @ character");
        if (address.indexOf('@') != address.lastIndexOf('@'))
            throw new IllegalArgumentException("address must contain exactly one @ character");
        if (!address.endsWith(".com") && !address.endsWith(".be"))
            throw new IllegalArgumentException("address must end with valid root domain");
    }
}
