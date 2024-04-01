import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        BinaryTree<Integer> tree = new BinarySearchTree<Integer>();
        Random rand;
        int num = args.length == 1 ? Integer.parseInt(args[0]) : 1;
        long start, stop;

        rand = new Random(1);
        System.out.print("insert: ");
        start = System.currentTimeMillis();
        for (int i = 0; i < num; ++i) {
            tree.insert(rand.nextInt(num));
        }
        stop = System.currentTimeMillis();
        System.out.println(stop - start);

        rand = new Random(1);
        System.out.print("search: ");
        for (int i = 0; i < num; ++i) {
            if (!tree.search(rand.nextInt(num))) {
                System.out.println("Fail");
                break;
            }
        }
        for (Integer i : tree) {
            if (!tree.search(i)) {
                System.out.println("Fail");
                break;
            }
        }
        System.out.println("Pass");

        tree.remove(num+1);

        rand = new Random(1);
        System.out.print("remove: ");
        start = System.currentTimeMillis();
        for (int i = 0; i < num; ++i) {
            tree.remove(rand.nextInt(num));
        }
        stop = System.currentTimeMillis();
        System.out.println(stop - start);

        rand = new Random(1);
        System.out.print("search: ");
        for (int i = 0; i < num; ++i) {
            if (tree.search(rand.nextInt(num))) {
                System.out.println("Fail");
                break;
            }
        }
        System.out.println("Pass");

        System.out.println(tree.root == null);
    }
}

abstract class BinaryTree<E> implements Iterable<E> {

    protected class Node<T> {

        protected Node(T data) {
            this.data = data;
        }

        protected T data;
        protected Node<T> left;
        protected Node<T> right;
    }

    public abstract void insert(E data);
    public abstract void remove(E data);
    public abstract boolean search(E data);

    protected Node<E> root;
}

class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E> {

    private Node<E> findIOP(Node<E> curr) {

        for (curr = curr.left; curr.right != null; curr = curr.right);

        return curr;
    }

    public void insert(E data) {

        Node<E> temp = new Node<E>(data);

        if (root == null) {
            root = temp;
        }
        else {
            Node<E> curr = root;

            while (true) {
                if (data.compareTo(curr.data) <= 0) {
                    if (curr.left != null) {
                        curr = curr.left;
                    }
                    else {
                        curr.left = temp;
                        break;
                    }
                }
                else {
                    if (curr.right != null) {
                        curr = curr.right;
                    }
                    else {
                        curr.right = temp;
                        break;
                    }
                }
            }
        }
    }

    public Iterator<E> iterator() {
        list = new ArrayList<E>();
        traverse(root);
        return list.iterator();
    }

    public void remove(E data) {
        if (root != null) {
            if (data.compareTo(root.data) == 0) {
                if (root.left == null || root.right == null) {
                    root = root.left != null ? root.left : root.right;
                }
                else {
                    Node<E> iop = findIOP(root);
                    E temp = root.data;
                    root.data = iop.data;
                    iop.data = temp;

                    if (root.left == iop) {
                        root.left = root.left.left;
                    }
                    else {
                        Node<E> curr = root.left;
                        while (curr.right != iop) {
                            curr = curr.right;
                        }
                        curr.right = curr.right.left;
                    }
                }
            }
            else {
                Node<E> curr = root;
                int cmp;
                while (true) {
                    cmp = data.compareTo(curr.data);
                    if (cmp < 0 && curr.left != null && data.compareTo(curr.left.data) != 0) {
                        curr = curr.left;
                    }
                    else if (cmp > 0 && curr.right != null && data.compareTo(curr.right.data) != 0) {
                        curr = curr.right;
                    }
                    else {
                        break;
                    }
                }

                if (cmp < 0 && curr.left != null) {
                    if (curr.left.left == null || curr.left.right == null) {
                        curr.left = curr.left.left != null ? curr.left.left : curr.left.right;
                    }
                    else {
                        Node<E> iop = findIOP(curr.left);
                        E temp = curr.left.data;
                        curr.left.data = iop.data;
                        iop.data = temp;

                        if (curr.left.left == iop) {
                            curr.left.left = curr.left.left.left;
                        }
                        else {
                            Node<E> node = curr.left.left;
                            while (node.right != iop) {
                                node = node.right;
                            }
                            node.right = node.right.left;
                        }
                    }
                }
                else if (cmp > 0 && curr.right != null) {
                    if (curr.right.left == null || curr.right.right == null) {
                        curr.right = curr.right.left != null ? curr.right.left : curr.right.right;
                    }
                    else {
                        Node<E> iop = findIOP(curr.right);
                        E temp = curr.right.data;
                        curr.right.data = iop.data;
                        iop.data = temp;

                        if (curr.right.left == iop) {
                            curr.right.left = curr.right.left.left;
                        }
                        else {
                            Node<E> node = curr.right.left;
                            while (node.right != iop) {
                                node = node.right;
                            }
                            node.right = node.right.left;
                        }
                    }
                }
            }
        }
    }

    public boolean search(E data) {

        Node<E> curr = root;

        while (curr != null) {
            if (data.compareTo(curr.data) == 0) {
                return true;
            }
            else if (data.compareTo(curr.data) < 0) {
                curr = curr.left;
            }
            else {
                curr = curr.right;
            }
        }

        return false;
    }

    private void traverse(Node<E> curr) {
        if (curr != null) {
            traverse(curr.left);
            list.add(curr.data);
            traverse(curr.right);
        }
    }

    private ArrayList<E> list;
}
