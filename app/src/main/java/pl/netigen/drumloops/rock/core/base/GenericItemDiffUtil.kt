package pl.netigen.drumloops.rock.core.base


import pl.netigen.drumloops.rock.core.data.Item

object GenericItemDiffUtil : ViewBindingDiffUtilCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem.equals(newItem)
}