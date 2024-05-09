package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity(name = "Account")
@Table(name = "accounts")
public class Account {
	@Id
    @Column(name = "Username")
	@NotNull(message = "username is required")
	private String Username;
	
	@Column(name = "Password")
	@NotNull(message = "password is required")
	private String Password;
	
	@Column(name = "Fullname")
	@NotNull(message = "fullname is required")
	private String Fullname;
	
	@Column(name = "Age")
	@NotNull(message = "age is required")
	private int age;
	
	@Column(name = "Selected")
	private boolean isSelected;

	public Account() {
	}

	public Account(@NotNull(message = "username is required") String username,
			@NotNull(message = "password is required") String password,
			@NotNull(message = "fullname is required") String fullname, @NotNull(message = "age is required") int age,
			boolean isSelected) {
		super();
		Username = username;
		Password = password;
		Fullname = fullname;
		this.age = age;
		this.isSelected = isSelected;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getFullname() {
		return Fullname;
	}

	public void setFullname(String fullname) {
		Fullname = fullname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	
}
