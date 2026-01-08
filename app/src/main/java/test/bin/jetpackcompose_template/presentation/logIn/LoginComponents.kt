package test.bin.jetpackcompose_template.presentation.logIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomOutlinedBox(
    defaultValue: String,
    onUsernameChange: (String) -> Unit,
    labelText: String,
    keyboard: KeyboardType
) {
    OutlinedTextField(
        value = defaultValue,
        onValueChange = onUsernameChange,
        label = { Text(labelText, color = Color.Black) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboard, imeAction = ImeAction.Done
        ),
        visualTransformation = if (keyboard == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None,
        textStyle = TextStyle(color = Color.Black)

    )
}

@Composable
fun ComposeButton(buttonText: String, onButtonClick: () -> Unit) {
    Button(
        onClick = onButtonClick,
        modifier = Modifier.fillMaxWidth(),
        elevation = ButtonDefaults.elevatedButtonElevation(),
        shape = RoundedCornerShape(8.dp), // ⬅️ Rounded corners
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
    ) {
        Text(buttonText)
    }
}

@Composable
fun DropDownDemo(items:ArrayList<String>,onItemSelected: (String) -> Unit) {

    val isDropDownExpanded = remember {
        mutableStateOf(false)
    }

    val itemPosition = remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier .wrapContentHeight() // Instead of fillMaxSize
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable {
                        isDropDownExpanded.value = true
                    }
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
            ) {
                Text(text = items[itemPosition.value], Modifier
                    .fillMaxWidth()
                    .padding(10.dp), color = Color.Black)

                Image(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "DropDown Icon"
                )
            }
            DropdownMenu(
                expanded = isDropDownExpanded.value,
                onDismissRequest = {
                    isDropDownExpanded.value = false
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f) // Takes 80% of screen width (adjust as needed)
                    .background(Color.White)
            ) {
                items.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                                color = Color.Black,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxSize()
                            )
                        }, modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onItemSelected(item)
                            isDropDownExpanded.value = false
                            itemPosition.value = index
                        })
                }
            }
        }

    }
}



