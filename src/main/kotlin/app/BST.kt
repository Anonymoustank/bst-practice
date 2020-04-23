package app

data class BST<T: Comparable<T>> (
        var value: T,
        var left: BST<T>?,
        var right: BST<T>?
    ) {

    /* find a node in the bst, or return null if it doesn't exist */
    fun find(target: T): T? {
        return when {
            value == target -> value
            target < value  -> left?.find(target)
            target > value  -> right?.find(target)
            else            -> null
        }
    }

    /* insert a node into the tree
    *  if the node already exists in the tree, this shouldn't do anything */
    fun insert(target: T) {
        TODO()
    }

    /* delete an item from the tree, and return the new root
    * if there are no elements left in the tree, return null
    * if the node doesn't exist in the tree, this shouldn't do anything */
    fun delete(target: T): BST<T>? {
        // notes: if target==value, you will need to return a node other than `this` as the new root
        TODO()
    }
}

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val tree = BST(5, BST(4, BST(2, null, null), null), BST(6, null, BST(8, BST(7, null, null), null)))
        println(tree.find(11))
    }
}