package Algorithms.GreedyAlgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class IntervalScheduling {

    public static List<Interval> scheduleIntervals(List<Interval> intervals) {
        List<Interval> schedule = new ArrayList<>();

        // Sort intervals by end time
        Collections.sort(intervals, Comparator.comparingInt(interval -> interval.end));

        // Add the first interval to the schedule
        if (!intervals.isEmpty())
            schedule.add(intervals.get(0));

        // Iterate through intervals to find compatible ones
        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            Interval lastScheduled = schedule.get(schedule.size() - 1);

            // If the current interval's start time is after the last scheduled interval's
            // end time, add it to the schedule
            if (current.start >= lastScheduled.end) {
                schedule.add(current);
            }
        }

        return schedule;
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(2, 4));
        intervals.add(new Interval(3, 6));
        intervals.add(new Interval(5, 7));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(9, 11));

        List<Interval> schedule = scheduleIntervals(intervals);

        System.out.println("Schedule:");
        for (Interval interval : schedule) {
            System.out.println("[" + interval.start + ", " + interval.end + "]");
        }
    }
}
