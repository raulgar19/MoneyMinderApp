package com.example.moneyminder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.moneyminder.ui.screens.gastos.AddGastoScreen
import com.example.moneyminder.ui.screens.gastos.GastosGrafScreen
import com.example.moneyminder.ui.screens.gastos.GastosScreen
import com.example.moneyminder.ui.screens.ingresos.AddIngresoScreen
import com.example.moneyminder.ui.screens.ingresos.IngresosGrafScreen
import com.example.moneyminder.ui.screens.ingresos.IngresosScreen
import com.example.moneyminder.ui.screens.login.LoginScreen
import com.example.moneyminder.ui.screens.others.MenuPrincipalScreen
import com.example.moneyminder.ui.screens.others.TerminosScreen
import com.example.moneyminder.ui.screens.profile.PerfilScreen
import com.example.moneyminder.ui.screens.register.AddUsuarioScreen
import com.example.moneyminder.ui.screens.settings.AjustesScreen
import com.example.moneyminder.ui.screens.settings.InfoScreen
import com.example.moneyminder.ui.screens.settings.SoporteAndComentariosScreen
import com.example.moneyminder.ui.screens.settings.email.AjusteCuentaScreen
import com.example.moneyminder.ui.screens.settings.email.UpdateCorreoScreen
import com.example.moneyminder.ui.screens.settings.pass.AjustePassScreen
import com.example.moneyminder.ui.screens.settings.email.PassCuentaScreen
import com.example.moneyminder.ui.screens.settings.pass.PassScreen
import com.example.moneyminder.ui.screens.settings.pass.UpdatePassScreen
import com.example.moneyminder.ui.screens.settings.user.AjusteUsuarioScreen
import com.example.moneyminder.ui.screens.settings.user.UpdateUsuarioScreen

