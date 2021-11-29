package com.se.video.library.utils;

import com.se.video.library.dao.models.Country;

public class CountryUtils {

        private CountryUtils (){}


        public static Country prepareCountry(){
            Country country = new Country();

            return country;
        }
}
