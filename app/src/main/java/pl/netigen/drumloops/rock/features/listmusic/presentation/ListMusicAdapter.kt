package pl.netigen.drumloops.rock.features.listmusic.presentation

import android.view.ViewGroup
import com.bumptech.glide.Glide
import pl.netigen.drumloops.rock.R
import pl.netigen.drumloops.rock.core.base.BaseAdapter
import pl.netigen.drumloops.rock.core.base.GenericItemDiffUtil
import pl.netigen.drumloops.rock.core.data.Item
import pl.netigen.drumloops.rock.databinding.ItemMusicBinding
import pl.netigen.drumloops.rock.features.listmusic.presentation.model.AudioDisplayable

class ListMusicAdapter(
    private val onMusicClicked: (AudioDisplayable) -> Unit,
    private val onMusicLikeClicked: (AudioDisplayable) -> Unit
) :
    BaseAdapter<Item, ItemMusicBinding>(GenericItemDiffUtil) {
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
            binding.like.setOnClickListener {
                onMusicLikeClicked(getItem(bindingAdapterPosition) as AudioDisplayable)
            }

        }

        override fun bind(item: Item) {
            item as AudioDisplayable
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
            }

        }
    }
}