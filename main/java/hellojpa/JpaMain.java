package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// 정석코드이며, 기본적으로 Spring 에서 다 해주기 때문에 JPA 사이클을 보여주는 형식의 코드이다.
public class JpaMain {
    public static void main(String[] args) {
        // persistence.xml 의  persistence-unit-name 을 작성
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");

        // 작업을 진행할 때 꼭 만들어주어야함
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 트랜잭션 시작

        // member 를 넣는 행위   => 그냥 실행하면 error 발생, 트랜잭션을 설정해주는 것이 중요함!
        try {
            // 삽입
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            em.persist(member);

            // 조회
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());

            // 삭제
            Member removeMember = em.find(Member. class, 1L);
            em.remove(removeMember);

            // 수정
            Member setMember = em.find(Member.class, 1L); // JPA 로 가져오면 관리대상이 된다.
            setMember.setName("HelloJPA"); // 관리하는 것에서 매핑되어 다음과 같은 코드로도 즉각 업데이트 된다.

            tx.commit(); // 트랜잭션 정상시 커밋
        }catch (Exception e){
            tx.rollback(); // 트랜잭션 문제 발생시 롤백
        }finally {
            em.close(); // 엔티티 매니저는 꼭 닫아주어야함.
        }

        emf.close();
    }
}
