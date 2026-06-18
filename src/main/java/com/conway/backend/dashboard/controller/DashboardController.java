package com.conway.backend.dashboard.controller;

import com.conway.backend.dashboard.dto.response.DashboardResponse;
import com.conway.backend.dashboard.dto.response.RecentEnquiryResponse;
import com.conway.backend.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardResponse getDashboard() {

        return dashboardService.getDashboard();
    }

    @GetMapping("/recent-enquiries")
    public List<RecentEnquiryResponse>
    getRecentEnquiries() {

        return dashboardService
                .getRecentEnquiries();
    }
}