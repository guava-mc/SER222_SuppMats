import java.util.Arrays;

public class ThisCodeWasMadeForSteppin {

    static int[] myNums;
    static int n = 0;

    public static void main(String[] args) {

        String string = "this is a variable that belongs to main!";
        try {
            n = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("must pass an integer value as first arg");
            System.exit(1);
        }

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
    private static void printNums() {
        if (myNums.length < 1){
            System.out.println("No data, dawg.");
        return;
        }
        String numsString = "";
        for(Integer i : myNums){
            numsString += i + ", ";
        }
        System.out.println(numsString.substring(0, numsString.length()-2));
    }
}
