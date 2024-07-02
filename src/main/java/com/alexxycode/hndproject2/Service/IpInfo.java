package com.alexxycode.hndproject2.Service;

import com.alexxycode.hndproject2.model.IpResponse;
import io.ipinfo.api.model.IPResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IpInfo {

@Value("${ipinfo_key}")
private String IPINFO_KEY;

public IpResponse getLocation(){
    String url = "https://ipinfo.io/?token=" + IPINFO_KEY;

    RestTemplate restTemplate = new RestTemplate();
    IpResponse response = restTemplate.getForObject(url, IpResponse.class);
    assert response != null;
    return response;
}

 /*   @Getter
    public static class IpInfoResponse {
        private String city;

        public void setCity(String city) {
            this.city = city;
        }
}*/

}
