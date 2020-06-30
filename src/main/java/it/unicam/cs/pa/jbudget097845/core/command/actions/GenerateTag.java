package it.unicam.cs.pa.jbudget097845.core.command.actions;

import it.unicam.cs.pa.jbudget097845.core.ApplicationController;
import org.json.JSONArray;

public class GenerateTag implements SendAction {

    ApplicationController controller = new ApplicationController();

    @Override
    public void execute(JSONArray jsonArray) {
        String name = jsonArray.getJSONObject(0).getString("name");
        String description = jsonArray.getJSONObject(0).getString("description");
        controller.addTag(name, description);
    }
}
