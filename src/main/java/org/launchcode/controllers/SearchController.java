package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<HashMap<String, String>> jobs = new ArrayList<>();

        for (HashMap<String, String> job : JobData.findAll()) {
            if (searchType.equals("all")) {
                for (String key : job.keySet()) {
                    String value = job.get(key);
                    if (value.toLowerCase().contains(searchTerm.toLowerCase())) {

                        jobs.add(job);
                    }
                }
            } else {
                if (!job.containsKey(searchType)) {
                    model.addAttribute("invalid", "Invalid Term!");
                    return "search";
                }else {

                    String value = job.get(searchType);
                    if (value.toLowerCase().contains(searchTerm.toLowerCase())) {
                        jobs.add(job);

                    }
                }

            }

        }

        model.addAttribute("jobs", jobs);
        return "search";

    }
}



