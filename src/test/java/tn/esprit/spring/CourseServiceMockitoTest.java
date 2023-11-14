package tn.esprit.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CourseServiceMockitoTest {

    @Mock //la classe Mocké
    ICourseRepository courseRepository;

    @InjectMocks //cest la classe qu'on va la tester
    CourseServicesImpl courseServices;

    Course course = new Course(2, TypeCourse.INDIVIDUAL, Support.SNOWBOARD,15.2f,2);
    List<Course> listCourses = new ArrayList<Course>() {
        {
            add(new Course(7, TypeCourse.INDIVIDUAL, Support.SNOWBOARD,20.2f,2));

            add(new Course(5, TypeCourse.INDIVIDUAL, Support.SNOWBOARD,156.2f,2));
        }
    };
    @Test
    public void testerRetriveCourses(){
        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(course));
        Course course1 =courseServices.retrieveCourse( 1L);
        Assertions.assertNotNull(course1);
    }
}
