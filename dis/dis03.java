public class dis03 {
    public static void main(String[] args){
        int[] LL = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0 };
        int[] UR = { 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
        int[][] S = {
                { 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0}
        };
        fillGrid(LL, UR, S);
        int [][] Target= {
                { 0, 11, 12, 13, 14 },
                { 1,  0, 15, 16, 17 },
                { 2,  3,  0, 18, 19 },
                { 4,  5,  6,  0, 20 },
                { 7,  8,  9, 10,  0 }
        };

    }

    public static void fillGrid(int[] LL, int[] UR, int[][] S){
        int N = S.length;
        int kL, kR;
        kL = kR = 0;
        for (int i = 0; i < N; i += 1){
            for(int j=0;j < N;j+=1){
                if(j<i){
                    S[i][j]=LL[kL];
                    kL++;
                } else if (j==i) {
                    S[i][j]=0;
                }else{
                    S[i][j]=UR[kR];
                    kR++;
                }
            }
        }
    }
}
