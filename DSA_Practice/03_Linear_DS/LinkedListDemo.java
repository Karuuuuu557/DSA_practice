/*
 * TOPIC: Singly Linked List
 * Concept intro:
 * Think of a scavenger hunt card chain: each card has data + direction to the next card.
 * Why it matters: unlike arrays, insertion/deletion near head is O(1) without shifting elements.
 * Link to earlier track: this extends array traversal knowledge from 01_Basics.
 */
public class LinkedListDemo {
    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    // Beginner example: build and traverse a simple list.
    static Node beginnerBuildAndTraverse() {
        Node head = null; // Start with empty list so each insertion step is visible.
        head = insertFront(head, 30); // Insert 30 first; it becomes head.
        head = insertFront(head, 20); // Insert 20 at front; 20 -> 30.
        head = insertFront(head, 10); // Insert 10 at front; 10 -> 20 -> 30.
        System.out.print("Beginner list: ");
        print(head); // Traverse from head until null to confirm links are correct.
        return head;
    }

    // Intermediate example: delete first matching node with edge-case handling.
    static void intermediateDeleteExample(Node head) {
        Node updated = deleteFirstMatch(head, 20); // Remove middle value to show pointer relinking.
        System.out.print("Intermediate after delete(20): ");
        print(updated);
        updated = deleteFirstMatch(updated, 99); // Non-existent delete should leave list unchanged.
        System.out.print("Intermediate after delete(99): ");
        print(updated);
    }

    // Pro example: reverse list in-place with O(1) extra space.
    static void proReverseExample(Node head) {
        Node prev = null, cur = head;
        while (cur != null) {
            Node nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        System.out.print("Pro reversed list: ");
        print(prev);
    }

    static Node insertFront(Node head, int x) {
        Node n = new Node(x);
        n.next = head;
        return n;
    }

    static Node deleteFirstMatch(Node head, int target) {
        if (head == null) return null;
        if (head.val == target) return head.next;
        Node cur = head;
        while (cur.next != null && cur.next.val != target) cur = cur.next;
        if (cur.next != null) cur.next = cur.next.next;
        return head;
    }

    static void print(Node head) {
        for (Node c = head; c != null; c = c.next) System.out.print(c.val + (c.next != null ? " -> " : "\n"));
    }

    public static void main(String[] args) {
        Node head = beginnerBuildAndTraverse();
        intermediateDeleteExample(head);
        proReverseExample(head);
    }
}

/*
 * Common pitfalls:
 * 1) Forgetting to return/update head after insert/delete at front.
 * 2) Dereferencing cur.next without null checks.
 * 3) Losing the remaining chain during reverse (always store next first).
 *
 * Practice problems:
 * Easy: count nodes iteratively.
 * Medium: find middle node with slow/fast pointers.
 * Hard: detect and remove a cycle.
 */
