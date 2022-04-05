package brc.studybuddy.model

import brc.studybuddy.input.DataInput

sealed interface DataModel<M, I>
        where M : DataModel<M, I>,
              I : DataInput<M, I>
{
    fun toInput(): I
}
