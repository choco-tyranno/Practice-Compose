package io.github.choco_tyranno.practicecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
    } else {
        UserCards(
            List(100){"$it 번 사용자"}
        )
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

@Composable
fun UserCard(name: String, index : Int) {
    var expended by rememberSaveable {
        mutableStateOf(false)
    }

    val extraSize by animateDpAsState(
        if (expended) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessVeryLow
        )
    )

    Card(
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()
        ) {

            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(id = R.string.card_profile_image),
                modifier = Modifier
                    .background(color = Color.Green)
                    .size(100.dp + extraSize)
            )

            Column(modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .height(100.dp + extraSize)
            ) {
                Text(text = "name : $name")
                Text(text = "index : $index")
                if (expended) Text(text = "Expended!")
            }

            DropDown(expended = expended){ expended = !expended}
        }
    }
}

@Composable
fun DropDown(expended : Boolean, onDropDownClick : ()->Unit){
    IconButton(onClick = onDropDownClick ) {
        Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_drop_down_24),
            contentDescription = stringResource(id = R.string.card_show_detail),
            modifier = Modifier
                .size(30.dp)
                .rotate(if (expended) 180f else 0f)
        )
    }
}

@Composable
fun UserCards(names : List<String>){
    LazyColumn{
        items(items = names){name->
            UserCard(name = name, index = names.indexOf(name))
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

@Preview(showBackground = true, heightDp = 100)
@Composable
fun UserCardPreview() {
    PracticeComposeTheme {
        UserCard("Choco tyranno", 0)
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 1000)
@Composable
fun UserCardsPreview(){
    PracticeComposeTheme{
        UserCards(names = List(100){"$it 번 사용자"})
    }
}
