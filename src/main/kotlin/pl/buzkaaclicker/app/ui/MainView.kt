package pl.buzkaaclicker.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.vavr.collection.List

private val mainRoutes = List.of(
    Route("Strona główna") {
        Text("gitara siema")
    },
    Route("Lewy przycisk") {},
    Route("Prawy przycisk") {},
    Route("Kopanie") {},
)

@Composable
fun MainView() {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0x42, 0x41, 0x52))
            .fillMaxSize()
    ) {
        val initialRouteIndex = 0
        val currentRoute = remember { mutableStateOf(mainRoutes[initialRouteIndex]) }
        NavigationBar(
            initialRouteIndex = initialRouteIndex,
            routes = mainRoutes,
            onRouteChange = {
                currentRoute.value = this
            },
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp))
                .background(Color(0x50, 0x4F, 0x63))
        ) {
            currentRoute.value.content.invoke()
        }
    }
}
