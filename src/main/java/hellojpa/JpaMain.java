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

//            Member findMember1 = em.find(Member.class, 1L);
//            System.out.println("findMember1.id = " + findMember1.getId());
//            System.out.println("findMember1.name = " + findMember1.getName());
//            findMember1.setName("송예찬");

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
//            Member member = new Member();
//            member.setId(100L);
//            member.setName("HelloJPA");
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA_2");

            // 영속 상태, 지금 이 시점에서는 디비에 저장하지 않음
//            System.out.println("===BEFORE===");
//            em.persist(member);
//            System.out.println("===AFTER===");

//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//            em.persist(member1);
//            em.persist(member2);

            Member member = em.find(Member.class, 150L);
//            member.setName("ZZZZZ");
            member.setName("AAAAA");
            // 영속성 컨테스트에서 끄집어 냄(detach) -> 준영속상태
//            em.detach(member);
            // 영속성 컨테스트를 다 지운다.(초기화)
            em.clear();
//            Member member2 = em.find(Member.class, 150L);

//            Member member = new Member(200L, "member200");
//            em.persist(member);
//            em.flush();

            System.out.println("==============================================================");

//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L);

            // 조회, 1차 캐쉬에서 바로 가져오기 때문에 sql문이 나가지 않음
//            System.out.println("findMember1.id = " + findMember.getId());
//            System.out.println("findMember1.name = " + findMember.getName());

            // 영속 엔티티의 동일성을 보장
//            System.out.println("result = " + (findMember1 == findMember2));



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
