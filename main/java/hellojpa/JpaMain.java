package hellojpa;

import jpa.start.Member;

import javax.persistence.*;
import java.util.List;

// 정석코드이며, 기본적으로 Spring 에서 다 해주기 때문에 JPA 사이클을 보여주는 형식의 코드이다.
public class JpaMain {
    public static void main(String[] args) {
        /*
         * 1. 엔티티 매니저 팩토리
         * 하나만 생성해서 애플리케이션 전체에서 공유
         */
        // persistence.xml 의  persistence-unit-name 을 작성
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        /*
         * 2. 엔티티 매니저
         * 쓰레드 간에 공유 X ( 사용하고 버려야 한다. )
         */
        // 작업을 진행할 때 꼭 만들어주어야함

        /*
         * 3. 트랜잭션
         * JPA 의 모든 데이터 변경은 트랜잭션 안에서 실행되어야 한다.
         */
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 트랜잭션 시작

        // try-catch-finally 가 트랜잭션 사용의 정석이다. why? 에러나면 rollback 이 필요하기 때문.
        try {
            // 1. 삽입
            // member 를 넣는 행위   => 그냥 실행하면 error 발생, 트랜잭션을 설정해주는 것이 중요함!
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            em.persist(member);

            // 2. 조회
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());

            // 2-1. 조회 반복 - JPQL
            List<Member> resultList = em.createQuery("select m from Member as m", Member.class) // 중요. 여기서 from Member 는 테이블 기준이 아닌 VO 기준으로 가져온다.
                    .getResultList();
            for (Member vo : resultList) {
                System.out.println("member.getName() = " + vo.getName());
            }

            // 2-2. 조회 - 페이징 ( 만약 SQL 이 달라져도 객체 기반이기에 지원하는 쿼리에 맞게 컨버트 된다. ) God JPA...
            List<Member> resultList2 = em.createQuery("select m from Member as m", Member.class) // 중요. 여기서 from Member 는 테이블 기준이 아닌 VO 기준으로 가져온다.
                    .setFirstResult(1) // 1부터
                    .setMaxResults(10) // 10까지
                    .getResultList();

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
