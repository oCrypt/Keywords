package me.cahrypt.com.keywords.container;

public class Container<T> {
    private T val;

    public Container(T val) {
        this.val = val;
    }

    public T get() { return val; }

    public void set(T val) { this.val = val; }
}
