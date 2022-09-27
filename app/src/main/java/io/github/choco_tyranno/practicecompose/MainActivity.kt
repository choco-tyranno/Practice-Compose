package io.github.choco_tyranno.practicecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.choco_tyranno.practicecompose.ui.theme.PracticeComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeComposeTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){
    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true)}

    if (shouldShowOnBoarding) {
        OnBoarding(onContinueClicked = { shouldShowOnBoarding = false })
    }
}

@Composable
fun OnBoarding(onContinueClicked : ()->Unit){
    Surface{
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = stringResource(id = R.string.onBoarding_welcome)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(modifier = Modifier.fillMaxWidth(),
                onClick = onContinueClicked
            ) {
                Text(text = stringResource(id = R.string.onBoarding_continue),
                    fontSize = 9.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 200, heightDp = 320)
@Composable
fun OnBoardingPreview(){
    PracticeComposeTheme {
        OnBoarding (onContinueClicked = {})
    }
}

