package com.github.chkypros.aoc2022.day7

import com.github.chkypros.aoc_template.AbstractSolution
import java.util.function.Predicate
import java.util.stream.Stream

const val FILESYSTEM_SIZE = 70000000
const val SPACE_NEEDED_FOR_UPDATE = 30000000

class NoSpaceLeftOnDevice : AbstractSolution() {
    override fun solvePartOne(stream: Stream<String>): Any {
        val root = prepareFSTree(stream)

        // Find dirs with total size less than 100000 and sum sizes
        return sumSizeFor(root) { it.isDir && it.size!! <= 100000 }
    }

    override fun solvePartTwo(stream: Stream<String>): Any {
        val root = prepareFSTree(stream)
        val spaceNeeded = SPACE_NEEDED_FOR_UPDATE - (FILESYSTEM_SIZE - root.size!!)
        return sizeOfSmallestDirForSpace(root, spaceNeeded)!!
    }

    private fun prepareFSTree(stream: Stream<String>): FSNode {
        val root = constructFSTree(stream)
        updateSizes(root)
        return root
    }

    private fun constructFSTree(stream: Stream<String>): FSNode {
        val lines = stream.toList()
        val root = FSNode(isDir = true, name = "/")
        var currentNode = root

        for (i in 1 until lines.size) {
            val line = lines[i]
            if (line.startsWith("$ ")) {
                if (line.equals("$ ls")) {
                    continue
                }
                val dirName = line.substringAfter("$ cd ")
                currentNode = if (".." == dirName) {
                    currentNode.parent!!
                } else {
                    val fsNode = FSNode(isDir = true, name = dirName, parent = currentNode)
                    currentNode.children.add(fsNode)
                    fsNode
                }
            } else if (line.startsWith("dir ")) {
                continue
            } else {
                val commandParts = line.split(" ")
                val fsNode = FSNode(
                    isDir = false,
                    name = commandParts[1],
                    parent = currentNode,
                    size = commandParts[0].toLong()
                )
                currentNode.children.add(fsNode)
            }
        }

        return root
    }

    private fun updateSizes(node: FSNode) {
        node.children
            .filter(FSNode::isDir)
            .forEach(this::updateSizes)

        node.size = node.children.map(FSNode::size).reduce { acc, l -> acc!! + l!! }
    }

    private fun sumSizeFor(node: FSNode, filter: Predicate<FSNode>): Long {
        val childrenSum = node.children.sumOf { sumSizeFor(it, filter) }

        val nodeSum = if (filter.test(node)) node.size!! else 0

        return nodeSum + childrenSum
    }

    private fun sizeOfSmallestDirForSpace(node: FSNode, spaceNeeded: Long): Long? {
        if (node.size!! < spaceNeeded) return null

        return node.children
            .filter(FSNode::isDir)
            .mapNotNull { sizeOfSmallestDirForSpace(it, spaceNeeded) }
            .minOrNull() ?: node.size!!
    }

    class FSNode(
        var isDir: Boolean,
        var name: String,
        var parent: FSNode? = null,
        var size: Long? = null,
        var children: ArrayList<FSNode> = ArrayList()
    )
}
