package generics.oef03;

public record SuccessOrFail<S, F>(S successValue, F failValue) {
    /* Cannot create private constructor for a record;
       but we can check that either success or failure value is provided. */
    public SuccessOrFail {
        if (successValue == null && failValue == null) throw new IllegalArgumentException("Need either successValue or failValue");
        if (successValue != null && failValue != null) throw new IllegalArgumentException("Need either successValue or failValue");
    }

    public static <S, F> SuccessOrFail<S, F> success(S value) {
        return new SuccessOrFail<S, F>(value, null);
    }
    public static <S, F> SuccessOrFail<S, F> fail(F value) {
        return new SuccessOrFail<S, F>(null, value);
    }

    public boolean isSuccess() {
        return successValue != null;
    }
}
