package org.pawpaw.processor.annotations;

import java.util.Set;
import java.util.TreeSet;

public class ControlledFile {
    private String className;

    public final Set<ControlledItem> controlledItems = new TreeSet<>();

    public ControlledFile(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean addItem(ControlledItem item){
        return controlledItems.add(item);
    }

    public Set<ControlledItem> getControlledItems() {
        return controlledItems;
    }

}
