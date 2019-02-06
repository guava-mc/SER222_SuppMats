import java.util.Arrays;

public class ThisCodeWasMadeForSteppin {

    static int[] myNums;
    static int n = 0;

    public static void main(String[] args) {
        if(args.length > 0)
            n = Integer.parseInt(args[0]);
        myNums = new int[n];

        stepStepSTEP(n);
        System.out.println(); //new line

        printNums();
        Arrays.sort(myNums);
        printNums();

        n = 1337;
    }

    //recursively count from 1 to n
    private static int stepStepSTEP(int n) {
        if(n <= 0) return n;
        System.out.println(stepStepSTEP(n - 1) + 1);
        myNums[ThisCodeWasMadeForSteppin.n-n] = n;
        return n;
    }

    //print myNums array
    private static void printNums(){
        String numsString = "";
        for(Integer i : myNums){
            numsString += i + ", ";
        }
        System.out.println(numsString.substring(0, numsString.length()-2));
    }
}
