package com.chips.divisive.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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
import com.chips.divisive.ui.main.dialog.ChipMaker
import com.chips.divisive.ui.theme.ChipsDivisiveTheme
//import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
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

                            BottomSheet(hiltViewModel()) {
                                showSheet = false
                            }
                        }
                    }

                }
            }
        }
    }




}

//@OptIn(ExperimentalMaterialNavigationApi::class)

@Composable
fun MyApp() {


    /*
        val bottomSheetNavigator = rememberBottomSheetNavigator()
        val navController = rememberNavController(bottomSheetNavigator)
        ModalBottomSheetLayout(bottomSheetNavigator) {
            NavHost(navController, "home") {
                composable(route = "home") {
                    ProfileListScreen(hiltViewModel(),
                        editCallback = {
                            navController.navigate("detial/$it")
                        },
                        calculateCallback = {
                            navController.navigate("sheet")
                        })            }
                bottomSheet(route = "sheet") {
                    Text("This is a cool bottom sheet!")
                }
            }
        }*/
}

/*

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun BottomSheetNavDemo() {


    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(bottomSheetNavigator)

    */
/*  ModalBottomSheetLayout(bottomSheetNavigator) {

      }*//*



    */
/*

    val modalBottomSheetState = rememberModalBottomSheetState()
    val bottomSheetNavigator = rememberBottomSheetNavigator()


    val navController = rememberNavController(bottomSheetNavigator)*//*


    ModalBottomSheetLayout(bottomSheetNavigator) {
        NavHost(navController, Destinations.Home) {
            composable(Destinations.Home) {

                ProfileListScreen(hiltViewModel(),
                    editCallback = {
                        navController.navigate("detial/$it")
                    },
                    calculateCallback = {
                        navController.navigate("calc")
                    })

                */
/* HomeScreen(
                     showSheet = {
                         navController.navigate(Destinations.Sheet + "?arg=From Home Screen")
                     },
                     showFeed = { navController.navigate(Destinations.Feed) }
                 )*//*

            }
            composable(route = "detial/{profileId}", arguments = listOf(
                navArgument("profileId") {
                    type = NavType.IntType
                }
            )) {
                ProfileDetailScreen(hiltViewModel()) {
//                    showSheet = false

                    navController.navigate("chip/${it.id}/${it.profileId}")
                }
            }
            composable(route = "calc") {
                MainScreen()
            }
            */
/*

            //            composable(Destinations.Feed) { Text("Feed!") }
                         bottomSheet("chip" + "?arg={arg}") { backstackEntry ->
                             val arg = backstackEntry.arguments?.getString("arg") ?: "Missing argument :("
                             ChipMaker(hiltViewModel()) {
            //                     showSheet = false
                             }
                         }
            *//*



            bottomSheet(route = "chip/{chipId}/{profileId}", arguments = listOf(
                navArgument("chipId") {
                    type = NavType.IntType
                },
                navArgument("profileId") {
                    type = NavType.IntType
                }
            )) {
                ChipMaker(hiltViewModel()) {

                }

                */
/*    ModalBottomSheet(
                        onDismissRequest = {  },
                        sheetState = modalBottomSheetState,
                        dragHandle = { BottomSheetDefaults.DragHandle() },
                    ) {

                    }*//*



                */
/*BottomSheet(hiltViewModel()) {
//                    showSheet = false
                }*//*

//                            MyDialogComposable()
            }
        }
    }
}
*/

private object Destinations {
    const val Home = "HOME"
    const val Detail = "FEED"
    const val Sheet = "SHEET"
}

