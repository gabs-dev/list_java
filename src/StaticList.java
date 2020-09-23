public class StaticList<T> {

    private int last;
    private T[] vector;

    public StaticList() {
        this.last = 0;
        this.vector = (T[]) new Object[10];
    }

    public void add(T item) {
        if (last == vector.length) {
            resize();
        }
        vector[last] = item;
        last++;
    }

    public void add(T item, int index) {
        if ((index < 0))
            throw new IllegalArgumentException("Invalid position");
        if (isFullList())
            throw new IllegalArgumentException("The list is full");
        if (index > last)
            add(item);
        relocateItems(index);
        vector[index] = item;
        last++;
    }

    public T remove(T key)  {
        if (isEmptyList())
            throw new IllegalArgumentException("The list is empty");
        if (key == null)
            throw new NullPointerException("The parameter can't be null");
        int i = 0;
        while (i < last && !vector[i].equals(key))
            i++;
        if (i >= last)
            return null;
        T item = this.vector[i];
        last -= 1;
        T[] aux = (T[]) new Object[size()];
        for (int j = 0; j < i; j++)
            aux[j] = vector[j];
        for (int k = i; k < last; k++)
            aux[k] = this.vector[k + 1];
        this.vector = aux;
        return item;
    }

    public T find(T item) {
        if (isEmptyList())
            throw new IllegalArgumentException("The list is empty");
        if (item == null)
            throw new NullPointerException("The parameter can't be null");
        int i = 0;
        while (i < last && !this.vector[i].equals(item))
            i++;
        if (i >= last)
            return null;
        else
            return this.vector[i];
    }

    public void merge(T[] list) {
        
    }

    public int size() {
        return vector.length;
    }

    public void showList() {
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] != null)
                System.out.print(vector[i] + " ");
        }
    }

    private void resize() {
        T[] aux = (T[]) new Object[2 * vector.length];
        copyElements(aux);
        vector = aux;
    }

    private void copyElements(T[] aux) {
        for (int i = 0; i < vector.length; i++) {
            aux[i] = vector[i];
        }
    }

    private void relocateItems(int index) {
        T[] aux = (T[]) new Object[vector.length];
        for (int i = 0; i < vector.length; i++) {
            if(vector[i] != null) {
                if (i >= index)
                    aux[i + 1] = vector[i];
                else
                    aux[i] = vector[i];
            }
        }
        vector = aux;
    }

    private boolean isFullList() {
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] == null) {
                return false;
            }
        }
        return true;
    }

    private boolean isEmptyList() {
        return last == 0;
    }

}
