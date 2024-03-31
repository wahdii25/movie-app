package com.yunwah.movieapp.presentation.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.yunwah.movieapp.R
import com.yunwah.movieapp.presentation.Dimens.MediumPadding1
import com.yunwah.movieapp.presentation.Dimens.MediumPadding2
import com.yunwah.movieapp.presentation.navgraph.Route
import com.yunwah.movieapp.ui.theme.MovieAppTheme

@Composable
fun OnBoardingScreen(
    navController: NavHostController,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally), // Center the image horizontally
                painter = painterResource(id = R.drawable.img_onboarding),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(MediumPadding1))
            Text(
                modifier = Modifier.padding(horizontal = MediumPadding2),
                text = "Access more with an account",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 28.sp,
                ),
                color = Color.Blue
            )
            Spacer(modifier = Modifier.height(MediumPadding1))
            Text(
                modifier = Modifier.padding(horizontal = MediumPadding2),
                text = "Login to an account so you could access more features",
                style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
                color = Color.Gray
            )
        }

        Column(modifier = Modifier.navigationBarsPadding()) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(size = 12.dp),
                onClick = {
//                    navController.popBackStack()
                    navController.navigate(Route.LoginScreen.route)
//                  event(OnBoardingEvent.SaveAppEntry)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Login")
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(size = 12.dp),
                border = BorderStroke(1.dp, Color.Blue),
                onClick = { /* Handle sign up button click */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Blue
                )
            ) {
                Text(text = "Sign Up")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPagePreview() {
    MovieAppTheme {
//        OnBoardingScreen()
    }
}