package com.alexxycode.hndproject2.Controller;

import com.alexxycode.hndproject2.Service.IpInfo;
import com.alexxycode.hndproject2.Service.WeatherReadings;
import com.alexxycode.hndproject2.model.GenaralResponse;
import com.alexxycode.hndproject2.model.IpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ipAndLocation {

    private final IpInfo ipInfo;
    private final WeatherReadings weatherReadings;

@GetMapping("/getlocation")
    public IpResponse getLocation(){
    return ipInfo.getLocation();
}
@GetMapping("/gettemp")
    public String getTemperature(){
    return weatherReadings.getTemperature();

}
@GetMapping("/api/hello/")
    public GenaralResponse getGreeting(@RequestParam String visitor_name){
    IpResponse response = ipInfo.getLocation();
    String temperature = weatherReadings.getTemperature();
    String city = response.city();
    String ip = response.ip();
    String greeting = ("Hello, " + visitor_name + "!, the temperature is "+temperature +" in " + city);

    return new GenaralResponse(ip, city, greeting);
}
}
