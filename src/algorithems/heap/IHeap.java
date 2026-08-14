package algorithems.heap;

public interface IHeap<T> {
    boolean add(T val);

    T pop();

    T peek();

    int size();

    boolean isEmpty();
}
