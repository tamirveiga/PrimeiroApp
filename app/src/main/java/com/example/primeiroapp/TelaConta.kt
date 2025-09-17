package com.example.primeiroapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaConta() {
    Scaffold(
        containerColor = Color(0xFF0D1117),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Conta", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF0D1117),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Icon(
                Icons.Default.Person,
                contentDescription = "Perfil",
                tint = Color.White,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Usuário Exemplo", style = MaterialTheme.typography.headlineSmall, color = Color.White)
            Text("@usuario", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Bio.", style = MaterialTheme.typography.bodyLarge, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text("50 ", color = Color.White, style = MaterialTheme.typography.bodyLarge)
                Text("Seguindo", color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.width(16.dp))
                Text("30 ", color = Color.White, style = MaterialTheme.typography.bodyLarge)
                Text("Seguidores", color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}