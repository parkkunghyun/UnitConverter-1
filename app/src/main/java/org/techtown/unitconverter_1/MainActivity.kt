package org.techtown.unitconverter_1

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.techtown.unitconverter_1.ui.theme.UnitConverter1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverter1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // 이게 배경임
                    modifier = Modifier.fillMaxSize(), // 화면을 전체 채움
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 둘중 뭐가 먼저나와야하는지 모름! 열과 행이 지정안되어있다!
                    //Greeting("Android")
                    //Greeting("Android")
                    UnitConverter()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter() {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        // ui elements를 하나씩 쌓을예정 - 기본이 수직이네
        Text(text = "Unit Converter", modifier = Modifier.padding(20.dp))

        Spacer(modifier = Modifier.height(16.dp))

        // 값이 바뀌는 순간 무엇을 해야할지 지정
        // ui가 업데이트가 안됨 - 이 이유는 아직 onValueChange를 설정 안해서!
        OutlinedTextField(value = "", onValueChange = {
            // 값이 바뀌는 순간 변경 되는것
        })

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            //val context = LocalContext.current
            Box {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Select")
                    // contentDescription - 시각장애인등을 위한 사람들을 위한 접근성 서비스
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                // 일단 닫혀있어야함!
                DropdownMenu(expanded = false, onDismissRequest = {
                    // 드롭다운 메뉴가 닫혔을때 어떻게 할지 지정
                }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters")}, onClick = {  })
                    DropdownMenuItem(text = { Text(text = "Meters")}, onClick = {  })
                    DropdownMenuItem(text = { Text(text = "Feet")}, onClick = {  })
                    DropdownMenuItem(text = { Text(text = "Milimeters")}, onClick = {  })
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Select")
                    // contentDescription - 시각장애인등을 위한 사람들을 위한 접근성 서비스
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = true, onDismissRequest = {
                    // 드롭다운 메뉴가 닫혔을때 어떻게 할지 지정
                }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters")}, onClick = {  })
                    DropdownMenuItem(text = { Text(text = "Meters")}, onClick = {  })
                    DropdownMenuItem(text = { Text(text = "Feet")}, onClick = {  })
                    DropdownMenuItem(text = { Text(text = "Milimeters")}, onClick = {  })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: ")
    }
}

//이것은 컴파일러에게 어떤 함수를 선언적으로 취급하라 명령
//프레임워크에게 이 함수는 ui의 일부가 composition과 recomposition이 가능하다는걸 알림

// ui의 변화를 보려면 어플을 다시 시작해야함
// 프리뷰를 통해 우리 화면에서 굳이 매번 시작안하고 볼수있다
@Preview(showBackground = true)
@Composable
fun UnitConverterPreView() {
    UnitConverter()
}