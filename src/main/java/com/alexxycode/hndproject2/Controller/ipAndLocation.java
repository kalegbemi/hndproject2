package com.alexxycode.hndproject2.Controller;

import com.alexxycode.hndproject2.Service.IpInfo;
import com.alexxycode.hndproject2.Service.WeatherReadings;
import com.alexxycode.hndproject2.model.GeneralResponse;
import com.alexxycode.hndproject2.model.IpResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ipAndLocation {

    private final IpInfo ipInfo;
    private final WeatherReadings weatherReadings;

    @GetMapping("/getlocation")
    public IpResponse getLocation(@RequestHeader(value = "value = X-Forwarded-For", required = false) String ipAddress) {
        if (ipAddress == null) {
            ipAddress = "172.0.0.1";
        }
        return ipInfo.getLocation(ipAddress);
    }

    @GetMapping("/gettemp")
    public String getTemperature(String city) {
        return weatherReadings.getTemperature(city);

    }

    @GetMapping("/api/hello")
    public GeneralResponse getGreeting(@RequestParam String visitor_name,
                                       HttpServletRequest httpServlet) {

        String ipAddress = getClientIp(httpServlet);
        IpResponse response = ipInfo.getLocation(ipAddress);
        log.info(response.toString());

        String city = response.city();
        String temperature = weatherReadings.getTemperature(city);
        String greeting = ("Hello, " + visitor_name + "!, the temperature is " + temperature + " in " + city);

        return new GeneralResponse(ipAddress, city, greeting);
    }

    private static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR" };

    private String getClientIp(HttpServletRequest request) {
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            log.info(header+" details = " + ip);
            if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
                log.info(ip);
                return ip;

            }
        }
        log.info(request.getRemoteAddr());
        return request.getRemoteAddr();

    }

   /* private String getClientIp(HttpServletRequest response) {
        String clientIps = response.getHeader("X-Forwarded-For");
        log.info("ip address from httpservletrequest = " + clientIps);
        String clientIp;
        if((clientIps == null) || (clientIps.isEmpty())){
            clientIp = response.getRemoteAddr();
            log.info("client ip is null, get remote addr ="+ clientIp);
        }
        else {
            clientIp = clientIps.split(",")[0].trim();
            log.info("client ip is ="+ clientIp);
        }
        return clientIp;
    }*/
}
