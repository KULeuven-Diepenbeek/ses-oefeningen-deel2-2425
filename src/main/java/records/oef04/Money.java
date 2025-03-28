package records.oef04;

public record Money(int amount, String currency) {
    public Money {
        if (currency == null) throw new NullPointerException("currency");

    }

    public Money add(Money other) {
        if (!this.currency().equals(other.currency())) throw new IllegalArgumentException("currency does not match");
        return new Money(this.amount() + other.amount(), this.currency());
    }
}
