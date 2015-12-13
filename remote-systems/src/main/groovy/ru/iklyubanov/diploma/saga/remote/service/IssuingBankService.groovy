package ru.iklyubanov.diploma.saga.remote.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.iklyubanov.diploma.saga.core.spring.Bank
import ru.iklyubanov.diploma.saga.core.spring.BankCard
import ru.iklyubanov.diploma.saga.core.spring.Currency
import ru.iklyubanov.diploma.saga.core.spring.util.CardStatus
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
    BankCard checkBankCard(Bank bank, String code, String firstName, String lastName, BigDecimal amount,
            String currencyType, String expiredDate, String ccvCode) throws NullPointerException,
            WrongEntityStateException, IllegalArgumentException{
        BankCard card = cardRepository.findBankCard(bank, code)
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
    private Currency findCurrency(String type) {
        currencyRepository.findCurrency(type)
    }

}
