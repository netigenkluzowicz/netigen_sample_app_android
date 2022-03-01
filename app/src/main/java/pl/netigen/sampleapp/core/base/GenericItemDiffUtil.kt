package pl.netigen.sampleapp.core.base

import pl.netigen.sampleapp.core.data.Item

object GenericItemDiffUtil : ViewBindingDiffUtilCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem
}
