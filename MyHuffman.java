package Huffman;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import org.junit.Test;

public class MyHuffman {
	static class Tree {
		private Node root;

		public Node getRoot() {
			return root;
		}

		public void setRoot(Node root) {
			this.root = root;
		}

	}

	static class Node implements Comparable<Node> {
		private String chars = "";
		private int frequence = 0;
		private Node parent;
		private Node leftNode;
		private Node rightNode;

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.frequence - o.frequence;
		}

		public boolean isLeaf() {
			return chars.length() == 1;
		}

		public boolean isRoot() {
			return this.parent == null;
		}

		public boolean isLeftChild() {
			return parent != null && this == parent.leftNode;
		}

		public String getChars() {
			return chars;
		}

		public void setChars(String chars) {
			this.chars = chars;
		}

		public int getFrequence() {
			return frequence;
		}

		public void setFrequence(int frequence) {
			this.frequence = frequence;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public Node getLeftNode() {
			return leftNode;
		}

		public void setLeftNode(Node leftNode) {
			this.leftNode = leftNode;
		}

		public Node getRightNode() {
			return rightNode;
		}

		public void setRightNode(Node rightNode) {
			this.rightNode = rightNode;
		}

	}

	public static Map<Character, Integer> statistics(char[] charArray) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c : charArray) {

			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
		}
		return map;
	}

	public static Tree buildTree(Map<Character, Integer> maplist,
			List<Node> leafs) {
		Character[] keys = maplist.keySet().toArray(new Character[0]);
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
		for (Character c : keys) {
			Node node = new Node();
			node.chars = c.toString();
			node.frequence = maplist.get(c);
			priorityQueue.add(node);
			leafs.add(node);
		}

		int size = priorityQueue.size();
		for (int i = 1; i < size; i++) {
			Node node1 = priorityQueue.poll();
			Node node2 = priorityQueue.poll();

			Node sumNode = new Node();
			sumNode.chars = node1.chars + node2.chars;
			sumNode.frequence = node1.frequence + node2.frequence;
			sumNode.leftNode = node1;
			sumNode.rightNode = node2;
			node1.parent = sumNode;
			node2.parent = sumNode;

			priorityQueue.add(sumNode);
		}

		Tree tree = new Tree();
		tree.root = priorityQueue.poll();
		return tree;
	}

	public static String encode(String originalStr,
			Map<Character, Integer> maplist) {
		if (originalStr == null || originalStr.equals("")) {
			return "";
		}

		char[] charArray = originalStr.toCharArray();
		List<Node> leafNodes = new ArrayList<>();
		buildTree(maplist, leafNodes);
		Map<Character, String> encodingMap = encodingMap(leafNodes);
		StringBuffer sb = new StringBuffer();
		for (char c : charArray) {
			sb.append(encodingMap.get(c));
		}
		return sb.toString();

	}

	public static String decode(String binaryStr,
			Map<Character, Integer> statistics) {
		if (binaryStr == null || binaryStr.equals("")) {
			return "";
		}
		char[] binaryCharArray = binaryStr.toCharArray();
		Queue<Character> queue = new LinkedList<Character>();
		for (int i = 0; i < binaryCharArray.length; i++) {
			queue.add(binaryCharArray[i]);
		}
		List<Node> leafNodes = new ArrayList<>();
		Tree tree = buildTree(statistics, leafNodes);
		StringBuffer sb = new StringBuffer();

		while (!queue.isEmpty()) {
			Node node = tree.root;
			while (!node.isLeaf()) {
				Character c = queue.poll();
				if (c == '0') {
					node = node.leftNode;
				} else {
					node = node.rightNode;
				}
			}
			sb.append(node.chars);
		}
		return sb.toString();
	}

	public static Map<Character, String> encodingMap(List<Node> leafNodes) {
		Map<Character, String> encodingMap = new HashMap<>();
		for (Node node : leafNodes) {
			StringBuffer sb = new StringBuffer();
			Node currentNode = node;
			while (currentNode.parent != null) {
				if (currentNode.isLeftChild()) {
					sb.insert(0, "0");
				} else {
					sb.insert(0, "1");
				}
				currentNode = currentNode.parent;
			}
			encodingMap.put(node.chars.charAt(0), sb.toString());
		}
		return encodingMap;
	}

	public static void main(String[] args) {
		String oriStr = "Huffman codes compress data very effectively: savings of 20% to 90% are typical, "
				+ "depending on the characteristics of the data being compressed. ÖÐ»ªáÈÆð";
		Map<Character, Integer> statistics = statistics(oriStr.toCharArray());
		String encodeStr = encode(oriStr, statistics);
		String decodeStr = decode(encodeStr, statistics);
		System.out.println("Original sstring: " + oriStr);
		System.out.println("Huffman encoed binary string: " + encodeStr);
		System.out.println("decoded string from binariy string: " + decodeStr);
		System.out.println("binary string of UTF-8: "
				+ getStringOfByte(oriStr, Charset.forName("UTF-8")));
		System.out.println("binary string of UTF-16: "
				+ getStringOfByte(oriStr, Charset.forName("UTF-16")));
		System.out.println("binary string of US-ASCII: "
				+ getStringOfByte(oriStr, Charset.forName("US-ASCII")));
		System.out.println("binary string of GB2312: "
				+ getStringOfByte(oriStr, Charset.forName("GB2312")));
	}

	public static String getStringOfByte(String str, Charset charset) {
		if (str == null || str.equals("")) {
			return "";
		}
		byte[] bytes = str.getBytes(charset);
		StringBuffer sb = new StringBuffer();
		for (byte b : bytes) {
			if (Integer.toBinaryString(b).length() != 8) {
				for (int i = 1; i <= 8 - Integer.toBinaryString(b).length(); i++)
					sb.append("0");
			}
			sb.append(Integer.toBinaryString(b));
		}
		return sb.toString();
	}

	@Test
	public void testCharSet() {
		byte[] bytes = "I am A".getBytes(Charset.forName("UTF-8"));
		for (int i = 0; i < bytes.length; i++) {
			System.out.print(bytes[i] + " ");
		}

	}

	@Test
	public void test1() {
		Comparator<Integer> com = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1, o2);
			}

		};
	}

}
