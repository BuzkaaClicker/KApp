package pl.buzkaaclicker.app.key

interface KeyBind {
    fun isActive(keyDetector: KeyDetector): Boolean
}

class SingleKeyBind(private val key: Key) : KeyBind {
    override fun isActive(keyDetector: KeyDetector) = keyDetector.isKeyDown(this.key)
}

class UnionKeyBind(private val keyBinds: List<KeyBind>) : KeyBind {
    override fun isActive(keyDetector: KeyDetector) = this.keyBinds.all { it.isActive(keyDetector) }
}
