package com.conway.backend.dashboard.service;

import com.conway.backend.dashboard.dto.response.DashboardResponse;
import com.conway.backend.dashboard.dto.response.RecentEnquiryResponse;

import java.util.List;

public interface DashboardService {

    DashboardResponse getDashboard();

    List<RecentEnquiryResponse> getRecentEnquiries();
}