package pl.netigen.sampleapp.listmusic.presentation

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pl.netigen.sampleapp.core.base.BaseFragment
import pl.netigen.sampleapp.core.extension.autoCleaned
import pl.netigen.drumloops.rock.databinding.ListMusicFragmentBinding
import pl.netigen.sampleapp.listmusic.presentation.model.AudioDisplayable
import pl.netigen.sampleapp.listmusic.presentation.model.MusicListDisplayable


@AndroidEntryPoint
class ListMusicFragment : BaseFragment<ListMusicFragmentBinding, MusicListDisplayable, ListMusicViewModel>() {

    override val viewModel: ListMusicViewModel by viewModels()

    private val audioListAdapter by autoCleaned(initializer = { ListMusicAdapter(::onMusicClicked, ::onLikeMusicClick) })
    private val likeListAdapter by autoCleaned(initializer = { ListMusicAdapter(::onMusicClicked, ::onLikeMusicClick) })
    override fun initView() {
        binding.run {
            likeMusicRecyclerView.adapter = likeListAdapter
            likeMusicRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            allMusicRecyclerView.adapter = audioListAdapter
            allMusicRecyclerView.layoutManager = GridLayoutManager(context, 3)
        }
    }

    private fun onMusicClicked(audioDisplayable: AudioDisplayable) {
//        findNavController().navigate(
//            ListMusicFragment.actionNotesFragmentToNoteDetailFragment(audioDisplayable.id)
//        )
    }

    private fun onLikeMusicClick(audioDisplayable: AudioDisplayable) {
        viewModel.clickLikeMusic(audioDisplayable.id)
//        findNavController().navigate(
//            ListMusicFragment.actionNotesFragmentToNoteDetailFragment(audioDisplayable.id)
//        )
    }

    override fun render(state: MusicListDisplayable) {
        val error = state.error
        if (error != null) {
            showToast(error)
        }

        if (state.isLoading) binding.progressBar.visibility = VISIBLE else binding.progressBar.visibility = GONE

        val allMusic = state.allAudio
        if (allMusic.isNotEmpty()) {
            allMusicLoaded(allMusic)
        }

        val likeMusic = state.likeAudio
        likeMusicLoaded(likeMusic)


    }

    private fun likeMusicLoaded(musicList: List<AudioDisplayable>) {
        likeListAdapter.submitList(musicList)
    }

    private fun allMusicLoaded(musicList: List<AudioDisplayable>) {
        audioListAdapter.submitList(musicList)
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) = ListMusicFragmentBinding.inflate(inflater, container, false)
}