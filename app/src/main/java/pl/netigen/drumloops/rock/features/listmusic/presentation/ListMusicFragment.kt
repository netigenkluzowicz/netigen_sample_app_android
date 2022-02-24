package pl.netigen.drumloops.rock.features.listmusic.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.netigen.drumloops.rock.core.base.BaseFragment
import pl.netigen.drumloops.rock.core.extension.autoCleaned
import pl.netigen.drumloops.rock.databinding.ListMusicFragmentBinding
import pl.netigen.drumloops.rock.features.listmusic.presentation.model.AudioDisplayable
import pl.netigen.drumloops.rock.features.listmusic.presentation.model.MusicListDisplayable


@AndroidEntryPoint
class ListMusicFragment : BaseFragment<ListMusicFragmentBinding, MusicListDisplayable, ListMusicViewModel>() {

    override val viewModel: ListMusicViewModel by viewModels()

    private val audioListAdapter by autoCleaned(initializer = { ListMusicAdapter(::onMusicClicked) })
    private val likeListAdapter by autoCleaned(initializer = { ListMusicAdapter(::onMusicClicked) })
    override fun initView() {
        binding.run {

        }
    }

    private fun onMusicClicked(audioDisplayable: AudioDisplayable) {
//        findNavController().navigate(
//            ListMusicFragment.actionNotesFragmentToNoteDetailFragment(audioDisplayable.id)
//        )
    }

    override fun render(state: MusicListDisplayable) {
        val error = state.error
        if(error!=null){
            showToast(error)
        }

        val allMusic = state.allAudio
        if(allMusic.isNotEmpty()){
            allMusicLoaded(allMusic)
        }

        val likeMusic = state.likeAudio
        if(likeMusic.isNotEmpty()){
            likeMusicLoaded(allMusic)
        }

    }

    private fun likeMusicLoaded(allMusic: List<AudioDisplayable>) {
        TODO("Not yet implemented")
    }

    private fun allMusicLoaded(allMusic: List<AudioDisplayable>) {

    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) = ListMusicFragmentBinding.inflate(inflater, container, false)
}