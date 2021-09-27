package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
// @Table(name="USER") // USER 라는 테이블로 설정됨.
public class Member {

    @Id
    private Long id; // @Id 는 PK를 알려주는 것
    private String name;

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
}
