package com.example.gamelb

import com.example.gamelb.api.models.Genre
import com.example.gamelb.api.models.Platform
import com.example.gamelb.api.models.Store

fun convertListOfObjectsToString(list: List<Any>): String{
    val object_names: MutableList<String> = mutableListOf()
    list.forEach {
        when(it){
            is Platform -> object_names.add(it.name)
            is Genre -> object_names.add(it.name)
            is Store -> object_names.add(it.name)
        }
    }
    return object_names.joinToString(", ")
}

fun formatDate(dateString: String): String {
    val dateSplit = dateString.split("-").toTypedArray()
    val months = mapOf("01" to "Januari", "02" to "Februari", "03" to "Maart", "04" to "April", "05" to "Mei", "06" to "Juni",
          "07" to "Juli", "08" to "Augustus", "09" to "September", "10" to "Oktober", "11" to "November", "12" to "December")
    return "${dateSplit[2].replaceFirst("0", "")} ${months.get(dateSplit[1])} ${dateSplit[0]}"
}