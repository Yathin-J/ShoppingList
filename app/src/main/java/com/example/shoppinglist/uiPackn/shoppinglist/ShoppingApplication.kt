/*
package com.example.shoppinglist.uiPackn.shoppinglist

import android.app.Application
import com.example.shoppinglist.data.db.ShoppingDatabase
import com.example.shoppinglist.data.repository.ShoppingRepository
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import java.util.Collections.singleton

class ShoppingApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ShoppingApplication))

        // Fixing the binding of ShoppingDatabase
        bind<ShoppingDatabase>() with singleton { ShoppingDatabase.getInstance(instance()) }

        // Binding ShoppingRepository correctly
        bind<ShoppingRepository>() with singleton { ShoppingRepository(instance()) }

        // Providing ShoppingViewModelFactory
        bind<ShoppingViewModelFactory>() with provider { ShoppingViewModelFactory(instance()) }
    }


}*/
