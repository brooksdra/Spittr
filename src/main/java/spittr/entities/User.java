package spittr.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Entity implementation class for Entity: Users
 *
 */
@Entity(name = "users")
@NamedQueries ({
    @NamedQuery(name = "User.findByUsername",    query = "SELECT u FROM users u WHERE LOWER(u.username) = LOWER(:username)"),
})
public class User implements Serializable {

    private static final long serialVersionUID = 3421729545917220170L;

	// Required for Spring Security
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "username", nullable = false, length = 60, unique = true)
	private String username;

	@Column(name = "password", nullable = false, length = 255)
	private String password;

	// @Column(columnDefinition="TINYINT NOT NULL DEFAULT 1")
	@Column(name = "enabled", nullable = false)
	private Boolean enabled;

//	@OneToMany(targetEntity = UserRole.class, cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
//	private List<UserRole> userRoles;


	@Column(name = "first_name", length = 50)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	public User() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getEnabled() {
        return enabled;
    }

    @Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}