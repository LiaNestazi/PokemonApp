package com.example.pokemonapp.presentation

import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.Pokemon
import com.example.domain.models.PokemonDetails
import com.example.domain.models.RequestParams
import com.example.domain.models.ResponseResult
import com.example.domain.usecase.GetAllPokemonsUseCase
import com.example.domain.usecase.GetPokemonDetailsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(
    private val getAllPokemonsUseCase: GetAllPokemonsUseCase,
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
) : ViewModel() {

    private val pokemonListPrivate = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> = pokemonListPrivate

    private val currentPokemonPrivate = MutableLiveData<PokemonDetails>()
    val currentPokemon: LiveData<PokemonDetails> = currentPokemonPrivate

    private var requestParamsPrivate = RequestParams(offset = 0, limit = 20)
    val requestParams = requestParamsPrivate

    private val errorMessagePrivate = MutableLiveData<String>()
    val errorMessagePublic = errorMessagePrivate

    private var countPrivate = MutableLiveData(0)
    val count = countPrivate

    private var isLoadingPrivate = MutableLiveData<Boolean>(true)
    val isLoading = isLoadingPrivate

    init {
        getAllPokemons()
    }

    //Переключение страниц
    fun nextPage(){
        var count = 0
        countPrivate.observeForever({
            count = it
        })
        if (requestParams.offset+20 < count){
            requestParams.offset = requestParams.offset+20
            getAllPokemons()
        }
    }
    fun previousPage(){
        if (requestParams.offset != 0){
            requestParams.offset = requestParams.offset-20
            getAllPokemons()
        }
    }

    //Функциональность
    fun getAllPokemons(){
        CoroutineScope(Dispatchers.IO).launch{
            isLoading.postValue(true)
            val pokemonCall = getPokemonsCall(requestParams)
            if (pokemonCall.errorMessage != null){
                errorMessagePrivate.postValue(pokemonCall.errorMessage!!)
            } else{
                if (pokemonCall.results != null){
                    pokemonListPrivate.postValue(pokemonCall.results!!)
                } else{
                    errorMessagePrivate.postValue("Can't load data")
                }
                if (pokemonCall.count != null){
                    countPrivate.postValue(pokemonCall.count!!)
                }
            }
            isLoading.postValue(false)
        }
    }

    fun getPokemonDetails(name: String){
        CoroutineScope(Dispatchers.IO).launch{
            isLoading.postValue(true)
            currentPokemonPrivate.postValue(getPokemonDetailsCall(name))
            isLoading.postValue(false)
        }
    }

    //Запросы в сеть
    private suspend fun getPokemonsCall(requestParams: RequestParams): ResponseResult {
        val job = CoroutineScope(Dispatchers.IO).async {
            getAllPokemonsUseCase.execute(requestParams)
        }
        this.requestParamsPrivate = requestParams
        return job.await()
    }
    private suspend fun getPokemonDetailsCall(name: String): PokemonDetails{
        val job = CoroutineScope(Dispatchers.IO).async {
            getPokemonDetailsUseCase.execute(name)
        }
        return job.await()
    }

}