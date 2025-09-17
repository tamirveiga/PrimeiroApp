package com.example.primeiroapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun PostagemUI(post: Postagem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Ícone de perfil
        Icon(
            Icons.Default.Person,
            contentDescription = "Perfil",
            tint = Color.White,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )

        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(post.autor, style = MaterialTheme.typography.titleMedium, color = Color.White)
                Spacer(modifier = Modifier.width(4.dp))
                Text(post.usuario, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }

            Text(post.conteudo, style = MaterialTheme.typography.bodyLarge, color = Color.White)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                AcaoIcone(Icons.Default.Menu, post.comentarios)
                AcaoIcone(Icons.Default.Refresh, post.compartilhamentos)
                AcaoIcone(Icons.Default.Favorite, post.curtidas)
                AcaoIcone(Icons.Default.Share, 0)
            }
        }
    }
}

@Composable
fun AcaoIcone(icon: ImageVector, count: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(4.dp))
        if (count > 0) {
            Text(count.toString(), color = Color.Gray, style = MaterialTheme.typography.bodySmall)
        }
    }
}