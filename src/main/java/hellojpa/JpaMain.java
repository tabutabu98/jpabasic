package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 객체지향 쿼리 언어1 - 기본 문법 소개

//            // JPQL
//            List<Member> result = em.createQuery(
//                    "select m from Member m where m.username like '%kim%'",
//                    Member.class
//            ).getResultList();
//
//            for (Member member : result) {
//                System.out.println("member = " + member);
//            }

            // Criteria
//            // Criteria 사용 준비
//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Member> query = cb.createQuery(Member.class);
//
//            // 루트 클래스 (조회를 시작할 클래스)
//            Root<Member> m = query.from(Member.class);
//
//            // 쿼리 생성
//            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
//            List<Member> resultList = em.createQuery(cq).getResultList();

            // Native SQL
//            Member member = new Member();
//            member.setUsername("member1");
//            em.persist(member);
//
//            // flush -> commit, query
//
//            // commit 시점에서도 쿼리가 나가지만 엔티티매니저의 쿼리 메서드에서도 flush가 된다.
//            List<Member> resultList = em.createNativeQuery("select MEMBER_ID, CITY, STREET, ZIPCODE, USERNAME from MEMBER", Member.class)
//                    .getResultList();
//
//            for (Member member1 : resultList) {
//                System.out.println("member1 = " + member1);
//            }

            // JDBC 직접 사용, SpringJdbcTemplate
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            // flush -> commit, query

            // flush를 해주는 이유는 JDBC나 SpringJdbcTemplate같은 경우 JPA와 관련이 없기 때문에 영속성 컨테스트에 있는 데이터를 디비에 뿌려주는 기능이 없기 때문
            em.flush();

            // 결과 0
            // dbconn.executeQuery("select * from member");

            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

}
