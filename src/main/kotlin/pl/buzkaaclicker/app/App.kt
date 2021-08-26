package pl.buzkaaclicker.app

import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import pl.buzkaaclicker.app.clicker.Clicker
import pl.buzkaaclicker.app.clicker.tickLoop
import pl.buzkaaclicker.app.config.Configuration
import pl.buzkaaclicker.app.config.YamlConfiguration
import pl.buzkaaclicker.app.key.KeyDetector
import pl.buzkaaclicker.app.key.LMB
import pl.buzkaaclicker.app.key.NativeKeyDetector
import pl.buzkaaclicker.app.key.NativeKeyRobot
import pl.buzkaaclicker.app.ui.MainView
import pl.buzkaaclicker.app.ui.system.WindowManageBar

fun main() = application {
    val configuration: Configuration = YamlConfiguration()
    val keyRobot = NativeKeyRobot()
    val keyDetector: KeyDetector = NativeKeyDetector()
    val appScope = CoroutineScope(
        Dispatchers.IO + CoroutineName("BuzkaaClickerMain") + SupervisorJob() + exceptionHandler())

    appScope.launch {
        createClicker(keyRobot, keyDetector, configuration.lmb())
            .tickLoop()
    }

    val exitApp: ApplicationScope.() -> Unit = {
        appScope.cancel("App closed")
        exitApplication()
    }

    MainWindow(onExit = exitApp)
}

private fun exceptionHandler() = CoroutineExceptionHandler { _, exception ->
    println("CoroutineExceptionHandler got $exception")
}

private fun createClicker(
    keyRobot: NativeKeyRobot,
    keyDetector: KeyDetector,
    config: Configuration.Clicker,
) = Clicker(
    { keyRobot.click(LMB) },
    keyDetector,
    config.keyBind,
    config.rateLimit,
)

@Composable
private fun ApplicationScope.MainWindow(onExit: ApplicationScope.() -> Unit) {
    val windowState = rememberWindowState(isMinimized = false)
    Window(
        onCloseRequest = ::exitApplication,
        undecorated = true,
        resizable = false,
        state = windowState,
    ) {
        window.background = java.awt.Color(0, 0, 0, 0)
        window.setSize(960, 580)

        WindowDraggableArea(
            modifier = Modifier.background(color = Color(0, 0, 0, 0))
                .fillMaxWidth()
                .fillMaxHeight()
        )

        DesktopMaterialTheme {
            MainView()
        }

        WindowManageBar(
            onClose = { onExit() },
            onMinimize = { windowState.isMinimized = true },
            modifier = Modifier.padding(30.dp),
        )
    }
}
