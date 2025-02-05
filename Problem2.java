public class Problem2 {

    //TC:O(N^2)
    //TC:O(N)
    public int findCelebrity(int n) {
        int [] indegrees = new int[n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(knows(i,j)){
                    indegrees[j]++;
                    indegrees[i]--;
                }
            }
        }
        for(int k=0;k<n;k++){
            if(indegrees[k]>=n-1){
                return k;
            }
        }

        return -1;
    }
}
