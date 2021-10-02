package jpashop.entity;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) // (strategy = GenerationType.AUTO) 생략가능
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;
    private String city;
    private String  street;
    private String zipcode;

    // get. 가급적 생성
    // set. 어디서든지 수정하여 남발성이 높아 유지보수성 감소
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
