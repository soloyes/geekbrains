package ru.geekbrains;

import java.util.NoSuchElementException;

public class LinkedList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void putLeft(E e) {
        Node<E> f = first;
        Node<E> newNode = new Node<>(e, null, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.previous = newNode;
        }
        size++;
    }

    public Node removeLeft() {
        first = first.next;
        size--;
        return first;
    }

    public void putRight(E e) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(e, l, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }

        size++;
    }

    public Node removeRight() {
        last = last.previous;
        last.next = null;
        size--;
        return last;
    }

    public Iterator getIterator() {
        return new Iterator(this);
    }

    private static class Node<E> {
        E value;
        Node<E> next;
        Node<E> previous;

        Node(E value, Node<E> previous, Node<E> next) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }
    }

    public static class Iterator {
        private LinkedList linkedList;
        private Node current;
        private Node previous;

        public Iterator(LinkedList linkedList) {
            this.linkedList = linkedList;
            reset();
        }

        public Node next() {
            if (hasNext()) {
                previous = current;
                current = current.next;
            } else throw new NoSuchElementException("End of collection!");
            return previous;
        }

        public boolean hasNext() {
            return current != null;
        }

        private void reset() {
            this.current = linkedList.first;
            previous = linkedList.first;
        }

        public Node remove(){
            Node tmp = next();
            tmp.next = null;
            tmp.previous = null;
            linkedList.size --;
            return tmp;
        }

        @Override
        public String toString() {

           StringBuilder stringBuilder = new StringBuilder("{ ");
            for (; hasNext(); ) {

                stringBuilder.append(next().value).append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append(" }");
            return stringBuilder.toString();
        }
    }

    @Override
    public String toString() {
        return getIterator().toString();
    }
}
