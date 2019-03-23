public interface Counter {

    int getCount();

    void setCount(int n);

    /**
     * increment result by 1
     */
    void count();

    /**
     * Count n times
     * @param n
     */
    void count(int n);

    /**
     * Count from 0 to n and print count to screen
     * @param n
     */
    void countToN(int n);
}
