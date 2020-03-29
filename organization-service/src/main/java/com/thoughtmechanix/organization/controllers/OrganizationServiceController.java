package com.thoughtmechanix.organization.controllers;

import com.thoughtmechanix.organization.model.Organization;
import com.thoughtmechanix.organization.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="v1/organizations")
public class OrganizationServiceController {
    @Autowired
    private OrganizationService orgService;

    /**
     * GET an Organization by ID
     * @param organizationId
     * @return ResponseEntity as JSON
     */
    @GetMapping(value="/{organizationId}")
    public ResponseEntity<?> getOrganization(@PathVariable("organizationId") String organizationId) {
        return new ResponseEntity<>(orgService.getOrg(organizationId), HttpStatus.OK);
    }

    /**
     * PUT method to update an Organization
     * @param organizationId
     * @param org
     * @return ResponseEntity as JSON
     */
    @PutMapping(value="/{organizationId}")
    public ResponseEntity<?> updateOrganization(@PathVariable("organizationId") String organizationId,
                                                @RequestBody Organization org) {
        return new ResponseEntity<>(orgService.updateOrg(org), HttpStatus.OK);
    }


    /**
     * POST method ot create a new Organization
     * @param organizationId
     * @param org
     * @return ResponseEntity as JSON
     */
    @PostMapping(value="/{organizationId}")
    public ResponseEntity<?> saveOrganization(@PathVariable("organizationId") String organizationId,
                                                @RequestBody Organization org) {
        return new ResponseEntity<>(orgService.saveOrg(org), HttpStatus.CREATED);
    }


    /**
     * DELETE an Organization
     * @param organizationId
     * @return ResponseEntity as JSON
     */
    @DeleteMapping(value="/{organizationId}")
    public ResponseEntity<?> deleteOrganization(@PathVariable("organizationId") String organizationId) {
        orgService.deleteOrg(organizationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
