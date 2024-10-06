package entity;


import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address extends AuditableEntity{

    public Address() {
    }

    public Address(String city, String country, String postalCode, Integer customerId, Integer version, Boolean isExpired, Boolean isActive) {
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.customerId = customerId;
        this.version = version;
        this.isExpired = isExpired;
        this.isActive = isActive;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    private String city;

    private String country;

    private String postalCode;

    private Integer customerId;
    @Version
    private Integer version;

    private Boolean isExpired;

    private Boolean isActive;
    @Override
    public String toString() {
        return "Address{" +
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
        Address address = (Address) o;
        return Objects.equals(addressId, address.addressId) &&
                Objects.equals(city, address.city) &&
                Objects.equals(country, address.country) &&
                Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(customerId, address.customerId) &&
                Objects.equals(version, address.version) &&
                Objects.equals(isExpired, address.isExpired) &&
                Objects.equals(isActive, address.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, city, country, postalCode, customerId, version, isExpired, isActive);
    }

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
}