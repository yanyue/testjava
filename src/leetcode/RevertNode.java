package leetcode;

class Node {
    int val;
    Node next;
}
public class RevertNode {
    public static void logout(Node head) {
        while (head != null) {
            System.out.println("Node val:" + head.val);
            head = head.next;
        }
        System.out.println("------------------");
    }

    public static void test() {
        Node head = new Node();
        head.val = 1;
        Node next1 = new Node();
        next1.val = 4;
        head.next = next1;
        Node next2 = new Node();
        next2.val = 3;
        next1.next = next2;
        Node next3 = new Node();
        next3.val = 9;
        next2.next = next3;
        Node next4 = new Node();
        next4.val = 2;
        next3.next = next4;
        logout(head);
        revert(head);
    }
    public static void revert(Node head) {
        Node p0 = null;
        Node p1 = head;
        Node p2 = head.next;
        Node res = head.next;
        while (p2 != null) {
            Node p3 = p2.next;
            if (p0 != null) {
                p0.next = p2;
            }
            p2.next = p1;
            p1.next = p3;
            p0 = p1;
            p1 = p3;
            if (p1 != null) {
                p2 = p1.next;
            } else {
                p2 = null;
            }
        }

        logout(res);
    }
}
