package com.example.assignmentweek3.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomeTextField(name: String,
                     info: String,
                     state: String,
                     onStateChange: (String) -> Unit,
                     keyboardType: KeyboardType = KeyboardType.Text,
                     modifier: Modifier = Modifier.fillMaxWidth(),
                     borderColor: Color = Color.Gray,
                     visualTransformation: VisualTransformation = VisualTransformation.None,){
    OutlinedTextField(
        value = state,
        label = { Text(text = name)},
        placeholder = { Text(text = info)},
        keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = ImeAction.Next
        ),
        onValueChange = onStateChange,
        shape = RoundedCornerShape(24.dp),
                visualTransformation = visualTransformation,
        modifier = modifier,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
        )
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomeTextFieldValidation(
    name: String,
    info: String,
    state: String,
    onChange: (String) -> Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
    invalidFeedBack: String = "Invalid input",
    modifier: Modifier = Modifier.fillMaxWidth(),
    borderColor: Color = Color.Gray,
    visualTransformation: VisualTransformation = VisualTransformation.None,){
    var textInput by remember { mutableStateOf("") }
    var isValid by remember{ mutableStateOf(true) }
    OutlinedTextField(
        value = state,
        label = { Text(text = name)},
        placeholder = { Text(text = info)},
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = ImeAction.Next
        ),
        onValueChange = {input ->
            isValid = onChange(input)
            textInput = input
        },
        shape = RoundedCornerShape(24.dp),
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            ),
        isError = !isValid,
        modifier = modifier
    )
    if (!isValid){
        Text(text = invalidFeedBack)
    }

}

@Composable
fun CustomeSnackBar(snackbarData: SnackbarData){
    Surface(
        shape = RoundedCornerShape(25.dp),
        color = Color.DarkGray,
        shadowElevation = 2.dp,
        modifier = Modifier
            .padding(20.dp)
    ) {
        Row (
            modifier = Modifier
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Icon(imageVector = Icons.Default.Mail,
                contentDescription = "mail icon", Modifier.size(14.dp), tint = Color.Green)
            Text(
                text = snackbarData.visuals.message,
                color = Color.White)
        }
    }
}

