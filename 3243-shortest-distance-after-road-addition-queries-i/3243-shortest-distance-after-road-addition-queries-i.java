class Solution {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
         
        // Graph initialized as an adjacency list
          List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Initially add the roads from city i to city i + 1 for 0 <= i < n - 1
        for (int i = 0; i < n - 1; i++) {
            graph.get(i).add(i + 1);
        }

        // Result to store the shortest path length after each query
        int[] result = new int[queries.length];
        
        // Process each query
        for (int i = 0; i < queries.length; i++) {
            // Add the new road to the graph
            int u = queries[i][0];
            int v = queries[i][1];
            graph.get(u).add(v);

            // Perform BFS to find the shortest path from city 0 to city n-1
            result[i] = bfs(graph, n);
        }

        return result;
    }

    private int bfs(List<List<Integer>> graph, int n) {
        // Distance array initialized to a large value
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[0] = 0;

        // BFS queue
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        // Perform BFS
        while (!queue.isEmpty()) {
            int city = queue.poll();

            for (int neighbor : graph.get(city)) {
                if (dist[neighbor] == -1) { // If the neighbor hasn't been visited
                    dist[neighbor] = dist[city] + 1;
                    queue.add(neighbor);
                }
            }
        }

        // The distance to the last city n-1
        return dist[n - 1];
        
    }
}