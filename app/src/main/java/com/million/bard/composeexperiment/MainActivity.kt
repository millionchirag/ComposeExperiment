package com.million.bard.composeexperiment

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.million.bard.composeexperiment.ui.theme.ComposeExperimentTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)


        setContent {
            ComposeExperimentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val systemUiController = rememberSystemUiController()
                    SideEffect {
                        systemUiController.statusBarDarkContentEnabled = true
                    }
                    Greeting("Changes Made In Brand Update")
                    Greeting("Added Another Changes")
                    Greeting("Seconds Branch Changes Made")
                    Greeting("Pull Request Changes")
                }
            }
        }
        //window.fitSystemWindowsWithAdjustResize()

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = Color.White
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LazyColumn() {
                        items(50) {
                            Text(text = "Items")
                        }
                    }

                }
            }
        },
        gesturesEnabled = drawerState.isOpen,
    ) {


        Column(
            modifier = Modifier
                .background(color = Color.Red)
                .windowInsetsPadding(WindowInsets.statusBars)
                .fillMaxSize()
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Green)
                .clickable {
                    scope.launch {
                        drawerState.open()
                    }
                }) {
                Text(text = "Click Me")
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(100) {
                    Text(text = name)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeExperimentTheme {
        Greeting("Android")
    }
}


fun Window.fitSystemWindowsWithAdjustResize() {
    setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )

    WindowCompat.setDecorFitsSystemWindows(this, true)

    ViewCompat.setOnApplyWindowInsetsListener(decorView) { view, insets ->
        val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
        val top = insets.getInsets(WindowInsetsCompat.Type.ime()).top

        WindowInsetsCompat
            .Builder()
            .setInsets(
                WindowInsetsCompat.Type.statusBars(),
                Insets.of(0, 0, 0, bottom)
            )
            .build()
            .apply { ViewCompat.onApplyWindowInsets(view, this) }
    }
}
