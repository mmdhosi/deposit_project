package DTO;


import entity.Deposit;

public class DepositMapper {

    public static DepositDTO toDTO(Deposit deposit) {
        if (deposit == null) {
            return null;
        }

        return new DepositDTO(
                deposit.getDepositId(),
                deposit.getDepositNumber(),
                deposit.getBalance(),
                deposit.getAccountType(),
                deposit.getExpired(),
                deposit.getActive(),
                deposit.getEndDate(),
                deposit.getVersion()
        );
    }

    public static Deposit toEntity(DepositDTO depositDTO) {
        if (depositDTO == null) {
            return null;
        }

        return new Deposit(
                depositDTO.getDepositNumber(),
                depositDTO.getBalance(),
                depositDTO.getAccountType(),
                depositDTO.getExpired(),
                depositDTO.getActive(),
                depositDTO.getEndDate(),
                depositDTO.getVersion()
        );
    }
}