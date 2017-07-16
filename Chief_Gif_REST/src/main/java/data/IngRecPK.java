package data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by sitora on 15.07.17.
 */
public class IngRecPK implements Serializable {
    private BigInteger ingId;
    private BigInteger recId;

    @Column(name = "ing_id", nullable = false, precision = 0)
    @Id
    public BigInteger getIngId() {
        return ingId;
    }

    public void setIngId(BigInteger ingId) {
        this.ingId = ingId;
    }

    @Column(name = "rec_id", nullable = false, precision = 0)
    @Id
    public BigInteger getRecId() {
        return recId;
    }

    public void setRecId(BigInteger recId) {
        this.recId = recId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IngRecPK ingRecPK = (IngRecPK) o;

        if (ingId != null ? !ingId.equals(ingRecPK.ingId) : ingRecPK.ingId != null) return false;
        if (recId != null ? !recId.equals(ingRecPK.recId) : ingRecPK.recId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ingId != null ? ingId.hashCode() : 0;
        result = 31 * result + (recId != null ? recId.hashCode() : 0);
        return result;
    }
}
