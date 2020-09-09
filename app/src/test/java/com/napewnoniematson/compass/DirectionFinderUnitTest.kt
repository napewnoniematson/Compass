package com.napewnoniematson.compass

import com.napewnoniematson.compass.logic.DirectionFinder
import com.napewnoniematson.compass.model.geo.GeoPoint
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DirectionFinderUnitTest {

    @Mock
    private lateinit var userLocationPoint: GeoPoint
    private lateinit var destinationPoint: GeoPoint

    @Before
    fun onSetup() {
        userLocationPoint = Mockito.mock(GeoPoint::class.java)
        destinationPoint = Mockito.mock(GeoPoint::class.java)
        `when`(userLocationPoint.latitude).thenReturn(0f)
        `when`(userLocationPoint.longitude).thenReturn(0f)
    }


    @Test
    fun destinationOnNorth() {
        destinationTest(1f, 0f, 0f)
    }

    @Test
    fun destinationOnNorthEast() {
        destinationTest(0.5f, 0.5f, 45f)
    }

    @Test
    fun destinationOnEast() {
        destinationTest(0f, 1f, 90f)
    }

    @Test
    fun destinationOnSouthEast() {
        destinationTest(-0.5f, 0.5f, 135f)
    }

    @Test
    fun destinationOnSouth() {
        destinationTest(-1f, 0f, 180f)
    }

    @Test
    fun destinationOnSouthWest() {
        destinationTest(-0.5f, -0.5f, 225f)
    }

    @Test
    fun destinationOnWest() {
        destinationTest(0f, -1f, 270f)
    }

    @Test
    fun destinationOnNorthWest() {
        destinationTest(0.5f, -0.5f, 315f)
    }

    private fun destinationTest(
        destinationLatitude: Float,
        destinationLongitude: Float,
        expectedAngle: Float
    ) {
        `when`(destinationPoint.latitude).thenReturn(destinationLatitude)
        `when`(destinationPoint.longitude).thenReturn(destinationLongitude)
        val angle = DirectionFinder.findDirectionAngle(userLocationPoint, destinationPoint)
        assertEquals(expectedAngle, angle)
    }
}