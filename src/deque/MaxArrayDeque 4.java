package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> c;
    public MaxArrayDeque(Comparator<T> C) {
        super();
        c = C;
    }

    public T max() {
        if (this.size() == 0) {
            return null;
        }
        T maximum = this.get(0);
        for (int i = 0; i < this.size(); i++) {
            if (c.compare(this.get(i), maximum) > 0) {
                maximum = this.get(i);
            }
        }
        return maximum;
    }

    public T max(Comparator<T> newC) {
        if (this.size() == 0) {
            return null;
        }
        T maximum = this.get(0);
        for (int i = 0; i < this.size(); i++) {
            if (newC.compare(this.get(i), maximum) > 0) {
                maximum = this.get(i);
            }
        }
        return maximum;
    }
}
