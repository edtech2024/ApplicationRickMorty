package com.example.applicationrickmorty.presentation.viewmodel

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.applicationrickmorty.R
import com.example.applicationrickmorty.domain.converter.StringValue
import com.example.applicationrickmorty.presentation.fragment.DetailFragment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class DetailViewModel @Inject constructor(val bundle: Bundle?, val context: DetailFragment.OnItemCloseListener?) : ViewModel() {

    private val _id: MutableStateFlow<String> = MutableStateFlow("0")
    val id: StateFlow<String> = _id.asStateFlow()

    fun setId(id: String) {
        _id.value = id
    }

    private val _name: MutableStateFlow<String> = MutableStateFlow("Name")
    val name: StateFlow<String> = _name.asStateFlow()

    fun setName(name: String) {
        _name.value = name
    }

    private val _type: MutableStateFlow<String> = MutableStateFlow("Type")
    val type: StateFlow<String> = _type.asStateFlow()

    fun setType(type: String) {
        _type.value = type
    }

    private val _status: MutableStateFlow<String> = MutableStateFlow("Status")
    val status: StateFlow<String> = _status.asStateFlow()

    fun setStatus(status: String) {
        _status.value = status
    }

    private val _gender: MutableStateFlow<String> = MutableStateFlow("Gender")
    val gender: StateFlow<String> = _gender.asStateFlow()

    fun setGender(gender: String) {
        _gender.value = gender
    }

    private val _image: MutableStateFlow<String> = MutableStateFlow("Image")
    val image: StateFlow<String> = _image.asStateFlow()

    fun setImage(image: String) {
        _image.value = image
    }

    private val _species: MutableStateFlow<String> = MutableStateFlow("Species")
    val species: StateFlow<String> = _species.asStateFlow()

    fun setSpecies(species: String) {
        _species.value = species
    }

    private val _created: MutableStateFlow<String> = MutableStateFlow("Created")
    val created: StateFlow<String> = _created.asStateFlow()

    fun setCreated(created: String) {
        _created.value = created
    }

    private val _episode: MutableStateFlow<String> = MutableStateFlow("Episode")
    val episode: StateFlow<String> = _episode.asStateFlow()

    fun setEpisode(episode: String) {
        _episode.value = episode
    }

    private val _location: MutableStateFlow<String> = MutableStateFlow("Location")
    val location: StateFlow<String> = _location.asStateFlow()

    fun setLocation(location: String) {
        _location.value = location
    }

    private val _origin: MutableStateFlow<String> = MutableStateFlow("Origin")
    val origin: StateFlow<String> = _origin.asStateFlow()

    fun setOrigin(origin: String) {
        _origin.value = origin
    }

    private val _url: MutableStateFlow<String> = MutableStateFlow("Url")
    val url: StateFlow<String> = _url.asStateFlow()

    fun setUrl(url: String) {
        _url.value = url
    }

    var argId: String = "0"
    var argName: String = "String"
    var argType: String = "String"
    var argStatus: String = "String"
    var argGender: String = "String"
    var argImage: String = "String"
    var argSpecies: String = "String"
    var argCreated: String = "String"
    var argEpisode: String = "String"
    var argLocation: String = "String"
    var argOrigin: String = "String"
    var argUrl: String = "String"

    init {
        if (bundle != null) {
            readBundle(bundle)

            setArgs(
                this.argId,
                this.argName,
                this.argType,
                this.argGender,
                this.argStatus,
                this.argImage,
                this.argSpecies,
                this.argCreated,
                this.argEpisode,
                this.argLocation,
                this.argOrigin,
                this.argUrl
            )
        }
    }

    override fun onCleared() {

    }

    private fun readBundle(bundle:Bundle){
        this.argId = bundle.getString(StringValue.StringResource(R.string.id).asString(context as Context),"5")
        this.argName = bundle.getString(StringValue.StringResource(R.string.name).asString(context as Context), "Name")
        this.argType = bundle.getString(StringValue.StringResource(R.string.type).asString(context as Context), "Type")
        this.argStatus = bundle.getString(StringValue.StringResource(R.string.status).asString(context as Context), "Status")
        this.argGender = bundle.getString(StringValue.StringResource(R.string.gender).asString(context as Context), "Gender")
        this.argImage = bundle.getString(StringValue.StringResource(R.string.image).asString(context as Context),"Image")
        this.argSpecies = bundle.getString(StringValue.StringResource(R.string.species).asString(context as Context), "Species")
        this.argCreated = bundle.getString(StringValue.StringResource(R.string.created).asString(context as Context), "Created")
        this.argEpisode = bundle.getString(StringValue.StringResource(R.string.episode).asString(context as Context), "Episode")
        this.argLocation = bundle.getString(StringValue.StringResource(R.string.location).asString(context as Context), "Location")
        this.argOrigin = bundle.getString(StringValue.StringResource(R.string.origin).asString(context as Context), "Origin")
        this.argUrl = bundle.getString(StringValue.StringResource(R.string.url).asString(context as Context), "Url")
    }

    private fun setArgs(id: String, name: String, type: String, gender: String, status: String, image: String,
                        species: String, created: String, episode: String, location: String, origin: String, url: String) {
        setId(id)
        setName(name)
        setType(type)
        setGender(gender)
        setStatus(status)
        setImage(image)
        setSpecies(species)
        setCreated(created)
        setEpisode(episode)
        setLocation(location)
        setOrigin(origin)
        setUrl(name)
    }

}