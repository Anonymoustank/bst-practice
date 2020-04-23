package app

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/* a test suite for the BST implementation */
class bstTest {
    /* check that a tree is consistent (ie left is always less than value, right is more) */
    fun <T: Comparable<T>> check(bst: BST<T>): Boolean {
        if(
            bst.left?.value != null &&
            (bst.left!!.value >= bst.value || !check(bst.left!!))
        ) return false

        if(
            bst.right?.value != null &&
            (bst.right!!.value <= bst.value || !check(bst.right!!))
        ) return false

        return true
    }

    /* check that a set of nodes are included in a tree */
    fun <T: Comparable<T>> checkNodesPresent(bst: BST<T>, values: List<T>): Boolean {
        for(v in values) {
            if(bst.find(v) != v) return false
        }
        return true
    }

    /* check that a set of nodes are not included in a tree */
    fun <T: Comparable<T>> checkNodesNotPresent(bst: BST<T>, values: List<T>): Boolean {
        for(v in values) {
            if(bst.find(v) == v) return false
        }
        return true
    }

    @Test
    fun insertTest1() {
        val t = BST(5, null, null)
        t.insert(6)
        assert(check(t))
        assert(checkNodesPresent(t, listOf(5, 6)))
    }

    @Test
    fun insertTest2() {
        val t = BST(5, null, null)
        t.insert(4)
        assert(check(t))
        assert(checkNodesPresent(t, listOf(5, 4)))
    }

    @Test
    fun insertTest3() {
        val t = BST(5, BST(2, null, BST(4, null, null)), BST(7, BST(6, null, null), BST(8, null, null)))
        t.insert(3)
        assert(check(t))
        assert(checkNodesPresent(t, listOf(2, 3, 4, 5, 6, 7, 8)))
    }

    @Test
    fun insertTest4() {
        val t = BST(5, BST(2, null, BST(4, null, null)), BST(7, BST(6, null, null), BST(8, null, null)))
        t.insert(9)
        assert(check(t))
        assert(checkNodesPresent(t, listOf(2, 4, 5, 6, 7, 8, 9)))
    }

    @Test
    fun insertTest5() {
        val t = BST(6, BST(1, BST(0, null, null), BST(4, BST(3, null, null), BST(5, null, null))), null)
        t.insert(2)
        assert(check(t))
        assert(checkNodesPresent(t, listOf(0, 1, 2, 3, 4, 5, 6)))
    }

    @Test
    fun insertTest6() {
        val t = BST(6, BST(1, BST(0, null, null), BST(4, BST(3, null, null), BST(5, null, null))), null)
        t.insert(3)
        assert(check(t))
        assert(checkNodesPresent(t, listOf(0, 1, 3, 4, 5, 6)))
    }

    @Test
    fun deleteTest1() {
        val t = BST(1, null, null)
        val nT = t.delete(1)
        assert(nT == null)
    }

    @Test
    fun deleteTest2() {
        val t = BST(1, BST(0, null, null), BST(2, null, null))
        val nT = t.delete(1)
        assert(nT != null)
        assert(check(nT!!))
        assert(checkNodesPresent(nT, listOf(0, 2)))
        assert(checkNodesNotPresent(nT, listOf(1)))
    }

    @Test
    fun deleteTest3() {
        val t = BST(1, BST(0, null, null), BST(2, null, null))
        val nT = t.delete(0)
        assert(nT != null)
        assert(check(nT!!))
        assert(checkNodesPresent(nT, listOf(1, 2)))
        assert(checkNodesNotPresent(nT, listOf(0)))
    }

    @Test
    fun deleteTest4() {
        val t = BST(1, BST(0, null, null), BST(2, null, null))
        val nT = t.delete(2)
        assert(nT != null)
        assert(check(nT!!))
        assert(checkNodesPresent(nT, listOf(0, 1)))
        assert(checkNodesNotPresent(nT, listOf(2)))
    }

    @Test
    fun deleteTest5() {
        val t = BST(6, BST(1, BST(0, null, null), BST(4, BST(3, null, null), BST(5, null, null))), null)
        val nT = t.delete(4)
        assert(nT != null)
        assert(check(nT!!))
        assert(checkNodesPresent(nT, listOf(0, 1, 3, 5, 6)))
        assert(checkNodesNotPresent(nT, listOf(4)))
    }

    @Test
    fun deleteTest6() {
        val t = BST(6, BST(1, BST(0, null, null), BST(4, BST(3, null, null), BST(5, null, null))), null)
        val nT = t.delete(1)
        assert(nT != null)
        assert(check(nT!!))
        assert(checkNodesPresent(nT, listOf(0, 3, 4, 5, 6)))
        assert(checkNodesNotPresent(nT, listOf(1)))
    }
}