package collections.oef4_4;

import java.util.*;

public class Scheduler {
    private final TreeSet<Job> jobs = new TreeSet<>(Comparator.comparing(Job::time));

    public void schedule(Job job) {
        jobs.add(job);
    }

    public List<Job> allJobs() {
        return new ArrayList<>(jobs);
    }

    public Job nextJob(double after) {
        return jobs.ceiling(new Job(after, "dummy"));
    }
}
