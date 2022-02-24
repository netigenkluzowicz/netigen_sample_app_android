package pl.netigen.drumloops.rock.features.listmusic.presentation

import android.view.ViewGroup
import pl.netigen.drumloops.rock.core.base.BaseAdapter
import pl.netigen.drumloops.rock.core.base.GenericItemDiffUtil
import pl.netigen.drumloops.rock.core.data.Item
import pl.netigen.drumloops.rock.databinding.ItemMusicBinding
import pl.netigen.drumloops.rock.features.listmusic.presentation.model.AudioDisplayable

class ListMusicAdapter(private val onMusicClicked: (AudioDisplayable) -> Unit) : BaseAdapter<Item, ItemMusicBinding>(GenericItemDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewBindingViewHolder<Item, ItemMusicBinding> {
        val inflater = parent.layoutInflater
        val binding = ItemMusicBinding.inflate(inflater, parent, false)
        return NextHoursViewHolder(binding)
    }

    inner class NextHoursViewHolder(
        binding: ItemMusicBinding
    ) :
        ViewBindingViewHolder<Item, ItemMusicBinding>(binding) {

        init {
            itemView.setOnClickListener {
                onMusicClicked(getItem(bindingAdapterPosition) as AudioDisplayable)
            }

        }

        override fun bind(item: Item) {
            item as AudioDisplayable


        }
    }
}