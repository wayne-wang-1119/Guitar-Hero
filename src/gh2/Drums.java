package gh2;
import deque.Deque;
import deque.ArrayDeque;
import gh2.GuitarString;
public class Drums {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = 1.0; // energy decay factor

    /* Buffer for storing sound data. */

    private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public Drums(double frequency) {

        int capacity = (int) (Math.round(SR / frequency));
        Deque<Double> a = new ArrayDeque<>();
        for (int i = 0; i < capacity; i++) {
            a.addLast(0.0);
        }
        buffer = a;
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {

        //
        //       Make sure that your random numbers are different from each
        //       other. This does not mean that you need to check that the numbers
        //       are different from each other. It means you should repeatedly call
        //       Math.random() - 0.5 to generate new random numbers for each array index.
        Deque<Double> newBuffer = new ArrayDeque<>();
        for (int i = 0; i < buffer.size(); i++) {
            double r = Math.random() - 0.5;
            newBuffer.addLast(r);
        }
        buffer = newBuffer;
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {

        double nextFirst = buffer.get(1);
        //double curFirst = buffer.get(0);
        double curFirst = buffer.removeFirst();
        int sign = (Math.random() > 0.5) ? 1 : 0;
        buffer.addLast((-1 * sign) * DECAY * (0.5) * (curFirst + nextFirst));
        //double toAdd = DECAY * (1/2) * (curFirst + nextFirst);
        //buffer.addLast(toAdd);
        //buffer.removeFirst();
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }
}
