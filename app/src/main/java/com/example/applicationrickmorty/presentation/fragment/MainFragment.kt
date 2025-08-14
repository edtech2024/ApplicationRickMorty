package com.example.applicationrickmorty.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.applicationrickmorty.ItemApplication
import com.example.applicationrickmorty.R
import com.example.applicationrickmorty.databinding.FragmentMainBinding
import com.example.applicationrickmorty.domain.iusecase.*
import com.example.applicationrickmorty.domain.model.ItemModel
import com.example.applicationrickmorty.domain.model.Location
import com.example.applicationrickmorty.domain.model.Origin
import com.example.applicationrickmorty.presentation.viewmodel.ListViewModel
import javax.inject.Inject

class MainFragment : Fragment() {

    // Creates a new fragment given parameters
    // MainFragment.newInstance()
    companion object {

        fun newInstance(): MainFragment {
            val fragmentMain = MainFragment()
            return fragmentMain
        }
    }

    // Define the events that the fragment will use to communicate
    interface OnItemOpenListener {
        // This can be any number of events to be sent to the activity
        fun onOpenItem(open: Bundle)
    }

    // Define the listener of the interface type
    // listener will the activity instance containing fragment
    private var listenerOpen: OnItemOpenListener? = null

    lateinit var listViewModel: ListViewModel

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var useCaseCreateItem: IUseCaseCreateItem
    @Inject
    lateinit var useCaseUpdateItem: IUseCaseUpdateItem
    @Inject
    lateinit var useCaseDeleteItem: IUseCaseDeleteItem
    @Inject
    lateinit var useCaseRequestItems: IUseCaseRequestItems
    @Inject
    lateinit var useCaseQueryLocalItems: IUseCaseQueryLocalItems
    @Inject
    lateinit var useCaseRefreshItems: IUseCaseRefreshItems

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listenerOpen = context as OnItemOpenListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as ItemApplication).appComponent.inject(this)

        listViewModel = ViewModelProvider(this, object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                return ListViewModel(
                    useCaseCreateItem, useCaseUpdateItem, useCaseDeleteItem,
                    useCaseRequestItems, useCaseQueryLocalItems, useCaseRefreshItems
                ) as T
            }
        }
        ).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate( inflater, container, false)
        _binding!!.lifecycleOwner = this
        _binding!!.listViewModel = listViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializationComposeViewMain(listViewModel)
        initializationEditTextSearch()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()
        listenerOpen = null
    }


    fun initializationEditTextSearch() {
        binding.etSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                listViewModel.applyFilterMethod()
            }
        })
    }

    private fun initializationComposeViewMain(viewModel: ListViewModel) {
        binding.composeViewMain.apply {
            // Dispose of the Composition when the view's LifecycleOwner is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Navigation(viewModel)
                }
            }
        }
    }

    @Composable
    fun Navigation(viewModel: ListViewModel) {

        val data = viewModel.itemsList.observeAsState(emptyList())

        MainScreen(modifier = Modifier,
            data = data as State<List<ItemModel>>,
            onClick = { itemModel -> onItemClicked(itemModel) } ,
            refresh = { viewModel.refresh() }
        )

    }

    @Composable
    fun MainScreen(modifier: Modifier = Modifier,
                   data: State<List<ItemModel>>,
                   onClick: (ItemModel) -> Unit,
                   refresh: () -> Unit) {

        Column(modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Black)
            .padding(10.dp)
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 2)
            ) {

                items(data.value, key = { itemModel -> itemModel.id!! } ) {
                    ListItem(it, onClick = onClick )
                }
            }

            Button(onClick = { refresh() }) {
                Text(text = "Refresh", fontSize = 25.sp)
            }
        }
    }

    @Composable
    fun ListItem(data: ItemModel,
                 modifier: Modifier = Modifier,
                 onClick: (ItemModel) -> Unit) {
         Surface() {
            Row(modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.Black)
                .padding(10.dp)
                .clickable {
                    onClick(data)
                    Toast.makeText(activity?.applicationContext, "Click", Toast.LENGTH_SHORT).show()
                }
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(data.image)
                        .crossfade(true)
                        .build(),
                    error = painterResource(R.drawable.ic_baseline_broken_image_24),
                    placeholder = painterResource(R.drawable.loading_img),
                    contentDescription = stringResource(R.string.item_photo),
                    contentScale = ContentScale.Crop
                )

                Text(text = data.name)
                Text(text = data.species)
                Text(text = data.status)
                Text(text = data.gender)
            }
        }
    }

    // Now we can fire the event when the user selects something in the fragment
    private fun onOpenClicked(bundle: Bundle){
        listenerOpen?.onOpenItem(bundle)
    }

    private fun onItemClicked(item: ItemModel){

        val args = Bundle()

        args.putString(getString(R.string.id), item.id.toString())
        args.putString(getString(R.string.type), item.type)
        args.putString(getString(R.string.image), item.image)
        args.putString(getString(R.string.name), item.name)
        args.putString(getString(R.string.gender), item.gender)
        args.putString(getString(R.string.status), item.status)
        args.putString(getString(R.string.species), item.species)
        args.putString(getString(R.string.origin), item.origin.toString())
        args.putString(getString(R.string.location), item.location.toString())
        args.putString(getString(R.string.episode), item.episode.toString())
        args.putString(getString(R.string.url), item.url)
        args.putString(getString(R.string.created), item.created)

        onOpenClicked(args)
    }

}