package tree.hudcreator.ui

class UIBuilder(private val size: Int, private val unicode: Char) {
    private val elements = MutableList(size) { UIElement(unicode, "base_icon") }
    fun setElement(index: Int, unicode: Char, iconName: String) {
        require(index in 0 until size) { "Index out of bounds" }
        elements[index] = UIElement(unicode, iconName)
    }

    fun addUnicode(index: Int, unicode: Char) {
        require(index in 0 until size) { "Index out of bounds" }
        elements[index].unicode = (elements[index].unicode.code + unicode.code).toChar()
    }

    fun subtractUnicode(index: Int, unicode: Char) {
        require(index in 0 until size) { "Index out of bounds" }
        elements[index].unicode = (elements[index].unicode.code - unicode.code).toChar()
    }

    fun build(): String {
        return elements.joinToString("") { it.unicode.toString() }
    }
}