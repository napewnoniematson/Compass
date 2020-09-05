package com.napewnoniematson.compass.view

import android.view.View

interface DestinationPointListenerProvider {
    fun getDestinationPointOnClickListener(): View.OnClickListener
}