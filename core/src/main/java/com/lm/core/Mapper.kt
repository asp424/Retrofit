package com.lm.core

import okhttp3.ResponseBody

interface Mapper {

    interface Data<D, U> : Mapper {
        fun map(data: D): U
    
    }

    interface DataToUI<D, U> : Data<D, U> {
        override fun map(data: D): U
        fun map(throwable: Throwable): U
        fun map(error: ResponseBody?): U
        fun map(): U
    }
}