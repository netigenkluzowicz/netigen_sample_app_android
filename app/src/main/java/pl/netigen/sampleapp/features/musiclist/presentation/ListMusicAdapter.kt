package pl.netigen.sampleapp.features.musiclist.presentation

import android.view.ViewGroup
import com.bumptech.glide.Glide
import pl.netigen.drumloops.rock.R
import pl.netigen.drumloops.rock.databinding.ItemMusicBinding

import pl.netigen.sampleapp.core.base.BaseAdapter
import pl.netigen.sampleapp.core.base.GenericItemDiffUtil
import pl.netigen.sampleapp.core.data.Item
import pl.netigen.sampleapp.core.extension.gone
import pl.netigen.sampleapp.core.extension.visible
import pl.netigen.sampleapp.features.musiclist.presentation.model.MusicDisplayable

class ListMusicAdapter(
    private val onMusicClicked: (MusicDisplayable) -> Unit,
    private val onMusicLikeClicked: (MusicDisplayable) -> Unit,
) : BaseAdapter<Item, ItemMusicBinding>(GenericItemDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewBindingViewHolder<Item, ItemMusicBinding> {
        val inflater = parent.layoutInflater
        val binding = ItemMusicBinding.inflate(inflater, parent, false)
        return NextHoursViewHolder(binding)
    }

    inner class NextHoursViewHolder(
        binding: ItemMusicBinding,
    ) :
        ViewBindingViewHolder<Item, ItemMusicBinding>(binding) {

        init {
            itemView.setOnClickListener {
                onMusicClicked(getItem(bindingAdapterPosition) as MusicDisplayable)
            }
            binding.like.setOnClickListener {
                onMusicLikeClicked(getItem(bindingAdapterPosition) as MusicDisplayable)
            }
        }

        override fun bind(item: Item) {
            item as MusicDisplayable
            binding.run {
                title.text = item.name
                val imageRes = when (item.id % 3) {
                    0 -> R.drawable.image_1
                    1 -> R.drawable.image_2
                    2 -> R.drawable.image_3
                    else -> R.drawable.image_1
                }
                Glide.with(itemView).load(imageRes).into(image)
                if (item.isLike) {
                    Glide.with(itemView).load(R.drawable.ic_baseline_star_24).into(like)
                } else {
                    Glide.with(itemView).load(R.drawable.ic_baseline_star_border_24).into(like)
                }
                if (item.isBuy) {
                    block.gone()
                } else {
                    block.visible()
                }
            }
        }
    }
}
