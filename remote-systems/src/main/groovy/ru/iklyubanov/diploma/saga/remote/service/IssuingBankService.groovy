package ru.iklyubanov.diploma.saga.remote.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.iklyubanov.diploma.saga.core.spring.Bank
import ru.iklyubanov.diploma.saga.core.spring.BankCard
import ru.iklyubanov.diploma.saga.core.spring.Currency
import ru.iklyubanov.diploma.saga.core.spring.Merchant
import ru.iklyubanov.diploma.saga.core.spring.Payment
import ru.iklyubanov.diploma.saga.core.spring.util.CardStatus
import ru.iklyubanov.diploma.saga.core.spring.util.MonetaryValue
import ru.iklyubanov.diploma.saga.core.spring.util.PaymentState
import ru.iklyubanov.diploma.saga.core.spring.util.WrongEntityStateException
import ru.iklyubanov.diploma.saga.remote.repository.BankCardRepository
import ru.iklyubanov.diploma.saga.remote.repository.CurrencyRepository
import ru.iklyubanov.diploma.saga.remote.repository.IssuingBankRepository

/**
 * Created by ivan on 12/13/2015.
 */
@Service
@Transactional
class IssuingBankService {

    @Autowired
    IssuingBankRepository bankRepository
    @Autowired
    BankCardRepository cardRepository
    @Autowired
    CurrencyRepository currencyRepository

    @Transactional(readOnly = true)
    Bank findBank(String bik) throws NullPointerException{
        Bank bank = bankRepository.findBankByBik(bik)
        if(!bank) {
            throw new NullPointerException("Банк с БИКом $bik не найден.")
        }
        bank
    }

    @Transactional(readOnly = true)
    BankCard findBankCard(Long id) {
        BankCard card = cardRepository.findOne(id)
        if(!card) {
            throw new NullPointerException("Карта с кодом $code не найдена.")
        }
        card
    }

    /**метод снятия средств с клиента*/
    def withdrawMoneyFromCard(BankCard card, Payment payment) {
        payment.paymentState = PaymentState.PROCESSED
        MonetaryValue payAmount = payment.paymentAmount
        MonetaryValue cardAmount = card.wallet.monetaryValue
        //если платеж в той же валюте - просто снимаем с карты средства
        if(cardAmount.currencyType.equals(payAmount.currencyType)) {
            cardAmount.amount -= payAmount.amount
        } else {//если нет то сначала конвертируем в валюту карты
            def payCurrency = findCurrency(payAmount.currencyType)
            def convertedAmount = payCurrency.conversionFactor * payAmount.amount / card.wallet.currency.conversionFactor
            cardAmount.amount -= convertedAmount
        }
        payment.paymentState = PaymentState.WITHDRAW
        payment
    }

    @Transactional(readOnly = true)
    BankCard checkBankCard(Bank bank, String code, String firstName, String lastName, BigDecimal amount,
            String currencyType, String expiredDate, String ccvCode) throws NullPointerException,
            WrongEntityStateException, IllegalArgumentException{
        BankCard card = cardRepository.findBankCardByAccount(bank, code)
        if(!card) {
            throw new NullPointerException("Карта с кодом $code не найдена.")
        }
        if(card.cardStatus != CardStatus.ACTIVE) {
            throw new WrongEntityStateException("Карта с кодом $code не активна.")
        }
        def client = card.client
        if(!client) {
            throw new NullPointerException("К карте с кодом $code не привязан клиент $firstName $lastName.")
        }
        if(!firstName.equalsIgnoreCase(client.firstName) || !lastName.equalsIgnoreCase(client.lastName)) {
            throw new IllegalArgumentException("Указаны не верные инициалы клиента!")
        }
        if(!ccvCode.equalsIgnoreCase(card.ccvCode)) {
            throw new IllegalArgumentException("Указан не верный CCV код.")
        }
        if(!expiredDate.equalsIgnoreCase(card.expiredDate)) {
            throw new IllegalArgumentException("Указана не верная дата окончания срока действия карты.")
        }
        def wallet = card.wallet
        if(!wallet) {
            throw new NullPointerException("К карте с кодом $code не привязан кошилек.")
        }
        if(wallet.currency && (currencyType.equalsIgnoreCase(wallet.currency.isoName)
                || currencyType.equalsIgnoreCase(wallet.currency.title))) {
            if(wallet.monetaryValue.amount.compareTo(amount) < 0) {
                throw new WrongEntityStateException("На карте $code не достаточно средств.")
            }
        } else if(wallet.currency) {
            def reqCurrency = findCurrency(currencyType)
            if(!reqCurrency) {
                throw new WrongEntityStateException("Валюта $currencyType не найдена.")
            }
            def reqAmount = reqCurrency.conversionFactor * amount
            def walletAmount = wallet.currency.conversionFactor * wallet.monetaryValue.amount
            if(walletAmount < reqAmount) {
                throw new WrongEntityStateException("На карте $code не достаточно средств.")
            }
        }
        card
    }

    @Transactional(readOnly = true)
    BankCard checkMerchantBankCard(Bank bank, String account, String merchantName, String inn) throws NullPointerException,
            WrongEntityStateException, IllegalArgumentException{
        if(inn && !inn.equalsIgnoreCase(bank.inn)) {
            throw new IllegalArgumentException("Указаны не верные инн банка получателя!")
        }
        BankCard card = cardRepository.findBankCardByAccount(bank, account)
        if(!card) {
            throw new NullPointerException("Карта с номером счета $account не найдена.")
        }
        if(card.cardStatus != CardStatus.ACTIVE) {
            throw new WrongEntityStateException("Карта с номером счета $account не активна.")
        }
        def client = card.client
        if(!client) {
            throw new NullPointerException("К карте с номером счета $account не привязан клиент $firstName $lastName.")
        }
        if(!client instanceof Merchant) {
            throw new WrongEntityStateException("Привязанный к счету клиент не является юр. лицом.")
        }
        Merchant merchant = (Merchant) client
        if(!merchantName.equalsIgnoreCase(merchant.fullName)) {
            throw new IllegalArgumentException("Указаны не верные инициалы юридического лица!")
        }
        def wallet = card.wallet
        if(!wallet) {
            throw new NullPointerException("К карте с кодом $code не привязан кошилек.")
        }

        card
    }

    @Transactional(readOnly = true)
    private Currency findCurrency(String type) {
        currencyRepository.findCurrency(type)
    }

}
