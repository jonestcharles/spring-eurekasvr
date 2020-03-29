package com.thoughtmechanix.organization.services;

import com.thoughtmechanix.organization.model.Organization;
import com.thoughtmechanix.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository orgRepository;

    public Organization getOrg(String organizationId) {
        return orgRepository.findById(organizationId);
    }

    public Organization saveOrg(Organization org){
        org.setId(UUID.randomUUID().toString());
        return orgRepository.save(org);
    }

    public Organization updateOrg(Organization org){
        return orgRepository.save(org);
    }

    public void deleteOrg(String organizationId){
        orgRepository.delete(organizationId);
    }
}
