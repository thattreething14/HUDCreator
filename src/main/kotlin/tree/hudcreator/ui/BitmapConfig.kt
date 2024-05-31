package tree.hudcreator.ui

data class BitmapConfig(
    val imageName: String,
    var height: Int,
    var ascent: Int,
    val unicode: Char
) {

    @JvmName("setHeightValue")
    fun setHeight(newHeight: Int) {
        height = newHeight
    }

    @JvmName("setAscentValue")
    fun setAscent(newAscent: Int) {
        ascent = newAscent
    }
}
