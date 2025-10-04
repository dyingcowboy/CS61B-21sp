package deque;

public interface Deque<T> {
        public void addFirst(T item);
        public void addLast(T item);
        public boolean isEmpty();
        public int size();
        public T removeFirst();
        public T removeLast();
        public T get(int index);
        default void printDeque(){
            for(int i = 0; i< size(); i++){
                System.out.print(get(i) + " ");
            }
            System.out.println();
        }
}
