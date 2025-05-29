import java.util.LinkedList;
import java.util.Queue;

class Main {
    public static void main(String args[]) {

        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(4);
        bst.insert(6);
        bst.insert(5);
        bst.insert(8);
        bst.insert(2);
        bst.insert(9);

        bst.preorder();
        bst.inorder();
        bst.postorder();
        bst.levelorder();

        bst.search(1);
        bst.delete(1);
        bst.search(1);
    }
}

class BinarySearchTree {
    TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(int data) {
        root = insertRec(root, data);
    }

    private TreeNode insertRec(TreeNode root, int key) {
        if (root == null) {
            return new TreeNode(key);
        }

        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }

        return root;
    }

    public void delete(int data) {
        root = deleteRec(root, data);
    }

    private TreeNode deleteRec(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.key) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRec(root.right, key);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            }

            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            TreeNode successor = findSuccessor(root.right);
            root.key = successor.key;
            root.right = deleteRec(root.right, successor.key);
        }

        return root;
    }

    private TreeNode findSuccessor(TreeNode root) {
        if (root.left == null)
            return root;

        return findSuccessor(root.left);
    }

    public void search(int data) {
        if (searchRec(root, data) != null) {
            System.out.println(data + " is exist");
        } else {
            System.out.println(data + " is not exist");
        }
    }

    private TreeNode searchRec(TreeNode root, int key) {
        if (root == null || root.key == key) {
            return root;
        }

        if (key < root.key) {
            return searchRec(root.left, key);
        } else {
            return searchRec(root.right, key);
        }
    }

    public void preorder() {
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(TreeNode root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    public void postorder() {
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(TreeNode root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.key + " ");
        }
    }

    public void levelorder() {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            System.out.print(node.key + " ");

            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }

        System.out.println();
    }

}

class TreeNode {
    int key;

    TreeNode left, right;

    public TreeNode(int data) {
        key = data;
        left = right = null;
    }
}
