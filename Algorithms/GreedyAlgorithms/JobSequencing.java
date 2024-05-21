package Algorithms.GreedyAlgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Job {
    char id;
    int deadline;
    int profit;

    public Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobSequencing {

    public static List<Character> sequenceJobs(List<Job> jobs) {
        List<Character> sequence = new ArrayList<>();

        // Sort jobs by profit in descending order
        Collections.sort(jobs, Comparator.comparingInt(job -> job.profit));

        // Find maximum deadline
        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        // Initialize array to track filled slots
        boolean[] slots = new boolean[maxDeadline];

        // Iterate through sorted jobs to select the highest profit job for each
        // deadline
        for (Job job : jobs) {
            for (int i = Math.min(maxDeadline - 1, job.deadline - 1); i >= 0; i--) {
                if (!slots[i]) {
                    slots[i] = true;
                    sequence.add(job.id);
                    break;
                }
            }
        }

        return sequence;
    }

    public static void main(String[] args) {
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job('a', 2, 100));
        jobs.add(new Job('b', 1, 19));
        jobs.add(new Job('c', 2, 27));
        jobs.add(new Job('d', 1, 25));
        jobs.add(new Job('e', 3, 15));

        List<Character> sequence = sequenceJobs(jobs);

        System.out.println("Job Sequence:");
        for (char jobId : sequence) {
            System.out.print(jobId + " ");
        }
    }
}
