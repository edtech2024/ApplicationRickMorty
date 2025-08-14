package com.example.applicationrickmorty.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.applicationrickmorty.ItemApplication
import com.example.applicationrickmorty.databinding.FragmentDetailBinding
import com.example.applicationrickmorty.presentation.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    // Creates a new fragment given parameters
    // DetailFragment.newInstance()
    companion object {

        private const val ID = "Id"
        private const val TYPE = "Type"
        private const val GENDER = "Gender"
        private const val NAME = "Name"
        private const val STATUS = "Status"
        private const val SPECIES = "Species"
        private const val ORIGIN = "Origin"
        private const val LOCATION = "Location"
        private const val IMAGE = "Image"
        private const val EPISODE = "Episode"
        private const val URL = "Url"
        private const val CREATED = "Created"

        fun newInstance(
            id: Int,
            name: String?,
            status: String?,
            species: String?,
            type: String?,
            gender: String?,
            origin: String?,
            location: String?,
            image: String?,
            episode: String?,
            url: String?,
            created: String?
        ): DetailFragment {
            val fragmentDetail = DetailFragment()
            val args = Bundle()

            args.putInt(ID, id)
            args.putString(TYPE, type)
            args.putString(GENDER, gender)
            args.putString(NAME, name)
            args.putString(STATUS, status)
            args.putString(SPECIES, species)
            args.putString(ORIGIN, origin.toString())
            args.putString(LOCATION, location.toString())
            args.putString(IMAGE, image)
            args.putString(EPISODE, episode.toString())
            args.putString(URL, url)
            args.putString(CREATED, created)

            fragmentDetail.arguments = args
            return fragmentDetail
        }
    }

    // Define the events that the fragment will use to communicate
    interface OnItemCloseListener{
        // This can be any number of events to be sent to the activity
        fun onCloseItem()
    }

    // Define the listener of the interface type
    // listener will the activity instance containing fragment
    private var listenerClose: OnItemCloseListener? = null

    lateinit var detailViewModel: DetailViewModel

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listenerClose = context as OnItemCloseListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as ItemApplication).appComponent.inject(this)

        detailViewModel = ViewModelProvider(this , object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DetailViewModel(getArguments(), listenerClose) as T
            }
        }
        ).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentDetailBinding.inflate( inflater, container, false)
        _binding!!.lifecycleOwner = viewLifecycleOwner
        _binding!!.detailViewModel = detailViewModel

        initializationComposeViewDetail()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()
        listenerClose = null
    }

    // Now we can fire the event when the user selects something in the fragment
    private fun onCloseClicked() {
        listenerClose?.onCloseItem()
    }

    private fun initializationComposeViewDetail() {
        binding.composeViewDetail.apply {
            // Dispose of the Composition when the view's LifecycleOwner is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Surface(modifier = Modifier.fillMaxSize()) {
                    DetailScreen()
                }
            }
        }
    }

    @Composable
    fun DetailScreen(modifier: Modifier = Modifier) {
        Row(modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Black)
            .padding(10.dp)
        ) {

            Box(modifier = Modifier.fillMaxSize()) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val id = detailViewModel.id.collectAsState()
                    val name = detailViewModel.name.collectAsState()
                    val type = detailViewModel.type.collectAsState()
                    val status = detailViewModel.status.collectAsState()
                    val gender = detailViewModel.gender.collectAsState()

                    Text(text = "Detail screen")

                    OutlinedTextField(
                        value = id.value,
                        onValueChange = { detailViewModel.setId(it) },
                        label = { Text(text = "id") }
                    )

                    OutlinedTextField(
                        value = name.value,
                        onValueChange = { detailViewModel.setName(it) },
                        label = { Text(text = "name") }
                    )

                    OutlinedTextField(
                        value = type.value,
                        onValueChange = { detailViewModel.setType(it) },
                        label = { Text(text = "type") }
                    )

                    OutlinedTextField(
                        value = status.value,
                        onValueChange = { detailViewModel.setStatus(it) },
                        label = { Text(text = "status") }
                    )

                    OutlinedTextField(
                        value = gender.value,
                        onValueChange = { detailViewModel.setGender(it) },
                        label = { Text(text = "gender") }
                    )

                    Button(onClick = {
                        onCloseClicked()
                    }) {
                        Text(text = "Закрыть")
                    }

                }
            }
        }
    }

}