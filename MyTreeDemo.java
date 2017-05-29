package BinaryTreeSummary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * TODO: һ��Ҫ��������д����������ĵݹ�ͷǵݹ�������
 * 
 * 1. ��������еĽڵ����: getNodeNumRec���ݹ飩��getNodeNum�������� 2.
 * ������������:getDepthRec���ݹ飩��getDepth 3. ǰ�����������������������:
 * preorderTraversalRec,preorderTraversal, inorderTraversalRec,
 * postorderTraversalRec
 * (https://en.wikipedia.org/wiki/Tree_traversal#Pre-order_2)
 * 4.�ֲ����������������δ������£��������ң�: levelTraversal, levelTraversalRec���ݹ�ⷨ����
 * 5.�������������Ϊ�����˫������: convertBST2DLLRec, convertBST2DLL
 * 6.���������K��Ľڵ������getNodeNumKthLevelRec, getNodeNumKthLevel
 * 7.���������Ҷ�ӽڵ�ĸ�����getNodeNumLeafRec, getNodeNumLeaf
 * 8.�ж����ö������Ƿ���ͬ������isSameRec,isSame 9. �ж϶������ǲ���ƽ���������isAVLRec
 * 10.��������ľ����ƻ��Ͳ��ƻ�ԭ�����������������mirrorRec,mirrorCopyRec
 * 10.1�ж��������Ƿ��ྵ��isMirrorRec 11.��������������ڵ����͹������Ƚڵ㣺getLastCommonParent,
 * getLastCommonParentRec, getLastCommonParentRec2
 * 12.��������нڵ�������룺getMaxDistanceRec 13.��ǰ��������к�������������ؽ���������rebuildBinaryTreeRec
 * 14.�ж϶������ǲ�����ȫ��������isCompleteBinaryTree, isCompleteBinaryTreeRec
 */
public class MyDemo {
	public static void main(String[] args) {
		/*
		 * 1 / \ 2 3 / \ \ 4 5 6
		 */

		TreeNode r1 = new TreeNode(1);
		TreeNode r2 = new TreeNode(2);
		TreeNode r3 = new TreeNode(3);
		TreeNode r4 = new TreeNode(4);
		TreeNode r5 = new TreeNode(5);
		TreeNode r6 = new TreeNode(6);
//		TreeNode r7 = new TreeNode(7);
		// r6.left = r7;
		r1.left = r2;
		r1.right = r3;
		r2.left = r4;
		r2.right = r5;
		r3.left = r6;
		System.out.println("NodeNumber:" + getNodeNumRec(r1));// 1. ��������еĽڵ����
		System.out.println("maxDeep:" + getDeptRec(r1));// 2.
														// ������������:getDepthRec���ݹ飩��getDepth
		// preOrder(r1);//�����㷨
		// System.out.println();
		// inOrder(r1);
		// System.out.println();
		// postOrder(r1);
		// System.out.println();
		// levelTraversal(r1);
		// System.out.println();
		// deepTraversal(r1);
		// converseBSTtoDLL(r1);//5�������������Ϊ�����˫������
		// while (head != null) {
		// System.out.print(head.val + " ");
		// head = head.right;
		// }
		// System.out.print(getNodeNumKthLevelRec(r1,
		// 2));//���������K��Ľڵ������getNodeNumKthLevelRec, getNodeNumKthLevel
		// System.out.print(getNodeNumLeafRec(r3)); //
		// 7.���������Ҷ�ӽڵ�ĸ�����getNodeNumLeafRec,// getNodeNumLeaf
		// 8.�ж����ö������Ƿ���ͬ������isSameRec,isSame
		// System.out.println(isSameRec(r1, r2));
		// 9. �ж϶������ǲ���ƽ���������isAVLRec
		// System.out.println(isAVLRec(r1));
		// 10.��������ľ����ƻ��Ͳ��ƻ�ԭ���������������
		// mirrorRec(r1);
		// levelTraversal(r1);
		// System.out.println();
		// // levelTraversal(mirrorRec2(r1));
		// System.out.println("isMirror:" + isMirror(r1, mirrorRec2(r1)));
		// 11.��������������ڵ����͹������Ƚڵ㣺getLastCommonParent, * getLastCommonParentRec,
		// getLastCommonParentRec2
		// if (isNode(r1, r4) && isNode(r1, r5)) {
		// TreeNode tree = getLastCommonParent(r1, r4, r5);
		// System.out.println(tree.val);
		// } else {
		// System.out.println("none");
		// }
		// 12.��������нڵ�������룺getMaxDistanceRec
		// System.out.println(getMaxDistanceRec(r2));
		// 13.��ǰ��������к�������������ؽ���������rebuildBinaryTreeRec
		// preOrder(r1);// �����㷨//1 2 4 5 3 6
		// // 4 2 5 1 3 6
		// System.out.println();
		// inOrder(r1);
		// System.out.println();
		// postOrder(r1);
		// System.out.println();
		// int[] arr1 = new int[] { 1, 2, 4, 5, 3, 6 };
		// int[] arr2 = new int[] { 4, 2, 5, 1, 3, 6 };
		//
		// TreeNode newTreeNode = rebuildBinaryTreeRec(
		// Arrays.asList(1, 2, 4, 5, 3, 6),
		// Arrays.asList(4, 2, 5, 1, 3, 6));
		// levelTraversal(newTreeNode);
		// 14.�ж϶������ǲ�����ȫ��������isCompleteBinaryTree, isCompleteBinaryTreeRec
		System.out.println(isCompleteBinaryTree(r1));
	}

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		public TreeNode(int val) {
			this.val = val;
		}
	}

	// private static int count;
	// private static int maxDeep;

	// 1. ��������еĽڵ����
	// public static int getNodeNumRec(TreeNode root) {
	// if (root == null) {
	// return count;
	// }
	// System.out.println(root.val);
	// count++;
	// getNodeNumRec(root.left);
	// getNodeNumRec(root.right);
	// return count;
	// }

	public static int getNodeNumRec(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return getNodeNumRec(root.left) + getNodeNumRec(root.right) + 1;
	}

	// 2. ������������:getDepthRec���ݹ飩��getDepth
	// public static int getDeptRec(TreeNode root, int count) {
	// if (root == null) {
	// return maxDeep;
	// }
	// // System.out.println(root.val);
	// count++;
	// if (maxDeep < count) {
	// maxDeep = count;
	// }
	// getDeptRec(root.left, count);
	// getDeptRec(root.right, count);
	// return maxDeep;
	// }

	public static int getDeptRec(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return Math.max(getDeptRec(root.left), getDeptRec(root.right)) + 1;
	}

	// 3. ǰ�����������������������:
	public static void preOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		System.out.print(root.val + " ");
		preOrder(root.left);
		preOrder(root.right);
	}

	public static void inOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		inOrder(root.left);
		System.out.print(root.val + " ");
		inOrder(root.right);
	}

	public static void postOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.val + " ");
	}

	// 4.�ֲ����������������δ������£��������ң�:
	public static void levelTraversal(TreeNode root) {
		if (root == null) {
			return;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode peekRoot = queue.poll();
			System.out.print(peekRoot.val + " ");
			if (peekRoot.left != null) {
				queue.add(peekRoot.left);
			}
			if (peekRoot.right != null) {
				queue.add(peekRoot.right);
			}
		}
	}

	// ������ȱ���
	public static void deepTraversal(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.empty()) {
			TreeNode peekRoot = stack.pop();
			System.out.print(peekRoot.val + " ");
			if (peekRoot.right != null) {
				stack.push(peekRoot.right);
			}
			if (peekRoot.left != null) {
				stack.push(peekRoot.left);
			}
		}
	}

	// 5�������������Ϊ�����˫������
	private static TreeNode head;
	private static TreeNode tail;

	public static void converseBSTtoDLL(TreeNode root) {
		if (root == null) {
			return;
		}
		converseBSTtoDLL(root.left);
		makeLinkNode(root);
		converseBSTtoDLL(root.right);

	}

	public static void makeLinkNode(TreeNode node) {
		if (head == null) {
			head = node;
			tail = node;
		} else {
			tail.right = node;
			node.left = tail;
			tail = node;
		}
	}

	// 6.���������K��Ľڵ������getNodeNumKthLevelRec, getNodeNumKthLevel
	public static int getNodeNumKthLevelRec(TreeNode root, int k) {
		if (root == null || k < 1) {
			return 0;
		}
		if (k == 1) {
			return 1;
		}
		return getNodeNumKthLevelRec(root.left, k - 1)
				+ getNodeNumKthLevelRec(root.right, k - 1);
	}

	// 7.���������Ҷ�ӽڵ�ĸ�����getNodeNumLeafRec, getNodeNumLeaf
	public static int getNodeNumLeafRec(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return 1;
		}
		return getNodeNumLeafRec(root.left) + getNodeNumLeafRec(root.right);
	}

	// 8.�ж����ö������Ƿ���ͬ������isSameRec,isSame
	public static boolean isSameRec(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		} else if (p == null || q == null) {
			return false;
		} else if (p.val != q.val) {
			return false;
		} else {
			return isSameRec(p.left, q.left) && isSameRec(p.right, q.right);
		}

	}

	// 9. �ж϶������ǲ���ƽ���������isAVLRec
	public static boolean isAVLRec(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (Math.abs(getDeptRec(root.left) - getDeptRec(root.right)) > 1) {
			return false;
		}
		return isAVLRec(root.left) && isAVLRec(root.right);
	}

	// 10.��������ľ����ƻ��Ͳ��ƻ�ԭ�����������������mirrorRec,mirrorCopyRec
	// * 10.1�ж��������Ƿ��ྵ��isMirrorRec
	public static void mirrorRec(TreeNode root) {
		if (root == null) {
			return;
		}
		TreeNode tempTree;
		tempTree = root.left;
		root.left = root.right;
		root.right = tempTree;
		mirrorRec(root.left);
		mirrorRec(root.right);
	}

	public static TreeNode mirrorRec2(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode newNode = new TreeNode(root.val);
		newNode.right = mirrorRec2(root.left);
		newNode.left = mirrorRec2(root.right);
		return newNode;
	}

	public static boolean isMirror(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		} else if (p == null || q == null) {
			return false;
		} else if (p.val != q.val) {
			return false;
		} else {
			return isMirror(p.left, q.right) && isMirror(p.right, q.left);
		}
	}

	// 11.��������������ڵ����͹������Ƚڵ㣺getLastCommonParent,getLastCommonParentRec,
	// getLastCommonParentRec2

	public static TreeNode getLastCommonParent(TreeNode root, TreeNode p,
			TreeNode q) {
		if (root == null || p == null || q == null) {
			return null;
		}
		boolean p_is_left = isNode(root.left, p);
		boolean q_is_left = isNode(root.left, q);
		if (p_is_left != q_is_left) {
			return root;
		} else if (p_is_left) {
			return getLastCommonParent(root.left, p, q);
		} else {
			return getLastCommonParent(root.right, p, q);
		}

	}

	public static boolean isNode(TreeNode root, TreeNode n) {
		if (root == null || n == null) {
			return false;
		}
		if (root.val == n.val) {
			return true;
		}
		return isNode(root.left, n) || isNode(root.right, n);

	}

	// 12. * ��������нڵ�������룺getMaxDistanceRec
	private static int count;

	public static int getMaxDistanceRec(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = getDeptRec(root.left);
		int right = getDeptRec(root.right);
		if (count < (left + right + 1)) {
			count = left + right + 1;
		}
		getMaxDistanceRec(root.left);
		getMaxDistanceRec(root.right);
		return count;
	}

	// 13.��ǰ��������к�������������ؽ���������rebuildBinaryTreeRec
	public static TreeNode rebuildBinaryTreeRec(List<Integer> preOrder,
			List<Integer> inOrder) {
		List<Integer> leftPreOrder;
		List<Integer> rightPreOrder;
		List<Integer> leftInOrder;
		List<Integer> rightInOrder;
		TreeNode root = null;
		int rootPos;
		if (preOrder.size() > 0 && preOrder.size() > 0) {
			root = new TreeNode(preOrder.get(0));
			rootPos = inOrder.indexOf(preOrder.get(0));
			leftInOrder = inOrder.subList(0, rootPos);
			rightInOrder = inOrder.subList(rootPos + 1, inOrder.size());
			leftPreOrder = preOrder.subList(1, 1 + rootPos);
			rightPreOrder = preOrder.subList(rootPos + 1, preOrder.size());
			root.left = rebuildBinaryTreeRec(leftPreOrder, leftInOrder);
			root.right = rebuildBinaryTreeRec(rightPreOrder, rightInOrder);
		}
		return root;
	}

	// 14.�ж϶������ǲ�����ȫ��������isCompleteBinaryTree, isCompleteBinaryTreeRec
	public static boolean isCompleteBinaryTree(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		boolean mustHaveNoChild = false;

		while (!queue.isEmpty()) {
			TreeNode peekNode = queue.poll();
			if (mustHaveNoChild) {
				if (peekNode.left != null || peekNode.right != null) {
					return false;
				}
			} else {
				if (peekNode.left == null && peekNode.right == null) {
					mustHaveNoChild = true;
				} else if (peekNode.left != null && peekNode.right == null) {
					queue.add(peekNode.left);
					mustHaveNoChild = true;
				} else if (peekNode.left == null && peekNode.right != null) {
					return false;
				} else {
					queue.add(peekNode.left);
					queue.add(peekNode.right);
				}
			}
		}
		return true;
	}
}