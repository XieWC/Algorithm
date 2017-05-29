package LinkedListSummary;

import java.util.HashMap;
import java.util.Map;

/**
 * http://blog.csdn.net/luckyxiaoqiang/article/details/7393134 轻松搞定面试中的链表题目
 * http://www.cnblogs.com/jax/archive/2009/12/11/1621504.html 算法大全（1）单链表
 * 
 * 目录： 1. 求单链表中结点的个数: getListLength 2. 将单链表反转:
 * reverseList（遍历），reverseListRec（递归） 3. 查找单链表中的倒数第K个结点（k > 0）: reGetKthNode 4.
 * 查找单链表的中间结点: getMiddleNode 5. 从尾到头打印单链表:
 * reversePrintListStack，reversePrintListRec（递归） 6. 已知两个单链表pHead1 和pHead2
 * 各自有序，把它们合并成一个链表依然有序: mergeSortedList, mergeSortedListRec 7. 判断一个单链表中是否有环:
 * hasCycle 8. 判断两个单链表是否相交: isIntersect 9. 求两个单链表相交的第一个节点: getFirstCommonNode
 * 10. 已知一个单链表中存在环，求进入环中的第一个节点: getFirstNodeInCycle, getFirstNodeInCycleHashMap
 * 11. 给出一单链表头指针pHead和一节点指针pToBeDeleted，O(1)时间复杂度删除节点pToBeDeleted: delete
 * 
 */
public class MyDemo {
	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		// n5.next = n1;
		// Node n11 = new Node(11);
		// Node n22 = new Node(22);
		// Node n33 = new Node(33);
		// Node n44 = new Node(44);
		// Node n55 = new Node(55);
		// n11.next = n22;
		// n22.next = n33;
		// n33.next = n44;
		// n44.next = n55;
		// n55.next = n3;

