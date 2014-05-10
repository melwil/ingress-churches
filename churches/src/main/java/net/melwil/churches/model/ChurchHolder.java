package net.melwil.churches.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChurchHolder {

    private int size = 0;
    private Set<Church> churches = new HashSet<Church>();

    public ChurchHolder() {

    }

    public void addChurch(Church church) {
        churches.add(church);
        size = churches.size();
    }

    public void addChurches(List<Church> churches) {
        this.churches.addAll(churches);
        size = this.churches.size();
    }

    public int getSize() {
        return size;
    }
}
