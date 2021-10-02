package jpa.findUsing;

import jpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class recycleList {
    public static void main(String[] args) {
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            // JPQL 사용
            // 중요. 여기서 from Member 는 테이블 기준이 아닌 VO 기준으로 가져온다.
            List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();
            for (Member vo : resultList) {
                System.out.println("member.getName() = " + vo.getName());
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
