package com.chips.divisive.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
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

            ChipsDivisiveTheme {
//                MainComposer()
//                BottomSheetNavDemo()
//                MyApp()

                ChipMaker(hiltViewModel())
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
fun MainComposer() {
    var showSheet by remember { mutableStateOf(false) }

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
//                    val navController: NavHostController = rememberNavController()
//                    val modalBottomSheetState = rememberModalBottomSheetState()
//


        val bottomSheetNavigator = rememberBottomSheetNavigator()
        val navController = rememberNavController(bottomSheetNavigator)


        ModalBottomSheetLayout(bottomSheetNavigator) {

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
                        showSheet = false

                        navController.navigate("chip/${it.id}/${it.profileId}")
                    }
                }
                composable(route = "calc") {
                    MainScreen()
                }
                bottomSheet(route = "chip/{chipId}/{profileId}", arguments = listOf(
                    navArgument("chipId") {
                        type = NavType.IntType
                    },
                    navArgument("profileId") {
                        type = NavType.IntType
                    }
                )) {

                    ChipMaker(hiltViewModel()) {
                        showSheet = false
                    }
//                            MyDialogComposable()
                }

                */
/*     composable(route = "chip/{chipId}/{profileId}", arguments = listOf(
                         navArgument("chipId") {
                             type = NavType.IntType
                         },
                         navArgument("profileId") {
                             type = NavType.IntType
                         }
                     )) { backStackEntry ->

                         val chipId = backStackEntry.arguments?.getInt("chipId")
                         val profileId = backStackEntry.arguments?.getInt("profileId")

                      *//*
*/
/*   BottomSheet(hiltViewModel()) {
                                showSheet = false
                            }*//*
*/
/*





                            ModalBottomSheet(
                                onDismissRequest = {  },
                                sheetState = modalBottomSheetState,
                                dragHandle = { BottomSheetDefaults.DragHandle() },
                            ) {
                                ChipMaker(hiltViewModel()) {

                                }
                            }



                        }*//*



            }
        }

    }
}

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

