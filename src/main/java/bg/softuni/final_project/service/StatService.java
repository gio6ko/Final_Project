package bg.softuni.final_project.service;

import bg.softuni.final_project.model.view.StatsView;

public interface StatService {

    void onRequest();

    StatsView getStats();
}
