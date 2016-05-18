package in.gagan.hibernate.dto;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="USER_DETAILS")
public class UserDetails {
	
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="foreign", parameters={@Parameter(value="userLogin", name="property")})
	@Column(name="USER_ID")
	private long userId;
	@Column(name="USER_NAME")
	private String userName;
	@Column(name="FIRST_NAME")
	private String firstName;
	@Column(name="LAST_NAME")
	private String lastName;
	@Column(name="DATE_OF_BIRTH")
	private Date dob;
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private UserLogin userLogin;
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="flatNo",column=@Column(name="PERMANENT_FLAT_NO")),
		@AttributeOverride(name="streetName",column=@Column(name="PERMANENT_STREET_NAME")),
		@AttributeOverride(name="city",column=@Column(name="PERMANENT_CITY")),
		@AttributeOverride(name="State",column=@Column(name="PERMANENT_STATE")),
		@AttributeOverride(name="pincode",column=@Column(name="PERMANENT_PINCODE")),
		@AttributeOverride(name="Country",column=@Column(name="PERMANENT_COUNTRY"))
	})
	private Address permanentAddress;	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="flatNo",column=@Column(name="TEMPORARY_FLAT_NO")),
		@AttributeOverride(name="streetName",column=@Column(name="TEMPORARY_STREET_NAME")),
		@AttributeOverride(name="city",column=@Column(name="TEMPORARY_CITY")),
		@AttributeOverride(name="State",column=@Column(name="TEMPORARY_STATE")),
		@AttributeOverride(name="pincode",column=@Column(name="TEMPORARY_PINCODE")),
		@AttributeOverride(name="Country",column=@Column(name="TEMPORARY_COUNTRY"))
	})
	private Address temporaryAddress;
	
	public UserLogin getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Address getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(Address permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public Address getTemporaryAddress() {
		return temporaryAddress;
	}
	public void setTemporaryAddress(Address temporaryAddress) {
		this.temporaryAddress = temporaryAddress;
	}
	

}
