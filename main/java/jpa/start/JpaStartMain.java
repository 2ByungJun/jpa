package jpa.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaStartMain {
    public static void main(String[] args) {
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello"); // 1. 엔티티 매니저 팩토리.  하나만 생성해서 애플리케이션 전체에서 공유
        EntityManager em = emf.createEntityManager(); // 2. 엔티티 매니저. 쓰레드 간에 공유 X ( 사용하고 버려야 한다. )
        EntityTransaction tx = em.getTransaction(); //  3. 트랜잭션. JPA 의 모든 데이터 변경은 트랜잭션 안에서 실행되어야 한다.
        tx.begin(); // 3-1. 트랜잭션 시작
        try {
            // code ...
            tx.commit();   // 트랜잭션 정상시 커밋(반영)
        }catch (Exception e){
            tx.rollback(); // 트랜잭션 문제 발생시 롤백
        }finally {
            em.close();    // 엔티티 매니저는 꼭 닫아주어야함.
        }
        emf.close();
    }
}
