package com.thoughtmechanix.assets.services;

import com.thoughtmechanix.assets.clients.OrganizationDiscoveryClient;
import com.thoughtmechanix.assets.clients.OrganizationFeignClient;
import com.thoughtmechanix.assets.clients.OrganizationRestTemplateClient;
import com.thoughtmechanix.assets.config.ServiceConfig;
import com.thoughtmechanix.assets.model.Asset;
import com.thoughtmechanix.assets.model.Organization;
import com.thoughtmechanix.assets.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    ServiceConfig config;

    @Autowired
    OrganizationFeignClient organizationFeignClient;

    @Autowired
    OrganizationRestTemplateClient organizationRestClient;

    @Autowired
    OrganizationDiscoveryClient organizationDiscoveryClient;

    private Organization retrieveOrgInfo(String organizationId, String clientType){
        Organization organization = null;

        switch (clientType) {
            case "feign":
                System.out.println("I am using the feign client");
                organization = organizationFeignClient.getOrganization(organizationId);
                break;
            case "rest":
                System.out.println("I am using the rest client");
                organization = organizationRestClient.getOrganization(organizationId);
                break;
            case "discovery":
                System.out.println("I am using the discovery client");
                organization = organizationDiscoveryClient.getOrganization(organizationId);
                break;
            default:
                organization = organizationRestClient.getOrganization(organizationId);
        }

        return organization;
    }

    public Asset getAsset(String organizationId, String assetId, String clientType) {
        Asset asset = assetRepository.findByOrganizationIdAndAssetId(organizationId, assetId);

        Organization org = retrieveOrgInfo(organizationId, clientType);

        return asset
                .withOrganizationName( org.getName())
                .withContactName( org.getContactName())
                .withContactEmail( org.getContactEmail() )
                .withContactPhone( org.getContactPhone() )
                .withComment(config.getExampleProperty());
    }

    public List<Asset> getAssetsByOrg(String organizationId){
        return assetRepository.findByOrganizationId( organizationId );
    }

    public Asset saveAsset(Asset asset){
        asset.withAssetId(UUID.randomUUID().toString());

        return assetRepository.save(asset);
    }

    public Asset updateAsset(Asset newAsset){
        /*Asset foundAsset = assetRepository.findByAssetId(assetId);
        foundAsset.setOrganizationId(newAsset.getOrganizationId());
        foundAsset.setAssetName(newAsset.getAssetName());
        foundAsset.setAssetType(newAsset.getAssetType());
        foundAsset.setComment(newAsset.getComment());*/
        return assetRepository.save(newAsset);
    }

    public void deleteAsset(String assetId){
        assetRepository.delete(assetId);
    }

}
