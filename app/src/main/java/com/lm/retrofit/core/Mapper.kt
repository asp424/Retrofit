package com.lm.retrofit.core

interface Mapper {

    interface Data<D, U> : Mapper {
        fun map(data: D?): U
    }

    interface DataToUI<D, U> : Data<D, U> {
        override fun map(data: D?): U
    }
}