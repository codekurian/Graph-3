import java.util.List;

class Problem1 {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        return kruskals(n,wells,pipes);


    }
    //SC:O(n + m)
    //TC:O((n + m) log(n + m))
    public int kruskals(int n, int[] wells, int[][] pipes) {
        int m = pipes.length;
        List<int[]> edges = new ArrayList<>();
        int[] parent = new int[n+1];
        //default all parents to itself to start with
        for(int i=0;i<=n;i++){
            parent[i]=i;
        }

        int cost = 0;

        //added all edges and assumed a dummy node 0 ; 
        for(int i=1;i<=n;i++){
            edges.add(new int[]{0,i, wells[i-1]});

        }

        for(int j = 0;j<m ;j++){
            edges.add(pipes[j]);
        }

        Collections.sort(edges,(a,b)-> a[2]-b[2]);

        // find the parent and if the parent is not same make it same and that adds it to the MST

        for(int[]edge: edges){
            int x =   edge[0];
            int y =     edge[1];
            int weight = edge[2];
            int px = findParent(parent,x);
            int py = findParent(parent,y);
            if(px != py){
                cost += weight;
                parent[py] = px;
            }
        }
        return cost;
    }

    int findParent(int[] parent,int x){

        //return parent[x] == x ? x : (parent[x] = findParent(parent, parent[x]));

        if(parent[x] != x){
            int up =  findParent(parent,parent[x]);
            parent[x] = up;
        }

        return parent[x];

    }
    /*
    ✅ Time Complexity:
    Building adjacency list: O(N + M)
    Prim’s Algorithm (using Min Heap): O((N + M) log(N))
    Total: O((N + M) log(N))
    ✅ Space Complexity:
    Adjacency List: O(N + M)
    Min Heap: O(N)
    Visited Array: O(N)
    Total: O(N + M)
    */
    public int prims(int n, int[] wells, int[][] pipes) {
        int m = pipes.length;
        List<int[]> edges = new ArrayList<>();
        boolean[] visited = new boolean[n+1];
        int cost = 0;
        Map<Integer,List<int[]>> map = new HashMap<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> a[1]-b[1]);
        //added all edges and assumed a dummy node 0 ; 
        for(int i=1;i<=n;i++){
            edges.add(new int[]{0,i, wells[i-1]});

        }

        for(int j = 0;j<m ;j++){
            edges.add(pipes[j]);
        }

        for(int[] edge:edges){
            int from = edge[0];
            int to =  edge[1];
            int w = edge[2];
            map.putIfAbsent(from,new ArrayList<int[]>());
            map.get(from).add(new int[]{to,w});

            map.putIfAbsent(to,new ArrayList<int[]>());
            map.get(to).add(new int[]{from,w});
        }

        pq.add(new int[]{0,0});

        while(!pq.isEmpty()){
            int[] curr =  pq.poll();
            int curNode = curr[0];
            int weight = curr[1];
            if(visited[curNode]) continue;
            visited[curNode] = true;
            cost+=weight;
            List<int[]> neighbours  = map.get(curNode);
            for(int[] ne : neighbours){
                int toNode =  ne[0];
                int toWight =  ne[1];
                if(!visited[toNode]){
                    pq.add(ne);
                }


            }
        }
        return cost;


    }
}