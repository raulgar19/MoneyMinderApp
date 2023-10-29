package com.example.moneyminder.ui.models

import java.math.BigDecimal
import java.sql.Timestamp
import java.util.Date

    data class Ingreso(var id : Int, var cantidad : BigDecimal, var fecha : Timestamp, var usuario : Usuario)
