package com.springrest.springrest.controller;

import com.springrest.springrest.controller.LoadRequest;
import com.springrest.springrest.controller.LoadResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LoadService {
    private List<LoadResponse> loadStorage = new ArrayList<>(); // In-memory storage for loads

    // Method to add a load
    public String  addLoad(LoadRequest loadRequest) {
        LoadResponse loadResponse = new LoadResponse();
        loadResponse.setLoadId(UUID.randomUUID()); // Generate a unique loadId
        loadResponse.setLoadingPoint(loadRequest.getLoadingPoint());
        loadResponse.setUnloadingPoint(loadRequest.getUnloadingPoint());
        loadResponse.setProductType(loadRequest.getProductType());
        loadResponse.setTruckType(loadRequest.getTruckType());
        loadResponse.setNoOfTrucks(loadRequest.getNoOfTrucks());
        loadResponse.setWeight(loadRequest.getWeight());
        loadResponse.setComment(loadRequest.getComment());
        loadResponse.setShipperId(loadRequest.getShipperId().toString()); // Convert UUID to String
        loadResponse.setDate(loadRequest.getDate());

        loadStorage.add(loadResponse);
        return "Load details added successfully";// Store the load
    }

    // Method to retrieve loads by shipperId
    public List<LoadResponse> getLoadsByShipperId(UUID shipperId) {
        List<LoadResponse> result = new ArrayList<>();
        for (LoadResponse load : loadStorage) {
            if (load.getShipperId().equals(shipperId.toString())) {
                result.add(load); // Add matching loads to the result list
            }
        }
        return result;
    }

    // Method to retrieve a load by loadId
    public LoadResponse getLoadById(UUID loadId) {
        for (LoadResponse load : loadStorage) {
            if (load.getLoadId().equals(loadId)) {
                return load;
            }
        }
        return null; // Load not found
    }

    // Method to update a load by loadId
    public boolean updateLoad(UUID loadId, LoadRequest loadRequest) {
        for (LoadResponse load : loadStorage) {
            if (load.getLoadId().equals(loadId)) {
                load.setLoadingPoint(loadRequest.getLoadingPoint());
                load.setUnloadingPoint(loadRequest.getUnloadingPoint());
                load.setProductType(loadRequest.getProductType());
                load.setTruckType(loadRequest.getTruckType());
                load.setNoOfTrucks(loadRequest.getNoOfTrucks());
                load.setWeight(loadRequest.getWeight());
                load.setComment(loadRequest.getComment());
                load.setDate(loadRequest.getDate());
                return true; // Load updated successfully
            }
        }
        return false; // Load not found
    }

    // Method to delete a load by loadId
    public boolean deleteLoad(UUID loadId) {
        return loadStorage.removeIf(load -> load.getLoadId().equals(loadId));
    }
}
