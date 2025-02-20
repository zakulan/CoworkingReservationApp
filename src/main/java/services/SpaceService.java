package services;

import models.CoworkingSpace;
import java.util.ArrayList;
import java.util.List;

public class SpaceService {
    private List<CoworkingSpace> spaces = new ArrayList<>();

    public void addSpace(CoworkingSpace space) {
        spaces.add(space);
    }

    public void removeSpace(String id) {
        spaces.removeIf(space -> space.getId().equals(id));
    }

    public List<CoworkingSpace> getAvailableSpaces() {
        List<CoworkingSpace> availableSpaces = new ArrayList<>();
        for (CoworkingSpace space : spaces) {
            if (space.isAvailability()) {
                availableSpaces.add(space);
            }
        }
        return availableSpaces;
    }

    public CoworkingSpace getSpaceById(String id) {
        for (CoworkingSpace space : spaces) {
            if (space.getId().equals(id)) {
                return space;
            }
        }
        return null;
    }

    public void updateSpaceAvailability(String id, boolean availability) {
        CoworkingSpace space = getSpaceById(id);
        if (space != null) {
            space.setAvailability(availability);
        }
    }
}