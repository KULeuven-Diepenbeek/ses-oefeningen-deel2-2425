package concurrency.oef5;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.I_Result;

@JCStressTest
@Outcome(id="0", expect = Expect.ACCEPTABLE, desc = "Incremented and decremented atomically")
@Outcome(id="", expect = Expect.FORBIDDEN, desc = "Unexpected counter value")
@State
public class CounterTest {

    private final Counter counter = new Counter();
    private final int N = 5;

    @Actor
    public void incrementer() {
        for (int i = 0; i < N; i++) {
            counter.increment();
        }
    }

    @Actor
    public void decrementer() {
        for (int i = 0; i < N; i++) {
            counter.decrement();
        }
    }

    @Arbiter
    public void arbiter(I_Result result) {
        result.r1 = counter.getCount();
    }

}