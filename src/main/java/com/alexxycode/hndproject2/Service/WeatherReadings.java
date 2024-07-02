package com.alexxycode.hndproject2.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherReadings {

    @Value("${openWeather-key}")
    private String apiKey;
    private final IpInfo ipInfo;

    public String getTemperature() {
        String city = ipInfo.getLocation().city();
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
        RestTemplate restTemplate = new RestTemplate();
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
        return response.getMain().getTemp() + " degrees Celsius";
    }

    public static class WeatherResponse {
        private Main main;

        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
        }

        public static class Main {
            private String temp;

            public String getTemp() {
                return temp;
            }

            public void setTemp(String temp) {
                this.temp = temp;
            }
        }
    }

}
