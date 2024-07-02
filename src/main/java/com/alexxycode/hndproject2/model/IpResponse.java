package com.alexxycode.hndproject2.model;

import io.ipinfo.api.context.Context;

public record IpResponse(
        String city,
        String country,
        String region,
        String ip,
        String loc) {
}
