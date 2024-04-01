package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LemonadeApp() {
    var currentState by remember { mutableIntStateOf(1) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            when (currentState) {
                1 -> {
                    LemonadeImageAndText(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center),
                        imageResourceId = R.drawable.lemon_tree,
                        textResourceId = R.string.prompt_1,
                        descriptionId = R.string.lemon_tree,
                        onImageClick = { currentState = 2 })
                }

                2 -> {
                    var tapsRequired = (2..4).random()
                    LemonadeImageAndText(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center),
                        imageResourceId = R.drawable.lemon_squeeze,
                        textResourceId = R.string.prompt_2,
                        descriptionId = R.string.lemon,
                        onImageClick = {
                            tapsRequired--
                            if (tapsRequired == 0) {
                                currentState = 3
                            }
                        })
                }

                3 -> {
                    LemonadeImageAndText(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center),
                        imageResourceId = R.drawable.lemon_drink,
                        textResourceId = R.string.prompt_3,
                        descriptionId = R.string.glass_of_lemonade,
                        onImageClick = { currentState = 4 })
                }

                4 -> {
                    LemonadeImageAndText(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center),
                        imageResourceId = R.drawable.lemon_restart,
                        textResourceId = R.string.prompt_4,
                        descriptionId = R.string.glass_of_nothing,
                        onImageClick = { currentState = 1 })
                }
            }
        }
    }
}

@Composable
fun LemonadeImageAndText(
    modifier: Modifier = Modifier,
    imageResourceId: Int,
    textResourceId: Int,
    descriptionId: Int,
    onImageClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onImageClick
        ) {
            Image(
                painter = painterResource(imageResourceId),
                contentDescription = stringResource(descriptionId)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(textResourceId),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
