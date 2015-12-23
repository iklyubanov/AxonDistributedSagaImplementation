package ru.iklyubanov.diploma.saga.core.spring.entity;

import ru.iklyubanov.diploma.saga.core.spring.util.ParentEntity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by kliubanov on 23.11.2015.
 */
@Entity
@Table(name = "currency")
public class Currency extends ParentEntity {

    /**
     * The currency's 3 letter
     *
     */
    @Column(name = "iso_name", length = 3, nullable = false)
    private String isoName;
    /**
     * The currency's name.
     */
    @Column(name = "title", length = 50, nullable = false)
    private String title;
    /**
     * Exchange rate in relation to the US dollar.
     */
    @Column(name = "conversion_factor", nullable = false)
    private BigDecimal conversionFactor;

    /**
     * The currency's 3 letter
     * @return the isoName
     */
    public String getIsoName() {
        return isoName;
    }

    /**
     * The currency's 3 letter
     * @param isoName the isoName to set
     */
    public void setIsoName(String isoName) {
        this.isoName = isoName;
    }

    /**
     * The currency's name.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * The currency's name.
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Exchange rate in relation to the US dollar.
     * If less when dollar then < 1, (руб=1/68=0,014, например, 100 руб * 0,014 = 1,4 USD)
     * If more when dollar then > 1
     * @return the conversionFactor
     */
    public BigDecimal getConversionFactor() {
        return conversionFactor;
    }

    /**
     * Exchange rate in relation to the US dollar.
     * @param conversionFactor the conversionFactor to set
     */
    public void setConversionFactor(BigDecimal conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Currency)) {
            return false;
        }
        Currency that = (Currency) obj;
        return this.getId().equals(that.getId());
    }
}