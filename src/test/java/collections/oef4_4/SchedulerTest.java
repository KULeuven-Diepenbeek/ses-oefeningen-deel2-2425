package collections.oef4_4;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
public class SchedulerTest {

    @Test
    public void test_scheduler() {
        var scheduler = new Scheduler();
        var job1 = new Job(1, "First job");
        var job2 = new Job(2, "Second job");
        var job3 = new Job(3, "Third job");

        scheduler.schedule(job3);
        scheduler.schedule(job1);
        scheduler.schedule(job2);

        assertThat(scheduler.allJobs()).containsExactly(job1, job2, job3);
        assertThat(scheduler.nextJob(2)).isEqualTo(job2);
        assertThat(scheduler.nextJob(2.5)).isEqualTo(job3);
    }


}
