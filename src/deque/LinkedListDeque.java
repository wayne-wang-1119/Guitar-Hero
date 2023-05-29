package deque;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    public static void main(String[] args) {
        Deque<Integer> lld = new LinkedListDeque<>();
    }
    private static class Node<T> {
        private T value;
        private Node<T> prev;
        private Node<T> next;

        Node(T v) {
            this.value = v;
        }
    }
    private Node<T> sentinel;
    private int size;
    private Node<T> head;

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }
    private class LinkedListDequeIterator implements Iterator<T> {
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

    public LinkedListDeque() {
        sentinel = new Node<T>(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        this.size = 0;
        this.head = sentinel.next;
    }

    @Override
    public void addFirst(T x) {
        Node<T> curHead = sentinel.next;
        Node<T> newHead = new Node<T>(x);
        newHead.next = curHead;
        newHead.prev = sentinel;
        curHead.prev = newHead;
        sentinel.next = newHead;
        this.size++;
        this.head = sentinel.next;

    }

    @Override
    public void addLast(T x) {
        Node<T> curTail = sentinel.prev;
        Node<T> newTail = new Node<T>(x);
        newTail.prev = curTail;
        newTail.next = sentinel;
        curTail.next = newTail;
        sentinel.prev = newTail;
        this.size++;
        this.head = sentinel.next;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node<T> dummy = sentinel.next;
        for (int i = 0; i < size; i++) {
            returnList.add(dummy.value);
            dummy = dummy.next;
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
        if (size == 0) {
            return null;
        }
        T res = this.head.value;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        this.head = sentinel.next;
        this.size--;
        return res;
    }

    @Override
    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        T res = sentinel.prev.value;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        this.size--;
        return res;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > this.size - 1) {
            return null;
        }
        Node<T> dummy = sentinel.next;
        for (int i = 0; i < this.size; i++) {
            if (i == index) {
                return dummy.value;
            } else {
                dummy = dummy.next;
            }
        }
        return null;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        if (index == 0) {
            T res = this.head.value;
            this.head = sentinel.next;
            return res;
        }
        this.head = this.head.next;
        return getRecursive(index - 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
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
        if (obj instanceof ArrayDeque otherObj) {
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
