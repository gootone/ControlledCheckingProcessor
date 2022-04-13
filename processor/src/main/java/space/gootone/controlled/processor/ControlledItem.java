package space.gootone.controlled.processor;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class ControlledItem implements Comparable<ControlledItem>{
    public final String name;
    public final Set<Controlled.Function> functionSet = new TreeSet<>();

    public ControlledItem(String name, Controlled.Function... functions) {
        this.name = name;
        Collections.addAll(functionSet, functions);
    }

    public String getName() {
        return name;
    }

    public Set<Controlled.Function> getFunctionSet() {
        return functionSet;
    }

    @Override
    public int compareTo(ControlledItem o) {
        return name.compareTo(o.getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ControlledItem){
            return name.equals(obj.toString());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
