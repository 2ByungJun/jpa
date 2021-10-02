package jpa.findUsing;

import jpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class paging {
    public static void main(String[] args) {
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            // 페이징 ( 만약 SQL 이 달라져도 객체 기반이기에 지원하는 쿼리에 맞게 컨버트 된다. )
            // 중요. 여기서 from Member 는 테이블 기준이 아닌 VO 기준으로 가져온다.
            List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1) // 1부터
                    .setMaxResults(10) // 10까지
                    .getResultList();
            for (Member member : resultList) {
                System.out.println("member.getName() = " + member.getName());
            }

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
