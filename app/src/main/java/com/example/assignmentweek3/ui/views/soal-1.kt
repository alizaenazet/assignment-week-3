package com.example.assignmentweek3.ui.views

import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChangeHistory
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignmentweek3.R

@Composable
fun LuasSegitaCounter(){
    var base by rememberSaveable { mutableStateOf<String>("") }
    val setBase: (String) -> Unit  = {base = it}
    var height by rememberSaveable { mutableStateOf<String>("") }
    val setHeight: (String) -> Unit  = {height = it}
    var area by rememberSaveable { mutableStateOf<Double>(0.0) }

    if (base.toDoubleOrNull() !== null && height.toDoubleOrNull() !== null){
        area = (base.toDouble() * height.toDouble()) / 2
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 25.dp, bottom = 15.dp, start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ){
        Icon(
            modifier = Modifier
                .size(100.dp),
            tint = Color.Blue,
            imageVector = Icons.Default.ChangeHistory,
            contentDescription = "Segitiga")
        CustomeTextField(
            name = "base", info = "base",
            state = base,
            onStateChange = setBase,
            borderColor = Color.Blue,
            keyboardType = KeyboardType.Number)
        CustomeTextField(
            name = "height", info = "insert the height",
            state = height,
            onStateChange = setHeight,
            borderColor = Color.Blue,
            keyboardType = KeyboardType.Number)
       Column(
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.spacedBy(5.dp)
       ) {
           Text(text = "The Triangle area is:",
               fontWeight = FontWeight.Bold,
               fontSize = 25.sp,
           )
           Text(text = area.toString(),
               fontWeight = FontWeight.Bold,
               fontSize = 30.sp,)
       }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LuasSegitaCounterPreview(){
    LuasSegitaCounter()
}