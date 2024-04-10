package com.arcadio.domain.adresses.shippingaddress.service;

import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import com.arcadio.domain.adresses.shippingaddress.ShippingAddressFactory;
import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressMapper;
import com.arcadio.domain.adresses.shippingaddress.repository.ShippingAddressRepository;
import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.exceptions.NoAddressesFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;
    private final ShippingAddressFactory shippingAddressFactory;
    public ShippingAddressService(ShippingAddressRepository shippingAddressRepository, ShippingAddressFactory shippingAddressFactory) {
        this.shippingAddressRepository = shippingAddressRepository;
        this.shippingAddressFactory = shippingAddressFactory;
    }

    public ShippingAddress createShippingAddress(ShippingAddressDTO shippingAddressDTO, Company company) {
        ShippingAddress shippingAddress = shippingAddressFactory.createShippingAddress(shippingAddressDTO, company);
        return shippingAddressRepository.save(shippingAddress);
    }

    public List<ShippingAddress> getAllShippingAddressesForCompany(Company company) {
        return shippingAddressRepository.findAllByCompany(company);
    }

    public void deleteShippingAddress(Long shippingAddress) {
        shippingAddressRepository.deleteById(shippingAddress);

    }

    public boolean updateShippingAddress(ShippingAddressDTO shippingAddressDto) {
        ShippingAddress shippingAddress = shippingAddressRepository.findById(shippingAddressDto.getId()).orElseThrow(() -> new NoAddressesFoundException("Address not found"));
        if (shippingAddress != null){
            shippingAddressFactory.mapShippingAddress(shippingAddressDto, shippingAddress);
            shippingAddressRepository.save(shippingAddress);
            return true;
        }else {
            return false;
        }
    }

    public ShippingAddressDTO getCompanyShippingAddressById(Long shippingAddress) {
        return shippingAddressRepository.findById(shippingAddress)
                .map(ShippingAddressMapper::map)
                .orElseThrow(() -> new NoAddressesFoundException("Address not found"));
    }

    public Set<ShippingAddress> convertShippingAddresses(Set<ShippingAddressDTO> shippingAddressDTOs) {
        Set<ShippingAddress> shippingAddresses = new HashSet<>();
        if (shippingAddressDTOs != null) {
            for (ShippingAddressDTO dto : shippingAddressDTOs) {
                ShippingAddress shippingAddress = new ShippingAddress();
                shippingAddress.setStreetName(dto.getStreetName());
                shippingAddress.setBuildingNumber(dto.getBuildingNumber());
                shippingAddress.setApartmentNumber(dto.getApartmentNumber());
                shippingAddress.setCity(dto.getCity());
                shippingAddress.setPostalCode(dto.getPostalCode());
                shippingAddresses.add(shippingAddress);
            }
        }
        return shippingAddresses;
    }
}
