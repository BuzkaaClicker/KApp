package pl.buzkaaclicker.app.clicker

import kotlinx.coroutines.delay
import pl.buzkaaclicker.app.key.KeyBind
import pl.buzkaaclicker.app.key.KeyDetector
import pl.buzkaaclicker.app.ratelimit.RateLimit
import java.time.Instant

data class Clicker(
    val clickExecutor: () -> Unit,
    val keyDetector: KeyDetector,
    val keyBind: KeyBind,
    val rateLimit: RateLimit,
)

private fun canClick(keyDetector: KeyDetector, keyBind: KeyBind, rateLimit: RateLimit, at: Instant) =
    keyBind.isActive(keyDetector) && rateLimit.isActiveAt(at).not()

tailrec suspend fun Clicker.tickLoop() {
    val rateLimit = this.rateLimit
    val now = Instant.now()
    val newModule = if (canClick(this.keyDetector, this.keyBind, rateLimit, now)) {
        this.clickExecutor.invoke()
        this.copy(
            rateLimit = rateLimit.hit(),
        )
    } else {
        this
    }

    delay(1L)
    newModule.tickLoop()
}

