package com.example.assignmentweek3.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddToDrive
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignmentweek3.ui.theme.PinkPastel
import kotlinx.coroutines.launch


fun countAverege(first:Int,second:Int,third:Int): Int {
    return ((first+second+third)/3)
}

fun isPassed(scoreAvg:Int):String{
     if (scoreAvg >= 70){
         return  "Siswa mengerti pelajaran"
     }
    return "Siswa perlu diberi soal tambahan"
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentScore(){
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var isCalculated by rememberSaveable { mutableStateOf(false) }
    var shawnScore by rememberSaveable { mutableStateOf("") }
    val setShawnScore: (String) -> Unit = { shawnScore = it ; isCalculated = false  }
    var peteScore by rememberSaveable { mutableStateOf("") }
    val setPeteScore: (String) -> Unit = { peteScore = it ; isCalculated = false }
    var ardhiScore by rememberSaveable { mutableStateOf("") }
    val setArdhiScore: (String) -> Unit = { ardhiScore = it ; isCalculated = false }

    Scaffold (
        snackbarHost = {
            SnackbarHost (
                hostState = snackbarHostState,
                snackbar = { CustomeSnackBar(snackbarData = it)}
            )
        }
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "StudentScore",
                modifier = Modifier
                    .background(PinkPastel)
                    .fillMaxWidth()
                    .padding(10.dp), fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Column(
            modifier =  Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = Icons.Default.AddToDrive,
                    contentDescription = "Image",
                    modifier = Modifier
                        .size(100.dp))

                CustomeTextField(name = "Shawn score",
                    info = "insert Shawn score",
                    keyboardType = KeyboardType.Number,
                    borderColor = PinkPastel,
                    state = shawnScore, onStateChange = setShawnScore)
                CustomeTextField(name = "Shawn score",
                    info = "insert Shawn score",
                    keyboardType = KeyboardType.Number,
                    borderColor = PinkPastel,
                    state = peteScore, onStateChange = setPeteScore)
                CustomeTextField(name = "Shawn score",
                    info = "insert Shawn score",
                    keyboardType = KeyboardType.Number,
                    borderColor = PinkPastel,
                    state = ardhiScore, onStateChange = setArdhiScore)
                Button(onClick = {
                    isCalculated = true

                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = isPassed(countAverege(shawnScore.toInt(),peteScore.toInt(),ardhiScore.toInt())),
                            duration = SnackbarDuration.Short
                        )
                    }

                }) {
                    Text(text = "Calculate average",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(15.dp))
                }
                if (isCalculated){
                    Column(
                        modifier = Modifier
                            .padding(15.dp)
                        ,
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier = Modifier
                                .border(2.dp, PinkPastel, RoundedCornerShape(25.dp))
                                .padding(15.dp)
                        ) {
                            Text(text = "Average score : ${countAverege(shawnScore.toInt(),peteScore.toInt(),ardhiScore.toInt())}",
                                fontWeight = FontWeight.Bold,
                                color = PinkPastel)
                        }
                    }
                }
            }
        }
        }

    }




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StudentScorePreview(){
    StudentScore()
}