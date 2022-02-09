package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member member1 = new Member();
//            member1.setId(1L);
//            member1.setName("syc");
//            em.persist(member1);
//            Member member2 = new Member();
//            member2.setId(2L);
//            member2.setName("홍길동");
//            em.persist(member2);

//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());
//            findMember.setName("송예찬");

            // Member 객체가 대상
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.name = " + member.getName());
//            }

            // 비영속 상태
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            // 영속 상태, 지금 이 시점에서는 디비에 저장하지 않음
            System.out.println("===BEFORE===");
            em.persist(member);
            System.out.println("===AFTER===");

            // 트랜젝션의 커밋에서 sql문을 던진다.
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
