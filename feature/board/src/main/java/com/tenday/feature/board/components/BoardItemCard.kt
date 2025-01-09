package com.tenday.feature.board.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.tenday.core.model.BoardDetails
import com.tenday.designsystem.components.HandsUpShadowCard
import com.tenday.designsystem.dimens.Dimens
import com.tenday.designsystem.theme.Gray600
import com.tenday.designsystem.theme.Gray700
import com.tenday.designsystem.theme.HandsUpTypography

@Composable
fun BoardItemCard(
    boardDetails: BoardDetails,
    onCardClick: (BoardDetails) -> Unit,
    modifier: Modifier = Modifier,
) {
    HandsUpShadowCard(
        modifier = modifier.clip(RoundedCornerShape(Dimens. cornerShape12)).clickable {
            onCardClick(boardDetails)
        },
        elevationSize = Dimens.cornerShape4,
        content = {
            Column(
                modifier = modifier.padding(
                    top = Dimens.margin14,
                    start = Dimens.margin16,
                    end = Dimens.margin16,
                    bottom = Dimens.margin16
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = modifier.weight(1f),
                        text = boardDetails.title,
                        style = HandsUpTypography.body2.copy(
                            fontWeight = FontWeight.SemiBold,
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = boardDetails.createdAt,
                        color = Gray600,
                        style = HandsUpTypography.body4,
                    )
                }
                Spacer(modifier = modifier.height(Dimens.margin6))
                Text(
                    text = boardDetails.content,
                    color = Gray700,
                    style = HandsUpTypography.body3,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    )
    Spacer(modifier = modifier.height(Dimens.margin12))
}

@Preview(name = "BoardItemCard")
@Composable
private fun PreviewBoardItemCard() {
    BoardItemCard(
        BoardDetails(
            3,
            "안녕하세요. 물류 자동화 프로젝트를 신설하고, 해당 프로젝트에 참여할 팀원을 모집합니다.   " +
                    "[프로젝트 목표] 1. 작업 안녕하세요. 물류 자동화 프로젝트를 신설하고, " +
                    "해당 프로젝트에 참여할 팀원을 모집합니다.   [프로젝트 목표] 1. 작업",
            "2024.01.14",
            "물류 자동화 프로젝트 신설 물류 자동화 프로젝트 신설 물류 자동화 " +
                    "프로젝트 신설 물류 자동화 프로젝트 신설"
        ),
        {}
    )
}