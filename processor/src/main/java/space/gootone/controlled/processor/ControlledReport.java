package space.gootone.controlled.processor;

import com.google.gson.Gson;

import java.util.Map;
import java.util.TreeMap;

public class ControlledReport {
    public final Map<String, ControlledFile> controlledFileMap = new TreeMap<>();

    public boolean addItem(String className, ControlledItem item) {
        ControlledFile controlledFile = controlledFileMap.get(className);
        if (controlledFile == null) {
            controlledFile = new ControlledFile(className);
            controlledFileMap.put(controlledFile.getClassName(), controlledFile);
        }
        return controlledFile.addItem(item);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
