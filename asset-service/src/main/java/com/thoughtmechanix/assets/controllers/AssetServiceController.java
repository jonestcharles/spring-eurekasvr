package com.thoughtmechanix.assets.controllers;

import com.thoughtmechanix.assets.config.ServiceConfig;
import com.thoughtmechanix.assets.model.Asset;
import com.thoughtmechanix.assets.services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value="/v1/organizations/{organizationId}/assets")
public class AssetServiceController {
    @Autowired
    private AssetService assetService;

    @Autowired
    private ServiceConfig serviceConfig;

    /**
     * GET all assets from an Organization
     * @param organizationId the Organization to get Assets of
     * @return ResponseEntity of Assets as JSON
     */
    @GetMapping(value="/")
    public ResponseEntity<?> getAssets(@PathVariable("organizationId") String organizationId) {
        return new ResponseEntity<>(assetService.getAssetsByOrg(organizationId), HttpStatus.OK);
    }

    /**
     * GET an Asset from an Organization by id
     * @param organizationId
     * @param assetId
     * @return ResponseEntity with Asset as JSON
     */
    @GetMapping(value="/{assetId}")
    public ResponseEntity<?> getAsset(@PathVariable("organizationId") String organizationId,
                                      @PathVariable("assetId") String assetId) {

        return new ResponseEntity<>(assetService.getAsset(organizationId, assetId, ""), HttpStatus.OK);
    }

    /**
     * GET an Asset from an Organization by id, using a defined service
     * @param organizationId
     * @param assetId
     * @param clientType
     * @return ResponseEntity as a JSON
     */
    @GetMapping(value="/{assetId}/{clientType}")
    public ResponseEntity<?> getAssetWithClient(@PathVariable("organizationId") String organizationId,
                                                @PathVariable("assetId") String assetId,
                                                @PathVariable("clientType") String clientType) {

        return new ResponseEntity<>(assetService.getAsset(organizationId, assetId, clientType), HttpStatus.OK);
    }

    /**
     * PUT mapping that updates an Asset with newAsset
     * @param newAsset
     * @param assetId
     * @return ResponseEntity
     */
    @PutMapping(value="/{assetId}")
    public ResponseEntity<?> updateAsset(@RequestBody Asset newAsset, @PathVariable("assetId") String assetId) {
        return new ResponseEntity<>(assetService.updateAsset(newAsset), HttpStatus.OK);
    }

    /**
     * POST method creating a new Asset with given params
     * @param asset
     * @return ResponseEntity confirming POST
     */
    @PostMapping(value="/")
    public ResponseEntity<?> saveAsset(@RequestBody Asset asset) {
        return new ResponseEntity<>(assetService.saveAsset(asset), HttpStatus.CREATED);
    }

    /**
     * DELETE method that deletes an Asset by ID
     * @param assetId
     * @return ResponseEntity
     */
    @DeleteMapping(value="/{assetId}")
    public ResponseEntity<?> deleteAsset( @PathVariable("assetId") String assetId) {
        assetService.deleteAsset(assetId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
