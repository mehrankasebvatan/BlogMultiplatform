package ir.kasebvatan.blog.pages.admin

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import ir.kasebvatan.blog.util.isUserLoggedIn

@Page
@Composable
fun HomeScreen() {
    isUserLoggedIn {
        HomePage()
    }
}

@Composable
fun HomePage() {
    println("Admin Home Page")
}