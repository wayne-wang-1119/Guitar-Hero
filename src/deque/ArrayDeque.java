package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    public static void main(String[] args) {
        Deque<Integer> ad = new ArrayDeque<>();
    }


    private int start;
    private int end;
    private Object[] items;
    private int size;
    private final int defaultSize;

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
    private class ArrayDequeIterator implements Iterator<T> {
        private int pos = 0;
        public boolean hasNext() {
            return pos < size;
        }
        public T next() {
            T returnItem = get(pos);
            pos++;
            return returnItem;
        }
    }

    public ArrayDeque() {
        this.start = 4;
        this.end = 5;
        this.defaultSize = 8;
        this.items = new Object[this.defaultSize];
        this.size = 0;
    }

    public void resize(int factor) {
        Object[] newItems = new Object[factor * this.size];
        for (int i = 0; i < this.size; i++) {
            newItems[i + 1] = get(i);
        }
        this.start = 0;
        this.end = this.size + 1;
        this.items = newItems;
    }

    @Override
    public void addFirst(T x) {
        if (this.size == items.length) {
            //resize and update this.size
            resize(2);
        }
        //not full, can add, 2 cases
        this.items[this.start] = x;
        this.start--;
        this.size++;
        if (this.start < 0) {
            this.start = items.length - 1;
        }
    }

    @Override
    public void addLast(T x) {
        if (this.size == this.items.length) {
            //resize and update this.size
            resize(2);
        }
        //not full, can add, 2 cases
        this.items[this.end] = x;
        this.end++;
        this.size++;
        if (this.end >= items.length) {
            this.end = 0;
        }
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int j = 0; j < this.size; j++) {
            returnList.add(get(j));
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        if (this.size < 1) {
            return null;
        }

        int idx = this.start + 1;
        if (idx >= items.length) {
            idx = 0;
        }
        //idx is the current position of head
        T toRemove = (T) this.items[idx];
        this.items[idx] = null; //remove the current head
        this.start = idx;
        this.size--;
        if (this.size >= this.defaultSize && this.size < this.items.length / 4) {
            resize(2);
        }
        return toRemove;
    }

    @Override
    public T removeLast() {
        if (this.size < 1) {
            return null;
        }
        int idx = this.end - 1;
        if (idx < 0) {
            idx = this.items.length - 1;
        }
        T toRemove = (T) this.items[idx];
        this.items[idx] = null;
        this.end = idx;
        this.size--;

        if (this.size >= this.defaultSize && this.size < this.items.length / 4) {
            resize(2);
        }
        return toRemove;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        int newIdx = this.start + 1 + index;
        if (newIdx >= this.items.length) {
            newIdx = newIdx - this.items.length;
        }
        return (T) this.items[newIdx];
    }

    @Override
    public T getRecursive(int index) {
        return get(index);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ArrayDeque otherObj) {
            if (this.size != otherObj.size) {
                return false;
            }
            for (T x : this) {
                if (!otherObj.contains(x)) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof LinkedListDeque otherObj) {
            if (this.size != otherObj.size()) {
                return false;
            }
            for (T x : this) {
                if (!otherObj.contains(x)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean contains(T x) {
        for (int i = 0; i < size; i++) {
            if (this.get(i).equals(x)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.toList().toString();
    }
}
