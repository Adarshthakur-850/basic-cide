#include <iostream>

struct TreeNode {
    int key;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int k) : key(k), left(nullptr), right(nullptr) {}
};

TreeNode* insert(TreeNode* root, int key) {
    if (root == nullptr) {
        return new TreeNode(key);
    }

    if (key < root->key) {
        root->left = insert(root->left, key);
    } else if (key > root->key) {
        root->right = insert(root->right, key);
    }

    return root;
}

TreeNode* minValueNode(TreeNode* node) {
    TreeNode* current = node;
    while (current->left != nullptr) {
        current = current->left;
    }
    return current;
}

TreeNode* deleteNode(TreeNode* root, int key) {
    if (root == nullptr) {
        return root;
    }

    if (key < root->key) {
        root->left = deleteNode(root->left, key);
    } else if (key > root->key) {
        root->right = deleteNode(root->right, key);
    } else {
        if (root->left == nullptr) {
            TreeNode* temp = root->right;
            delete root;
            return temp;
        } else if (root->right == nullptr) {
            TreeNode* temp = root->left;
            delete root;
            return temp;
        }

        TreeNode* temp = minValueNode(root->right);
        root->key = temp->key;
        root->right = deleteNode(root->right, temp->key);
    }

    return root;
}

void preorderTraversal(TreeNode* root) {
    if (root == nullptr) {
        return;
    }

    std::cout << root->key << " ";
    preorderTraversal(root->left);
    preorderTraversal(root->right);
}

void postorderTraversal(TreeNode* root) {
    if (root == nullptr) {
        return;
    }

    postorderTraversal(root->left);
    postorderTraversal(root->right);
    std::cout << root->key << " ";
}

int findMax(TreeNode* root) {
    while (root->right != nullptr) {
        root = root->right;
    }
    return root->key;
}

int findMin(TreeNode* root) {
    while (root->left != nullptr) {
        root = root->left;
    }
    return root->key;
}

int main() {
    TreeNode* root = nullptr;

    while (true) {
        std::cout << "\nBinary Search Tree Operations:" << std::endl;
        std::cout << "1. Insert" << std::endl;
        std::cout << "2. Delete" << std::endl;
        std::cout << "3. Preorder Traversal" << std::endl;
        std::cout << "4. Postorder Traversal" << std::endl;
        std::cout << "5. Find Max" << std::endl;
        std::cout << "6. Find Min" << std::endl;
        std::cout << "7. Exit" << std::endl;

        int choice;
        std::cout << "Enter your choice: ";
        std::cin >> choice;

        switch (choice) {
            case 1:
                int key;
                std::cout << "Enter the value to insert: ";
                std::cin >> key;
                root = insert(root, key);
                break;

            case 2:
                int deleteKey;
                std::cout << "Enter the value to delete: ";
                std::cin >> deleteKey;
                root = deleteNode(root, deleteKey);
                break;

            case 3:
                std::cout << "Preorder Traversal: ";
                preorderTraversal(root);
                std::cout << std::endl;
                break;

            case 4:
                std::cout << "Postorder Traversal: ";
                postorderTraversal(root);
                std::cout << std::endl;
                break;

            case 5:
                int maxVal = findMax(root);
                std::cout << "Maximum value in the tree: " << maxVal << std::endl;
                break;

            case 6:
                int minVal = findMin(root);
                std::cout << "Minimum value in the tree: " << minVal << std::endl;
                break;

            case 7:
                return 0;

            default:
                std::cout << "Invalid choice. Please try again." << std::endl;
        }
    }

    return 0;
}
