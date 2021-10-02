# JPA 
---

본 레포지토리는 김영한의 스프링 부트와 JPA 실무 완전 정복 로드맵을 시청하며 작성되어지고 있습니다.

링크 : https://www.inflearn.com/roadmaps/149

## StartCode

```java
public class JpaStartMain {
    public static void main(String[] args) {
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello"); // 1. 엔티티 매니저 팩토리.  하나만 생성해서 애플리케이션 전체에서 공유
        EntityManager em = emf.createEntityManager(); // 2. 엔티티 매니저. 쓰레드 간에 공유 X ( 사용하고 버려야 한다. )
        EntityTransaction tx = em.getTransaction(); //  3. 트랜잭션. JPA 의 모든 데이터 변경은 트랜잭션 안에서 실행되어야 한다.
        tx.begin(); // 3-1. 트랜잭션 시작
        try {
            // CRUD.. Code.. etc...

            tx.commit();   // 트랜잭션 정상시 커밋(반영)
        }catch (Exception e){
            tx.rollback(); // 트랜잭션 문제 발생시 롤백
        }finally {
            em.close();    // 엔티티 매니저는 꼭 닫아주어야함.
        }
        emf.close();
    }
}
```

## CRUD
```java
...
        tx.begin();
try {
// INSERT
MemberVO insertMember = new MemberVO;
        insertMember.setId("1L");
        insertMember.setName("name");
        entityManager.persist(insertMember);
// READ
MemberVO readMember = entityManager.find((Mamber.class, 1L);
// DELETE
MemberVO deleteMember = entityManager.find((Mamber.class, 1L);
        entityManager.remove(deleteMember);
// UPDATE
MemberVO updateMember = entityManager.find((Mamber.class, 1L);
        updateMember.setName("changeName");

        tx.commit();
}catch (Exception e){
        tx.rollback();
}finally {
        entityManager.close();
}       
```