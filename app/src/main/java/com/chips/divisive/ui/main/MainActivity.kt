package com.chips.divisive.ui.main

//import androidx.compose.foundation.layout.BoxScopeInstance.align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.chips.divisive.ui.home.MainScreen
import com.chips.divisive.ui.main.dialog.BottomSheet
import com.chips.divisive.ui.theme.ChipsDivisiveTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var showSheet by remember { mutableStateOf(false) }


            ChipsDivisiveTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController: NavHostController = rememberNavController()

                    NavHost(navController = navController, startDestination = "profiles") {
                        composable("profiles") {
                            ProfileListScreen(hiltViewModel(),
                                editCallback = {
                                    navController.navigate("detial/$it")
                                },
                                calculateCallback = {
                                    navController.navigate("calc")
                                })
                        }
                        composable(route = "detial/{profileId}", arguments = listOf(
                            navArgument("profileId") {
                                type = NavType.IntType
                            }
                        )) {
                            ProfileDetailScreen(hiltViewModel()) {
                                navController.navigate("chip/${it.id}/${it.profileId}")
                            }
                        }
                        composable(route = "calc") {
                            MainScreen()
                        }
                        composable(route = "chip/{chipId}/{profileId}", arguments = listOf(
                            navArgument("chipId") {
                                type = NavType.IntType
                            },
                            navArgument("profileId") {
                                type = NavType.IntType
                            }
                        )) { backStackEntry ->

                            val chipId = backStackEntry.arguments?.getInt("chipId")
                            val profileId = backStackEntry.arguments?.getInt("profileId")

                            BottomSheet(hiltViewModel(), chipId, profileId) {
                                showSheet = false
                            }
                        }
                    }

                }
            }
        }
    }
}
