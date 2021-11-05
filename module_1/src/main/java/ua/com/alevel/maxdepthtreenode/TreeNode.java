package ua.com.alevel.maxdepthtreenode;

public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}

	public TreeNode() {
	}

	public static int maxDeepth(TreeNode root) {
		if (root == null) return 0;
		int left = maxDeepth(root.left);
		int right = maxDeepth(root.right);
		return Math.max(left, right) + 1;
	}
	public void initTreeNode() {
		System.out.println("Максимальная глубина по заданному дереву:30,10,20,150,40,60,80:");
		System.out.println("---30---");
		System.out.println("-10--20");
		System.out.println("-40-50");
		System.out.println("----60-");
		System.out.println("----80");
		TreeNode treeNode = new TreeNode(30);
		treeNode.left = new TreeNode(10);
		treeNode.right = new TreeNode(20);
		treeNode.right.left = new TreeNode(40);
		treeNode.right.right = new TreeNode(50);
		treeNode.right.right.right = new TreeNode(60);
		treeNode.right.right.right.left = new TreeNode(80);
		System.out.println(maxDeepth(treeNode));
	}

}
