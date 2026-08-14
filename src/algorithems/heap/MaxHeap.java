package algorithems.heap;

public class MaxHeap<T extends Comparable<T>> extends MinHeap<T> implements IHeap<T> {

    public MaxHeap(int maxSize) {
        super(maxSize);
    }

    public MaxHeap() {
        this(512);
    }

    @Override
    protected void heapifyUp(int idx) {
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

    @Override
    protected void heapifyDown(int idx) {
        if (leftIdx(idx) >= size) {
            return;
        }

        int biggerChildIdx;
        T current = data.get(idx);
        T biggerChild;

        if (rightIdx(idx) < size) {
            T left = data.get(leftIdx(idx));
            T right = data.get(rightIdx(idx));
            biggerChildIdx = left.compareTo(right) < 0 ? rightIdx(idx) : leftIdx(idx); // left < right
        } else {
            biggerChildIdx = leftIdx(idx);
        }
        biggerChild = data.get(biggerChildIdx);

        if (current.compareTo(biggerChild) < 0) { // current < biggerChild
            data.set(idx, biggerChild);
            data.set(biggerChildIdx, current);
            heapifyDown(biggerChildIdx);
        }
    }
}
