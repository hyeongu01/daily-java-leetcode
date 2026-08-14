package algorithems.heap;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> implements IHeap<T> {
    List<T> data;
    int size;
    int maxSize;

    public MinHeap(int maxSize) {
        this.data = new ArrayList<T>(maxSize);
        this.size = 0;
        this.maxSize = maxSize;
    }

    public MinHeap() {
        this(512);
    }

    public boolean add(T val) {
        if (size >= maxSize) {
            return false;
        }
        data.add(val);
        heapifyUp(size);
        size ++;
        return true;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T current = data.getFirst();
        T last = data.removeLast();
        size --;
        if (!isEmpty()) {
            data.set(0, last);
            heapifyDown(0);
        }
        return current;
    }

    public T peek() {
        return isEmpty() ? null : data.getFirst();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void heapifyUp(int idx) {
        if (idx == 0) {
            return;
        }
        T current = data.get(idx);
        T parent = data.get(parentIdx(idx));

        if (current.compareTo(parent) < 0) { // current < parent
            data.set(idx, parent);
            data.set(parentIdx(idx), current);
            heapifyUp(parentIdx(idx));
        }
    }

    private void heapifyDown(int idx) {
        if (leftIdx(idx) >= size) {
            return;
        }

        T current = data.get(idx);
        T left = data.get(leftIdx(idx));

        if (rightIdx(idx) < size) {
            T right = data.get(rightIdx(idx));

            if (right.compareTo(left) < 0) { // right < left
                if (current.compareTo(right) > 0) { // current > right
                    data.set(idx, right);
                    data.set(rightIdx(idx), current);
                    heapifyDown(rightIdx(idx));
                }
            } else {
                if (current.compareTo(left) > 0) { // current > left
                    data.set(idx, left);
                    data.set(leftIdx(idx), current);
                    heapifyDown(leftIdx(idx));
                }
            }

        } else {
            if (current.compareTo(left) > 0) { // current > left
                data.set(idx, left);
                data.set(leftIdx(idx), current);
                heapifyDown(leftIdx(idx));
            }
        }
    }

    private int parentIdx(int idx) {
        return (idx - 1) / 2;
    }

    private int leftIdx(int idx) {
        return (idx * 2) + 1;
    }

    private  int rightIdx(int idx) {
        return (idx * 2) + 2;
    }
}
