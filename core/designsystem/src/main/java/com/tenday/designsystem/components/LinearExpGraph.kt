package com.tenday.designsystem.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tenday.designsystem.theme.CardShadow
import com.tenday.designsystem.theme.Gray200
import com.tenday.designsystem.theme.HandsUpTypography
import com.tenday.designsystem.theme.TransparentBlack20
import com.tenday.designsystem.theme.White

@Composable
fun LinearExpGraph(
    currentValue: Int,
    maxValue: Int = 27000,
    modifier: Modifier = Modifier,
) {
    val progress = currentValue.toFloat() / maxValue
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(20.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val cornerRadius = 50f

            // Draw background bar
            val backgroundBarPath = Path().apply {
                addRoundRect(
                    RoundRect(
                        rect = Rect(Offset.Zero, Size(size.width, size.height)),
                        topLeft = CornerRadius(cornerRadius),
                        topRight = CornerRadius(cornerRadius),
                        bottomLeft = CornerRadius(cornerRadius),
                        bottomRight = CornerRadius(cornerRadius)
                    )
                )
            }

            drawPath(
                path = backgroundBarPath,
                color = TransparentBlack20,
            )

            // Clip progress bar to fit within the background bar
            clipPath(backgroundBarPath) {
                // Create progress bar path with rounded left corners
                val progressBarWidth = size.width * progress
                val progressBarPath = Path().apply {
                    moveTo(0f, 0f) // Top-left corner
                    lineTo(progressBarWidth, 0f) // Top-right corner
                    lineTo(progressBarWidth, size.height) // Bottom-right corner
                    lineTo(0f, size.height) // Bottom-left corner
                    arcTo(
                        rect = Rect(
                            left = 0f,
                            top = 0f,
                            right = cornerRadius,
                            bottom = size.height
                        ),
                        startAngleDegrees = 90f,
                        sweepAngleDegrees = 180f,
                        forceMoveTo = false
                    )
                    close()
                }

                // Draw progress bar
                drawPath(
                    path = progressBarPath,
                    brush = gradientBrush
                )

                // Optional: Add shadow effect
                drawPath(
                    path = progressBarPath,
                    brush = Brush.verticalGradient(
                        colors = shadowGradient,
                        startY = 0f,
                        endY = size.height * 0.4f
                    )
                )
            }
        }
    }
}

@Preview(name = "LinearExpGraph")
@Composable
private fun PreviewLinearExpGraph() {
    Box(
        modifier = Modifier
            .background(color = Color.Gray)
            .padding(20.dp),
    ) {
        LinearExpGraph(14000)
    }
}