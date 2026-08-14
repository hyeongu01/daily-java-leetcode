package algorithems.heap;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap<T extends Comparable<T>> implements IHeap<T> {
    List<T> data;
    int size;
    int maxSize;

    public MaxHeap(int maxSize) {
        this.data = new ArrayList<T>(maxSize);
        this.size = 0;
        this.maxSize = maxSize;
    }

    public MaxHeap() {
        this(512);
    }


    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return data.getFirst();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size >= maxSize;
    }

    public boolean add(T val) {
        if (isFull()) {
            return false;
        }
        data.add(val);
        heapifyUp(size);
        size ++;
        return true;
    }

    private void heapifyUp(int idx) {
        if (idx == 0) {
            return;
        }
        T current = data.get(idx);
        T parent = data.get(parentIdx(idx));

        if (parent.compareTo(current) < 0) { // parent < current
            data.set(idx, parent);
            data.set(parentIdx(idx), current);
            heapifyUp(parentIdx(idx));
        }
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T result = data.getFirst();
        T last = data.removeLast();
        size --;

        if (!isEmpty()) {
            data.set(0, last);
            heapifyDown(0);
        }
        return result;
    }

    private void heapifyDown(int idx) {
        if (isLeaf(idx)) {
            return;
        }

        T left = data.get(leftIdx(idx));
        T current = data.get(idx);
        if (rightIdx(idx) < size) {
            T right = data.get(rightIdx(idx));

            if (left.compareTo(right) < 0) { // left < right
                if (current.compareTo(right) < 0) { // current < right
                    data.set(rightIdx(idx), current);
                    data.set(idx, right);
                    heapifyDown(rightIdx(idx));
                }
            } else { // left >= right
                if (current.compareTo(left) < 0) { // current < left
                    data.set(leftIdx(idx), current);
                    data.set(idx, left);
                    heapifyDown(leftIdx(idx));
                }
            }
        } else {
            if (current.compareTo(left) < 0) { // current < left
                data.set(leftIdx(idx), current);
                data.set(idx, left);
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

    private int rightIdx(int idx) {
        return (idx * 2) + 2;
    }

    private boolean isLeaf(int idx) {
        return leftIdx(idx) >= size;
    }
}
