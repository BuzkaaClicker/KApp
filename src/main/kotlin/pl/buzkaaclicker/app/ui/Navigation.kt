package pl.buzkaaclicker.app.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NavigationHeader() {
    Row {
        Image(
            painter = painterResource("ui/logo.png"),
            contentDescription = "Logo",
            modifier = Modifier.size(30.dp),
        )

        Row(
            modifier = Modifier.padding(6.dp),
        ) {
            Text("Buzkaa", fontWeight = FontWeight.Light, color = Color.White, fontSize = 24.sp)
            Text("Clicker", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 24.sp)
        }
    }
}

@Composable
fun NavigationBar() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(300.dp)
            .padding(20.dp)
            .fillMaxHeight(),
    ) {
        NavigationHeader()

        Spacer(Modifier.height(20.dp))

        NavigationItems()
    }
}

@Composable
private fun NavigationItems() {
    val (tab, setTab) = remember { mutableStateOf(0) }
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        NavigationItem("Strona główna", active = tab == 0) {
            setTab(0)
        }
        NavigationItem("Lewy przycisk", active = tab == 1) {
            setTab(1)
        }
        NavigationItem("Prawy przycisk", active = tab == 2) {
            setTab(2)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun NavigationItem(title: String, active: Boolean = false, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .clickable(onClick = onClick)
            .width(200.dp),
    ) {
        val alpha: Float by animateFloatAsState(if (active) 1f else 0f)
        Box(
            modifier = Modifier
                .width(2.dp)
                .height(20.dp)
                .padding(vertical = 2.dp)
                .graphicsLayer(alpha = alpha)
                .background(Color.White),
        )

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .padding(20.dp)
        ) {
            AnimatedContent(
                targetState = if (active) FontWeight.Bold else FontWeight.Light,
                transitionSpec = {
                    if (active) {
                        slideInVertically({ height -> height }) + fadeIn() with
                                slideOutVertically({ height -> -height }) + fadeOut()
                    } else {
                        slideInVertically({ height -> -height }) + fadeIn() with
                                slideOutVertically({ height -> height }) + fadeOut()
                    }.using(
                        SizeTransform(clip = false)
                    )
                }
            ) { fontWeight ->
                Text(title, color = Color.White, fontSize = 18.sp, fontWeight = fontWeight)
            }
        }
    }
}
