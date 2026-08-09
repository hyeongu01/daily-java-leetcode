package utils;

public class TestCase<T, M> {
    public T input;
    public M output;

    public TestCase(T input, M output) {
        this.input = input;
        this.output = output;
    }
}
