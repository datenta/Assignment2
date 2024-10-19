package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState



class Start : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartPager(onSkip = {
                startActivity(Intent(this@Start, MainActivity::class.java))
                finish()
            })
        }
    }
}

data class OnboardingPageData(
    val description: String,
    val imageRes: Int
)

val onboardingPages = listOf(
    OnboardingPageData(
        description = "Узнавай о премьерах",
        imageRes = R.drawable.wfh_4
    ),
    OnboardingPageData(
        description = "Создавай коллекции",
        imageRes = R.drawable.wfh_8
    ),
    OnboardingPageData(
        description = "Делись с друзьями",
        imageRes = R.drawable.wfh_2
    )
)

@Composable
fun StartPager(onSkip: () -> Unit) {
    val pagerState = rememberPagerState()

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Skillcinema",
                fontSize = 24.sp,
            )

            TextButton(onClick = onSkip) {
                Text(
                    text = "Пропустить",
                    color = Color(0xFFB5B5C9),
                    fontSize = 14.sp,
                    lineHeight = 15.sp
                )
            }
        }

        HorizontalPager(
            count = onboardingPages.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            val onboardingPage = onboardingPages[page]
            OnboardingScreen(onboardingPage)
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
    }
}

@Composable
fun OnboardingScreen(pageData: OnboardingPageData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(166.dp))
        Image(
            painter = painterResource(id = pageData.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(16.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(68.dp))

        Text(
            text = pageData.description,
            fontSize = 32.sp,
            modifier = Modifier.padding(end = 140.dp)
        )
    }
}