		print(n1);
		// print(n11);
		// System.out.println(getListLength(n1));
		// // Node reNode = reverseList(n1);
		// // print(reNode);
		// Node reKNode = reGetKthNode(n1, 2);
		// System.out.println("The re 2:" + reKNode.val);
		// Node middleNode = getMiddleNode(n3);
		// System.out.println("The middle node is:" + middleNode.val);
		// Node mergeHead = mergeSortedList(n11, n1);
		// print(n1);
		// reversePrintList(n1);
		// System.out.println(hasCycle(n1));
		// System.out.println(isIntersect(n1, n11));
		// Node intersectNode = getFirstCommonNode(n1, n11);
		// System.out.println(intersectNode.val);
		// Node first = getFirstNodeInCycle(n11);
		// Node second = getFirstNodeInCycleHashMap(n11);
		//
		// System.out.println(first.val);
		// System.out.println(second.val);
		delete(n1, n1);
		print(n1);
	}

	@SuppressWarnings("unused")
	private static class Node {
		int val;
		Node next;

		public Node(int val) {
			this.val = val;
		}
	}

	public static void print(Node head) {
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
		System.out.println();
	}

	// 1. 求单链表中结点的个数: getListLength
	public static int getListLength(Node head) {
		if (head == null) {
			return 0;
		}
		return getListLength(head.next) + 1;
	}

	// 2 将单链表反转: reverseList（遍历），reverseListRec（递归）
	public static Node reverseList(Node head) {
		Node current = head;
		Node preNode = null;
		Node postNode = null;
		while (current != null) {
			postNode = current.next;
			current.next = preNode;
			preNode = current;
			current = postNode;
		}
		return preNode;
	}

	// 3.查找单链表中的倒数第K个结点（k > 0）: reGetKthNode
	public static Node reGetKthNode(Node head, int k) {
		Node kNode = head;
		for (int i = 1; i <= k; i++) {
			if (kNode != null) {
				kNode = kNode.next;
			} else {
				return new Node(0);
			}
		}
		while (kNode != null) {
			kNode = kNode.next;
			head = head.next;
		}
		return head;
	}

	// 4.查找单链表的中间结点: getMiddleNode
	public static Node getMiddleNode(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}
		Node preNode = head;
		Node postNode = head;
		while (postNode.next != null && postNode.next.next != null) {
			postNode = postNode.next.next;
			preNode = preNode.next;
		}
		return preNode;
	}

	// 5. 从尾到头打印单链表:reversePrintListStack，reversePrintListRec（递归==相当于运用栈的思想
	public static void reversePrintList(Node head) {
		if (head == null) {
			return;
		}
		reversePrintList(head.next);
		System.out.print(head.val + " ");
	}

	// 6. 已知两个单链表pHead1 和pHead2 各自有序，把它们合并成一个链表依然有序: mergeSortedList,
	// mergeSortedListRec
	public static Node mergeSortedList(Node p, Node q) {
		if (p == null) {
			return q;
		}
		if (q == null) {
			return p;
		}
		Node mergeNode = null;
		if (p.val < q.val) {
			mergeNode = p;
			mergeNode.next = mergeSortedList(p.next, q);
		} else {
			mergeNode = q;
			mergeNode.next = mergeSortedList(p, q.next);
		}
		return mergeNode;
	}

	// 7. 判断一个单链表中是否有环: * hasCycle
	public static boolean hasCycle(Node head) {
		Node preNode = head;
		Node postNode = head;
		if (head == null || head.next == null) {
			return false;
		}
		while (postNode != null && postNode.next != null) {
			postNode = postNode.next.next;
			preNode = preNode.next;
			if (preNode == postNode) {
				return true;
			}
		}
		return false;
	}

	// 8. 判断两个单链表是否相交: isIntersect
	public static boolean isIntersect(Node p, Node q) {
		Node pTail;
		while (p != null) {
			p = p.next;
		}
		pTail = p;
		while (q != null) {
			q = q.next;
		}
		return pTail == q;
	}

	// 9. 求两个单链表相交的第一个节点: getFirstCommonNode
	public static Node getFirstCommonNode(Node p, Node q) {
		int pLength = getListLength(p);
		int qLength = getListLength(q);
		if (pLength > qLength) {
			for (int i = 1; i <= (pLength - qLength); i++) {
				p = p.next;
			}
		} else {
			for (int i = 1; i <= (qLength - pLength); i++) {
				q = q.next;
			}
		}
		for (int i = 0; i < Math.min(pLength, qLength); i++) {
			if (p == q) {
				return p;
			}
			p = p.next;
			q = q.next;
		}
		return null;
	}

	// 10. 已知一个单链表中存在环，求进入环中的第一个节点: getFirstNodeInCycle,
	// getFirstNodeInCycleHashMap
	public static Node getFirstNodeInCycle(Node node) {
		Node slow = node;
		Node fast = node;
		Node temp = null;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				temp = fast;
				break;
			}
		}
		if (fast == null || fast.next == null) {
			return null;
		}
		while (node != temp) {
			node = node.next;
			temp = temp.next;
		}
		return temp;
	}

	// getFirstNodeInCycleHashMap
	public static Node getFirstNodeInCycleHashMap(Node node) {
		Map<Node, Integer> map = new HashMap<>();
		while (node != null) {
			if (map.get(node) != null) {
				return node;
			}
			map.put(node, node.val);
			node = node.next;
		}
		return null;
	}

	// 11. 给出一单链表头指针pHead和一节点指针pToBeDeleted，O(1)时间复杂度删除节点pToBeDeleted: delete
	public static void delete(Node pHead, Node pToBeDeleted) {
		if (pHead == null || pToBeDeleted == null) {
			return;
		}
		if (pToBeDeleted.next != null) {
			pToBeDeleted.val = pToBeDeleted.next.val;
			pToBeDeleted.next = pToBeDeleted.next.next;
		} else if (pHead.next == null) {
			pHead = null;
		} else {
			while (pHead.next.next != null) {
				pHead = pHead.next;
			}
			pHead.next = null;
		}

	}
}
