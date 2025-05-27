import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int key;
    TreeNode left, right;

    public TreeNode(int item) {
        key = item;
        left = right = null;
    }
}

public class BinarySearchTree {

    TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    // insertion
    public void insert(TreeNode root, int key) {
        root = insertRec(root, key);
    }

    public TreeNode insertRec(TreeNode root, int key) {

        if (root == null) {
            root = new TreeNode(key);
            return root;
        }

        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }

        return root;
    }

    // deletion
    public void delete(TreeNode root, int key) {
        root = deleteLeafNode(root, key);
        root = deleteNodeWithOneChild(root, key);
        root = deleteNodeWithTwoChild(root, key);
    }

    public TreeNode deleteLeafNode(TreeNode root, int key) {

        if (root == null)
            return null;

        if (key < root.key) {
            root.left = deleteLeafNode(root.left, key);
        } else if (key > root.key) {
            root.right = deleteLeafNode(root.right, key);
        } else {
            root = null;
        }

        return root;
    }

    public TreeNode deleteNodeWithOneChild(TreeNode root, int key) {

        if (root == null)
            return null;

        if (key < root.key) {
            root.left = deleteLeafNode(root.left, key);
        } else if (key > root.key) {
            root.right = deleteLeafNode(root.right, key);
        } else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
        }

        return root;
    }

    public TreeNode deleteNodeWithTwoChild(TreeNode root, int key) {

        if (root == null)
            return null;

        if (key < root.key) {
            root.left = deleteNodeWithTwoChild(root.left, key);
        } else if (key > root.key) {
            root.right = deleteNodeWithTwoChild(root.right, key);
        } else {
            TreeNode successor = findSuccessor(root.right);
            root.key = successor.key;
            root.right = deleteNodeWithOneChild(root.right, successor.key);
        }
        return root;
    }

    public TreeNode findSuccessor(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    public TreeNode findPredecessor(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }

        return root;
    }

    // search
    public TreeNode search(TreeNode root, int key) {
        return searchRec(root, key);
    }

    public TreeNode searchRec(TreeNode root, int key) {

        if (root == null || key == root.key)
            return root;

        if (key < root.key)
            return searchRec(root.left, key);

        return searchRec(root.right, key);
    }

    // traversal

    // pre order travesel
    public void preorder(TreeNode root) {
        preorderRec(root);
    }

    public void preorderRec(TreeNode root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    // in order travesel
    public void inorder(TreeNode root) {
        inorderRec(root);
    }

    public void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    // post order travesel
    public void postorder(TreeNode root) {
        postorderRec(root);
    }

    public void postorderRec(TreeNode root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.key + " ");
        }
    }

    // level order travesel
    public void levelorderRec(TreeNode root) {
        if (root == null)
            return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.key + " ");

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }
}