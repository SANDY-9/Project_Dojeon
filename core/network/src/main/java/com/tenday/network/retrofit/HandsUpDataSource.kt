package com.tenday.network.retrofit

import com.tenday.core.model.BoardDetails
import com.tenday.core.model.Exp
import com.tenday.core.model.ExpDetails
import com.tenday.core.model.NotificationDetails
import com.tenday.network.model.UserResponse

interface HandsUpDataSource {
    suspend fun login(id: String, pwd: String): String
    suspend fun getBoardList(token: String): List<BoardDetails>
    suspend fun updateBoardReadCount(boardId: Int, token: String)
    suspend fun getNotificationList(token: String): List<NotificationDetails>
    suspend fun getUserDetails(token: String): UserResponse
    suspend fun updateMessagingToken(accessToken: String, messagingToken: String): Boolean
    suspend fun getLastExpList(token: String): List<Exp>
    suspend fun getExpDetails(token: String): ExpDetails
}