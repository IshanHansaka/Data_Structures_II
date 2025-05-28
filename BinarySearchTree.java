import java.util.LinkedList;
import java.util.Queue;

class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(6);
        bst.insert(7);
        bst.insert(12);
        bst.insert(2);
        bst.insert(5);
        bst.insert(10);

        bst.delete(6);

        if (bst.search(6)) {
            System.out.println("Node is exist");
        } else {
            System.out.println("Node is not exist");
        }

        System.out.print("Preorder: ");
        bst.preorder();

        System.out.print("Inorder: ");
        bst.inorder();

        System.out.print("Postorder: ");
        bst.postorder();

        System.out.print("Level Order: ");
        bst.levelorder();
    }
}

class BinarySearchTree {
    TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    // Insert a node into the BST
    public void insert(int data) {
        root = insertRec(root, data);
    }

    // Recursive insert method
    public TreeNode insertRec(TreeNode root, int key) {
        if (root == null) {
            return new TreeNode(key);
        }

        if (key < root.key)
            root.left = insertRec(root.left, key); // Insert in left subtree
        else if (key > root.key)
            root.right = insertRec(root.right, key); // Insert in right subtree

        return root;
    }

    // Delete a node from the BST
    public void delete(int data) {
        root = deleteRec(root, data);
    }

    // Recursive delete method
    private TreeNode deleteRec(TreeNode root, int key) {
        if (root == null)
            return null;

        if (key < root.key) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRec(root.right, key);
        } else {
            // Node with no childrens
            if (root.left == null && root.right == null) {
                return null;
            }

            // Node with one child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // Node with two children: get the inorder successor (smallest in the right
            // subtree)
            TreeNode successor = findSuccessor(root.right);
            root.key = successor.key;
            root.right = deleteRec(root.right, successor.key); // Delete the inorder successor
        }

        return root;
    }

    // Find the minimum node in a subtree (used for deleting a node with two
    // children)
    private TreeNode findSuccessor(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    // Preorder traversal: Root -> Left -> Right
    public void preorder() {
        preorderRec(root);
        System.out.println();
    }

    public void preorderRec(TreeNode root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    // Inorder traversal: Left -> Root -> Right (returns sorted order)
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    public void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    // Postorder traversal: Left -> Right -> Root
    public void postorder() {
        postorderRec(root);
        System.out.println();
    }

    public void postorderRec(TreeNode root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.key + " ");
        }
    }

    // Level-order traversal: Breadth-first traversal using a queue
    public void levelorder() {
        if (root == null) {
            return;
        }

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
        System.out.println();
    }

    // Search for a key in the BST
    public boolean search(int data) {
        return searchRec(root, data) != null;
    }

    // Recursive search method
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
}

// TreeNode class representing each node in the BST
class TreeNode {
    int key;
    TreeNode left, right;

    public TreeNode(int data) {
        key = data;
        left = right = null;
    }
}
