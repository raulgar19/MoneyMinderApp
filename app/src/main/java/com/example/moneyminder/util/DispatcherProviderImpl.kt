package com.example.moneyminder.util

import kotlinx.coroutines.Dispatchers

class DispatcherProviderImpl : DispatcherProvider{
    override val mainDispatcher = Dispatchers.Main
    override val ioDispatcher = Dispatchers.IO
    override val cpuDispatcher = Dispatchers.Default
}