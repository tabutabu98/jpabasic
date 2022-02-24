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
            // 상속관계 매핑
            Movie movie = new Movie();
            movie.setDirector("A");
            movie.setActor("a");
            movie.setName("바람과 함께 사라지다");
            movie.setPrice(10000);

            em.persist(movie);

            // 영속성 컨텍스트에 있는 정보를 sql 날리기
            em.flush();
            em.clear();

            Item item = em.find(Item.class, movie.getId());
            System.out.println("findMovie = " + item);

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
