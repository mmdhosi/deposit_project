package DTO;


import entity.Address;

public class AddressMapper {
    // Convert Address to AddressDTO
    public static AddressDTO toDTO(Address address) {
        if (address == null) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressId(address.getAddressId());
        addressDTO.setCity(address.getCity());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setPostalCode(address.getPostalCode());
        addressDTO.setCustomerId(address.getCustomerId());
        addressDTO.setVersion(address.getVersion());
        addressDTO.setExpired(address.getExpired());
        addressDTO.setActive(address.getActive());

        return addressDTO;
    }

    // Convert AddressDTO to Address
    public static Address toEntity(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        }

        Address address = new Address();
        address.setAddressId(addressDTO.getAddressId());
        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setCustomerId(addressDTO.getCustomerId());
        address.setVersion(addressDTO.getVersion());
        address.setExpired(addressDTO.getExpired());
        address.setActive(addressDTO.getActive());

        return address;
    }
}
