public class DebugMe {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Counter counter = new CounterClass();

        for(int i = 0; i < 25; i++) {
            counter.count();
        }

        System.out.println("We have counted "   //expect 25
                + counter.getCount());

        counter.count(25);
        System.out.println("We have counted "    //expect 50
                + counter.getCount());

        counter.countToN(10);                 //expect eleven lines going from 0 - 10.


        /**
         * NOTE TO MASON: break count() again.
         */
//        counter.setCount(0);                    //expect 100
//        while(counter.getCount() != 100)
//            counter.count();
//        System.out.println(counter.getCount());

        /**
         * stack trace demo
         */
//        counter.setCount(0);
//
//        counter.countToN(-1);

    }
}