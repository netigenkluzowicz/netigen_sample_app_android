package pl.netigen.sampleapp.core.base

import androidx.recyclerview.widget.DiffUtil
import pl.netigen.sampleapp.core.data.Item

abstract class ViewBindingDiffUtilCallback<T : Item> :
    DiffUtil.ItemCallback<T>()
