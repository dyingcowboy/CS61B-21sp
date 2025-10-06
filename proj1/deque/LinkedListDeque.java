package deque;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    public class Node {
        public T item;
        public Node next;
        public Node prev;

        private Node(T i, Node h, Node g){
            item = i;
            prev = h;
            next = g;
        }
    }

    private int size;
    private Node sentinel;

    /* Create an empty List. */
    public LinkedListDeque(){
        size = 0;
        sentinel= new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(T item){
        Node p = new Node(item, sentinel, sentinel.next);

        sentinel.next.prev = p;
        sentinel.next = p;
        size ++;
    }

    @Override
    public void addLast(T item){
        Node p = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
        size ++;
    }
    @Override
    public T removeFirst(){
        T item;
        if(isEmpty()){
            return null;
        }
        else {
            size --;
            item = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next= sentinel.next.next;
            return item;
        }
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public T removeLast(){
        T item;
        if(isEmpty()){
            return null;
        }
        else{
            size --;
            item = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            return item;
        }
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size){
            return null;
        }
        Node p = sentinel.next;
        for(int i = 0; i < index; i++){
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        return helpGet(sentinel.next, index);
    }
    public void printDeque(){
        for(int i = 0; i< size(); i++){
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    // 改为private，避免外部调用
    private T helpGet(Node p, int index) {
        if (index == 0) {
            return p.item;
        }

        return helpGet(p.next, index - 1);
    }

    public Iterator<T> iterator(){
        return new SLListSetIterator();
    }

    private class SLListSetIterator implements Iterator<T>{
        private int wizPos;

        public SLListSetIterator(){
            wizPos = 0;
        }

        public boolean hasNext(){
            return wizPos < size;
        }

        public T next(){
            Node p = sentinel.next;
            for(int i = 0; i < wizPos; i++){
                p = p.next;
            }
            wizPos ++;
            return p.item;
        }
    }

    public boolean equals(Object o) {
        // 同一对象直接返回true
        if (this == o) return true;

        // 不是Deque类型或长度不同，直接返回false
        if (!(o instanceof Deque)) return false;
        Deque<?> other = (Deque<?>) o;
        if (size() != other.size()) return false;

        // 逐个比较元素
        for (int i = 0; i < size(); i++) {
            T thisItem = get(i);
            Object otherItem = other.get(i);

            // 处理null值情况
            if (thisItem == null) {
                if (otherItem != null) return false;
            } else if (!thisItem.equals(otherItem)) {
                return false;
            }
        }
        return true;
    }

}
