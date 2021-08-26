package pl.buzkaaclicker.app.key

interface KeyDetector {
    fun isKeyDown(key: Key): Boolean
}

class NativeKeyDetector : KeyDetector {
    override fun isKeyDown(key: Key) = true
}
