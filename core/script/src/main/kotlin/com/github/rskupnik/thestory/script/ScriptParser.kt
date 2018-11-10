package com.github.rskupnik.thestory.script

import com.github.rskupnik.thestory.script.domain.*
import com.github.rskupnik.wordplay.output.AnchoredObject
import com.github.rskupnik.wordplay.output.MetaList
import com.github.rskupnik.wordplay.output.WordplayOutput
import java.util.*
import kotlin.collections.HashSet

const val PARAM_ID = "id"
const val PARAM_CLICKABLE = "clickable"
const val EXITS_LIST_ID = "exits"

class ScriptParser {

    fun parse(input: WordplayOutput): Script {
        val elements: MutableSet<Element> = TreeSet()

        // Parse anchored objects
        elements.addAll(input.anchoredObjects.map { anchoredObjectToText(it) })

        // Parse newlines
        input.text.forEachIndexed { index, c -> if (c == '\n') elements.add(Newline(index)) }

        // Parse remaining pieces of text
        var start = 0
        var end: Int
        val toAdd: MutableSet<Element> = HashSet()
        elements.forEach {
            // Set the bounding endIndex to the beginning position of the element
            // This is done to check if there is any text before the element
            end = it.position

            // Add the text leading up to the element, if there is any
            val text = input.text.substring(start, end)
            if (text != "" && text != "\n") {
                toAdd.add(Text(start, text, null, false, null))
            }

            // Move the startPosition for next iteration to the end of the object
            start = end + it.length
        }

        // Merge the toAdd list with the elements list
        elements.addAll(toAdd)

        // Add the remainder of the text after the last element
        // startIndex at this point holds the end of the last element
        val remainder = input.text.substring(start)
        elements.add(Text(start, remainder, null, false, null))

        return Script(elements, parseExits(input))
    }

    private fun parseExits(input: WordplayOutput): Set<Exit> =
            findExitList(input)?.data?.asSequence()
                    ?.map { Exit.valueOf(it.toUpperCase()) }
                    ?.toCollection(HashSet()) ?: emptySet()

    private fun findExitList(input: WordplayOutput): MetaList? = input.metaObjects.find { it.id == EXITS_LIST_ID } as? MetaList

    private fun anchoredObjectToText(obj: AnchoredObject): Text = Text(
            obj.position,
            obj.text,
            obj.getStringParam(PARAM_ID),
            obj.getParam(PARAM_CLICKABLE) != null,
            extractColor(obj)
    )

    private fun extractColor(obj: AnchoredObject): Text.Color? {
        val color = obj.getStringParam("color")

        return if (color == null || color.equals("")) null
        else if (color.startsWith("#")) extractColorHex(color)
        else if (color.startsWith("(")) extractColorRGBA(color)
        else null
    }

    private fun extractColorHex(color: String): Text.Color {
        val color = color.substring(1, color.length)    // Skip the '#'
        val values: Array<Int> = arrayOf(
                Integer.parseInt(color.substring(0, 2), 16),
                Integer.parseInt(color.substring(2, 4), 16),
                Integer.parseInt(color.substring(4, 6), 16),
                Integer.parseInt(color.substring(6, 8), 16)
        )
        return Text.Color(values[0], values[1], values[2], values[3])
    }

    private fun extractColorRGBA(color: String): Text.Color {
        val values = color
                .substring(1, color.length - 1)
                .split(",")
                .map { Integer.parseInt(it.trim()) }
        return Text.Color(values[0], values[1], values[2], values[3])
    }
}