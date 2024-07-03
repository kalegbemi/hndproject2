package com.alexxycode.hndproject2.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherReadings {

    @Value("${openWeather-key}")
    private String apiKey;

    public String getTemperature(String city) {

        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
        RestTemplate restTemplate = new RestTemplate();
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
        assert response != null;
        return response.main.getTemp() + " degrees Celsius";
    }
    @Getter @Setter
    public static class WeatherResponse {
        private Main main;

        @Getter @Setter
        public static class Main {
            private String temp;

        }
    }

}
