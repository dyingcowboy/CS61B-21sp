package deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private int size;
    private T[] items;
    int maxsize;
    int nextFirst = 4;
    int nextLast = 5;

    private int plusOne(int index){
        return (index + 1) % maxsize;
    }

    private int minusOne(int index){
        return (index - 1 + maxsize) % maxsize;
    }

    public ArrayDeque(){
        size = 0;
        maxsize = 8;
        items = (T [])new Object[maxsize];
    }

    @Override
    public void addFirst(T item){
        if(size == maxsize){
            resize(maxsize * 2);
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst = minusOne(nextFirst);
    }

    @Override
    public void addLast(T item){
        if(size == maxsize){
            resize(maxsize * 2);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size ++;
    }

    @Override
    public T removeLast(){
        if(maxsize >= 16 && size <= maxsize/4){
            resize( maxsize / 2 );
        }
        if(isEmpty()){
            return null;
        }
        nextLast = minusOne(nextLast);
        T returnItem = items[nextLast];
        items[nextLast] = null;
        size --;
        return returnItem;
    }

    @Override
    public T removeFirst(){
        if(maxsize >= 16 && size <= (maxsize / 4)){
            resize( maxsize / 2 );
        }
        if(isEmpty()){
            return null;
        }
        nextFirst = plusOne(nextFirst);
        T returnItem = items[nextFirst];
        items[nextFirst] = null;
        size --;
        return returnItem;
    }

    public int size(){
        return size;
    }

    public T get(int index){
        if (index < 0 || index >= size){
            return null;
        }
        int itemIndex = plusOne(nextFirst);
        for(int i = 0; i < index; i++){
            itemIndex = plusOne(itemIndex);
        }
        return items[itemIndex];
    }

    public void resize(int capacity){
        T[] o = (T[]) new Object[capacity];
        int i = 0;
        int index = plusOne(nextFirst);
        for(int j = 0; j < size; j++){
            o[i] = items[index];
            index = plusOne(index);
            i++;
        }
        nextLast = size;
        nextFirst = capacity - 1;
        items = o;
        maxsize = capacity;
    }
    public void printDeque(){
        for(int i = 0; i< size(); i++){
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public Iterator<T> iterator(){
        return new ArrayIterator();
    }
    public class ArrayIterator implements Iterator<T>{
        private int wizPos;
        private int iterated;
        private int initialSize;

        public ArrayIterator(){
            wizPos = plusOne(nextFirst);
            iterated = 0;
            initialSize = size;
        }

        public boolean hasNext(){
            // 用初始size判断，不受后续修改影响
            return iterated < initialSize;
        }

        public T next(){
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements to iterate");
            }
            T item = items[wizPos];
            wizPos = plusOne(wizPos);
            iterated++;
            return item;
        }
    }

}
