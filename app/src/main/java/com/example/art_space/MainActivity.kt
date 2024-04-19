package com.example.art_space

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.art_space.ui.theme.Art_SpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Art_SpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace("Android")
                }
            }
        }
    }
}

@Composable
fun ArtSpace(name: String, modifier: Modifier = Modifier) {
    var indice by remember { mutableStateOf(1) }

    indice = when(indice){
        6 -> 1
        0 -> 5
        else -> indice
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.padding(55.dp))
        MuroImagen(indice)
        Spacer(modifier = Modifier.padding(40.dp))
        Descripcion(indice)
        Spacer(modifier = Modifier.padding(15.dp))
        Controlador({indice++}, {indice--})
    }
}



@Composable
fun MuroImagen(indice: Int){
    val id = when(indice) {
        1 -> R.drawable.elgrito
        2 -> R.drawable.elbeso
        3 -> R.drawable.lasmeninas
        4 -> R.drawable.monalisa
        else -> R.drawable.nocheestrellada
    }
    Box(
        modifier = Modifier
            .shadow(
                elevation = 4.dp, // Intensidad de la sombra
            )
            .width(300.dp) // Ancho del marco
            .height(400.dp) // Alto del marco
            .background(Color.White)
            .padding(horizontal = 18.dp, vertical = 22.dp) // Ajusta el espacio entre la imagen y el marco blanco

    ) {
        // Imagen dentro del marco blanco
        Image(
            painter = painterResource(id),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White) // Fondo blanco para la imagen
        )
    }

}

@Composable
fun Descripcion(indice: Int){
    val titulo = when(indice){
        1 -> stringResource(R.string.elgrito)
        2 -> stringResource(R.string.elbeso)
        3 -> stringResource(R.string.lasmeninas)
        4 -> stringResource(R.string.monalisa)
        else -> stringResource(R.string.nocheestrellada)
    }
    val artista = when(indice){
        1 -> stringResource(R.string.elgritoartista)
        2 -> stringResource(R.string.elbesoartista)
        3 -> stringResource(R.string.lasmeninasartista)
        4 -> stringResource(R.string.monalisaartista)
        else -> stringResource(R.string.nocheestrelladaartista)
    }
    val anio = when(indice){
        1 -> stringResource(R.string.elgritoanio)
        2 -> stringResource(R.string.elbesoanio)
        3 -> stringResource(R.string.lasmeninasanio)
        4 -> stringResource(R.string.monalisaanio)
        else -> stringResource(R.string.nocheestrelladaanio)
    }

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .background(Color(red = 236, green = 235, blue = 244))
            .padding(15.dp)
    ) {
        Text(
            text = titulo,
            fontSize = 32.sp,
            fontWeight = FontWeight.Light,
            color = Color.Black
        )
        Row{
            Text(text = artista, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = " ($anio)", color = Color.Black)
        }

    }
}

@Composable
fun Controlador(
    siguienteImagen: () -> Unit,
    anteriorImagen: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = anteriorImagen,
            modifier = Modifier
                .padding(start = 20.dp)
                .width(150.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 73, green = 93, blue = 146)
            )
        ) {
            Text(stringResource(R.string.previous))
        }
        Button(
            onClick = siguienteImagen,
            modifier = Modifier
                .padding(end = 20.dp)
                .width(150.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 73, green = 93, blue = 146)
            )
        ) {
            Text(stringResource(R.string.next))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Art_SpaceTheme {
        ArtSpace("Android")
    }
}