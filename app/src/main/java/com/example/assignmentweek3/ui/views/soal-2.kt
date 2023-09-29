package com.example.assignmentweek3.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face2
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


    fun generateBmiResult(weight: Int, height: Int): String {
        val score: Int = (height*height) / weight
        var result:String = "";
        if(score < 18.5){
            result = "Underweight"
        }else if(score < 24.9) {
            result = "Normal Weight"
        }else if(score < 29.9){
            result = "Overweight"
        }else if(score < 34.9){
            result = "Obesity Class I"
        }else if(score < 39.9){
            result = "Obesity Class 2"
        }else if(score > 39.9){
            result = "Obesity Class 3"
        }
        val bmiResult = "Your Height: ${height}\nYour Weight: ${weight}\nYour BMI Score: ${score}\nyou are ${result} weight"
        return bmiResult
    }

@Composable
fun BmiAnalysist(){
    var weight by rememberSaveable { mutableStateOf<String>("") }
    fun weightOnChange(input:String): Boolean{
        if (input.isEmpty() || input.toDoubleOrNull() === null || input.toDoubleOrNull()!! < 1){
            weight = ""
            return false  }
        weight = input
        return true
    }
    var height by rememberSaveable { mutableStateOf("") }
    fun heightOnChange(input:String): Boolean{
        if (input.isEmpty() || input.toDoubleOrNull() === null || input.toDoubleOrNull()!! < 1){
            height = ""
            return false  }
        height = input
        return true
    }

    var isAllValid by rememberSaveable { mutableStateOf<Boolean>(false) }
    val  setIsAllValid: (condition:Boolean) -> Unit ={ isAllValid = it}
    var openAlertDialog by remember { mutableStateOf(false) }
    val setOpenAlertDialog: (isOpen:Boolean) ->  Unit = {openAlertDialog = it}
    setIsAllValid((weight.toDoubleOrNull() !== null && weight.toDoubleOrNull()!! > 0) && (height.toDoubleOrNull() !== null && height.toDoubleOrNull()!! > 0))


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 15.dp, start = 10.dp, end = 10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Icon(imageVector = Icons.Filled.Face2,
            contentDescription = "Face",
            tint = Color.Blue,
            modifier = Modifier
                .size(100.dp)
        )

        CustomeTextFieldValidation(
            name = "Weight(Kg)",
            info = "insert u weight in Kg",
            state = weight,
            onChange = ::weightOnChange,
            borderColor = Color.Blue,
            invalidFeedBack = "Please enter a valid Weight greather than 0",
            keyboardType = KeyboardType.Number
        )
        CustomeTextFieldValidation(
            name = "Height(Kg)",
            info = "insert u Height in Kg",
            borderColor = Color.Blue,
            invalidFeedBack = "Please enter a valid Height greather than 0",
            state = height,
            onChange = ::heightOnChange,
            keyboardType = KeyboardType.Number
        )


        Button(onClick = {
            if (isAllValid){
                setOpenAlertDialog(true)
            }else{
                setOpenAlertDialog(false)
            }
        }, modifier = Modifier.fillMaxWidth()) {
            var buttonColor : Color;

            if(openAlertDialog){
                buttonColor = Color.Blue
            }else{
                buttonColor = Color.Gray
            }
            Text(text = "Calculate BMI")
        }
        if (openAlertDialog){
            AlertDialog({ setOpenAlertDialog(false) }, { setOpenAlertDialog(false) }, dialogTitle= "Your Bmi Analysis", dialogText = generateBmiResult(weight.toInt(),height.toInt()))
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
) {
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Ok")
            }
        },
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BmiAnalysistPreview(){
    BmiAnalysist()
}