package com.example.pokemonapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokemonapp.presentation.constants.ErrorConstants
import com.example.pokemonapp.presentation.managers.ConnectionManager
import com.example.pokemonapp.presentation.ui.composables.dialogs.ErrorDialog
import com.example.pokemonapp.presentation.ui.composables.pages.MainPage
import com.example.pokemonapp.presentation.ui.theme.PokemonAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val vm by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        val connectionManager = ConnectionManager(applicationContext)

        super.onCreate(savedInstanceState)
        setContent {
            PokemonAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (connectionManager.checkConnection()){
                        true -> {
                            when (vm.errorMessagePublic.observeAsState().value){
                                null -> {
                                    MainPage(mainViewModel = vm)
                                }
                                else -> {
                                    ErrorDialog(
                                        onReloadButtonClick = {
                                            finish()
                                            startActivity(intent)
                                        },
                                        title = ErrorConstants.INTERNAL_ERROR,
                                        text = ErrorConstants.TRY_AGAIN_HINT,
                                        errorMsg = vm.errorMessagePublic.observeAsState().value !!
                                    )
                                }
                            }
                        }
                        else -> {
                            ErrorDialog(
                                onReloadButtonClick = {
                                    finish()
                                    startActivity(intent)
                                },
                                title = ErrorConstants.INTERNET_ERROR,
                                text = ErrorConstants.CHECK_CONNECTION_HINT,
                                errorMsg = ErrorConstants.INTERNET_ERROR_MSG
                            )
                        }
                    }
                }
            }
        }
    }
}
