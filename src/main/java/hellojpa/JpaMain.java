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

//            Member member = em.find(Member.class, 150L);
//            member.setName("ZZZZZ");
//            member.setName("AAAAA");
            // 영속성 컨테스트에서 끄집어 냄(detach) -> 준영속상태
//            em.detach(member);
            // 영속성 컨테스트를 다 지운다.(초기화)
//            em.clear();
//            Member member2 = em.find(Member.class, 150L);

//            Member member = new Member(200L, "member200");
//            em.persist(member);
//            em.flush();

//            System.out.println("==============================================================");

//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L);

            // 조회, 1차 캐쉬에서 바로 가져오기 때문에 sql문이 나가지 않음
//            System.out.println("findMember1.id = " + findMember.getId());
//            System.out.println("findMember1.name = " + findMember.getName());

            // 영속 엔티티의 동일성을 보장
//            System.out.println("result = " + (findMember1 == findMember2));

            // 필드와 컬럼 매핑
//            Member member = new Member();
//            member.setId(3L);
//            member.setUsername("C");
//            member.setRoleType(RoleType.GUEST);
//
//            em.persist(member);

            // 기본 키 매핑
//            Member member = new Member();
//            member.setUsername("C");
//
//            System.out.println("==========================");
//            // Identity전략에서는 persistence 시점에서 쿼리문을 날린다.
//            em.persist(member);
//            System.out.println("member.id = " + member.getId());
//            System.out.println("==========================");

            // 딘방향 연관관계
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setTeamId(team.getId());
//            em.persist(member);
//
//            Member findMember = em.find(Member.class, member.getId());
//
//            Long findTeamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);

            // 저장
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setTeam(team);
//            em.persist(member);
//            em.flush();
//            em.clear();
//
//            Member findMember = em.find(Member.class, member.getId());
//
//            Team findTeam = findMember.getTeam();
//            System.out.println("findTeam = " + findTeam.getName());
//
//            Team newTeam = em.find(Team.class, 100L);
//            findMember.setTeam(newTeam);

            // 양방향 연관관계와 연관관계의 주인 1 - 기본
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setTeam(team);
//            em.persist(member);
//            em.flush();
//            em.clear();
//
//            Member findMember = em.find(Member.class, member.getId());
//
//            List<Member> members = findMember.getTeam().getMembers();
//
//            for (Member m : members) {
//                System.out.println("m = " + m.getUsername());
//            }

            // 양방향 연관관계와 연관관계의 주인 2 - 주의점, 정리
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
//            member.changeTeam(team);
            em.persist(member);

//            team.getMembers().add(member);    // <- set시점에서 이미 세팅해두면 사용할 필요가 없다.(연관관계 편의 메서드)
            team.addMember(member);

            em.flush();
            em.clear();

            team.addMember(member);

            Team findTeam = em.find(Team.class, team.getId());  // 1차 캐시(영속성 컨텍스트) <- 플러쉬, 클리어를 하지 않았을 경우 디비에 저장되어있지 않기 때문에
            List<Member> members = findTeam.getMembers();

            System.out.println("==================================");
//            for (Member m : members) {
//                System.out.println("m = " + m.getUsername());
//            }
            System.out.println("members = " + findTeam); // <- 스택오버플로우(무한루프)
            System.out.println("==================================");

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
