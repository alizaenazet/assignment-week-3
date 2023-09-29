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
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.assignmentweek3.ui.theme.PinkPastel
import kotlinx.coroutines.launch
import java.util.Calendar



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgeGenerator(){
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var isCalculated by rememberSaveable { mutableStateOf<Boolean>(false) }
    var name by rememberSaveable { mutableStateOf<String>("") }
    val setName :(String)-> Unit = {name = it; isCalculated = false}
    var birthYear by rememberSaveable { mutableStateOf<String>("")}
    val setBirthYear :(String) -> Unit = {birthYear = it; isCalculated = false}
    val year : Int = Calendar.getInstance().get(Calendar.YEAR)
    fun calculateAge(yearOfBirt: Int, yearRN: Int): Int{
        return yearRN - yearOfBirt
    }
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
              .fillMaxWidth()
          ,
          verticalArrangement = Arrangement.SpaceBetween
      ){
          Text(text = "AgeCalculator",
              modifier = Modifier
                  .background(PinkPastel)
                  .fillMaxWidth()
                  .padding(10.dp)
              , fontWeight = FontWeight.Bold,
              color = Color.White)
          Column(
              modifier = Modifier
                  .fillMaxWidth()
                  .fillMaxHeight()
                  .padding(top = 150.dp, bottom = 20.dp, start = 10.dp, end = 10.dp),
              horizontalAlignment = Alignment.CenterHorizontally,
              verticalArrangement = Arrangement.spacedBy(10.dp)
          ) {
              Icon(imageVector = Icons.Default.SentimentVerySatisfied,
                  tint = Color.Magenta,
                  modifier = Modifier
                      .size(100.dp),
                  contentDescription = "img",)

              CustomeTextField(
                  name = "name",
                  info = "insert your name",
                  state = name,
                  borderColor = PinkPastel,
                  onStateChange = setName
              )

              CustomeTextField(
                  name = "year",
                  info = "insert your birth year",
                  state = birthYear,
                  onStateChange = setBirthYear,
                  borderColor = PinkPastel,
                  keyboardType = KeyboardType.Number
              )

              Button(
                  colors = ButtonDefaults.buttonColors(PinkPastel),
                  onClick = { isCalculated = true
                      scope.launch {
                          snackbarHostState.showSnackbar(
                              message = "Hi, ${name} Your Age is ${calculateAge(birthYear.toInt(),year)} years",
                              duration = SnackbarDuration.Short
                          )
                      }
                  }

              ) {
                  Text(text = "Calculate Your age")
              }
              if (isCalculated){
                  Column(
                      modifier = Modifier
                          .fillMaxHeight()
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
                          Text(text = "Hi, ${name} Your Age is ${calculateAge(birthYear.toInt(),year)} years",
                              fontWeight = FontWeight.Bold,
                              color = PinkPastel)
                      }
                  }
              }
          }
      }
  }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AgeGeneratorPreview(){
    AgeGenerator()
}