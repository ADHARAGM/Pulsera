package com.ces.pulsera.modelo

class MainProvider {
    companion object{
        fun random():QuoteModelMain{
            val position=0
            return quote[position]
        }

        private val quote = listOf<QuoteModelMain>(
            QuoteModelMain("hola","adhara Galarza")
        )
    }

}