@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(route = AppScreens.LoginScreen.route){
            LoginScreen(
                goMenuPrincipal = { correo ->
                    navController.popBackStack()
                    navController.navigate(AppScreens.MenuPrincipalScreen.route + "/${correo}")
                },
                goAddUsuario = {
                    navController.navigate(AppScreens.AddUsuarioScreen.route)
                }
            )
        }
        composable(
            route = AppScreens.MenuPrincipalScreen.route + "/{correo}",
            arguments = listOf(
                navArgument("correo") {
                    type = NavType.StringType
                }
            )
        ){ backStackEntry ->
            MenuPrincipalScreen(
                goIngresos = { correo ->
                    navController.navigate(AppScreens.IngresosScreen.route + "/${correo}")
                },
                goGastos = { correo ->
                    navController.navigate(AppScreens.GastosScreen.route + "/${correo}")
                },
                goPerfil = { correo ->
                    navController.navigate(AppScreens.PerfilScreen.route + "/${correo}")
                },
                goAjustes = { correo ->
                    navController.navigate(AppScreens.AjustesScreen.route + "/${correo}")
                },
                correo = backStackEntry.arguments?.getString("correo") ?: "null"
            )
        }
        composable(
            route = AppScreens.GastosScreen.route + "/{correo}",
            arguments = listOf(
                navArgument("correo") {
                    type = NavType.StringType
                }
            )
        ){backStackEntry ->
            GastosScreen(
                goMenu = { correo ->
                    navController.navigate(AppScreens.MenuPrincipalScreen.route + "/${correo}")
                },
                goAddGasto = { correo ->
                    navController.navigate(AppScreens.AddGastoScreen.route + "/${correo}")
                },
                goGraf = { correo ->
                    navController.navigate(AppScreens.GastosGrafScreen.route + "/${correo}")
                },
                correo = backStackEntry.arguments?.getString("correo") ?: "null"
            )
        }
        composable(
            route = AppScreens.PerfilScreen.route + "/{correo}",
            arguments = listOf(
                navArgument("correo") {
                    type = NavType.StringType
                }
            )){ backStackEntry ->
            PerfilScreen(
                goBack = {
                    navController.popBackStack()
                },
                correo = backStackEntry.arguments?.getString("correo") ?: "null"
            )
        }
        composable(route = AppScreens.AddUsuarioScreen.route) {
            AddUsuarioScreen(
                goBack = {
                    navController.popBackStack()
                },
                goLogin = {
                    navController.navigate(AppScreens.LoginScreen.route)
                },
                goTerminos = {
                    navController.navigate(AppScreens.TerminosScreen.route)
                }
            )
        }
        composable(
            route = AppScreens.IngresosScreen.route + "/{correo}",
            arguments = listOf(
                navArgument("correo") {
                    type = NavType.StringType
                }
            )
        ){ backStackEntry ->
            IngresosScreen(
                goMenu = { correo ->
                    navController.navigate(AppScreens.MenuPrincipalScreen.route + "/${correo}")
                },
                goAddIngreso = { correo ->
                    navController.navigate(AppScreens.AddIngresoScreen.route + "/${correo}")
                },
                goGraf = { correo ->
                    navController.navigate(AppScreens.IngresosGrafScreen.route + "/${correo}")
                },
                correo = backStackEntry.arguments?.getString("correo") ?: "null"
            )
        }
        composable(
            route = AppScreens.UpdateUsuarioScreen.route + "/{correo}",
            arguments = listOf(
                navArgument("correo") {
                    type = NavType.StringType
                }
            )){ backStackEntry ->
            UpdateUsuarioScreen(
                goBack = {
                    navController.popBackStack()
                },
                goMenu = { correo ->
                    navController.navigate(AppScreens.MenuPrincipalScreen.route + "/${correo}")
                },
                correo = backStackEntry.arguments?.getString("correo") ?: "null"
            )
        }
        composable(
            route = AppScreens.UpdateCorreoScreen.route + "/{correo}",
            arguments = listOf(
                navArgument("correo") {
                    type = NavType.StringType
                }
            )){ backStackEntry ->
            UpdateCorreoScreen(
                goBack = {
                    navController.popBackStack()
                },
                goLogin = {
                    navController.navigate(AppScreens.LoginScreen.route)
                },
                correo = backStackEntry.arguments?.getString("correo") ?: "null"
            )
        }
        composable(
            route = AppScreens.AjustesScreen.route + "/{correo}",
            arguments = listOf(
                navArgument("correo") {
                    type = NavType.StringType
                }
            )){ backStackEntry ->
            AjustesScreen(
                goBack = {
                    navController.popBackStack()
                },
                goUsuario = { correo ->
                    navController.navigate(AppScreens.AjusteUsuarioScreen.route + "/${correo}")
                },
                goSecurity = { correo ->
                    navController.navigate(AppScreens.PassScreen.route + "/${correo}")
                },
                goCuenta = { correo ->
                    navController.navigate(AppScreens.PassCuentaScreen.route + "/${correo}")
                },
                goSoporte = {
                    navController.navigate(AppScreens.SoporteAndComentariosScreen.route)
                },
                goAcerca = {
                    navController.navigate(AppScreens.InfoScreen.route)
                },
                correo = backStackEntry.arguments?.getString("correo") ?: "null"
            )
        }
        composable(route = AppScreens.InfoScreen.route){
            InfoScreen(navController)
        }
        composable(
            route = AppScreens.PassScreen.route + "/{correo}",
            arguments = listOf(
                navArgument("correo") {
                    type = NavType.StringType
                }
            )){ backStackEntry ->
            PassScreen(
                goBack = {
                    navController.popBackStack()
                },
                goAjustePass = { correo ->
                    navController.popBackStack()
                    navController.navigate(AppScreens.AjustePassScreen.route + "/${correo}")
                },
                correo = backStackEntry.arguments?.getString("correo") ?: "null"
            )
        }
        composable(
            route = AppScreens.PassCuentaScreen.route + "/{correo}",
            arguments = listOf(
                navArgument("correo") {
                    type = NavType.StringType
                }
            )){ backStackEntry ->
            PassCuentaScreen(
                goBack ={
                    navController.popBackStack()
                },
                goAjusteCuenta = { correo ->
                    navController.popBackStack()
                    navController.navigate(AppScreens.AjusteCuentaScreen.route + "/${correo}")
                },
                correo = backStackEntry.arguments?.getString("correo") ?: "null"
            )
        }
        composable(
            route = AppScreens.UpdatePassScreen.route + "/{correo}",
            arguments = listOf(
                navArgument("correo") {
                    type = NavType.StringType
                }
            )){ backStackEntry ->
            UpdatePassScreen(
                goBack = {
                    navController.popBackStack()
                },
                goLogin = {
                    navController.navigate(AppScreens.LoginScreen.route)
                },
                correo = backStackEntry.arguments?.getString("correo") ?: "null"
            )
        }
        composable(
            route = AppScreens.AjusteUsuarioScreen.route + "/{correo}",
            arguments = listOf(
                navArgument("correo") {
                    type = NavType.StringType
                }
            )){ backStackEntry ->
            AjusteUsuarioScreen(
                goBack = {
                    navController.popBackStack()
                },
                goUsuario = {correo ->
                    navController.navigate(AppScreens.UpdateUsuarioScreen.route + "/${correo}")
                },
                goLogin = {
                  navController.navigate(AppScreens.LoginScreen.route)
                },
                correo = backStackEntry.arguments?.getString("correo") ?: "null"
            )
        }
        composable(
            route = AppScreens.AjusteCuentaScreen.route + "/{correo}",
            arguments = listOf(
                navArgument("correo") {
                    type = NavType.StringType
                }
            )){ backStackEntry ->
            AjusteCuentaScreen(
                goBack = {
                    navController.popBackStack()
                },
                goCorreo = { correo ->
                    navController.navigate(AppScreens.UpdateCorreoScreen.route + "/${correo}")
                },
                correo = backStackEntry.arguments?.getString("correo") ?: "null"
            )
        }
        composable(
            route = AppScreens.AjustePassScreen.route + "/{correo}",
            arguments = listOf(
                navArgument("correo") {
                    type = NavType.StringType
                }
            )){ backStackEntry ->
            AjustePassScreen(
                goBack = {
                    navController.popBackStack()
                },
                goPass = { correo ->
                    navController.navigate(AppScreens.UpdatePassScreen.route + "/${correo}")
                },
                correo = backStackEntry.arguments?.getString("correo") ?: "null"
            )
        }
        composable(
            route = AppScreens.AddGastoScreen.route + "/{correo}",
            arguments = listOf(
                navArgument("correo") {
                    type = NavType.StringType
                }
            )){ backStackEntry ->
            AddGastoScreen(
                goBack = {
                    navController.popBackStack()
                },
                goGastos = { correo ->
                    navController.navigate(AppScreens.GastosScreen.route + "/${correo}")
                },
                correo = backStackEntry.arguments?.getString("correo") ?: "null"
            )
        }
        composable(
            route = AppScreens.AddIngresoScreen.route + "/{correo}",
            arguments = listOf(
                navArgument("correo") {
                    type = NavType.StringType
                }
            )){ backStackEntry ->
            AddIngresoScreen(
                goBack = {
                    navController.popBackStack()
                },
                goIngresos = { correo ->
                    navController.navigate(AppScreens.IngresosScreen.route + "/${correo}")
                },
                correo = backStackEntry.arguments?.getString("correo") ?: "null"
            )
        }
        composable(route = AppScreens.SoporteAndComentariosScreen.route){
            SoporteAndComentariosScreen(navController)
        }
        composable(route = AppScreens.TerminosScreen.route){
            TerminosScreen(
                goBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AppScreens.GastosGrafScreen.route + "/{correo}",
            arguments = listOf(
                navArgument("correo") {
                    type = NavType.StringType
                }
            )
        ){backStackEntry ->
            GastosGrafScreen(
                goBack = {
                    navController.popBackStack()
                },
                correo = backStackEntry.arguments?.getString("correo") ?: "null"
            )
        }
        composable(
            route = AppScreens.IngresosGrafScreen.route + "/{correo}",
            arguments = listOf(
                navArgument("correo") {
                    type = NavType.StringType
                }
            )
        ){backStackEntry ->
            IngresosGrafScreen(
                goBack = {
                    navController.popBackStack()
                },
                correo = backStackEntry.arguments?.getString("correo") ?: "null"
            )
        }
    }
}