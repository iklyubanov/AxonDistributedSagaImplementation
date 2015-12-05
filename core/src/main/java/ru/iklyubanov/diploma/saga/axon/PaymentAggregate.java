package ru.iklyubanov.diploma.saga.axon;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import ru.iklyubanov.diploma.saga.axon.command.CreatePaymentCommand;
import ru.iklyubanov.diploma.saga.axon.event.CreatePaymentEvent;
import ru.iklyubanov.diploma.saga.axon.util.TransactionId;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by kliubanov on 27.11.2015.
 */
public class PaymentAggregate extends AbstractAnnotatedAggregateRoot {
    private static final long serialVersionUID = 1299083385130634014L;

    @AggregateIdentifier
    private TransactionId transactionId;

    /**
     * Тип карты (PaymentSystemType)
     */
    private String paymentSystemType;
    /**
     * К оплате
     */
    private BigDecimal amount;
    /**
     * Валюта
     */
    private String currencyType;
    /**Код карты*/
    private String code;
    /**
     * Имя держателя карты
     */
    private String firstName;
    /**
     * Фамилия держателя карты
     */
    private String lastName;
    /**Дата окончания срока действия карты*/
    private String expiredDate;
    /** Код безопасности (CVC2, CVV2, CID, CSC)*/
    private String ccvCode;
    /** Тип платежа*/
    private String paymentType;
    /** БИК банка получателя*/
    private String merchantBankBIK;
    /** Номер счета в банке-получателе*/
    private String merchantBankAccount;
    /** Получатель (не > 160 симв)*/
    private String merchant;
    /** ИНН (не > 160 симв)*/
    private String merchantINN;

    @SuppressWarnings("UnusedDeclaration")
    protected PaymentAggregate() {
    }


    private PaymentAggregate(TransactionId transactionId) {
        this.transactionId = transactionId;
    }

    public static class Builder {

        private PaymentAggregate paymentAggregate;

        public Builder(TransactionId transactionId) {
            this.paymentAggregate = new PaymentAggregate(transactionId);
        }

        public PaymentAggregate build() {
            //создаем event
            CreatePaymentEvent createPaymentEvent = new CreatePaymentEvent.Builder(paymentAggregate.getTransactionId())
                    .addPaymentSystemType(paymentAggregate.getPaymentSystemType()).addAmount(paymentAggregate.getAmount())
                    .addCurrencyType(paymentAggregate.getCurrencyType()).addCode(paymentAggregate.getCode())
                    .addFirstName(paymentAggregate.getFirstName())
                    .addLastName(paymentAggregate.getLastName()).addExpiredDate(paymentAggregate.getExpiredDate()).addCcvCode(paymentAggregate.getCcvCode())
                    .addPaymentType(paymentAggregate.getPaymentType()).addMerchantBankBIK(paymentAggregate.getMerchantBankBIK())
                    .addMerchantBankAccount(paymentAggregate.getMerchantBankAccount()).addMerchant(paymentAggregate.getMerchant())
                    .addMerchantINN(paymentAggregate.getMerchantINN()).build();
            //добавляем event в очередь незакоммиченных event-ов
            paymentAggregate.apply(createPaymentEvent);
            return paymentAggregate;
        }

        public Builder addPaymentSystemType(String paymentSystemType) {
            paymentAggregate.paymentSystemType = paymentSystemType;
            return this;
        }

        public Builder addAmount(BigDecimal amount) {
            paymentAggregate.amount = amount;
            return this;
        }

        public Builder addCurrencyType(String currencyType) {
            paymentAggregate.currencyType = currencyType;
            return this;
        }

        public Builder addCode(String code) {
            paymentAggregate.code = code;
            return this;
        }

        public Builder addFirstName(String firstName) {
            paymentAggregate.firstName = firstName;
            return this;
        }

        public Builder addLastName(String lastName) {
            paymentAggregate.lastName = lastName;
            return this;
        }

        public Builder addExpiredDate(String expiredDate) {
            paymentAggregate.expiredDate = expiredDate;
            return this;
        }

        public Builder addCcvCode(String ccvCode) {
            paymentAggregate.ccvCode = ccvCode;
            return this;
        }

        public Builder addPaymentType(String paymentType) {
            paymentAggregate.paymentType = paymentType;
            return this;
        }

        public Builder addMerchantBankBIK(String merchantBankBIK) {
            paymentAggregate.merchantBankBIK = merchantBankBIK;
            return this;
        }

        public Builder addMerchantBankAccount(String merchantBankAccount) {
            paymentAggregate.merchantBankAccount = merchantBankAccount;
            return this;
        }

        public Builder addMerchant(String merchant) {
            paymentAggregate.merchant = merchant;
            return this;
        }

        public Builder addMerchantINN(String merchantINN) {
            paymentAggregate.merchantINN = merchantINN;
            return this;
        }
    }

    @EventHandler/*TODo fix it*/
    public void on(CreatePaymentEvent event) {
        //this.transactionId = event.getPaymentId();
    }

    public TransactionId getTransactionId() {
        return transactionId;
    }

    public String getPaymentSystemType() {
        return paymentSystemType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public String getCode() {
        return code;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public String getCcvCode() {
        return ccvCode;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getMerchantBankBIK() {
        return merchantBankBIK;
    }

    public String getMerchantBankAccount() {
        return merchantBankAccount;
    }

    public String getMerchant() {
        return merchant;
    }

    public String getMerchantINN() {
        return merchantINN;
    }
}
