import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = items.length - 1;
        nextLast = 0;
        size = 0;
    }
    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resizeUp();
        }
        items[nextFirst] = x;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size++;
    }
    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resizeUp();
        }
        items[nextLast] = x;
        nextLast = (nextLast + 1 + items.length) % items.length;
        size++;
    }

    public void resizeUp() {
        T[] newArray = (T[]) new Object[size * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = get(i);
        }
        items = newArray;
        nextLast = size;
        nextFirst = items.length - 1;
    }
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            returnList.add(get(i));
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else if (size < (items.length / 4)) {
            resizeDown();
        }
        T returnValue = get(0);
        items[(nextFirst + 1 + items.length) % items.length] = null;
        nextFirst = (nextFirst + 1 + items.length) % items.length;
        size--;
        return returnValue;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else if (size < (items.length / 4)) {
            resizeDown();
        }
        T returnValue = get(size - 1);
        items[(nextLast - 1 + items.length) % items.length] = null;
        nextLast = (nextLast - 1 + items.length) % items.length;
        size--;
        return returnValue;
    }

    public void resizeDown() {
        T[] newArray = (T[]) new Object[items.length / 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = get(i);
        }
        items = newArray;
        nextLast = size;
        nextFirst = items.length - 1;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return items[(index + nextFirst + 1) % items.length];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive");
    }
}
