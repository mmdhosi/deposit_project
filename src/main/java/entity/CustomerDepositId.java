package entity;//package entity;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//public class CustomerDepositId implements Serializable {
//    private Integer customerId;
//    private Integer depositId;
//    private Integer addressId;
//
//    // Default constructor
//    public CustomerDepositId() {}
//
//    // Constructor
//    public CustomerDepositId(Integer customerId, Integer depositId, Integer addressId) {
//        this.customerId = customerId;
//        this.depositId = depositId;
//        this.addressId = addressId;
//    }
//
//    // Getters and Setters
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof CustomerDepositId)) return false;
//        CustomerDepositId that = (CustomerDepositId) o;
//        return Objects.equals(customerId, that.customerId) &&
//                Objects.equals(depositId, that.depositId) &&
//                Objects.equals(addressId, that.addressId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(customerId, depositId, addressId);
//    }
//}