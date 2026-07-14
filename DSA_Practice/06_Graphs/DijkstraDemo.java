import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
 * TOPIC: Dijkstra shortest path (non-negative weights)
 * Concept intro:
 * GPS-like logic: always expand the currently cheapest known location first.
 * Why it matters: gives shortest path in weighted graphs faster than brute force.
 * Link: combines adjacency lists (GraphRepresentation) + min-heap (HeapPriorityQueueDemo).
 */
public class DijkstraDemo {
    static class Edge { int to, w; Edge(int to, int w) { this.to = to; this.w = w; } }

    public static void main(String[] args) {
        int n = 5;
        List<List<Edge>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());
        add(g,0,1,4); add(g,0,2,1); add(g,2,1,2); add(g,1,3,1); add(g,2,3,5); add(g,3,4,3);

        // Beginner example: standard shortest distances from source 0.
        int src = 0; // Source node where all distances start.
        int[] dist = new int[n]; // dist[i] = best known distance from src to i.
        Arrays.fill(dist, Integer.MAX_VALUE); // Infinity means "not reached yet".
        dist[src] = 0; // Source to itself is always zero.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0])); // Min-heap by distance.
        pq.offer(new int[]{0, src}); // Push {distance,node} for processing frontier.

        while (!pq.isEmpty()) { // Keep expanding cheapest frontier node.
            int[] cur = pq.poll(); // Get node with smallest queued distance.
            int d = cur[0], u = cur[1];
            if (d != dist[u]) continue; // Ignore stale heap entries after a better path was found.
            for (Edge e : g.get(u)) { // Relax every outgoing edge.
                int nd = d + e.w; // Candidate distance through current node.
                if (nd < dist[e.to]) { // Keep only improvements.
                    dist[e.to] = nd;
                    pq.offer(new int[]{nd, e.to}); // Re-queue improved node state.
                }
            }
        }
        System.out.println("Beginner shortest distances from 0: " + Arrays.toString(dist));

        // Intermediate example: query one specific destination cost.
        int destination = 4;
        System.out.println("Intermediate shortest cost 0->4: " + dist[destination]);

        // Pro example: discussion prompt for path reconstruction trade-off.
        System.out.println("Pro note: add a parent[] array during relaxations to reconstruct actual paths.");
    }

    static void add(List<List<Edge>> g, int u, int v, int w) {
        g.get(u).add(new Edge(v, w));
        g.get(v).add(new Edge(u, w));
    }
}

/*
 * Common pitfalls:
 * 1) Using Dijkstra with negative weights (invalid; use Bellman-Ford).
 * 2) Forgetting stale-entry check, causing unnecessary extra work.
 * 3) Storing distances in int when sums can overflow for huge weights.
 *
 * Practice problems:
 * Easy: modify to directed graph only.
 * Medium: output parent[] and reconstruct path from source to target.
 * Hard: adapt to multi-source shortest paths.
 */
