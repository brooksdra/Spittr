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
@Entity(name = "authorities")
@NamedQueries ({
    @NamedQuery(name = "Authority.findByUsername",    query = "SELECT a FROM authorities a WHERE LOWER(a.username) = LOWER(:username)"),
})
public class Authorities implements Serializable {

    private static final long serialVersionUID = 3421729545917220170L;

	// Required for Spring Security
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "authority_id")
	private int authorityId;

	@Column(name = "username", nullable = false, length = 60, unique = true)
	private String username;

	@Column(name = "authority", nullable = false, length = 45)
	private String authority;
	public Authorities() {
		super();
	}

	public int getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

    @Override
	public String toString() {
		return "Authority [authorityId=" + authorityId + ", username=" + username + ", authority=" + authority + "]";
	}
	
	
}