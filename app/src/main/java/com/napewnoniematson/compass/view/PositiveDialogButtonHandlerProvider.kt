package com.napewnoniematson.compass.view

import com.napewnoniematson.compass.logic.reader.location.PositiveDialogButtonHandler

interface PositiveDialogButtonHandlerProvider {
    fun getPositiveDialogButtonHandler(): PositiveDialogButtonHandler
}