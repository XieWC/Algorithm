package LinkedListSummary;

import java.util.HashMap;
import java.util.Map;

/**
 * http://blog.csdn.net/luckyxiaoqiang/article/details/7393134 ���ɸ㶨�����е�������Ŀ
 * http://www.cnblogs.com/jax/archive/2009/12/11/1621504.html �㷨��ȫ��1��������
 * 
 * Ŀ¼�� 1. �������н��ĸ���: getListLength 2. ��������ת:
 * reverseList����������reverseListRec���ݹ飩 3. ���ҵ������еĵ�����K����㣨k > 0��: reGetKthNode 4.
 * ���ҵ�������м���: getMiddleNode 5. ��β��ͷ��ӡ������:
 * reversePrintListStack��reversePrintListRec���ݹ飩 6. ��֪����������pHead1 ��pHead2
 * �������򣬰����Ǻϲ���һ��������Ȼ����: mergeSortedList, mergeSortedListRec 7. �ж�һ�����������Ƿ��л�:
 * hasCycle 8. �ж������������Ƿ��ཻ: isIntersect 9. �������������ཻ�ĵ�һ���ڵ�: getFirstCommonNode
 * 10. ��֪һ���������д��ڻ�������뻷�еĵ�һ���ڵ�: getFirstNodeInCycle, getFirstNodeInCycleHashMap
 * 11. ����һ������ͷָ��pHead��һ�ڵ�ָ��pToBeDeleted��O(1)ʱ�临�Ӷ�ɾ���ڵ�pToBeDeleted: delete
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

	// 1. �������н��ĸ���: getListLength
	public static int getListLength(Node head) {
		if (head == null) {
			return 0;
		}
		return getListLength(head.next) + 1;
	}

	// 2 ��������ת: reverseList����������reverseListRec���ݹ飩
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

	// 3.���ҵ������еĵ�����K����㣨k > 0��: reGetKthNode
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

	// 4.���ҵ�������м���: getMiddleNode
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

	// 5. ��β��ͷ��ӡ������:reversePrintListStack��reversePrintListRec���ݹ�==�൱������ջ��˼��
	public static void reversePrintList(Node head) {
		if (head == null) {
			return;
		}
		reversePrintList(head.next);
		System.out.print(head.val + " ");
	}

	// 6. ��֪����������pHead1 ��pHead2 �������򣬰����Ǻϲ���һ��������Ȼ����: mergeSortedList,
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

	// 7. �ж�һ�����������Ƿ��л�: * hasCycle
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

	// 8. �ж������������Ƿ��ཻ: isIntersect
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

	// 9. �������������ཻ�ĵ�һ���ڵ�: getFirstCommonNode
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

	// 10. ��֪һ���������д��ڻ�������뻷�еĵ�һ���ڵ�: getFirstNodeInCycle,
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

	// 11. ����һ������ͷָ��pHead��һ�ڵ�ָ��pToBeDeleted��O(1)ʱ�临�Ӷ�ɾ���ڵ�pToBeDeleted: delete
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
