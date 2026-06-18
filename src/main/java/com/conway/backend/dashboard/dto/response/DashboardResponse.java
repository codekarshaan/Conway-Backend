package com.conway.backend.dashboard.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardResponse {

    private long totalEnquiries;

    private long newEnquiries;

    private long contactedEnquiries;

    private long inProgressEnquiries;

    private long closedEnquiries;
}