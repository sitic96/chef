package data;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by sitora on 15.07.17.
 */
@Entity
@Table(name = "`User`")
public class User {
    private BigInteger user_id;
    private String user_name;
    private String profilePicture;
    private String password;

    public User(BigInteger id, String name, String profilePicture) {
        this.user_id = id;
        this.user_name = name;
        this.profilePicture = profilePicture;
    }

    public User() {
    }

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column(name = "user_id", nullable = false, precision = 0)
    public BigInteger getUser_id() {
        return user_id;
    }

    public void setUser_id(BigInteger id) {
        this.user_id = id;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = -1, unique = true)
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String name) {
        this.user_name = name;
    }

    @Basic
    @Column(name = "profile_picture", nullable = true, length = -1)
    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Basic
    @Column(name = "password", nullable = false, length = -1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (user_id != null ? !user_id.equals(user.user_id) : user.user_id != null) return false;
        if (user_name != null ? !user_name.equals(user.user_name) : user.user_name != null) return false;
        if (profilePicture != null ? !profilePicture.equals(user.profilePicture) : user.profilePicture != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user_id != null ? user_id.hashCode() : 0;
        result = 31 * result + (user_name != null ? user_name.hashCode() : 0);
        result = 31 * result + (profilePicture != null ? profilePicture.hashCode() : 0);
        return result;
    }
}
