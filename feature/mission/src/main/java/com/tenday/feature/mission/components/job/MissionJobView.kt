package com.tenday.feature.mission.components.job

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.tenday.core.common.enums.AchieveGrade
import com.tenday.designsystem.components.missionCard.WeeklyMissionCard
import com.tenday.designsystem.dimens.Dimens
import com.tenday.designsystem.theme.White
import com.tenday.feature.mission.R
import com.tenday.feature.mission.components.MissionExpTitle
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun MissionJobView(
    jobFamily: String,
    jobGroup: Int,
    onListScroll: () -> Unit,
    onShowJobToolTip: (IntOffset) -> Unit,
    modifier: Modifier = Modifier,
) {
    var visibleTable by rememberSaveable { mutableStateOf(true) }

    val lazyColumnState = rememberLazyListState()
    LaunchedEffect(lazyColumnState) {
        snapshotFlow { lazyColumnState.firstVisibleItemScrollOffset }.collectLatest { offset ->
            // 스크롤 오프셋에 따라 크기 변경
            visibleTable = offset <= 0
            onListScroll()
        }
    }
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(color = White),
        ) {
            Box(
                modifier = modifier.padding(
                    start = Dimens.margin20,
                    end = Dimens.margin20,
                    bottom = Dimens.margin16,
                )
            ) {
                JobMissionCard(
                    jobFamily = jobFamily,
                    jobGroup = jobGroup,
                    visibleTable = visibleTable,
                    onShowTooltip = onShowJobToolTip,
                )
            }
        }
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = Dimens.margin20,
                end = Dimens.margin20,
                bottom = Dimens.margin24,
                top = 0.dp
            ),
            state = lazyColumnState,
        ) {
            stickyHeader {
                MissionExpTitle(
                    type = stringResource(R.string.mission_job_title)
                )
            }
            items(5) {
                if(it == 1) {
                    WeeklyMissionCard(
                        it + 1,
                        listOf(6,7,8,9),
                        listOf( "02.02~02.05", "02.02~02.05", "02.02~02.05", "99.99~02.08",),
                        listOf(AchieveGrade.MIN, AchieveGrade.MAX)
                    )
                }
                else if(it == 0) {
                    WeeklyMissionCard(it + 1)
                }
                else {
                    WeeklyMissionCard(
                        it + 1,
                        listOf(10,11,12,13,14),
                        listOf( "02.02~02.05", "02.02~02.05", "02.02~02.05", "99.99~02.08", "02.02~02.05"),
                        listOf()
                    )
                }
                Spacer(modifier = modifier.height(Dimens.margin12))
            }
        }
    }
}

@Preview(name = "MissionJobView")
@Composable
private fun PreviewMissionJobView() {
    MissionJobView("음성 1센터", 1, {}, {})
}