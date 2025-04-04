package multithreading;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.I_Result;

@JCStressTest
@Outcome(id="0", expect = Expect.ACCEPTABLE, desc="Normal")
@Outcome(id="", expect = Expect.FORBIDDEN, desc="Race condition")
@State
public class MyCounterTest {

    private final CounterDemo.Counter counter = new CounterDemo.Counter();

    @Actor
    public void actor1() {
        counter.increment();
    }

    @Actor
    public void actor2() {
        counter.decrement();
    }

    @Arbiter
    public void arbiter(I_Result r) {
        r.r1 = counter.getCount();
    }
}
