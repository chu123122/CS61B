package gitlet;

public class Log {

    public static Commit HEAD=Commit.getHEAD();
    public static void logFollowTheCommitTree(){
       Commit check=HEAD;
        while(check!=null){
            printlnCommit(check);
            //if(check.parent().SHA1()==null||check.SHA1().equals(check.parent().SHA1()))break;
            check=check.parent();
        }
    }

    private static void printlnCommit(Commit commit){
        System.out.println("===");
        System.out.println("commit "+commit.SHA1());
        System.out.println("Date: "+commit.timeScale());
        System.out.println(commit.message());
        System.out.println();
    }
}
