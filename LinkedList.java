
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class LinkedList {

    public Node head;//头节点
    public Node current;//当前节点
    public Node next;//下一个节点

    public LinkedList() {
    }

    public LinkedList(Node head) {

        this.head = head;
    }


    //向链表中添加节点
    public void add(int data) {

        if (head == null) {
            head = new Node(data);
            current = head;
        } else {
            current.next = new Node(data);
            current = current.next;
        }
    }

    //向链表中添加节点
    public void add(Node node) {
        if (head == null) {
            head = node;
            current = head;
        } else {
            current.next = head;
            current = current.next;
        }
    }

    //求单链表中节点个数
    public int getNodeNum() {

        return getNodeNum(this.head);
    }

    private int getNodeNum(Node head) {

        current = head;
        if (current == null)
            return 0;
        int num = 0;
        while (current != null) {
            num++;
            current = current.next;
        }
        return num;
    }

    //查找链表中倒数第k个节点
    public Node getKthNode(int k) {

        return getKthNode(this.head, k);
    }

    private Node getKthNode(Node head, int k) {

        if (head == null || k < 1)
            return null;

        int size = getNodeNum(head);
        if (k > size)
            return null;
        current = head;
        //倒数第k个节点,就是正数第size-k+1个节点,当前指向第一个节点，还需要移动size-k次
        for (int i = 1; i <= size - k; i++)
            current = current.next;

        return current;


    }
    //双指针移动思路
    private Node getKthNodeByTwoPoint(Node head, int k) {

        if (head == null || k < 1)
            return null;
        Node first = head;
        Node second = head;
        for (int i = 1; i <= k - 1; i++) {
            first = first.next;
            if (first == null)
                return null;
        }

        while (first.next != null) {
            first = first.next;
            second = second.next;
        }

        return second;
    }

    //查找链表中间节点
    public Node findMidNode() {

        return findMidNode(this.head);
    }

    private Node findMidNode(Node head) {

        //链表为空或链表只有一个节点
        if (head == null || head.next == null)
            return head;
        Node first = head;
        Node second = head;

        while (first != null && first.next != null) {
            first = first.next.next;
            second = second.next;
        }

        return second;

    }

    //合并两个有序链表 合并完依然有序
    public static LinkedList merge(LinkedList l1, LinkedList l2) {

        Node mergeHead = merge(l1.head, l2.head);
        LinkedList mergeLinkedList = new LinkedList(mergeHead);
        return mergeLinkedList;
    }

    private static Node merge(Node head1, Node head2) {

        //如果其中一个为空链表 就直接返回另一个链表
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;

        Node head = null;
        Node current = null;
        while (head1 != null && head2 != null) {
            if (head1.getData() < head2.getData()) {
                if (head == null) {
                    head = head1;
                    current = head;
                    head1 = head1.next;
                } else {
                    current.next = head1;
                    current = current.next;
                    head1 = head1.next;
                }
            } else {
                if (head == null) {
                    head = head2;
                    current = head;
                    head2 = head2.next;
                } else {
                    current.next = head2;
                    current = current.next;
                    head2 = head2.next;
                }
            }
        }

        if (head1 != null) {
            current.next = head1;
        }
        if (head2 != null) {
            current.next = head2;
        }

        return head;
    }

    //反转链表
    public static LinkedList reverse(LinkedList linkedList) {

        Node reverseHead = reverse(linkedList.head);

        LinkedList reverseLinkedList = new LinkedList(reverseHead);
        return reverseLinkedList;

    }

    private static Node reverse(Node head) {

        if (head == null || head.next == null)
            return head;

        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }


        return pre;

    }

    //从尾到头打印链表
    public void reversePrint() {

        reversePrint(this.head);
    }

    //借用栈 非递归
    private void reversePrint(Node head) {

        if (head == null)
            return;
        Node current = head;
        Stack<Node> stack = new Stack<>();
        while (current != null) {
            stack.push(current);
            current = current.next;
        }

        while (!stack.empty()) {
            System.out.print(stack.pop().getData() + " ");
        }
        System.out.println();

    }

    //递归打印
    private void reversePrintR(Node head) {

        if (head == null)
            return;
        reversePrintR(head.next);
        System.out.print(head.getData() + " ");
    }

    //判断链表是否有环
    public boolean hasCycle() {

        return hasCycle(this.head);
    }

    private boolean hasCycle(Node head) {

        if (head == null)
            return false;

        Node first = head;
        Node second = head;
        while (first != null) {
            first = first.next.next;
            second = second.next;
            if (first == second)
                return true;
        }

        return false;

    }

    //求链表中环的长度
    public int getCycleLength() {

        return getCycleLength(this.head);
    }

    private int getCycleLength(Node head) {

        if (head == null)
            return 0;

        Node first = head;
        Node second = head;
        Node meetNode = null;

        while (first != null && first.next != null) {

            first = first.next.next;
            second = second.next;
            if (first == second) {
                meetNode = first;
                break;
            }
        }

        if (meetNode == null)
            return 0;

        int length = 1;
        Node current = meetNode.next;
        while (current != meetNode) {
            length++;
            current = current.next;
        }

        return length;


    }

    //求有环链表的环的入口点
    public Node getCycleStart() {

        return getCycleStart(this.head);
    }

    private Node getCycleStart(Node head) {

        if (head == null)
            return null;

        Node first = head;
        Node second = head;
        Node meetNode = null;
        while (first != null && first.next != null) {

            first = first.next.next;
            second = second.next;
            if (first == second) {

                meetNode = first;
                break;
            }

        }

        if (meetNode == null)
            return null;

        int length = 1;
        Node current = meetNode.next;
        while (current != meetNode) {
            length++;
            current = current.next;
        }

        first = head;
        second = head;
        for (int i = 1; i <= length; i++)
            first = first.next;

        while (first != second) {
            first = first.next;
            second = second.next;
        }

        return first;

    }

    //求有环链表的环的入口点 借助map
    private Node getCycleStartByMap(Node head) {

        if (head == null)
            return null;

        Map<Node, Boolean> map = new HashMap<>();
        Node current = head;

        while (current != null) {

            if (map.containsKey(current))
                return current;
            else {
                map.put(current, true);
                current = current.next;
            }
        }
        return null;
    }

    //求有环链表的环的入口点  断表法
    private Node getCycleStartByCut(Node head) {
        //此方法只能在确定为有环链表的前提
        if (head == null || head.next == null)
            return null;
        Node current = head;
        Node front = head.next;
        while (front != null) {

            current.next = null;
            current = front;
            front = front.next;
        }
        return current;

    }

    private Node getCycleStart1(Node head) {

        if (head == null)
            return null;


        Node first = head;
        Node second = head;


        while (first != null && first.next != null) {
            first = first.next.next;
            second = second.next;
            if (first == second) {

                first = head;
                while (first != second) {
                    first = first.next;
                    second = second.next;
                }
                Node startNode = first;
                return startNode;
            }
        }
        return null;
    }

    //判断无环链表是否相交
    public static boolean isIntersect(LinkedList l1, LinkedList l2) {

        return isIntersect(l1.head, l2.head);
    }

    private static boolean isIntersect(Node head1, Node head2) {

        if (head1 == null || head2 == null)
            return false;

        Node current1 = head1;
        Node current2 = head2;
        while (current1.next != null) {
            current1 = current1.next;
        }

        while (current2.next != null) {
            current2 = current2.next;
        }

        if (current1 == current2)
            return true;
        else
            return false;
    }

    //借助栈去判断
    private boolean isIntersectByStack(Node head1, Node head2) {

        if (head1 == null || head2 == null)
            return false;

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        Node current1 = head1;
        Node current2 = head2;

        while (current1 != null) {
            stack1.push(current1);
            current1 = current1.next;
        }

        while (current2 != null) {
            stack2.push(current2);
            current2 = current2.next;
        }

        if (stack1.pop() == stack2.pop())
            return true;
        else
            return false;

    }

    //求无环链表相交的第一个节点
    public static Node getIntersectNode(LinkedList l1, LinkedList l2) {

        return getIntersectNode(l1.head, l2.head);
    }

    private static Node getIntersectNode(Node head1, Node head2) {

        if (head1 == null || head2 == null)
            return null;

        int length1 = 0;
        int length2 = 0;
        Node current1 = head1;
        Node current2 = head2;
        while (current1 != null) {
            length1++;
            current1 = current1.next;
        }
        while (current2 != null) {
            length2++;
            current2 = current2.next;
        }

        current1 = head1;
        current2 = head2;

        int lengthDif = (length1 > length2) ? (length1 - length2) : (length2 - length1);
        if (length1 > length2) {
            for (int i = 1; i <= lengthDif; i++)
                current1 = current1.next;
        } else if (length1 < length2) {
            for (int i = 1; i <= lengthDif; i++)
                current2 = current2.next;
        }
        while (current1 != null && current2 != null) {
            if (current1 == current2)
                return current1;
            current1 = current1.next;
            current2 = current2.next;
        }

        return null;
    }

    //删除有序链表重复节点
    public static Node deleteDuplication(LinkedList linkedList) {

        return deleteDuplication(linkedList.head);
    }

    private static Node deleteDuplication(Node head) {

        if (head == null || head.next == null)
            return head;

        Set<Integer> set = new HashSet<>();//采用HashSet  特点是不保留相同数据

        Node pre = head;
        Node current = head.next;
        set.add(pre.getData());
        while (current != null) {

            //如果set中不包含该值 就添加进去
            if (!set.contains(current.getData())) {

                set.add(current.getData());
                pre = current;
                current = current.next;
            } else {

                pre.next = current.next;
                current = current.next;
            }
        }

        return head;

    }

    //删除链表中的重复节点  全部删除
    public Node deleteALlSameNode() {

        return deleteAllSameNode(this.head);


    }

    private Node deleteAllSameNode(Node head) {

        //只有0个节点或1个节点 直接返回即可
        if (head == null || head.next == null)
            return head;

        Node temp = new Node(-1);//新建一个节点  防止头结点被删除
        temp.next = head;
        Node pre = temp;
        Node current = head;
        Node next = null;

        while (current != null && current.next != null) {

            next = current.next;
            //如果当前节点与下一个节点相同 就不断往后寻找是否还有相同的节点
            if (current.getData() == next.getData()) {

                while (next != null && current.getData() == next.getData()) {

                    next = next.next;
                }
                //将所有的重复节点全部删除掉
                pre.next = next;
                current = next;
            } else {

                pre = current;
                current = current.next;
            }
        }

        //返回剩下的链表
        return temp.next;
    }


    //输出链表
    public void display() {

        display(this.head);
    }

    private void display(Node node) {

        if (node == null)
            return;
        Node current = node;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.next;
        }

        System.out.println();
    }

    //内置节点类
    public class Node {

        int data;
        Node next;

        public Node() {
        }

        public Node(int data) {

            this.data = data;

        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        //测试方法1:向链表中添加节点
        // LinkedList l1 = new LinkedList();
        //for (int i = 0; i < 10; i++)
        //  l1.add(i);
        //System.out.print("输出链表：");
        //l1.display(l1);
        //测试方法: 求单链表节点个数
        //int num = l1.getNodeNum();
        //System.out.println("链表中节点个数：" + num);
        //测试方法: 求倒数第k个节点
        //int k = 10;
        //System.out.println("倒数第" + k + "个节点：" + l1.getKthNode(10).getData());
        //测试方法：求链表中间节点
        //System.out.println("链表中间节点：" + l1.findMidNode().getData());
        LinkedList l2 = new LinkedList();
        //for (int i = 10; i < 20; i++)
        //  l2.add(i);
        //LinkedList mergeList = LinkedList.merge(l1, l2);
        //mergeList.display(mergeList);
        //System.out.println("链表逆置： ");
        //l1 = LinkedList.reverse(l1);
        //l1.display();
        //l1.display();
        //l1.reversePrint();

        /*-------------   测试：删除重复节点 全部删除掉  -----------------*/

        /*LinkedList sameList = new LinkedList();

        for (int i = 1; i <= 10; i++)
            sameList.add(i);
        sameList.add(2);
        sameList.display();
        sameList.head = sameList.deleteALlSameNode();
        sameList.display();*/
        /*-------------   测试：删除重复节点 只删除多余项  保留一个 -----------------*/
        /*LinkedList sameList = new LinkedList();
        for (int i = 1; i <= 10; i++)
            sameList.add(i);
        sameList.add(2);
        sameList.head = deleteDuplication(sameList);
        sameList.display();*/
    }
}

