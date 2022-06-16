package com.ahmedorabi.weatherapp.features.add_city.framework

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ahmedorabi.weatherapp.core.db.AppDatabase
import com.ahmedorabi.weatherapp.core.db.WeatherDao
import com.ahmedorabi.weatherapp.core.domain.model.City
import com.ahmedorabi.weatherapp.core.domain.model.HistoricalModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class InRoomLocalDataSourceAddCityTest {

    private lateinit var weatherDao: WeatherDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        weatherDao = db.weatherDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertCity() {

        val city = City(name = "test1")
        val city2 = City(name = "test2")


//        val user: User = TestUtil.createUser(3).apply {
//            setName("george")
//        }
//        userDao.insert(user)
        runBlocking {
            weatherDao.insertCity(city)
            weatherDao.insertCity(city2)

            //   val byName = userDao.findUsersByName("george")
            //  assertEquals(list.size, 3)
        }

    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
//        val user: User = TestUtil.createUser(3).apply {
//            setName("george")
//        }
//        userDao.insert(user)
        val list = weatherDao.getAllCities()
        //   val byName = userDao.findUsersByName("george")
        assertEquals(list.size, 2)
    }

    @Test
    @Throws(Exception::class)
    fun insertHistoricalModel() {

        val city = HistoricalModel(name = "test3", dateTime = "", desc = "desc", temp = 20)

//        val user: User = TestUtil.createUser(3).apply {
//            setName("george")
//        }
//        userDao.insert(user)
        runBlocking {
            weatherDao.insertHistoricalModel(city)
            val item = weatherDao.getAllHistoricalModel("test3")
            assertEquals(item.size, 1)
        }

    }

}