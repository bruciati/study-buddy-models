package brc.studybuddy.input

import brc.studybuddy.model.DataModel

sealed interface DataInput<M, I>
        where M : DataModel<M, I>,
              I : DataInput<M, I>
{
    fun toModel(): M
    fun updateModel(model: M): M
}
