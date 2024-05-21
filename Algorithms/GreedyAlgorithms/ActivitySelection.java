package Algorithms.GreedyAlgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Activity {
    int start;
    int end;

    public Activity(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class ActivitySelection {

    public static List<Activity> selectActivities(List<Activity> activities) {
        List<Activity> selectedActivities = new ArrayList<>();

        // Sort activities by their end times
        Collections.sort(activities, Comparator.comparingInt(activity -> activity.end));

        Activity lastSelectedActivity = null;

        for (Activity activity : activities) {
            // If the activity starts after the end time of the last selected activity,
            // select it
            if (lastSelectedActivity == null || activity.start >= lastSelectedActivity.end) {
                selectedActivities.add(activity);
                lastSelectedActivity = activity;
            }
        }

        return selectedActivities;
    }

    public static void main(String[] args) {
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity(1, 4));
        activities.add(new Activity(3, 5));
        activities.add(new Activity(0, 6));
        activities.add(new Activity(5, 7));
        activities.add(new Activity(3, 8));
        activities.add(new Activity(5, 9));
        activities.add(new Activity(6, 10));
        activities.add(new Activity(8, 11));
        activities.add(new Activity(8, 12));
        activities.add(new Activity(2, 13));
        activities.add(new Activity(12, 14));

        List<Activity> selectedActivities = selectActivities(activities);

        System.out.println("Selected Activities:");
        for (Activity activity : selectedActivities) {
            System.out.println("[" + activity.start + ", " + activity.end + "]");
        }
    }
}
