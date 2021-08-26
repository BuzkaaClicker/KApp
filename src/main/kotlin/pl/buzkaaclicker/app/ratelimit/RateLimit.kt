package pl.buzkaaclicker.app.ratelimit

import java.time.Duration
import java.time.Instant

interface RateLimit {
    fun isActiveAt(at: Instant): Boolean

    fun hit(): RateLimit
}

data class ConstantRateLimit(
    val delay: Duration,
    val lastHitAt: Instant? = null,
) : RateLimit {
    override fun isActiveAt(at: Instant) =
        lastHitAt?.plus(delay)?.isAfter(at) ?: false

    override fun hit() = ConstantRateLimit(delay, Instant.now())
}
