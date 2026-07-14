/*
 * TOPIC: Binary Search Tree
 * Concept intro:
 * Like a sorted dictionary tree: smaller keys go left, larger keys go right.
 * Why it matters: search/insert/delete are fast on balanced data.
 * Link: combines BinaryTree traversal + BinarySearch range-halving idea.
 */
public class BinarySearchTreeDemo {
    static class Node { int v; Node l, r; Node(int v) { this.v = v; } }

    static Node insert(Node n, int x) {
        if (n == null) return new Node(x);
        if (x < n.v) n.l = insert(n.l, x); else if (x > n.v) n.r = insert(n.r, x);
        return n;
    }

    static boolean search(Node n, int x) {
        while (n != null) {
            if (x == n.v) return true;
            n = (x < n.v) ? n.l : n.r;
        }
        return false;
    }

    static Node min(Node n) { while (n.l != null) n = n.l; return n; }
    static Node delete(Node n, int x) {
        if (n == null) return null;
        if (x < n.v) n.l = delete(n.l, x);
        else if (x > n.v) n.r = delete(n.r, x);
        else {
            if (n.l == null) return n.r;
            if (n.r == null) return n.l;
            Node succ = min(n.r);
            n.v = succ.v;
            n.r = delete(n.r, succ.v);
        }
        return n;
    }
    static void inorder(Node n) { if (n == null) return; inorder(n.l); System.out.print(n.v + " "); inorder(n.r); }

    public static void main(String[] args) {
        // Beginner example: build BST then search.
        Node root = null; // Start empty because BST grows with inserts.
        int[] vals = {8, 3, 10, 1, 6, 14, 4, 7, 13};
        for (int v : vals) root = insert(root, v);
        System.out.println("Beginner search(6): " + search(root, 6));

        // Intermediate example: inorder traversal should be sorted.
        System.out.print("Intermediate inorder(sorted): "); inorder(root); System.out.println();

        // Pro example: delete node with two children and verify structure stays valid.
        root = delete(root, 3);
        System.out.print("Pro inorder after delete(3): "); inorder(root); System.out.println();
        System.out.println("Pro trade-off: unbalanced BST can degrade to O(n), consider AVL/Red-Black in production.");
    }
}

/*
 * Common pitfalls:
 * 1) Forgetting special handling for delete with two children.
 * 2) Not defining duplicate-key policy.
 * 3) Assuming all BST operations are O(log n) even when tree is skewed.
 *
 * Practice problems:
 * Easy: find minimum and maximum keys.
 * Medium: validate if a tree is a BST.
 * Hard: find kth smallest without converting to array.
 */
