package pl.buzkaaclicker.app.config

import pl.buzkaaclicker.app.key.KeyBind
import pl.buzkaaclicker.app.key.SingleKeyBind
import pl.buzkaaclicker.app.ratelimit.ConstantRateLimit
import pl.buzkaaclicker.app.ratelimit.RateLimit
import java.time.Duration

interface Configuration {
    fun lmb(): Clicker

    data class Clicker(val keyBind: KeyBind, val rateLimit: RateLimit)
}

class YamlConfiguration : Configuration {
    override fun lmb() = Configuration.Clicker(
        SingleKeyBind(SAMPLE_KEY),
        ConstantRateLimit(Duration.ofSeconds(1)),
    )

    companion object {
        private const val SAMPLE_KEY = 0x04
    }
}
