package pl.netigen.drumloops.rock.core.base

import androidx.recyclerview.widget.DiffUtil
import pl.netigen.drumloops.rock.core.data.Item

abstract class ViewBindingDiffUtilCallback<T : Item> :
    DiffUtil.ItemCallback<T>()