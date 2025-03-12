/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Entity.CoworkingReservationsEntity;
import Persistence.CoworkingReservationsEntityJpaController;
import java.util.List;

/**
 * Controller for managing coworking space reservations.
 * Handles creation, retrieval, and deletion of reservations.
 * 
 * @author brandonescudero
 */
public class CoworkingReservationsController {
    
    // JPA Controller for database operations
    CoworkingReservationsEntityJpaController CoworkingReservationsEntityJpaController = new CoworkingReservationsEntityJpaController();
    
    /**
     * Creates a new coworking space reservation.
     * 
     * @param CoworkingReservationsEntity The reservation entity to be created.
     * @return true if the reservation is successfully created, false otherwise.
     */
    public boolean create(CoworkingReservationsEntity CoworkingReservationsEntity) {
        try {
            CoworkingReservationsEntityJpaController.create(CoworkingReservationsEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Retrieves all coworking space reservations.
     * 
     * @return A list of all reservations.
     */
    public List<CoworkingReservationsEntity> find() {
        return CoworkingReservationsEntityJpaController.findCoworkingReservationsEntityEntities();
    }
    
    /**
     * Deletes a coworking space reservation by ID.
     * 
     * @param id The ID of the reservation to be deleted.
     * @return true if the reservation is successfully deleted, false otherwise.
     */
    public boolean detroy(Long id) {
        try {
            CoworkingReservationsEntityJpaController.destroy(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}