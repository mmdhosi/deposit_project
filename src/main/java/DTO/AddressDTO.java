package DTO;


import java.util.Objects;

public class AddressDTO {
    private Integer addressId;
    private String city;
    private String country;
    private String postalCode;
    private Integer customerId;
    private Integer version;
    private Boolean isExpired;
    private Boolean isActive;

    // Constructors
    public AddressDTO() {
    }

    public AddressDTO(Integer addressId, String city, String country, String postalCode, Integer customerId, Integer version, Boolean isExpired, Boolean isActive) {
        this.addressId = addressId;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.customerId = customerId;
        this.version = version;
        this.isExpired = isExpired;
        this.isActive = isActive;
    }

    // Getters and Setters
    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getExpired() {
        return isExpired;
    }

    public void setExpired(Boolean expired) {
        isExpired = expired;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "addressId=" + addressId +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", customerId=" + customerId +
                ", version=" + version +
                ", isExpired=" + isExpired +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDTO that = (AddressDTO) o;
        return Objects.equals(addressId, that.addressId) &&
                Objects.equals(city, that.city) &&
                Objects.equals(country, that.country) &&
                Objects.equals(postalCode, that.postalCode) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(version, that.version) &&
                Objects.equals(isExpired, that.isExpired) &&
                Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, city, country, postalCode, customerId, version, isExpired, isActive);
    }
}