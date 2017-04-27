package ehist.mem;

import java.util.Iterator;

/**
 * <p>
 * === ReadOnlyArray Class ===
 * </p><p>
 * Date : March 13, 2017
 * </p><p>
 *  An interface to an existing array that prevents change to the
 * existing array. Changes to the elements is limited by the element
 * itself.
 * </p>
 *
 * @since EHist 1.0
 * @author Michael van Dyk
 *
 * @param <T> The type of element stored in the array.
 */
public final class ReadOnlyArray<T> implements Iterable<T> {

    /** The reference to an existing array. This array cannot
     * modified externally and will not be modified internally. **/
    private final T[] array;

    /**
     *  Creates this as an interface to the backing array. Allows for external
     * calls to access elements of the array.
     * @param array the array to create the interface to
     * @throws NullPointerException if the given array is null
     */
    public ReadOnlyArray(T[] array) {
        if (array == null) {
            throw new NullPointerException();
        }
        this.array = array;
    }

    /**
     *  Gets the element at the given index.
     * @param index the index of the element
     * @return the element at the given index
     */
    public T get(int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException(String.format("Index out of range [0, %d) : %d", array.length, index));
        }
        return (array[index]);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Iterator<T> iterator() {
        return (new ReadOnlyArrayIterator());
    }

    /**
     * @return the size of the array
     */
    public int size() {
        return (array.length);
    }

    /**
     * Iterator for the ReadOnlyArray.
     */
    private final class ReadOnlyArrayIterator implements Iterator<T> {

        /** current position in the array **/
        private int position = 0;

        /** makes an iterator */
        public ReadOnlyArrayIterator() { }

        @Override
        public boolean hasNext() {
            return (position < array.length);
        }

        @Override
        public T next() {
            return (hasNext() ? array[position++] : null);
        }
    }
}
