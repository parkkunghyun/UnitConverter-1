package org.techtown.unitconverter_1

import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.techtown.unitconverter_1.ui.theme.UnitConverter1Theme
import kotlin.math.roundToInt

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
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.0) }
    val oConversionFactor = remember { mutableStateOf(1.0) }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 32.sp,
        color = Color.Red
    )

    fun convertUnits() {
        // ?: 아건 elvis operator인데 if문임! 삼항은 아니고 널이면 저걸 받음
        //
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        // 너무 길어지지않게 만들기
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.00
        outputValue = result.toString()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        // ui elements를 하나씩 쌓을예정 - 기본이 수직이네
        Text(text = "Unit Converter", modifier = Modifier.padding(5.dp),
            style = customTextStyle)

        Spacer(modifier = Modifier.height(16.dp))

        // 값이 바뀌는 순간 무엇을 해야할지 지정
        // ui가 업데이트가 안됨 - 이 이유는 아직 onValueChange를 설정 안해서!
        OutlinedTextField(value = inputValue, onValueChange = {
            // 값이 바뀌는 순간 변경 되는것
            inputValue = it
            convertUnits()
        }, label = { Text(text = "Enter value")})

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            // input Box
            Box {
                Button(onClick = {
                    iExpanded = true
                }) {
                    // Select인데 여기서 내가 드롭버튼을 누르면그걸로 이름 변경되게
                    Text(text = inputUnit)
                    // contentDescription - 시각장애인등을 위한 사람들을 위한 접근성 서비스
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                // 일단 닫혀있어야함!
                DropdownMenu(expanded = iExpanded, onDismissRequest = {
                    // 드롭다운 메뉴가 닫혔을때 어떻게 할지 지정
                    iExpanded = false
                }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters")}, onClick = {
                        iExpanded = false
                        inputUnit = "Centimeters"
                        conversionFactor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters")}, onClick = {
                        iExpanded = false
                        inputUnit = "Meters"
                        conversionFactor.value = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet")}, onClick = {
                        iExpanded = false
                        inputUnit = "Feet"
                        conversionFactor.value = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Milimeters")}, onClick = {
                        iExpanded = false
                        inputUnit = "Milimeters"
                        conversionFactor.value = 0.001
                        convertUnits()
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            // output Box
            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    // contentDescription - 시각장애인등을 위한 사람들을 위한 접근성 서비스
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {
                    // 드롭다운 메뉴가 닫혔을때 어떻게 할지 지정
                    oExpanded = false
                }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters")}, onClick = {
                        oExpanded = false
                        outputUnit = "Centimeters"
                        oConversionFactor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters")}, onClick = {
                        oExpanded = false
                        outputUnit = "Meters"
                        oConversionFactor.value = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet")}, onClick = {
                        oExpanded = false
                        outputUnit = "Feet"
                        oConversionFactor.value = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Milimeters")}, onClick = {
                        oExpanded = false
                        outputUnit = "Milimeters"
                        oConversionFactor.value = 0.001
                        convertUnits()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        //Result Text
        Text(text = "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium)
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

/**
 * event를 통해 state의 변화를 가져옴
 * state관리를 사용자 액션이나 데이터 업데이트 같은 이벤트가 일어났을때 변화함
 *
 * remember함수 와recomposition state
 *
 */