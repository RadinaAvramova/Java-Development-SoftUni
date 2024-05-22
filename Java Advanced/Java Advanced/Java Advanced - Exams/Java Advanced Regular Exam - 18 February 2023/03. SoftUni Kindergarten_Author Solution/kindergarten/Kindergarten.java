package kindergarten;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Kindergarten {
    private String name;
    private int capacity;
    private List<Child> registry;

    public Kindergarten(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.registry = new ArrayList<>();
    }

    public List<Child> getRegistry() {
        return registry;
    }

    public void setRegistry(List<Child> registry) {
        this.registry = registry;
    }

    public int getChildrenCount() {
        return this.registry.size();
    }

    public boolean addChild(Child child) {
        if (this.getChildrenCount() == this.capacity) {
            return false;
        }
        this.registry.add(child);
        return true;
    }

    public boolean removeChild(String firstName) {
        for (int i = 0; i < this.registry.size(); i++) {
            if (this.registry.get(i).getFirstName().equals(firstName)) {
                this.registry.remove(i);
                return true;
            }
        }
        return false;
    }

    /* public String updateContactInfo(String fullName, String parentName, String contactNumber) {
        String[] names = fullName.split(" ");
        Child childToUpdate = this.registry.stream()
                .filter(x -> x.getFirstName().equals(names[0]) && x.getLastName().equals(names[1])).findFirst().orElse(null);

        if (childToUpdate == null) {
            return "Unable to update contact info.";
        }

        childToUpdate.setParentName(parentName);
        childToUpdate.setContactNumber(contactNumber);

        return String.format("%s contact info is updated.", fullName);
    } */
    public Child getChild(String firstName){
        return registry.stream().filter(e -> e.getFirstName().equals(firstName)).findFirst().orElse(null);
    }

    public String registryReport() {
        List<Child> sortedChildren = this.registry.stream()
                .sorted(Comparator.comparing(Child::getAge).thenComparing(Child::getFirstName).thenComparing(Child::getLastName))
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb.append("Registered children in ").append(this.name).append(":");

        for (Child child : sortedChildren) {
            sb.append(System.lineSeparator()).append("--").append(System.lineSeparator());
            sb.append(child.toString());
        }

        return sb.toString().trim();
    }
}