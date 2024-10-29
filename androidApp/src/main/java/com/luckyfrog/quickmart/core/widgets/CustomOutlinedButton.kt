package com.luckyfrog.quickmart.core.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomOutlinedButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    buttonTextColor: Color = MaterialTheme.colorScheme.onPrimary,
    buttonTextFontSize: TextUnit = 14.sp,
    height: Dp = 48.dp,
    buttonContainerColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit,
    isWithIcon: Boolean = false,
    buttonIcon: Painter? = null
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        border = BorderStroke(
            1.dp,
            color = MaterialTheme.colorScheme.primary,
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonContainerColor
        ),
        shape = RoundedCornerShape(
            10.dp
        )
    ) {
        Row {
            if (isWithIcon && buttonIcon != null) {
                Image(
                    painter =
                    buttonIcon,
                    contentDescription = "Icon Button"
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
            Text(
                text = buttonText,
                color = buttonTextColor,
                fontSize = buttonTextFontSize
            )
        }


    }
}