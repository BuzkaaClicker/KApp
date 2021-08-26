package pl.buzkaaclicker.app.ui.system

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.unit.dp

@Composable
fun WindowManageBar(onClose: () -> Unit, onMinimize: () -> Unit, modifier: Modifier) {
    Row(
        modifier = modifier
            .height(15.dp)
    ) {
        // macOS/Darwin like behavior - all buttons become darker when one of the buttons is hovered
        val hovered = remember { mutableStateOf(false) }
        val onHoverChange = { it: Boolean -> hovered.value = it }

        val closeColor = if (hovered.value) {
            Color(0xAA, 0x2F, 0x27)
        } else {
            Color(0xFF, 0x5F, 0x57)
        }
        CircleButton(onClose, onHoverChange, closeColor)

        Spacer(Modifier.width(7.dp))

        val minimizeColor = if (hovered.value) {
            Color(0x9F, 0x8C, 0x00)
        } else {
            Color(0xFE, 0xBC, 0x2E)
        }
        CircleButton(onMinimize, onHoverChange, minimizeColor)
    }
}

@Composable
private fun CircleButton(onClick: () -> Unit, onHoverChange: (hovered: Boolean) -> Unit, color: Color) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(13.dp)
            .background(color)
            .clickable(onClick = onClick)
            .pointerMoveFilter(
                onEnter = {
                    onHoverChange(true)
                    false
                },
                onExit = {
                    onHoverChange(false)
                    false
                }
            )
    )
}
