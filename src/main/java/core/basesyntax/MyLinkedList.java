package core.basesyntax;

import java.util.List;
import java.util.Objects;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;

    class Node<E> {
        private Node<E> prev;
        private E item;
        private Node<E> next;
    }

    @Override
    public void add(T value) {
        Node<T> newNode = new Node<>();
        newNode.item = value;

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> newNode = new Node<>();
        Node<T> current = head;
        newNode.item = value;
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            if (index > 0 && index < size) {
                newNode.next = current;
                newNode.prev = current.prev;
                current.prev = newNode;
                newNode.prev.next = newNode;
            } else if (index == 0) {
                newNode.next = head;
                newNode.prev = null;
                head.prev = newNode;
                head = newNode;
            } else if (index == size) {
                tail.next = newNode;
                newNode.prev = tail;
                newNode.next = null;
                tail = newNode;
            }
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            Node<T> newNode = new Node<>();
            newNode.item = list.get(i);
            newNode.prev = tail;
            newNode.next = null;
            if (tail != null) {
                tail.next = newNode;
            } else {
                head = newNode;
            }
            tail = newNode;
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    @Override
    public T set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        T oldValue = node.item;
        node.item = value;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        node.prev = null;
        node.next = null;
        size--;

        return node.item;
    }

    @Override
    public boolean remove(T object) {
        Node<T> node = head;
        while (node != null) {
            if (Objects.equals(node.item, object)) {
                if (node.prev != null) {
                    node.prev.next = node.next;
                } else {
                    head = node.next;
                }

                if (node.next != null) {
                    node.next.prev = node.prev;
                } else {
                    tail = node.prev;
                }

                node.prev = null;
                node.next = null;
                size--;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }
}
