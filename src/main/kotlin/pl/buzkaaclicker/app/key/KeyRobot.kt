package pl.buzkaaclicker.app.key

import java.time.Instant

interface KeyRobot {
    fun click(key: Key)
}

class NativeKeyRobot : KeyRobot {
    override fun click(key: Key) {
        println("clicked: $key at ${Instant.now().epochSecond}")
    }
}
