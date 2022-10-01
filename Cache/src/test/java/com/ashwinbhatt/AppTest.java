package com.ashwinbhatt;

import com.ashwinbhatt.Cache.Cache;
import com.ashwinbhatt.Cache.factories.CacheFactories;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        Cache<Integer, Integer> cache= new CacheFactories<Integer, Integer>().defaultCache(3);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        cache.put(2, 3);
        cache.get(2);
        assertEquals(cache.get(2), new Integer(3));
        assertEquals(cache.get(3), new Integer(3));
        assertEquals(cache.get(4), new Integer(4));
    }
}
