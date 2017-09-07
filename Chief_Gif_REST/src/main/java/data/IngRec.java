package data;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by sitora on 15.07.17.
 */
@Entity
@Table(name = "Ing_Rec", schema = "public", catalog = "chef")
@IdClass(IngRecPK.class)
public class IngRec {
    private BigInteger id;
    private BigInteger ingId;
    private BigInteger recId;

    @Basic
    @Column(name = "id", nullable = true, precision = 0)
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Id
    @Column(name = "ing_id", nullable = false, precision = 0)
    public BigInteger getIngId() {
        return ingId;
    }

    public void setIngId(BigInteger ingId) {
        this.ingId = ingId;
    }

    @Id
    @Column(name = "rec_id", nullable = false, precision = 0)
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

        IngRec ingRec = (IngRec) o;

        if (id != null ? !id.equals(ingRec.id) : ingRec.id != null) return false;
        if (ingId != null ? !ingId.equals(ingRec.ingId) : ingRec.ingId != null) return false;
        if (recId != null ? !recId.equals(ingRec.recId) : ingRec.recId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ingId != null ? ingId.hashCode() : 0);
        result = 31 * result + (recId != null ? recId.hashCode() : 0);
        return result;
    }
}
