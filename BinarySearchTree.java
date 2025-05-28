class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(6);
        bst.insert(7);
        bst.insert(12);
        bst.insert(2);
        bst.insert(5);
        bst.insert(10);

        bst.preorder();
        bst.inorder();
        bst.postorder();

        bst.deleteLeafNode(5);

        bst.preorder();
        bst.inorder();
        bst.postorder();
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

    public TreeNode insertRec(TreeNode root, int key) {
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }

        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        return root;
    }

    public void deleteLeafNode(int data) {
        root = deleteLeafNodeRec(root, data);
    }

    private TreeNode deleteLeafNodeRec(TreeNode root, int key) {
        if (root == null)
            return null;

        if (key < root.key) {
            root.left = deleteLeafNodeRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteLeafNodeRec(root.right, key);
        } else {
            if (root.left == null && root.right == null) {
                root = null;
            }
        }

        return root;
    }

    public void deleteNodeWithOneChild(int data) {
        root = deleteNodeWithOneChildRec(root, data);
    }

    private TreeNode deleteNodeWithOneChildRec(TreeNode root, int key) {
        if (root == null)
            return null;

        if (key < root.key) {
            root.left = deleteNodeWithOneChildRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteNodeWithOneChildRec(root.right, key);
        } else {
            if (root.left == null ^ root.right == null) {
                return (root.left != null) ? root.left : root.right;
            }
        }

        return root;
    }

    public void deleteNodeWithTwoChildren(int data) {
        root = deleteNodeWithTwoChildrenRec(root, data);
    }

    public TreeNode deleteNodeWithTwoChildrenRec(TreeNode root, int key) {
        if (root == null)
            return null;

        if (key < root.key) {
            root.left = deleteNodeWithTwoChildrenRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteNodeWithTwoChildrenRec(root.right, key);
        } else {
            TreeNode successor = findSuccessor(root.right);
            root.key = successor.key;
            root.right = deleteNodeWithTwoChildrenRec(root.right, successor.key);
        }

        return root;
    }

    private TreeNode findSuccessor(TreeNode root) {
        if (root.left == null) {
            return root;
        }
        return findSuccessor(root.left);
    }

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
}

class TreeNode {
    int key;
    TreeNode left, right;

    public TreeNode(int data) {
        key = data;
        left = right = null;
    }
}