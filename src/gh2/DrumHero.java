package gh2;
import deque.ArrayDeque;
import deque.Deque;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class DrumHero {
    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final Deque<Drums> reference = new ArrayDeque<>();
    private static Deque<Drums> played = new ArrayDeque<>();
    public static void main(String[] args) {
        for (int i = 0; i < 37; i++) {
            double freq = 440.0 * Math.pow(2.0, (i - 24.0) / 12.0);
            reference.addLast(new Drums(freq));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                reference.get(keyboard.indexOf(key)).pluck();
                played.addLast(reference.get(keyboard.indexOf(key)));
            }
            double sample = 0;
            /* compute the superposition of samples */
            for (int i = 0; i < reference.size(); i++) {
                sample += reference.get(i).sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < 37; i++) {
                reference.get(i).tic();
            }
        }
    }
}
