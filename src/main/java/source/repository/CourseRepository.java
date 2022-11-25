package source.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    Page<Course> findAllByNameContainingIgnoreCase(String name, PageRequest pageRequest);
}
