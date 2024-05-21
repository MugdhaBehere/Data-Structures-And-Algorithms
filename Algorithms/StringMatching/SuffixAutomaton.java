package Algorithms.StringMatching;

import java.util.*;

public class SuffixAutomaton {
    static class State {
        Map<Character, State> transitions;
        State suffixLink;
        int length;
        boolean isClone;

        State(int length) {
            this.transitions = new HashMap<>();
            this.suffixLink = null;
            this.length = length;
            this.isClone = false;
        }
    }

    private State root;
    private State last;

    public SuffixAutomaton(String text) {
        root = new State(0);
        last = root;
        for (char c : text.toCharArray()) {
            extend(c);
        }
        setSuffixLinks();
    }

    private void extend(char c) {
        State newState = new State(last.length + 1);
        State current = last;
        while (current != null && !current.transitions.containsKey(c)) {
            current.transitions.put(c, newState);
            current = current.suffixLink;
        }
        if (current == null) {
            newState.suffixLink = root;
        } else {
            State next = current.transitions.get(c);
            if (next.length == current.length + 1) {
                newState.suffixLink = next;
            } else {
                State clone = new State(current.length + 1);
                clone.transitions.putAll(next.transitions);
                clone.suffixLink = next.suffixLink;
                clone.isClone = true;
                newState.suffixLink = next.suffixLink = clone;
                while (current != null && current.transitions.get(c) == next) {
                    current.transitions.put(c, clone);
                    current = current.suffixLink;
                }
            }
        }
        last = newState;
    }

    private void setSuffixLinks() {
        Queue<State> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            State current = queue.poll();
            for (char c : current.transitions.keySet()) {
                State child = current.transitions.get(c);
                if (!child.isClone) {
                    queue.add(child);
                }

                State suffixLink = current.suffixLink;
                while (suffixLink != null && !suffixLink.transitions.containsKey(c)) {
                    suffixLink = suffixLink.suffixLink;
                }
                child.suffixLink = suffixLink != null ? suffixLink.transitions.get(c) : root;
            }
        }
    }

    public boolean contains(String pattern) {
        State current = root;
        for (char c : pattern.toCharArray()) {
            if (!current.transitions.containsKey(c)) {
                return false;
            }
            current = current.transitions.get(c);
        }
        return true;
    }

    public static void main(String[] args) {
        String text = "banana";
        SuffixAutomaton sa = new SuffixAutomaton(text);
        System.out.println("Contains 'ana': " + sa.contains("ana")); // Output: Contains 'ana': true
        System.out.println("Contains 'xyz': " + sa.contains("xyz")); // Output: Contains 'xyz': false
    }
}
