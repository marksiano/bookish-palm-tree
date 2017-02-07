/** Implementation of a circularly linked list */

public class LinkedListDeque<Item> {

    private Node sentinel;
    private int size;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        Node(Node p, Item i, Node n) {
            item = i;
            prev = p;
            next = n;
        }

        /*Accessor methods*/
        public Item getItem() {
            return item;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setNext(Node n) {
            next = n;
        }

        public void setPrev(Node p) {
            prev = p;
        }
    }

    public LinkedListDeque() {
        //Create the sentinel
        sentinel = new Node(null, null, null);

        //Have the sentinel point to itself
        sentinel.setPrev(sentinel);
        sentinel.setNext(sentinel);

        size = 0;
    }

    public void addFirst(Item x) {
        Node firstNode = sentinel.getNext();
        Node newNode = new Node(sentinel, x, firstNode);
        sentinel.setNext(newNode);
        firstNode.setPrev(newNode);

        size++;
    }

    public void addLast(Item x) {
        Node lastNode = sentinel.getPrev();
        Node newNode = new Node(lastNode, x, sentinel);
        sentinel.setPrev(newNode);
        lastNode.setNext(newNode);

        size++;
    }

    public Item removeFirst() {
        Node frontNode = sentinel.getNext();
        Item i = frontNode.getItem();
        sentinel.setNext(frontNode.getNext());
        frontNode.getNext().setPrev(frontNode.getPrev());

        size--;

        return i;
    }

    public Item removeLast() {
        Node backNode = sentinel.getPrev();
        Item i = backNode.getItem();
        sentinel.setPrev(backNode.getPrev());
        backNode.getPrev().setNext(backNode.getNext());

        size--;

        return i;
    }

    public void printDeque() {
        Node pointer = sentinel.getNext();

        while (pointer != sentinel) {
            System.out.print(pointer.getItem() + " ");
            pointer = pointer.getNext();
        }

        System.out.println();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Item get(int index) {
        Node pointer = sentinel.getNext();
        int i = 0;

        while (i < index) {
            pointer = pointer.getNext();
            i++;
        }

        return pointer.getItem();
    }

    /*Recursively get the item at position INDEX in the list*/
    public Item getRecursive(int index) {
        return getHelper(index, sentinel.getNext());
    }

    private Item getHelper(int index, Node pointer) {
        if (index == 0) {
            return pointer.getItem();
        } else {
            return getHelper(index - 1, pointer.getNext());
        }
    }
}