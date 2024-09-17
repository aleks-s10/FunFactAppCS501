package com.example.funfactapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.funfactapp.ui.theme.FunFactAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FunFactAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FunFactApp()
                }
            }
        }
    }
}

@Composable
fun FunFactApp() {
    val funFacts = listOf(
        "Avocados are not vegetables",
        "Dead skin cells are a main ingredient in household dust",
        "The Hawaiian alphabet has only 12 letters",
        "Octopuses have three hearts",
        "The circulatory system is more than 60,000 miles long"
    )

    var currentFactIndex by remember { mutableIntStateOf(funFacts.indices.random()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Did You Know?",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF6200EE), Color(0xFF3700B3))
                    )
                )
                .padding(16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Yellow, fontWeight = FontWeight.Bold)) {
                        append("Fun Fact: ")
                    }
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append(funFacts[currentFactIndex])
                    }
                },
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                currentFactIndex = (currentFactIndex + 1) % funFacts.size
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Next Fact", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FunFactAppPreview() {
    FunFactAppTheme {
        FunFactApp()
    }
}